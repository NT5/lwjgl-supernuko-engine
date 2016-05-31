package info.nt5.test.states;

import info.nt5.engine.game.GameManager;
import info.nt5.engine.game.elements.Model;
import info.nt5.engine.game.elements.button.Button;
import info.nt5.engine.game.elements.gui.GUIOverlay;
import info.nt5.engine.game.elements.util.Align;
import info.nt5.engine.game.elements.util.MouseAABB;
import info.nt5.engine.game.state.State;
import info.nt5.engine.game.state.StateManager;
import info.nt5.engine.game.state.transition.FadeTransition;
import info.nt5.engine.game.state.transition.FadeTransition.Fade;
import info.nt5.engine.graphics.Camera;
import info.nt5.engine.graphics.Color;
import info.nt5.engine.graphics.Texture;
import info.nt5.engine.graphics.text.BitmapFont;
import info.nt5.engine.graphics.text.BitmapFormat;
import info.nt5.engine.input.Keyboard;
import info.nt5.engine.input.Mouse;
import info.nt5.engine.lang.Lang;
import info.nt5.engine.math.Vector2f;
import info.nt5.engine.math.Vector3f;
import info.nt5.engine.sound.ALPlayer;
import info.nt5.engine.util.FilePaths;
import info.nt5.engine.util.Logger;

public class Intro implements State {

	private Model model;
	private ALPlayer sound;

	private MouseAABB mouseAABB;
	private Button btn, btn2;

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

		model = new Model(FilePaths.getModel("blend.obj"), Texture.fromImage(FilePaths.getModel("blend.jpg")),
				new Vector3f(0f, -5.5f, 0f));

		model.setScale(new Vector3f(2f));

		sound = new ALPlayer(FilePaths.getWav("click1.wav"));
		// sound.setPitch(0.5f);
		// sound.setGain(0.1f);

		mouseAABB = new MouseAABB(gm, new Vector2f(0.001f), Camera.worldCamera);

		btn = new Button(mouseAABB);
		btn.setTextures(Texture.fromColor(Color.ORANGE), Texture.fromColor(Color.WHITE), Texture.fromColor(Color.BLUE));
		btn.setPadding(new Vector2f(1f, 0f));
		btn.setClickedButton(Mouse.MOUSE_BUTTON_1);
		btn.setText(new BitmapFont(new BitmapFormat("Button Test", Color.BLACK, new Vector2f(0.2f))));
		btn.setTextAlign(Align.UPPER_LEFT);
		btn.setPosition(new Vector3f(0f, 3f, 0f));

		btn2 = new Button(mouseAABB);
		btn2.setTextures(Texture.fromColor(Color.ORANGE), Texture.fromColor(Color.WHITE), Texture.fromColor(Color.BLUE));
		btn2.setText(new BitmapFont(new BitmapFormat("Le button kawaii", Color.BLACK, new Vector2f(0.2f))));
		btn2.setTextAlign(Align.UPPER_LEFT);
		btn2.setPadding(new Vector2f((btn.getSize().x - btn2.getSize().x) + btn2.getPadding().x, 0f));
		btn2.setPosition(new Vector3f(btn.getPosition().x, btn.getPosition().y + (btn.getSize().y * -2f) - 0.1f,
				btn.getPosition().z));
	}

	@Override
	public void update(GameManager gm, StateManager game) {
		model.rotation.y = (model.rotation.y <= 360f ? model.rotation.y + 0.5f : 0f);
		model.rotation.x = (model.rotation.x <= 360f ? model.rotation.x + 0.5f : 0f);
		model.rotation.z = (model.rotation.z <= 360f ? model.rotation.z + 0.5f : 0f);

		mouseAABB.update();

		// btn2.setPosition(mouseAABB.getPosition3f());

		btn.update();
		btn2.update();

		model.update();

		GUIOverlay.update(gm, game);

		if (Keyboard.isPressed(Keyboard.KEY_SPACE)) {
			if (!game.isTransitioning()) {
				game.enterState(1, new FadeTransition(Fade.OUT, 0.01f), new FadeTransition(Fade.IN, 0.01f));
			}
		}

		if (Keyboard.isPressed(Keyboard.KEY_S)) {
			sound.stop();
		}
		if (Keyboard.isPressed(Keyboard.KEY_P)) {
			sound.play();
		}
		if (Keyboard.isPressed(Keyboard.KEY_L)) {
			sound.pause();
		}
	}

	@Override
	public void render(GameManager gm, StateManager game) {
		model.render();
		btn.render();
		btn2.render();
		GUIOverlay.render(gm, game);
	}

	@Override
	public void leave(GameManager gm, StateManager game) {
		Logger.debug(Lang.getString("states.leave", this.getClass().getSimpleName()));

		btn.dispose();
		btn2.dispose();
		model.dispose();
		sound.dispose();
	}

}
