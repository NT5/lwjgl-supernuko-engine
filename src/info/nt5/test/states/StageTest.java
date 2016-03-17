package info.nt5.test.states;

import info.nt5.engine.game.GameManager;
import info.nt5.engine.game.state.State;
import info.nt5.engine.game.state.StateGame;
import info.nt5.engine.input.Keyboard;
import info.nt5.engine.math.Vector3f;
import info.nt5.engine.game.elements.Stage;

public class StageTest implements State {

	private Stage stage;

	@Override
	public int getID() {
		return 3;
	}

	@Override
	public void init(GameManager gm, StateGame game) {
		stage = new Stage();
		stage.addActor("neko1", new Vector3f(-5f, 0f, 0.0f));
		stage.addActor("neko2");
		stage.setBackground();
	}

	@Override
	public void enter(GameManager gm, StateGame game) {

	}

	@Override
	public void update(GameManager gm, StateGame game) {
		if (Keyboard.isPressed(Keyboard.KEY_SPACE)) {
			game.enterState(0);
		}
	}

	@Override
	public void render(GameManager gm, StateGame game) {
		stage.render();
	}

	@Override
	public void leave(GameManager gm, StateGame game) {

	}

}
