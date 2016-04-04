package info.nt5.test.states;

import info.nt5.engine.game.GameManager;
import info.nt5.engine.game.elements.Background;
import info.nt5.engine.game.elements.Stage;
import info.nt5.engine.game.elements.Textbox;
import info.nt5.engine.game.elements.actor.actors.Kanon;
import info.nt5.engine.game.state.State;
import info.nt5.engine.game.state.StateGame;
import info.nt5.engine.graphics.Color;
import info.nt5.engine.graphics.text.BitmapFormatBuilder;
import info.nt5.engine.input.Keyboard;
import info.nt5.engine.math.Vector3f;
import info.nt5.engine.util.Logger;

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

		stage.addActor(new Kanon(new Vector3f(-5f, 0f, 0f)));

		Kanon.flipActor();

		stage.addActor(new Kanon(new Vector3f(5f, 0f, 0f)));

		Kanon.flipActor();

		stage.getActor(0).getPart(1).setAnimation(10, 0.80, 5, -1);

		stage.addTextbox(new Textbox(Color.CYAN.withAlpha(0.75f), 1.5f, 0.3f, new Vector3f(-5f, 4.2f, 0.0f),
				new BitmapFormatBuilder("poi poi!")));

		stage.addTextbox(new Textbox(Color.PINK.withAlpha(0.75f), 1.5f, 0.3f, new Vector3f(5f, 4.2f, 0.0f),
				new BitmapFormatBuilder("nyan nyan!", Color.LIME)));

		stage.addTextbox(new Textbox(Color.SILVER.withAlpha(0.75f), 5f, 1f, new Vector3f(), new BitmapFormatBuilder(
				"1-2-3-4-5-6-7-8-9-10-11-12-13-14-15-16-17-18-19-20-21-22-23-24-25-26-27-28-29-30-31-32-33-34-35-36-37-38-39-40-41-42-43-44-45-46-47-48-49-50")));

		stage.getTextbox(2).setTextSpeed(1);
		stage.getTextbox(2).addText(new BitmapFormatBuilder(" nyan", Color.BLUE));
		stage.getTextbox(2).setHeaderText(new BitmapFormatBuilder("header-san"));

		// String[] StringCollection = { "text 1!", "text 2!", "text 3!" };

		// stage.getTextbox(2).addTextCollection(StringCollection);

		stage.setBackground(new Background(Color.GRAY));

		Logger.info("%s", stage.getTextboxs().toString());
		Logger.info("%s", stage.getActors().toString());
	}

	@Override
	public void update(GameManager gm, StateGame game) {

		stage.update();

		if (Keyboard.isPressed(Keyboard.KEY_SPACE)) {
			game.enterState(0);
		}

		if (Keyboard.isPressed(Keyboard.KEY_C)) {
		}

		if (Keyboard.isPressed(Keyboard.KEY_B)) {
		}

		if (Keyboard.isPressed(Keyboard.KEY_X)) {
			stage.getTextbox(2).setTextSpeed(0);
		}

		if (Keyboard.isPressed(Keyboard.KEY_Q)) {
		}

		if (Keyboard.isPressed(Keyboard.KEY_E)) {
		}

		if (Keyboard.isDown(Keyboard.KEY_W)) {
			stage.getTextbox(2).translateY(0.08f);
		}
		if (Keyboard.isDown(Keyboard.KEY_S)) {
			stage.getTextbox(2).translateY(-0.08f);
		}
		if (Keyboard.isDown(Keyboard.KEY_D)) {
			stage.getTextbox(2).translateX(0.08f);
		}
		if (Keyboard.isDown(Keyboard.KEY_A)) {
			stage.getTextbox(2).translateX(-0.08f);
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
