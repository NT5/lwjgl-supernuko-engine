package info.nt5.test;

import info.nt5.engine.game.GameManager;
import info.nt5.engine.game.state.*;

public class Main2 extends StateGame {

	private static GameManager manager;

	public Main2(String title) {
		super(manager, title);
	}

	public static void main(String[] args) {
		manager = new GameManager(new Main2("window"), 800, 600, true, false, true, true);
		manager.start();
	}

	@Override
	public void initStatesList() {
		addState(new IntroState());
		addState(new MainMenuState());
	}
}
