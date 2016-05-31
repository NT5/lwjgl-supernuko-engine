package info.nt5.engine.game.elements.gui;

import info.nt5.engine.game.GameManager;
import info.nt5.engine.game.elements.Stage;
import info.nt5.engine.game.elements.button.Button;
import info.nt5.engine.game.elements.button.ButtonCallback;
import info.nt5.engine.game.elements.button.ButtonCollection;
import info.nt5.engine.game.elements.textbox.Textbox;
import info.nt5.engine.game.elements.util.Align;
import info.nt5.engine.game.elements.util.MouseAABB;
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
import info.nt5.engine.util.Screenshot;

public class GUIOverlay {

	private static Stage stage;
	private static ButtonCollection buttons;
	private static ALPlayer clickSound;

	public static void init(GameManager gm, StateManager game) {
		stage = new Stage();
		FPSCounter.init(gm, game);
		stage.addText(

				new BitmapFont(

						new BitmapFormat(Lang.getString("guioverlay.buildtag"), Color.BLACK)
								.setSize(new Vector2f(0.12f)),

						new Vector3f(-9.9f, -5.5f, 0f)

				)

		);

		stage.addTextbox(new Textbox(Color.GRAY.withAlpha(0.75f), 10f, 0.45f, new Vector3f(0f, 5.3f, 0f),
				new BitmapFormat(Lang.getString("guioverlay.upperinstructions"), Color.BLACK)
						.setSize(new Vector2f(0.1f))));

		buttons = new ButtonCollection(new MouseAABB(gm, new Vector2f(0.01f), Camera.guiCamera));

		clickSound = new ALPlayer(FilePaths.getWav("click1.wav"));
		clickSound.setPitch(0.5f);

		class ButtonController implements ButtonCallback {
			private int buttonId;

			public ButtonController(int buttonId) {
				this.buttonId = buttonId;
			}

			@Override
			public void onClick() {
				if (!clickSound.isPlaying()) {
					clickSound.setPitch(0.5f);
					clickSound.play();
				}
				switch (buttonId) {
				case 0:
					if (!game.isTransitioning()) {
						game.enterState(0, new FadeTransition(Fade.OUT, 0.1f), new FadeTransition(Fade.IN, 0.1f));
					}
					break;
				case 1:
					if (!game.isTransitioning()) {
						game.enterState(1, new FadeTransition(Fade.OUT, 0.1f), new FadeTransition(Fade.IN, 0.1f));
					}
					break;
				case 2:
					if (!game.isTransitioning()) {
						game.enterState(2, new FadeTransition(Fade.OUT, 0.1f), new FadeTransition(Fade.IN, 0.1f));
					}
					break;
				case 3:
					if (!game.isTransitioning()) {
						game.enterState(3, new FadeTransition(Fade.OUT, 0.1f), new FadeTransition(Fade.IN, 0.1f));
					}
					break;
				case 4:
					if (!game.isTransitioning()) {
						game.enterState(4, new FadeTransition(Fade.OUT, 0.1f), new FadeTransition(Fade.IN, 0.1f));
					}
					break;
				case 5:
					gm.getWindow().setFullscreen(gm.getWindow().isFullscreen() ? false : true);
					break;
				case 6:
					gm.getWindow().close();
					break;
				case 7:
					Screenshot.take(gm.getWindow());
					break;
				default:
					break;
				}
			}

			@Override
			public void onOver() {
			}

			@Override
			public void onCursorEnter() {
				if (!clickSound.isPlaying()) {
					clickSound.setPitch(1.5f);
					clickSound.play();
				}
			}

			@Override
			public void onCursorLeave() {
			}
		}

		Vector2f btnPadding = new Vector2f(0.5f, 0f);
		Color textColor = Color.BLACK;
		Vector2f textSize = new Vector2f(0.18f);
		Texture[] textures = { Texture.fromColor(Color.ORANGE), Texture.fromColor(Color.WHITE),
				Texture.fromColor(Color.LIME) };
		int clickedButton = Mouse.MOUSE_BUTTON_LEFT;

		buttons.setSpacing(new Vector2f(0.05f, 0f));

		buttons.addButton(new Button(buttons.getMouseAABB())
				.setText(new BitmapFont(new BitmapFormat("Model Test", textColor, textSize)))
				.setTextures(textures[0], textures[1], textures[2]).setPadding(btnPadding)
				.setTextAlign(Align.UPPER_LEFT).setClickedButton(clickedButton)
				.setButtonCallback(new ButtonController(0)));

		buttons.addButtonToDown(new Button(buttons.getMouseAABB())
				.setText(new BitmapFont(new BitmapFormat("Character test", textColor, textSize)))
				.setTextures(textures[0], textures[1], textures[2]).setPadding(btnPadding)
				.setClickedButton(clickedButton).setButtonCallback(new ButtonController(1)));

		buttons.addButtonToDown(new Button(buttons.getMouseAABB())
				.setText(new BitmapFont(new BitmapFormat("Font Test", textColor, textSize)))
				.setTextures(textures[0], textures[1], textures[2]).setPadding(btnPadding)
				.setClickedButton(clickedButton).setButtonCallback(new ButtonController(2)));

		buttons.addButtonToDown(new Button(buttons.getMouseAABB())
				.setText(new BitmapFont(new BitmapFormat("Stage test", textColor, textSize)))
				.setTextures(textures[0], textures[1], textures[2]).setPadding(btnPadding)
				.setClickedButton(clickedButton).setButtonCallback(new ButtonController(3)));

		buttons.addButtonToDown(new Button(buttons.getMouseAABB())
				.setText(new BitmapFont(new BitmapFormat("Physics test", textColor, textSize)))
				.setTextures(textures[0], textures[1], textures[2]).setPadding(btnPadding)
				.setClickedButton(clickedButton).setButtonCallback(new ButtonController(4)));

		buttons.addButtonToDown(new Button(buttons.getMouseAABB())
				.setText(new BitmapFont(new BitmapFormat("Screenshot", textColor, textSize)))
				.setTextures(textures[0], textures[1], textures[2]).setPadding(btnPadding)
				.setClickedButton(clickedButton).setButtonCallback(new ButtonController(7)));

		buttons.addButtonToDown(new Button(buttons.getMouseAABB())
				.setText(new BitmapFont(new BitmapFormat("Fullscreen", textColor, textSize)))
				.setTextures(textures[0], textures[1], textures[2]).setPadding(btnPadding)
				.setClickedButton(clickedButton).setButtonCallback(new ButtonController(5)));

		buttons.addButtonToDown(new Button(buttons.getMouseAABB())
				.setText(new BitmapFont(new BitmapFormat("Close", textColor, textSize)))
				.setTextures(textures[0], textures[1], textures[2]).setPadding(btnPadding)
				.setClickedButton(clickedButton).setButtonCallback(new ButtonController(6)));

		buttons.translate(new Vector3f(-10f, 4.3f, 0f));

		buttons.setCamera(Camera.guiCamera);
		stage.setCamera(Camera.guiCamera);
	}

