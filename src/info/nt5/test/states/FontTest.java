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
import info.nt5.engine.graphics.text.BitmapFont;
import info.nt5.engine.input.Keyboard;
import info.nt5.engine.math.Vector3f;
import info.nt5.engine.util.Logger;

public class FontTest implements State {

	private static final Color clearColor = Color.PURPLE;

	private BitmapFont text;
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

		Vector3f text_pos = new Vector3f(-9f, -4.3f, 0.0f);
		Vector3f container_pos = new Vector3f(0f, -4.2f, 0.0f);
		Vector3f neko_pos = new Vector3f(5f, 0f, 0.0f);

		bg = new Background();
		textbox = new Textbox(container_pos);
		kanon = new Kanon();
		text = new BitmapFont("neko-kawaii-desu nyan~ nyan~ kyuun~~ kyuun~~ poi~ poi~\npoi~\npoi~\npoi~ :D!!", text_pos,
				Color.GREEN);
		text.setBold();

		kanon.translate(neko_pos);
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
		text.dispose();
		kanon.dispose();
	}

}
