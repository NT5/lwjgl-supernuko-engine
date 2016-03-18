package info.nt5.test.states;

import info.nt5.engine.game.GameManager;
import info.nt5.engine.game.state.State;
import info.nt5.engine.game.state.StateGame;
import info.nt5.engine.graphics.Color;
import info.nt5.engine.input.Keyboard;
import info.nt5.engine.math.Vector3f;
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
		stage = new Stage();
	}

	@Override
	public void enter(GameManager gm, StateGame game) {
		stage.addActor("neko1", new Actor(new Vector3f(-5f, 0f, 0f)));
		stage.addActor("neko2", new Actor(new Vector3f(5f, 0f, 0f)));

		stage.addActor("colored1", new Actor(Color.BLUE, 1f));

		stage.addTextbox("textbox1", new Textbox(new Color(Color.CYAN.r, Color.CYAN.g, Color.CYAN.b, 0.75f), new Vector3f(0f, 4.2f, 0.0f)));
		stage.getTextbox("textbox1").setText("textbox 1!");

		stage.addTextbox("textbox2", new Textbox(new Color(Color.PINK.r, Color.PINK.g, Color.PINK.b, 0.75f), new Vector3f(0f, -4.2f, 0.0f)));
		stage.getTextbox("textbox2").setText("textbox 2!");

		stage.setBackground(new Background(Color.GRAY));
	}

	@Override
	public void update(GameManager gm, StateGame game) {
		if (Keyboard.isPressed(Keyboard.KEY_SPACE)) {
			game.enterState(0);
		}

		if (Keyboard.isPressed(Keyboard.KEY_C)) {
			stage.getTextbox("textbox2").setText("nyan nyan");
		}

		if (Keyboard.isPressed(Keyboard.KEY_B)) {
			stage.getTextbox("textbox2")
					.setText("neko-kawaii-desu nyan~ nyan~ kyuun~~ kyuun~~ poi~ poi~\npoi~\npoi~\npoi~ :D!!");
		}
	}

	@Override
	public void render(GameManager gm, StateGame game) {
		stage.render();
	}

	@Override
	public void leave(GameManager gm, StateGame game) {
		stage.dispose();
	}

}
