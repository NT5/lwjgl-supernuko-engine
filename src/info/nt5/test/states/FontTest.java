package info.nt5.test.states;

import static org.lwjgl.opengl.GL11.glClearColor;

import info.nt5.engine.game.GameManager;
import info.nt5.engine.game.state.State;
import info.nt5.engine.game.state.StateGame;
import info.nt5.engine.graphics.Color;
import info.nt5.engine.graphics.text.BitmapFont;
import info.nt5.engine.graphics.text.BitmapFormat;
import info.nt5.engine.input.Keyboard;
import info.nt5.engine.math.Vector3f;
import info.nt5.engine.util.Logger;

public class FontTest implements State {

	private static final Color clearColor = Color.PURPLE;

	private BitmapFont text;

	@Override
	public int getID() {
		return 2;
	}

	@Override
	public void init(GameManager gm, StateGame game) {
		Logger.debug("Font state init!");
	}

	@Override
	public void enter(GameManager gm, StateGame game) {
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
	}

	@Override
	public void update(GameManager gm, StateGame game) {
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
	public void render(GameManager gm, StateGame game) {
		text.render(1);
	}

	@Override
	public void leave(GameManager gm, StateGame game) {
		Logger.debug("Font state lave!");

		text.dispose();
	}

}
