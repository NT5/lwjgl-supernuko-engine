package info.nt5.test;

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
import info.nt5.engine.game.Crate;

public class IntroState implements State {

	private Crate crate1;

	public Camera camera = new Camera(new Matrix4f());

	@Override
	public int getID() {
		return 0;
	}

	@Override
	public void init(GameManager gm, StateGame game) {
		Logger.debug("Intro state init!");

		glClearColor(0.0f, 1.0f, 0.0f, 1.0f);
		glActiveTexture(GL_TEXTURE1);
		glEnable(GL_DEPTH_TEST);

		Shader.LoadAllShaders();
		
		Shader.defaultShader.bind();
		Matrix4f pr_matrix = Matrix4f.orthographic(-10.0f, 10.0f, -10.0f * 9.0f / 16.0f, 10.0f * 9.0f / 16.0f, -10.0f, 10.0f);
		Shader.defaultShader.setUniformMat4f("vw_matrix", Matrix4f.translate(camera.position));
		Shader.defaultShader.setUniformMat4f("pr_matrix", pr_matrix);
		Shader.defaultShader.setUniform1i("tex", 1);
		
		Shader.defaultShader.unbind();

		crate1 = new Crate();
		crate1.translate(new Vector3f(0f, 0.75f, 0.0f));
	}

	@Override
	public void enter(GameManager gm, StateGame game) {
		Logger.debug("Intro state enter!");
		glClearColor(0.0f, 1.0f, 0.0f, 1.0f);
	}

	@Override
	public void update(GameManager gm, StateGame game) {
		// camera.update();
		if (Keyboard.isPressed(Keyboard.KEY_SPACE)) {
			game.enterState(1);
		}
		
		if (Keyboard.isDown(Keyboard.KEY_W)) {
			camera.position.y -= 0.05f;
		}
		if (Keyboard.isDown(Keyboard.KEY_S)) {
			camera.position.y += 0.05f;
		}
		if (Keyboard.isDown(Keyboard.KEY_D)) {
			camera.position.x -= 0.05f;
		}
		if (Keyboard.isDown(Keyboard.KEY_A)) {
			camera.position.x += 0.05f;
		}
	}

	@Override
	public void render(GameManager gm, StateGame game) {
		camera.render();

		crate1.render();
	}

	@Override
	public void leave(GameManager gm, StateGame game) {
		Logger.debug("Intro state leave!");
	}

}
