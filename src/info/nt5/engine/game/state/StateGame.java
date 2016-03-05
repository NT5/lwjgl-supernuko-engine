package info.nt5.engine.game.state;

import java.util.HashMap;

import info.nt5.engine.game.GameAbstract;
import info.nt5.engine.game.GameManager;
import info.nt5.engine.game.state.transition.BlankTransition;
import info.nt5.engine.game.state.transition.Transition;
import info.nt5.engine.util.Logger;

public abstract class StateGame extends GameAbstract {

	private HashMap<Integer, State> states = new HashMap<Integer, State>();

	private State currentState;
	private State nextState;
	
	private Transition leave;
	private Transition enter;
	
	private GameManager manager;

	public StateGame(GameManager manager, String title) {
		super(manager, title);
	}

	public abstract void initStatesList();

	@Override
	public void init(GameManager gm) {
		//currentState.init(gm, this);
		this.manager = gm;
		
		currentState = new State() {

			@Override
			public int getID() {
				return -1;
			}

			@Override
			public void init(GameManager gm, StateGame game) {
			}

			@Override
			public void enter(GameManager gm, StateGame game) {
			}

			@Override
			public void update(GameManager gm, StateGame game) {
			}

			@Override
			public void render(GameManager gm, StateGame game) {	
			}

			@Override
			public void leave(GameManager gm, StateGame game) {
			}
			
		};
		
		initStatesList();
		for (State state : states.values()){
		    state.init(manager, this);
		}
		/*for (int i = 0; i < states.size(); i++) {
			getState(i).init(manager, this);
		}*/
	}

	@Override
	public void enter(GameManager gm) {
		currentState.enter(gm, this);
	}

	@Override
	public void update(GameManager gm) {
		
		if ( leave != null ) {
			leave.update(gm, this);
			if ( leave.isComplete() ) {
				currentState.leave(gm, this);
				State previous = currentState;
				currentState = nextState;
				nextState = null;
				leave = null;
				
				if ( enter == null ) {
					currentState.enter(gm, this);
				}
				else {
					enter.init(currentState, previous);
				}
			}
			else {
				return;
			}
		}
		
		if ( enter != null ) {
			enter.update(gm, this);
			if (enter.isComplete()) {
				currentState.enter(gm, this);
				enter = null;
			}
			else {
				return;
			}
		}
		
		currentState.update(gm, this);
	}

	@Override
	public void render(GameManager gm) {
		if ( leave != null ) {
			leave.preRender(gm, this);
		}
		else if ( enter != null ) {
			enter.preRender(gm, this);
		}
		
		currentState.render(gm, this);
		
		if ( leave != null ) {
			leave.postRender(gm, this);
		}
		else if ( enter != null ) {
			enter.postRender(gm, this);
		}
	}

	@Override
	public void leave(GameManager gm) {
		currentState.leave(gm, this);
	}

	public State getState(int id) {
		return (State) states.get(new Integer(id));
	}

	public State getCurrentState() {
		return currentState;
	}

	public int getCurrentStateID() {
		return currentState.getID();
	}
	
	public boolean isTransitioning() {
		return ( leave != null ) || ( enter != null );
	}

	public void addState(State state) {
		states.put(state.getID(), state);

		if (state.getID() == 0) {
			currentState = state;
		}
	}

	public void enterState(int state) {
		/*leave(manager);
		currentState = getState(state);
		currentState.enter(manager, this);*/
		enterState( state, null, null );
	}
	
	public void enterState(int state, Transition leave, Transition enter) {
		if ( leave == null ) {
			leave = new BlankTransition();
		}
		if ( enter == null ) {
			enter = new BlankTransition();
		}
		this.leave = leave;
		this.enter = enter;
		
		nextState = getState(state);
		
		if ( nextState == null ) {
			Logger.error(String.format("The state with the ID %s is an invalid", state));
			throw new RuntimeException();
		}
		leave.init(currentState, nextState);
	}
	
	public Transition getLeaveTransition() {
		return leave;
	}
	
	public Transition getEnterTransition() {
		return enter;
	}
	
	public State getNextState() {
		return nextState;
	}

}
