package info.nt5.test.states;

import static org.lwjgl.opengl.GL11.glClearColor;

import info.nt5.engine.game.GameManager;
import info.nt5.engine.game.elements.Crate;
import info.nt5.engine.game.elements.GUIOverlay;
import info.nt5.engine.game.state.State;
import info.nt5.engine.game.state.StateGame;
import info.nt5.engine.game.state.transition.FadeTransition;
import info.nt5.engine.graphics.Color;
import info.nt5.engine.input.Keyboard;
import info.nt5.engine.math.Vector3f;
import info.nt5.engine.util.Logger;

public class Intro implements State {

	private Crate crate1, crate2;

	private static final Color clearColor = Color.CYAN;

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
		glClearColor(clearColor.r, clearColor.g, clearColor.b, clearColor.a);
		crate1 = new Crate(Color.GREEN);
		crate1.translate(new Vector3f(-5f, 0f, 0.0f));

		crate2 = new Crate(Color.PINK.withAlpha(0.55f));
		crate2.translate(new Vector3f(5f, 0f, 0.0f));
	}

	@Override
	public void update(GameManager gm, StateGame game) {
		GUIOverlay.update(gm, game);

		if (Keyboard.isPressed(Keyboard.KEY_SPACE)) {
			game.enterState(1, null, new FadeTransition());
		}

		if (Keyboard.isDown(Keyboard.KEY_W)) {
			crate1.position.y += 0.08f;
		}
		if (Keyboard.isDown(Keyboard.KEY_S)) {
			crate1.position.y -= 0.08f;
		}
		if (Keyboard.isDown(Keyboard.KEY_A)) {
			crate1.position.x -= 0.08f;
		}
		if (Keyboard.isDown(Keyboard.KEY_D)) {
			crate1.position.x += 0.08f;
		}

		if (Keyboard.isDown(Keyboard.KEY_I)) {
			crate2.position.y += 0.08f;
		}
		if (Keyboard.isDown(Keyboard.KEY_K)) {
			crate2.position.y -= 0.08f;
		}
		if (Keyboard.isDown(Keyboard.KEY_J)) {
			crate2.position.x -= 0.08f;
		}
		if (Keyboard.isDown(Keyboard.KEY_L)) {
			crate2.position.x += 0.08f;
		}

	}

	@Override
	public void render(GameManager gm, StateGame game) {
		crate1.render();
		crate2.render();
		GUIOverlay.render(gm, game);
	}

	@Override
	public void leave(GameManager gm, StateGame game) {
		Logger.debug("Intro state leave!");

		crate1.dispose();
		crate2.dispose();
	}

}
