package info.nt5.test;

import static org.lwjgl.opengl.GL11.*;

import info.nt5.engine.game.GameManager;
import info.nt5.engine.game.state.State;
import info.nt5.engine.game.state.StateGame;
import info.nt5.engine.input.Keyboard;
import info.nt5.engine.util.Logger;

public class IntroState implements State {

	@Override
	public int getID() {
		return 0;
	}

	@Override
	public void init(GameManager gm, StateGame game) {
		Logger.debug("Intro state init!");
	}

	@Override
	public void enter(GameManager gm, StateGame game) {
		Logger.debug("Intro state enter!");
	}

	@Override
	public void update(GameManager gm, StateGame game) {
		if (Keyboard.isPressed(Keyboard.KEY_SPACE)) {
			game.enterState(1);
		}
	}

	@Override
	public void render(GameManager gm, StateGame game) {
		glClearColor(0.0f, 1.0f, 0.0f, 1.0f);
	}

	@Override
	public void leave(GameManager gm, StateGame game) {
		Logger.debug("Intro state leave!");
	}

}
