package info.nt5.test.states;

import info.nt5.engine.game.GameManager;
import info.nt5.engine.game.elements.Model;
import info.nt5.engine.game.elements.gui.GUIOverlay;
import info.nt5.engine.game.state.State;
import info.nt5.engine.game.state.StateManager;
import info.nt5.engine.game.state.transition.FadeTransition;
import info.nt5.engine.graphics.Color;
import info.nt5.engine.graphics.Texture;
import info.nt5.engine.input.Keyboard;
import info.nt5.engine.lang.Lang;
import info.nt5.engine.math.Vector3f;
import info.nt5.engine.util.Logger;

public class Intro implements State {

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
		gm.getWindow().setClearColor(clearColor);

		model = new Model("assets/models/blend.obj", Texture.fromImage("assets/models/blend.jpg"),
				new Vector3f(0f, -5.5f, 0f));

		model.setScale(new Vector3f(2f));
	}

	@Override
	public void update(GameManager gm, StateManager game) {

		model.rotation.y = (model.rotation.y <= 360f ? model.rotation.y + 0.5f : 0f);
		model.rotation.x = (model.rotation.x <= 360f ? model.rotation.x + 0.5f : 0f);
		model.rotation.z = (model.rotation.z <= 360f ? model.rotation.z + 0.5f : 0f);

		model.update();

		GUIOverlay.update(gm, game);

		if (Keyboard.isPressed(Keyboard.KEY_SPACE)) {
			game.enterState(1, new FadeTransition(0, 0.01f), new FadeTransition(1, 0.01f));
		}
	}

	@Override
	public void render(GameManager gm, StateManager game) {
		model.render();

		GUIOverlay.render(gm, game);
	}

	@Override
	public void leave(GameManager gm, StateManager game) {
		Logger.debug(Lang.getString("states.leave", this.getClass().getSimpleName()));

		model.dispose();
	}

}
