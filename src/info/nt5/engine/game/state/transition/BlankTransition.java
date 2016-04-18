package info.nt5.engine.game.state.transition;

import info.nt5.engine.game.GameManager;
import info.nt5.engine.game.state.State;
import info.nt5.engine.game.state.StateGame;

public class BlankTransition implements Transition {

	@Override
	public void init(State current, State next) {
	}

	@Override
	public void preRender(GameManager gm, StateGame game) {
	}

	@Override
	public void postRender(GameManager gm, StateGame game) {
	}

	@Override
	public void update(GameManager gm, StateGame game) {
	}

	@Override
	public boolean isComplete() {
		return true;
	}

}
