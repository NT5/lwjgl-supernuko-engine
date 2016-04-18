package info.nt5.test.states;

import info.nt5.engine.game.GameManager;
import info.nt5.engine.game.elements.Background;
import info.nt5.engine.game.elements.GUIOverlay;
import info.nt5.engine.game.elements.Stage;
import info.nt5.engine.game.elements.actor.actors.Kanon;
import info.nt5.engine.game.elements.actor.actors.Maru;
import info.nt5.engine.game.elements.textbox.Textbox;
import info.nt5.engine.game.state.State;
import info.nt5.engine.game.state.StateGame;
import info.nt5.engine.graphics.Color;
import info.nt5.engine.graphics.text.BitmapFormat;
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
		String LongText = "12345678910111213141516171819201234567891011121314151617181920123456789101112131415161718192012345678910111213141516171819201234567891011121314151617181920";
		stage.addActor(new Kanon(new Vector3f(-5f, 0f, 0f)));
		stage.addActor(new Maru(new Vector3f(5f, 0f, 0f)));

		stage.getActor(0).getPart(1).animation.set(10, 0.80, 5, -1);
		stage.getActor(1).getPart(1).animation.set(10, 0.80, 5, -1);
		stage.getActor(1).getPart(2).animation.set(10, 0.80, 5, -1);

		stage.addTextbox(

				new Textbox(

						Color.CYAN.withAlpha(0.75f), 1.5f, 0.3f,

						new Vector3f(-5f, 4.2f, 0.0f),

						new BitmapFormat("poi poi!")

				)

		);

		stage.addTextbox(

				new Textbox(

						Color.PINK.withAlpha(0.75f),

						1.5f, 0.3f,

						new Vector3f(5f, 4.2f, 0.0f),

						new BitmapFormat("nyan nyan!", Color.LIME)

				)

		);

		stage.addTextbox(

				new Textbox(

						Color.SILVER.withAlpha(0.75f), 5f, 1f,

						new Vector3f(),

						new BitmapFormat(LongText)

				)

		);
		stage.getTextbox(2).addTextToCurrentCollection(new BitmapFormat(LongText, Color.BLACK));
		stage.getTextbox(2).addTextToCurrentCollection(new BitmapFormat(LongText, Color.PURPLE));
		stage.getTextbox(2).addTextToCurrentCollection(new BitmapFormat("nyan\nnyan", Color.BLUE));
		stage.getTextbox(2).setHeaderText(new BitmapFormat("header-san"));
		stage.getTextbox(2).getCollectionCurrent().setRenderSpeed(1);

		stage.setBackground(new Background(Color.GRAY));
	}

	@Override
	public void update(GameManager gm, StateGame game) {
		GUIOverlay.update(gm, game);

		stage.update();

		if (Keyboard.isPressed(Keyboard.KEY_SPACE)) {
			game.enterState(0);
		}

		if (Keyboard.isPressed(Keyboard.KEY_X)) {
			stage.getTextbox(2).getCollectionCurrent().setRenderSpeed(0);
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
		GUIOverlay.render(gm, game);
	}

	@Override
	public void leave(GameManager gm, StateGame game) {
		Logger.debug("Stage state leave");
		stage.dispose();
	}

}
