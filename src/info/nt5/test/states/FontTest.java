package info.nt5.test.states;

import static org.lwjgl.opengl.GL11.glClearColor;

import info.nt5.engine.game.GameManager;
import info.nt5.engine.game.elements.Background;
import info.nt5.engine.game.elements.Textbox;
import info.nt5.engine.game.elements.actor.Actor;
import info.nt5.engine.game.elements.actor.actors.Kanon;
import info.nt5.engine.game.state.State;
import info.nt5.engine.game.state.StateGame;
import info.nt5.engine.graphics.Color;
import info.nt5.engine.graphics.text.BitmapBuilder;
import info.nt5.engine.graphics.text.BitmapFormatBuilder;
import info.nt5.engine.input.Keyboard;
import info.nt5.engine.math.Vector3f;
import info.nt5.engine.util.Logger;

public class FontTest implements State {

	private static final Color clearColor = Color.PURPLE;

	private BitmapBuilder text;
	private Background bg;
	private Textbox textbox;
	private Actor kanon;

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

		bg = new Background();
		textbox = new Textbox(new Vector3f(0f, -4.2f, 0.0f));
		kanon = new Kanon(new Vector3f(5f, 0f, 0.0f));

		text = new BitmapBuilder(new Vector3f(-9f, -4.3f, 0.0f));

		BitmapFormatBuilder[] collection = {

				new BitmapFormatBuilder("hello", Color.RED, true),

				new BitmapFormatBuilder("hello", Color.BLACK),

				new BitmapFormatBuilder("hello\n", Color.GRAY, true),

				new BitmapFormatBuilder("hello\n", Color.BLUE),

				new BitmapFormatBuilder("hello")

		};

		text.add(collection);
		
	}

	@Override
	public void update(GameManager gm, StateGame game) {
		if (Keyboard.isPressed(Keyboard.KEY_SPACE)) {
			game.enterState(3);
		}

		if (Keyboard.isDown(Keyboard.KEY_W)) {
			kanon.translateY(0.08f);
		}
		if (Keyboard.isDown(Keyboard.KEY_S)) {
			kanon.translateY(-0.08f);
		}
		if (Keyboard.isDown(Keyboard.KEY_D)) {
			kanon.translateX(0.08f);
		}
		if (Keyboard.isDown(Keyboard.KEY_A)) {
			kanon.translateX(-0.08f);
		}
	}

	@Override
	public void render(GameManager gm, StateGame game) {
		bg.render();
		kanon.render();
		textbox.render();

		text.render();
	}

	@Override
	public void leave(GameManager gm, StateGame game) {
		Logger.debug("Font state lave!");
		bg.dispose();
		textbox.dispose();
		kanon.dispose();

		text.dispose();
	}

}
