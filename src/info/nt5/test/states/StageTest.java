package info.nt5.test.states;

import info.nt5.engine.game.GameManager;
import info.nt5.engine.game.elements.Background;
import info.nt5.engine.game.elements.Stage;
import info.nt5.engine.game.elements.actor.actors.Kanon;
import info.nt5.engine.game.elements.actor.actors.Maru;
import info.nt5.engine.game.elements.gui.GUIOverlay;
import info.nt5.engine.game.elements.textbox.Textbox;
import info.nt5.engine.game.state.State;
import info.nt5.engine.game.state.StateManager;
import info.nt5.engine.graphics.Color;
import info.nt5.engine.graphics.text.BitmapFormat;
import info.nt5.engine.input.Keyboard;
import info.nt5.engine.lang.Lang;
import info.nt5.engine.math.Vector3f;
import info.nt5.engine.util.Logger;

public class StageTest implements State {

	private Stage stage;
	private static final Color clearColor = Color.ORANGE;

	@Override
	public int getID() {
		return 3;
	}

	@Override
	public void init(GameManager gm, StateManager game) {
		Logger.debug(Lang.getString("states.init", this.getClass().getSimpleName()));

		stage = new Stage();

		for (int i = 0; i < 100; i++) {
			// stage.addActor(new Kanon());
		}
	}

	@Override
	public void enter(GameManager gm, StateManager game) {
		Logger.debug(Lang.getString("states.enter", this.getClass().getSimpleName()));
		gm.getWindow().setClearColor(clearColor);

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
		stage.getTextbox(2).addTextToCurrentCollection(LongText);
		stage.getTextbox(2).addTextToCurrentCollection(LongText);
		stage.getTextbox(2).addTextToCurrentCollection("nyan\nnyan");
		stage.getTextbox(2).setHeaderText(new BitmapFormat("header-san"));
		stage.getTextbox(2).getCollectionCurrent().setRenderSpeed(1);
		stage.getTextbox(2).bindAnimationCallback(stage.getActor(0).getPart(2).animation);

		stage.setBackground(new Background(Color.GRAY));
	}

	@Override
	public void update(GameManager gm, StateManager game) {
		GUIOverlay.update(gm, game);

		stage.update();

		if (Keyboard.isPressed(Keyboard.KEY_SPACE)) {
			game.enterState(4);
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
	public void render(GameManager gm, StateManager game) {
		stage.render();
		GUIOverlay.render(gm, game);
	}

	@Override
	public void leave(GameManager gm, StateManager game) {
		Logger.debug(Lang.getString("states.leave", this.getClass().getSimpleName()));
		stage.dispose();
	}

}
