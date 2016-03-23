package info.nt5.test;

import info.nt5.engine.game.GameManager;
import info.nt5.engine.game.state.*;
import info.nt5.test.states.*;

public class Main2 extends StateGame {

	private static GameManager manager;

	public Main2(String title) {
		super(manager, title);
	}

	public static void main(String[] args) {
		manager = new GameManager(new Main2("A life with..."), 1152, 648, true, false, true, true);
		manager.start();
	}

	@Override
	public void initStatesList() {
		addState(new Intro());
		addState(new MainMenu());
		addState(new FontTest());
		addState(new StageTest());
	}
}