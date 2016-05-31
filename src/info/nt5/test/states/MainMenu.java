package info.nt5.test.states;

import info.nt5.engine.game.GameManager;
import info.nt5.engine.game.elements.Background;
import info.nt5.engine.game.elements.actor.Actor;
import info.nt5.engine.game.elements.actor.actors.Maru;
import info.nt5.engine.game.elements.gui.GUIOverlay;
import info.nt5.engine.game.elements.textbox.Textbox;
import info.nt5.engine.game.state.State;
import info.nt5.engine.game.state.StateManager;
import info.nt5.engine.graphics.Color;
import info.nt5.engine.graphics.text.BitmapFont;
import info.nt5.engine.graphics.text.BitmapFormat;
import info.nt5.engine.input.Keyboard;
import info.nt5.engine.lang.Lang;
import info.nt5.engine.math.Vector2f;
import info.nt5.engine.math.Vector3f;
import info.nt5.engine.util.Logger;

public class MainMenu implements State {

	private Actor actor;
	private Background bg;
	private Textbox textbox;
	private static final Color clearColor = Color.WHITE;

	@Override
	public int getID() {
		return 1;
	}

	@Override
	public void init(GameManager gm, StateManager game) {
		Logger.debug(Lang.getString("states.init", this.getClass().getSimpleName()));
	}

	@Override
	public void enter(GameManager gm, StateManager game) {
		Logger.debug(Lang.getString("states.enter", this.getClass().getSimpleName()));

		gm.getWindow().setClearColor(clearColor);

		actor = new Maru(new Vector3f(5f, 0f, 0f));

		textbox = new Textbox(

				Color.GRAY.withAlpha(0.75f),

				new Vector3f(0f, -4f, 0f),

				new BitmapFormat("onii-chan!!! daisukiiii~", Color.BLUE)

		);

		textbox.addTextToCurrentCollection(

				new String[] {

						"onii-chan!!! daisukiiii", "onii-chan!!! daisukiiii~\n", "onii-chan!!!\ndaisukiiii~",
						"onii-chan!!! daisukiiii~", "onii-chan!!! daisukiiii~"

				}

		);

		textbox.addTextCollection(

				new BitmapFont[] {

						new BitmapFont(

								new BitmapFormat("text 1!~", Color.CYAN)

						),

						new BitmapFont(

								new BitmapFormat("text 2!~", Color.YELLOW)

						),

						new BitmapFont(

								new BitmapFormat("text 3!~", Color.GRAY)

						),

				}

		);

		textbox.setHeaderText(new BitmapFormat("Maru-chan", Color.CYAN, new Vector2f(0.2f), true));

		textbox.setGlobalTextSpeed(1);
		textbox.bindAnimationCallback(actor.getPart(2).animation);

		actor.getPart(1).animation.set(10, 0.80, 5, -1); // Eye

		bg = new Background();
	}

	@Override
	public void update(GameManager gm, StateManager game) {
		GUIOverlay.update(gm, game);
		actor.update();
		textbox.update();
		bg.update();

		if (Keyboard.isPressed(Keyboard.KEY_SPACE)) {
			game.enterState(2);
		}

		if (Keyboard.isDown(Keyboard.KEY_W)) {
			actor.translateY(0.08f);
		}
		if (Keyboard.isDown(Keyboard.KEY_S)) {
			actor.translateY(-0.08f);
		}
		if (Keyboard.isDown(Keyboard.KEY_D)) {
			actor.translateX(0.08f);
		}
		if (Keyboard.isDown(Keyboard.KEY_A)) {
			actor.translateX(-0.08f);
		}

		if (Keyboard.isPressed(Keyboard.KEY_Q)) {
			actor.getPart(1).nextTextureSet();
		}
		if (Keyboard.isPressed(Keyboard.KEY_E)) {
			textbox.getCollectionCurrent().setRenderSpeed(0);
		}

		if (Keyboard.isPressed(Keyboard.KEY_X)) {
			textbox.setPrevCollection();
		}
	}

	@Override
	public void render(GameManager gm, StateManager game) {
		bg.render();
		actor.render();
		textbox.render();
		GUIOverlay.render(gm, game);
	}

	@Override
	public void leave(GameManager gm, StateManager game) {
		Logger.debug(Lang.getString("states.leave", this.getClass().getSimpleName()));
		actor.dispose();
		bg.dispose();
		textbox.dispose();
	}

}