	public static void update(GameManager gm, StateManager game) {
		Camera.worldCamera.update();
		stage.update();
		buttons.update();
		FPSCounter.update(gm, game);

		if (Keyboard.isPressed(Keyboard.KEY_F12)) {
			Screenshot.take(gm.getWindow());
		}

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
		if (Keyboard.isPressed(Keyboard.KEY_4)) {
			game.enterState(4);
		}

		if (Keyboard.isPressed(Keyboard.KEY_F11)) {
			gm.getWindow().setFullscreen(gm.getWindow().isFullscreen() ? false : true);
		}
		if (Keyboard.isPressed(Keyboard.KEY_ESCAPE)) {
			gm.getWindow().close();
		}

		if (Keyboard.isDown(Keyboard.KEY_LEFT_CONTROL)) {
			if (Keyboard.isPressed(Keyboard.KEY_C)) {
				Camera.worldCamera.position = new Vector3f();
				Camera.worldCamera.scale = new Vector3f(1f);
				Camera.worldCamera.roll = 0f;
			}

			if (Keyboard.isDown(Keyboard.KEY_W)) {
				Camera.worldCamera.position.y -= 0.08f;
			}
			if (Keyboard.isDown(Keyboard.KEY_S)) {
				Camera.worldCamera.position.y += 0.08f;
			}
			if (Keyboard.isDown(Keyboard.KEY_D)) {
				Camera.worldCamera.position.x -= 0.08f;
			}
			if (Keyboard.isDown(Keyboard.KEY_A)) {
				Camera.worldCamera.position.x += 0.08f;
			}

			if (Keyboard.isDown(Keyboard.KEY_E)) {
				Camera.worldCamera.roll += 1f;
			}
			if (Keyboard.isDown(Keyboard.KEY_Q)) {
				Camera.worldCamera.roll -= 1f;
			}

			if (Keyboard.isDown(Keyboard.KEY_Z)) {
				Camera.worldCamera.scale.x -= 0.008f;
				Camera.worldCamera.scale.y -= 0.008f;
			}
			if (Keyboard.isDown(Keyboard.KEY_X)) {
				Camera.worldCamera.scale.x += 0.008f;
				Camera.worldCamera.scale.y += 0.008f;
			}

			if (Mouse.isScrollDown()) {
				Camera.worldCamera.scale.x -= 0.08f;
				Camera.worldCamera.scale.y -= 0.08f;
			}
			if (Mouse.isScrollUp()) {
				Camera.worldCamera.scale.x += 0.08f;
				Camera.worldCamera.scale.y += 0.08f;
			}
		}
		if (Keyboard.isDown(Keyboard.KEY_LEFT_SHIFT)) {
			if (Mouse.isScrollDown()) {
				Camera.worldCamera.roll += 2f;
			}
			if (Mouse.isScrollUp()) {
				Camera.worldCamera.roll -= 2f;
			}
		}
	}

	public static void render(GameManager gm, StateManager game) {
		Camera.worldCamera.render();
		stage.render();
		buttons.render();
		FPSCounter.render(gm, game);
	}

	public static void dispose() {
		stage.dispose();
		buttons.dispose();
		clickSound.dispose();
		FPSCounter.dispose();
	}
}
