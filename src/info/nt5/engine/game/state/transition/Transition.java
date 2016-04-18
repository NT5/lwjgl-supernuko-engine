package info.nt5.engine.game.state.transition;

import info.nt5.engine.game.GameManager;
import info.nt5.engine.game.state.State;
import info.nt5.engine.game.state.StateManager;

public interface Transition {

	public void init(State current, State next);

	public void preRender(GameManager gm, StateManager game);

	public void postRender(GameManager gm, StateManager game);

	public void update(GameManager gm, StateManager game);

	public boolean isComplete();

}
