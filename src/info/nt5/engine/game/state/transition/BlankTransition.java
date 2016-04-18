package info.nt5.engine.game.state.transition;

import info.nt5.engine.game.GameManager;
import info.nt5.engine.game.state.State;
import info.nt5.engine.game.state.StateManager;

public class BlankTransition implements Transition {

	@Override
	public void init(State current, State next) {
	}

	@Override
	public void preRender(GameManager gm, StateManager game) {
	}

	@Override
	public void postRender(GameManager gm, StateManager game) {
	}

	@Override
	public void update(GameManager gm, StateManager game) {
	}

	@Override
	public boolean isComplete() {
		return true;
	}

}
