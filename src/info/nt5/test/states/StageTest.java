package info.nt5.test.states;

import info.nt5.engine.game.GameManager;
import info.nt5.engine.game.state.State;
import info.nt5.engine.game.state.StateGame;
import info.nt5.engine.input.Keyboard;
import info.nt5.engine.math.Vector3f;
import info.nt5.engine.game.elements.Stage;
import info.nt5.engine.game.elements.Textbox;

public class StageTest implements State {

	private Stage stage;

	private Textbox textbox;

	@Override
	public int getID() {
		return 3;
	}

	@Override
	public void init(GameManager gm, StateGame game) {

		stage = new Stage();

		stage.addActor("neko1", new Vector3f(-5f, 0f, 0.0f));
		stage.addActor("neko2", new Vector3f(5f, 0f, 0.0f));
		
		stage.addTextbox("textbox1");

		stage.setBackground();

		textbox = new Textbox();
		textbox.translate(new Vector3f(0f, -4.2f, 0.0f));
		textbox.setText("holiwis");
	}

	@Override
	public void enter(GameManager gm, StateGame game) {

	}

	@Override
	public void update(GameManager gm, StateGame game) {
		if (Keyboard.isPressed(Keyboard.KEY_SPACE)) {
			game.enterState(0);
		}

		if (Keyboard.isPressed(Keyboard.KEY_C)) {
			textbox.setText("nyan nyan");
		}
		
		if ( Keyboard.isPressed(Keyboard.KEY_B) ) {
			textbox.setText("neko-kawaii-desu nyan~ nyan~ kyuun~~ kyuun~~ poi~ poi~\npoi~\npoi~\npoi~ :D!!");
		}
	}

	@Override
	public void render(GameManager gm, StateGame game) {
		stage.render();
		textbox.render();
	}

	@Override
	public void leave(GameManager gm, StateGame game) {

	}

}
