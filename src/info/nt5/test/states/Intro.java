package info.nt5.test.states;

import static org.lwjgl.opengl.GL11.glClearColor;

import info.nt5.engine.game.GameManager;
import info.nt5.engine.game.elements.Crate;
import info.nt5.engine.game.elements.Model;
import info.nt5.engine.game.elements.gui.GUIOverlay;
import info.nt5.engine.game.state.State;
import info.nt5.engine.game.state.StateManager;
import info.nt5.engine.game.state.transition.FadeTransition;
import info.nt5.engine.graphics.Color;
import info.nt5.engine.graphics.Texture;
import info.nt5.engine.input.Keyboard;
import info.nt5.engine.input.Mouse;
import info.nt5.engine.lang.Lang;
import info.nt5.engine.math.Vector3f;
import info.nt5.engine.math.Vector4f;
import info.nt5.engine.util.Logger;

public class Intro implements State {

	private Crate crate1, crate2;

	private Model model;

	private static final Color clearColor = Color.CYAN;

	@Override
	public int getID() {
		return 0;
	}

	@Override
	public void init(GameManager gm, StateManager game) {
		Logger.debug(Lang.getString("states.init", this.getClass().getSimpleName()));
	}

	@Override
	public void enter(GameManager gm, StateManager game) {
		Logger.debug(Lang.getString("states.enter", this.getClass().getSimpleName()));
		glClearColor(clearColor.r, clearColor.g, clearColor.b, clearColor.a);
		crate1 = new Crate(Color.GREEN);
		crate1.translate(new Vector3f(-5f, 0f, 0.0f));

		crate2 = new Crate(Color.PINK.withAlpha(0.55f));
		crate2.translate(new Vector3f(5f, 0f, 0.0f));

		model = new Model("assets/models/blend.obj", Texture.fromImage("assets/models/blend.jpg"), new Vector3f(0f, 0f, 0f));
		model.setScale(new Vector3f(1f));
	}

	@Override
	public void update(GameManager gm, StateManager game) {
		model.rotation.y = (model.rotation.y <= 360f ? model.rotation.y + 0.5f : 0f);
		model.rotation.x = (model.rotation.x <= 360f ? model.rotation.x + 0.5f : 0f);
		model.rotation.z = (model.rotation.z <= 360f ? model.rotation.z + 0.5f : 0f);

		model.update();

		GUIOverlay.update(gm, game);

		Vector4f MatrixCursor = Mouse.getMatrixAxis(gm);

		crate2.setPosition(new Vector3f(MatrixCursor.x, MatrixCursor.y, 0f));

		if (Keyboard.isPressed(Keyboard.KEY_SPACE)) {
			game.enterState(1, new FadeTransition(), new FadeTransition(1));
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
	public void render(GameManager gm, StateManager game) {
		crate1.render();
		crate2.render();

		model.render();

		GUIOverlay.render(gm, game);
	}

	@Override
	public void leave(GameManager gm, StateManager game) {
		Logger.debug(Lang.getString("states.leave", this.getClass().getSimpleName()));

		crate1.dispose();
		crate2.dispose();
		model.dispose();
	}

}
