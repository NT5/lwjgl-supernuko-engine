package info.nt5.engine.game.state;

import info.nt5.engine.game.GameManager;

public interface State {

	public int getID();

	public void init(GameManager gm, StateManager game);

	public void enter(GameManager gm, StateManager game);

	public void update(GameManager gm, StateManager game);

	public void render(GameManager gm, StateManager game);

	public void leave(GameManager gm, StateManager game);

}
