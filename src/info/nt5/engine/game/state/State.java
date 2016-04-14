package info.nt5.engine.game.state;

import info.nt5.engine.game.GameManager;

public interface State {

	public int getID();

	public void init(GameManager gm, StateGame game);

	public void enter(GameManager gm, StateGame game);

	public void update(GameManager gm, StateGame game);

	public void render(GameManager gm, StateGame game);

	public void leave(GameManager gm, StateGame game);

}
