package info.nt5.engine.game.state.transition;

import info.nt5.engine.game.GameManager;
import info.nt5.engine.game.state.State;
import info.nt5.engine.game.state.StateGame;

public interface Transition {

	public void init(State current, State next);

	public void preRender(GameManager gm, StateGame game);

	public void postRender(GameManager gm, StateGame game);

	public void update(GameManager gm, StateGame game);

	public boolean isComplete();

}
