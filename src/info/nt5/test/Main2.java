package info.nt5.test;

import info.nt5.engine.game.GameManager;
import info.nt5.engine.game.state.StateManager;
import info.nt5.test.states.FontTest;
import info.nt5.test.states.Intro;
import info.nt5.test.states.MainMenu;
import info.nt5.test.states.StageTest;
import info.nt5.test.states.jboxTest;

public class Main2 extends StateManager {

	private static GameManager game;

	public Main2(String title) {
		super(game, title);
	}

	public static void main(String[] args) {
		game = new GameManager(new Main2("A life with..."), 1152, 648, true, false, true, true);
		game.start();
	}

	@Override
	public void initStatesList() {
		addState(new Intro());
		addState(new MainMenu());
		addState(new FontTest());
		addState(new StageTest());
		addState(new jboxTest());
	}
}