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
	
	private Shader shader;
	private Crate crate1;
	
	public Camera camera = new Camera( new Matrix4f() );
	
	@Override
	public int getID() {
		return 0;
	}

	@Override
	public void init(GameManager gm, StateGame game) {
		Logger.debug("Intro state init!");
		
		glClearColor(0.0f, 1.0f, 0.0f, 1.0f);
		glActiveTexture(GL_TEXTURE1);
		
		Matrix4f pr_matrix = Matrix4f.orthographic(0.0f, gm.getWindow().getWidth(), 0.0f, gm.getWindow().getHeight(), -1.0f, 1.0f);
		
		Logger.info("pr_matrix: %s", pr_matrix.toString());
		
		shader = Shader.fromPath("res/shaders/shader.vs", "res/shaders/shader.fs");
		shader.bind();
		
		shader.setUniformMat4f("vw_matrix", Matrix4f.translate( new Vector3f() ) );	
		shader.setUniformMat4f("pr_matrix", pr_matrix);
		shader.setUniform1i("tex", 1);
		
		shader.unbind();
		
		crate1 = new Crate();
		crate1.translate(new Vector3f(0f, 0.75f, 0.0f));
	}

	@Override
	public void enter(GameManager gm, StateGame game) {
		Logger.debug("Intro state enter!");
	}

	@Override
	public void update(GameManager gm, StateGame game) {
		camera.update();
		if (Keyboard.isPressed(Keyboard.KEY_SPACE)) {
			game.enterState(1);
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
		
		camera.dispose();
		shader.dispose();
	}

}
