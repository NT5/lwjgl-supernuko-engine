package info.nt5.test.states;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;

import info.nt5.engine.game.GameManager;
import info.nt5.engine.game.state.State;
import info.nt5.engine.game.state.StateGame;
import info.nt5.engine.input.Keyboard;
import info.nt5.engine.math.Matrix4f;
import info.nt5.engine.math.Vector3f;
import info.nt5.engine.util.Logger;

import info.nt5.engine.graphics.shader.Shader;
import info.nt5.engine.graphics.Camera;
import info.nt5.engine.graphics.Color;
import info.nt5.engine.graphics.Cursor;
import info.nt5.engine.graphics.Texture;
import info.nt5.engine.game.Crate;

public class Intro implements State {

	private Crate crate1, crate2;

	public Camera camera = new Camera(new Matrix4f());
	private static final Color clearColor = Color.CYAN;

	@Override
	public int getID() {
		return 0;
	}

	@Override
	public void init(GameManager gm, StateGame game) {

		Logger.debug("Intro state init!");

		glClearColor(clearColor.r, clearColor.g, clearColor.b, clearColor.a);
		glActiveTexture(GL_TEXTURE1);
		// glEnable(GL_DEPTH_TEST);

		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

		Shader.LoadAllShaders();

		Shader.defaultShader.bind();
		Matrix4f pr_matrix = Matrix4f.orthographic(-10.0f, 10.0f, -10.0f * 9.0f / 16.0f, 10.0f * 9.0f / 16.0f, -10.0f,
				10.0f);
		Shader.defaultShader.setUniformMat4f("vw_matrix", Matrix4f.translate(camera.position));
		Shader.defaultShader.setUniformMat4f("pr_matrix", pr_matrix);
		Shader.defaultShader.setUniform1i("tex", 1);

		Shader.defaultShader.unbind();

		gm.getWindow().setCursor(new Cursor(Texture.fromImage("assets/img/cursor.png")));
		gm.getWindow().setIcon();
	}

	@Override
	public void enter(GameManager gm, StateGame game) {
		Logger.debug("Intro state enter!");
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glClearColor(clearColor.r, clearColor.g, clearColor.b, clearColor.a);

		crate1 = new Crate(Color.GREEN);
		crate1.translate(new Vector3f(-5f, 0f, 0.0f));

		crate2 = new Crate(Color.PINK.withNewAlpha(0.55f));
		crate2.translate(new Vector3f(5f, 0f, 0.0f));
	}

	@Override
	public void update(GameManager gm, StateGame game) {
		if (Keyboard.isPressed(Keyboard.KEY_ESCAPE)) {
			gm.getWindow().close();
		}

		if (Keyboard.isPressed(Keyboard.KEY_SPACE)) {
			game.enterState(1);
		}

		if (Keyboard.isPressed(Keyboard.KEY_1)) {
			game.enterState(1);
		}
		if (Keyboard.isPressed(Keyboard.KEY_2)) {
			game.enterState(2);
		}
		if (Keyboard.isPressed(Keyboard.KEY_3)) {
			game.enterState(3);
		}

		if (Keyboard.isPressed(Keyboard.KEY_F11)) {
			gm.getWindow().setFullscreen(gm.getWindow().isFullscreen() ? false : true);
		}

		if (Keyboard.isDown(Keyboard.KEY_C)) {
			camera.position = new Vector3f();
		}

		if (Keyboard.isDown(Keyboard.KEY_W)) {
			camera.position.y -= 0.08f;
		}
		if (Keyboard.isDown(Keyboard.KEY_S)) {
			camera.position.y += 0.08f;
		}
		if (Keyboard.isDown(Keyboard.KEY_D)) {
			camera.position.x -= 0.08f;
		}
		if (Keyboard.isDown(Keyboard.KEY_A)) {
			camera.position.x += 0.08f;
		}

		if (Keyboard.isDown(Keyboard.KEY_UP)) {
			crate1.position.y += 0.08f;
		}
		if (Keyboard.isDown(Keyboard.KEY_DOWN)) {
			crate1.position.y -= 0.08f;
		}
		if (Keyboard.isDown(Keyboard.KEY_LEFT)) {
			crate1.position.x -= 0.08f;
		}
		if (Keyboard.isDown(Keyboard.KEY_RIGHT)) {
			crate1.position.x += 0.08f;
		}

		if (Keyboard.isDown(Keyboard.KEY_I)) {
			crate2.position.y += 0.08f;
		}
		if (Keyboard.isDown(Keyboard.KEY_K)) {
			crate2.position.y -= 0.08f;
		}
		if (Keyboard.isDown(Keyboard.KEY_J)) {
			crate2.position.x -= 0.08f;
		}
		if (Keyboard.isDown(Keyboard.KEY_L)) {
			crate2.position.x += 0.08f;
		}
	}

	@Override
	public void render(GameManager gm, StateGame game) {
		camera.render();

		crate1.render();
		crate2.render();
	}

	@Override
	public void leave(GameManager gm, StateGame game) {
		Logger.debug("Intro state leave!");
		glDisable(GL_BLEND);

		crate1.dispose();
		crate2.dispose();
	}

}
