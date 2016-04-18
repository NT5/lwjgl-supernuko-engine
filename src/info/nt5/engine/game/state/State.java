package info.nt5.engine.game.state;

import info.nt5.engine.game.GameManager;

public interface State {

	boolean isInit = false;
	boolean isEnter = false;

	public int getID();

	public void init(GameManager gm, StateGame game);

	public void enter(GameManager gm, StateGame game);

	public void update(GameManager gm, StateGame game);

	public void render(GameManager gm, StateGame game);

	public void leave(GameManager gm, StateGame game);

	public default boolean isInit() {
		return isInit;
	}

	public default boolean isEnter() {
		return isEnter;
	}

}
