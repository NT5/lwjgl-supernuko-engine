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
import info.nt5.engine.input.Keyboard;
import info.nt5.engine.math.Vector3f;
import info.nt5.engine.util.Logger;

public class MainMenu implements State {

	private Actor actor;
	private Background bg;
	private Textbox textbox;

	@Override
	public int getID() {
		return 1;
	}

	@Override
	public void init(GameManager gm, StateGame game) {
		Logger.debug("Menu state init!");
	}

	@Override
	public void enter(GameManager gm, StateGame game) {
		Logger.debug("Menu state enter!");

		glClearColor(0.0f, 0.0f, 1.0f, 1.0f);

		int speed = 3;

		actor = new Kanon(new Vector3f(5f, 0f, 0f));

		textbox = new Textbox(Color.GRAY.withAlpha(0.75f), new Vector3f(0f, -4f, 0f), "onii-chan!!! daisukiiii~");
		textbox.setTextColor(Color.BLACK);
		textbox.setHeaderText("Kanon-chan");
		textbox.setHeaderTextColor(Color.CYAN);

		textbox.setTextSpeed(speed);

		actor.getPart(1).setAnimation(10, 0.80, 5, -1);
		actor.getPart(2).setAnimation((speed * 2), 0, 0.5, textbox.calcTextRenderTime());

		bg = new Background();
	}

	@Override
	public void update(GameManager gm, StateGame game) {
		actor.update();

		if (Keyboard.isPressed(Keyboard.KEY_SPACE)) {
			game.enterState(2);
		}
		if (Keyboard.isPressed(Keyboard.KEY_1)) {
			game.enterState(0);
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
			textbox.setTextSpeed(0);
			actor.getPart(2).endAnimation();
		}

		if (Keyboard.isPressed(Keyboard.KEY_X)) {
		}
	}

	@Override
	public void render(GameManager gm, StateGame game) {
		bg.render();
		actor.render();
		textbox.render();
	}

	@Override
	public void leave(GameManager gm, StateGame game) {
		Logger.debug("Menu state leave!");
		actor.dispose();
		bg.dispose();
		textbox.dispose();
	}

}
