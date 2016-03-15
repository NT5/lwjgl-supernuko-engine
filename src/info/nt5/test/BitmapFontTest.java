package info.nt5.test;

import static org.lwjgl.opengl.GL11.*;

import info.nt5.engine.game.GameManager;
import info.nt5.engine.game.state.State;
import info.nt5.engine.game.state.StateGame;
import info.nt5.engine.graphics.Color;
import info.nt5.engine.graphics.text.BitmapFont;
import info.nt5.engine.input.Keyboard;
import info.nt5.engine.math.Vector3f;

public class BitmapFontTest implements State {

	private static final Color clearColor = Color.PURPLE;

	private BitmapFont text;

	@Override
	public int getID() {
		return 2;
	}

	@Override
	public void init(GameManager gm, StateGame game) {
		text = new BitmapFont("neko-kawaii-desu nyan~ nyan~ kyuun~~ kyuun~~ poi~ poi~\npoi~\npoi~\npoi~ :D!!");
		text.translate(new Vector3f(-10f, 0f, 0.0f));
	}

	@Override
	public void enter(GameManager gm, StateGame game) {
		glClearColor(clearColor.r, clearColor.g, clearColor.b, clearColor.a);
	}

	@Override
	public void update(GameManager gm, StateGame game) {
		if (Keyboard.isPressed(Keyboard.KEY_SPACE)) {
			game.enterState(0);
		}
	}

	@Override
	public void render(GameManager gm, StateGame game) {
		text.render();
	}

	@Override
	public void leave(GameManager gm, StateGame game) {

	}

}
