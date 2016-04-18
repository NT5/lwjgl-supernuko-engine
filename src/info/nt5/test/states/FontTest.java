package info.nt5.test.states;

import static org.lwjgl.opengl.GL11.glClearColor;

import info.nt5.engine.game.GameManager;
import info.nt5.engine.game.elements.GUIOverlay;
import info.nt5.engine.game.elements.actor.Actor;
import info.nt5.engine.game.elements.actor.actors.Kanon;
import info.nt5.engine.game.state.State;
import info.nt5.engine.game.state.StateManager;
import info.nt5.engine.graphics.Color;
import info.nt5.engine.graphics.text.BitmapFont;
import info.nt5.engine.graphics.text.BitmapFormat;
import info.nt5.engine.graphics.text.FontEventHandler;
import info.nt5.engine.input.Keyboard;
import info.nt5.engine.math.Vector3f;
import info.nt5.engine.util.Logger;

public class FontTest implements State {

	private static final Color clearColor = Color.PURPLE;

	private BitmapFont text;
	private Actor actor;

	@Override
	public int getID() {
		return 2;
	}

	@Override
	public void init(GameManager gm, StateManager game) {
		Logger.debug("Font state init!");
	}

	@Override
	public void enter(GameManager gm, StateManager game) {
		Logger.debug("Font state enter!");
		glClearColor(clearColor.r, clearColor.g, clearColor.b, clearColor.a);

		text = new BitmapFont(

				new BitmapFormat[] {

						new BitmapFormat("onii-chan!!! daisukiiii~", Color.RED),
						new BitmapFormat("onii-chan!!! daisukiiii~", Color.LIME),
						new BitmapFormat("onii-chan!!! daisukiiii~\n", Color.BLACK),
						new BitmapFormat("onii-chan!!!\ndaisukiiii~", Color.CYAN),
						new BitmapFormat("onii-chan!!! daisukiiii~\n", Color.PINK),
						new BitmapFormat("onii-chan!!!\ndaisukiiii~", Color.LIME)

				},

				new Vector3f(-5f, 0f, 0.0f)

		);
		text.addText(new BitmapFormat("onii-chan!!! daisukiiii~", Color.WHITE));
		text.setRenderSpeed(1);
		actor = new Kanon();

		class FontEventHandleClass implements FontEventHandler {
			@Override
			public void onCreateChar(int asciiCode, Vector3f position, Color color) {
			}

			@Override
			public void onUpdate(int speed, int delta) {
			}

			@Override
			public void onRender(int fromIndex, int toIndex) {
			}

			@Override
			public void onAddToRenderList(int currentRenderIndex) {
			}

			@Override
			public void onRenderListEnd() {
				actor.getPart(2).animation.end();
			}
		}

		actor.getPart(2).animation.set(3, 0, 2, -1);
		text.setEventHandler(new FontEventHandleClass());
	}

	@Override
	public void update(GameManager gm, StateManager game) {
		GUIOverlay.update(gm, game);
		text.update();
		actor.update();
		if (Keyboard.isPressed(Keyboard.KEY_SPACE)) {
			game.enterState(3);
		}

		if (Keyboard.isDown(Keyboard.KEY_W)) {
			text.translateY(0.08f);
		}
		if (Keyboard.isDown(Keyboard.KEY_S)) {
			text.translateY(-0.08f);
		}
		if (Keyboard.isDown(Keyboard.KEY_D)) {
			text.translateX(0.08f);
		}
		if (Keyboard.isDown(Keyboard.KEY_A)) {
			text.translateX(-0.08f);
		}
	}

	@Override
	public void render(GameManager gm, StateManager game) {
		actor.render();
		text.render();
		GUIOverlay.render(gm, game);
	}

	@Override
	public void leave(GameManager gm, StateManager game) {
		Logger.debug("Font state lave!");

		text.dispose();
		actor.dispose();
	}

}
