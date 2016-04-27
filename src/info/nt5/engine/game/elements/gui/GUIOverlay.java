package info.nt5.engine.game.elements.gui;

import info.nt5.engine.game.GameManager;
import info.nt5.engine.game.elements.Stage;
import info.nt5.engine.game.elements.textbox.Textbox;
import info.nt5.engine.game.state.StateManager;
import info.nt5.engine.graphics.Camera;
import info.nt5.engine.graphics.Color;
import info.nt5.engine.graphics.text.BitmapFormat;
import info.nt5.engine.graphics.text.BitmapFont;
import info.nt5.engine.input.Keyboard;
import info.nt5.engine.math.Vector2f;
import info.nt5.engine.math.Vector3f;

public class GUIOverlay {

	private static Stage stage;

	public static void init(GameManager gm, StateManager game) {
		stage = new Stage();
		FPSCounter.init(gm, game);
		stage.addText(

				new BitmapFont(

						new BitmapFormat("Development build", Color.BLACK).setSize(new Vector2f(0.12f)),

						new Vector3f(-9.9f, -5.5f, 0f)

				)

		);

		stage.addTextbox(new Textbox(Color.GRAY.withAlpha(0.75f), 10f, 0.35f, new Vector3f(0f, 5.3f, 0f),
				new BitmapFormat(
						"Utiliza la tecla espacio o los numeros 0, 1, 2, 3, para navegar entre las pantallas!, Usa F11 para cambiar a pantalla completa o preciona ESC para salir!",
						Color.BLACK).setSize(new Vector2f(0.1f))));

	}

	public static void update(GameManager gm, StateManager game) {
		Camera.defaultCam.update();
		stage.update();
		FPSCounter.update(gm, game);

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

		if (Keyboard.isDown(Keyboard.KEY_LEFT_CONTROL)) {
			if (Keyboard.isPressed(Keyboard.KEY_C)) {
				Camera.defaultCam.position = new Vector3f();
				Camera.defaultCam.scale = new Vector3f(1f);
				Camera.defaultCam.roll = 0f;
			}

			if (Keyboard.isDown(Keyboard.KEY_W)) {
				Camera.defaultCam.position.y -= 0.08f;
			}
			if (Keyboard.isDown(Keyboard.KEY_S)) {
				Camera.defaultCam.position.y += 0.08f;
			}
			if (Keyboard.isDown(Keyboard.KEY_D)) {
				Camera.defaultCam.position.x -= 0.08f;
			}
			if (Keyboard.isDown(Keyboard.KEY_A)) {
				Camera.defaultCam.position.x += 0.08f;
			}

			if (Keyboard.isDown(Keyboard.KEY_E)) {
				Camera.defaultCam.roll += 1f;
			}
			if (Keyboard.isDown(Keyboard.KEY_Q)) {
				Camera.defaultCam.roll -= 1f;
			}

			if (Keyboard.isDown(Keyboard.KEY_Z)) {
				Camera.defaultCam.scale.x -= 0.008f;
				Camera.defaultCam.scale.y -= 0.008f;
			}
			if (Keyboard.isDown(Keyboard.KEY_X)) {
				Camera.defaultCam.scale.x += 0.008f;
				Camera.defaultCam.scale.y += 0.008f;
			}
		}
	}

	public static void render(GameManager gm, StateManager game) {
		Camera.defaultCam.render();
		stage.render();
		FPSCounter.render(gm, game);
	}

}
