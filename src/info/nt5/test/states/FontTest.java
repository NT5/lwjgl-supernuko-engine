package info.nt5.test.states;

import info.nt5.engine.game.GameManager;
import info.nt5.engine.game.elements.actor.Actor;
import info.nt5.engine.game.elements.actor.actors.Kanon;
import info.nt5.engine.game.elements.gui.GUIOverlay;
import info.nt5.engine.game.state.State;
import info.nt5.engine.game.state.StateManager;
import info.nt5.engine.graphics.Color;
import info.nt5.engine.graphics.text.BitmapFont;
import info.nt5.engine.graphics.text.BitmapFormat;
import info.nt5.engine.graphics.text.FontEventHandler;
import info.nt5.engine.input.Keyboard;
import info.nt5.engine.lang.Lang;
import info.nt5.engine.math.Vector3f;
import info.nt5.engine.util.Logger;

public class FontTest implements State {

	private static final Color clearColor = Color.PINK;

	private Actor actor;

	private BitmapFont text;

	@Override
	public int getID() {
		return 2;
	}

	@Override
	public void init(GameManager gm, StateManager game) {
		Logger.debug(Lang.getString("states.init", this.getClass().getSimpleName()));
	}

	@Override
	public void enter(GameManager gm, StateManager game) {
		Logger.debug(Lang.getString("states.enter", this.getClass().getSimpleName()));
		gm.getWindow().setClearColor(clearColor);

		actor = new Kanon();

		class FontEventHandleClass implements FontEventHandler {
			@Override
			public void onCreateChar(int asciiCode) {
			}

			@Override
			public void onUpdate(int speed, int delta) {
			}

			@Override
			public void onRender() {
			}

			@Override
			public void onAddToRenderList(int currentRenderIndex) {
				actor.getPart(2).animation.set(3, 0, 2, -1);
			}

			@Override
			public void onRenderListEnd() {
				actor.getPart(2).animation.end();
			}
		}

		actor.getPart(2).animation.set(3, 0, 2, -1);

		text = new BitmapFont(

				new BitmapFormat(Lang.getString("state.fonttest.pangram"), Color.CYAN),

				new Vector3f(-10f, 0f, 0f)

		);
		text.setRenderSpeed(1);
		text.setEventHandler(new FontEventHandleClass());
	}

	@Override
	public void update(GameManager gm, StateManager game) {
		GUIOverlay.update(gm, game);
		this.text.update();
		this.actor.update();
		if (Keyboard.isPressed(Keyboard.KEY_SPACE)) {
			game.enterState(3);
		}

		if (Keyboard.isDown(Keyboard.KEY_W)) {
			this.text.translateY(0.08f);
		}
		if (Keyboard.isDown(Keyboard.KEY_S)) {
			this.text.translateY(-0.08f);
		}
		if (Keyboard.isDown(Keyboard.KEY_D)) {
			this.text.translateX(0.08f);
		}
		if (Keyboard.isDown(Keyboard.KEY_A)) {
			this.text.translateX(-0.08f);
		}

		if (Keyboard.isPressed(Keyboard.KEY_E)) {
			this.text.addText("\n" + Lang.getString("state.fonttest.pangram"));
		}
	}

	@Override
	public void render(GameManager gm, StateManager game) {
		this.actor.render();
		this.text.render();

		GUIOverlay.render(gm, game);
	}

	@Override
	public void leave(GameManager gm, StateManager game) {
		Logger.debug(Lang.getString("states.leave", this.getClass().getSimpleName()));

		this.actor.dispose();
		this.text.dispose();
	}

}
