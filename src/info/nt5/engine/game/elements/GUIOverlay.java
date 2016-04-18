package info.nt5.engine.game.elements;

import info.nt5.engine.game.GameManager;
import info.nt5.engine.game.state.StateGame;
import info.nt5.engine.graphics.Color;
import info.nt5.engine.graphics.text.BitmapFont;
import info.nt5.engine.graphics.text.BitmapFormat;
import info.nt5.engine.input.Keyboard;
import info.nt5.engine.math.Vector2f;
import info.nt5.engine.math.Vector3f;

public class GUIOverlay {

	private static BitmapFont text;

	public static void init(GameManager gm, StateGame game) {
		text = new BitmapFont(

				new BitmapFormat[] {

						new BitmapFormat("Dev build.", Color.BLACK, true).setSize(new Vector2f(0.15f))

				},

				new Vector3f(-9.9f, -5.5f, 0f)

		);
	}

	public static void update(GameManager gm, StateGame game) {
		text.update();

		if (Keyboard.isPressed(Keyboard.KEY_0)) {
			game.enterState(0);
		}
		if (Keyboard.isPressed(Keyboard.KEY_1)) {
			game.enterState(1);
		}
		if (Keyboard.isPressed(Keyboard.KEY_2)) {
			game.enterState(2);
		}
		if (Keyboard.isPressed(Keyboard.KEY_3)) {
			game.enterState(3);
		}

		if (Keyboard.isPressed(Keyboard.KEY_F11)) {
			gm.getWindow().setFullscreen(gm.getWindow().isFullscreen() ? false : true);
		}
		if (Keyboard.isPressed(Keyboard.KEY_ESCAPE)) {
			gm.getWindow().close();
		}
	}

	public static void render(GameManager gm, StateGame game) {
		text.render();
	}

}
