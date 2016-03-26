package info.nt5.test.states;

import static org.lwjgl.opengl.GL11.glClearColor;

import info.nt5.engine.game.GameManager;
import info.nt5.engine.game.elements.Background;
import info.nt5.engine.game.elements.actor.Actor;
import info.nt5.engine.game.state.State;
import info.nt5.engine.game.state.StateGame;
import info.nt5.engine.graphics.Color;
import info.nt5.engine.input.Keyboard;
import info.nt5.engine.math.Vector3f;
import info.nt5.engine.util.Logger;

public class MainMenu implements State {

	private Actor acT;
	private Background bg;

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

		acT = new Actor();
		acT.translate(new Vector3f(5f, 0f, 0f));

		bg = new Background(Color.PINK);
	}

	@Override
	public void update(GameManager gm, StateGame game) {
		if (Keyboard.isPressed(Keyboard.KEY_SPACE)) {
			game.enterState(2);
		}
		if (Keyboard.isPressed(Keyboard.KEY_1)) {
			game.enterState(0);
		}

		if (Keyboard.isDown(Keyboard.KEY_W)) {
			acT.translateY(0.08f);
		}
		if (Keyboard.isDown(Keyboard.KEY_S)) {
			acT.translateY(-0.08f);
		}
		if (Keyboard.isDown(Keyboard.KEY_D)) {
			acT.translateX(0.08f);
		}
		if (Keyboard.isDown(Keyboard.KEY_A)) {
			acT.translateX(-0.08f);
		}

		if (Keyboard.isPressed(Keyboard.KEY_Q)) {
			acT.textures.nextEyeSprite();
			acT.Parts.get(1).texture = acT.textures.getCurrentEye();
		}
		if (Keyboard.isPressed(Keyboard.KEY_E)) {
			acT.textures.nextMouthSprite();
			acT.Parts.get(2).texture = acT.textures.getCurrentMouth();
		}
	}

	@Override
	public void render(GameManager gm, StateGame game) {
		bg.render();
		acT.render();
	}

	@Override
	public void leave(GameManager gm, StateGame game) {
		Logger.debug("Menu state leave!");
		acT.dispose();
		bg.dispose();
	}

}
