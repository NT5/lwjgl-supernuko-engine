package info.nt5.test.states;

import info.nt5.engine.game.GameManager;
import info.nt5.engine.game.state.State;
import info.nt5.engine.game.state.StateGame;
import info.nt5.engine.graphics.Color;
import info.nt5.engine.input.Keyboard;
import info.nt5.engine.math.Vector3f;
import info.nt5.engine.util.Logger;
import info.nt5.engine.game.elements.Actor;
import info.nt5.engine.game.elements.Background;
import info.nt5.engine.game.elements.Stage;
import info.nt5.engine.game.elements.Textbox;

public class StageTest implements State {

	private Stage stage;

	@Override
	public int getID() {
		return 3;
	}

	@Override
	public void init(GameManager gm, StateGame game) {
		Logger.debug("Stage state init!");
		stage = new Stage();
	}

	@Override
	public void enter(GameManager gm, StateGame game) {

		Logger.debug("Stage state enter!");

		stage.addActor(new Actor(new Vector3f(-5f, 0f, 0f)));
		stage.addActor(new Actor(new Vector3f(5f, 0f, 0f)));

		stage.addTextbox(new Textbox(new Color(Color.CYAN.r, Color.CYAN.g, Color.CYAN.b, 0.75f),
				new Vector3f(0f, 4.2f, 0.0f), "textbox 1!"));

		stage.addTextbox(new Textbox(new Color(Color.PINK.r, Color.PINK.g, Color.PINK.b, 0.75f), 7f, 1.3f,
				new Vector3f(0f, -4.2f, 0.0f), "textbox 2!"));

		stage.addTextbox(new Textbox(new Vector3f(), "textbox 3!"));

		stage.getTextbox(1).setHeaderText("header-san");

		stage.setBackground(new Background(Color.GRAY));

		Logger.info("%s", stage.getTextboxs().toString());
		Logger.info("%s", stage.getActors().toString());
	}

	@Override
	public void update(GameManager gm, StateGame game) {
		if (Keyboard.isPressed(Keyboard.KEY_SPACE)) {
			game.enterState(0);
		}

		if (Keyboard.isPressed(Keyboard.KEY_C)) {
			stage.getTextbox(1).setHeaderText("nico-nico-ni~");
			stage.getTextbox(1).setText("nyan nyan");
		}

		if (Keyboard.isPressed(Keyboard.KEY_B)) {
			stage.getTextbox(1).setHeaderText("><!");
			stage.getTextbox(1)
					.setText("neko-kawaii-desu nyan~ nyan~ kyuun~~ kyuun~~ poi~ poi~\npoi~\npoi~\npoi~ :D!!");
		}

		if (Keyboard.isDown(Keyboard.KEY_W)) {
			stage.getTextbox(1).translateY(0.08f);
		}
		if (Keyboard.isDown(Keyboard.KEY_S)) {
			stage.getTextbox(1).translateY(-0.08f);
		}
		if (Keyboard.isDown(Keyboard.KEY_D)) {
			stage.getTextbox(1).translateX(0.08f);
		}
		if (Keyboard.isDown(Keyboard.KEY_A)) {
			stage.getTextbox(1).translateX(-0.08f);
		}
	}

	@Override
	public void render(GameManager gm, StateGame game) {
		stage.render();
	}

	@Override
	public void leave(GameManager gm, StateGame game) {
		Logger.debug("Stage state leave");
		stage.dispose();
	}

}
