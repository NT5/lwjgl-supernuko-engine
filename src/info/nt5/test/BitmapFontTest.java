package info.nt5.test;

import static org.lwjgl.opengl.GL11.*;

import info.nt5.engine.game.GameManager;
import info.nt5.engine.game.elements.Background;
import info.nt5.engine.game.elements.Textbox;
import info.nt5.engine.game.elements.Actor;
import info.nt5.engine.game.state.State;
import info.nt5.engine.game.state.StateGame;
import info.nt5.engine.graphics.Color;
import info.nt5.engine.graphics.text.BitmapFont;
import info.nt5.engine.input.Keyboard;
import info.nt5.engine.math.Vector3f;

public class BitmapFontTest implements State {

	private static final Color clearColor = Color.PURPLE;

	private BitmapFont text;
	private Background bg;
	private Textbox textbox;
	private Actor neko;

	@Override
	public int getID() {
		return 2;
	}

	@Override
	public void init(GameManager gm, StateGame game) {
		
		Vector3f text_pos = new Vector3f(-9f, -4.3f, 0.0f);
		Vector3f container_pos = new Vector3f(0f, -4.2f, 0.0f);
		Vector3f neko_pos = new Vector3f(-5f, 0f, 0.0f);

		bg = new Background();
		textbox = new Textbox();
		neko = new Actor();
		text = new BitmapFont("neko-kawaii-desu nyan~ nyan~ kyuun~~ kyuun~~ poi~ poi~\npoi~\npoi~\npoi~ :D!!");
		
		neko.get().translate(neko_pos);
		
		textbox.get().translate(container_pos);
		text.translate(text_pos);
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
		
		if (Keyboard.isDown(Keyboard.KEY_W)) {
			neko.get().position.y += 0.08f;
		}
		if (Keyboard.isDown(Keyboard.KEY_S)) {
			neko.get().position.y -= 0.08f;
		}
		if (Keyboard.isDown(Keyboard.KEY_D)) {
			neko.get().position.x += 0.08f;
		}
		if (Keyboard.isDown(Keyboard.KEY_A)) {
			neko.get().position.x -= 0.08f;
		}
	}

	@Override
	public void render(GameManager gm, StateGame game) {
		bg.render();
		neko.render();
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		textbox.render();
		glDisable(GL_BLEND);
		text.render();
	}

	@Override
	public void leave(GameManager gm, StateGame game) {
	}

}
