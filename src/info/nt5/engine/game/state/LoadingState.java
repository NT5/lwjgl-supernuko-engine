package info.nt5.engine.game.state;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL13.GL_TEXTURE1;
import static org.lwjgl.opengl.GL13.glActiveTexture;

import info.nt5.engine.game.GameManager;
import info.nt5.engine.game.elements.GUIOverlay;
import info.nt5.engine.game.state.transition.FadeTransition;
import info.nt5.engine.graphics.Camera;
import info.nt5.engine.graphics.Color;
import info.nt5.engine.graphics.Cursor;
import info.nt5.engine.graphics.Texture;
import info.nt5.engine.graphics.shader.Shader;
import info.nt5.engine.graphics.text.BitmapFont;
import info.nt5.engine.graphics.text.BitmapFormat;
import info.nt5.engine.math.Matrix4f;
import info.nt5.engine.math.Vector2f;
import info.nt5.engine.math.Vector3f;
import info.nt5.engine.util.Logger;

public class LoadingState implements State {
	private BitmapFont text;
	private Camera camera = new Camera(new Matrix4f());

	@Override
	public int getID() {
		return -1;
	}

	@Override
	public void init(GameManager gm, StateGame game) {
		Logger.debug("Loading state init!");

		glActiveTexture(GL_TEXTURE1);

		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

		Shader.LoadAllShaders();
		GUIOverlay.init(gm, game);

		Shader.geometryShader.bind();
		Matrix4f pr_matrix = Matrix4f.orthographic(-10.0f, 10.0f, -10.0f * 9.0f / 16.0f, 10.0f * 9.0f / 16.0f, -10.0f,
				10.0f);
		Shader.geometryShader.setUniformMat4f("vw_matrix", Matrix4f.translate(camera.position));
		Shader.geometryShader.setUniformMat4f("pr_matrix", pr_matrix);
		Shader.geometryShader.setUniform1i("tex", 1);
		Shader.geometryShader.unbind();

		Shader.textShader.bind();
		Shader.textShader.setUniformMat4f("vw_matrix", Matrix4f.translate(camera.position));
		Shader.textShader.setUniformMat4f("pr_matrix", pr_matrix);
		Shader.textShader.setUniform1i("tex", 1);
		Shader.textShader.unbind();

		gm.getWindow().setCursor(new Cursor(Texture.fromImage("assets/img/cursor.png")));
		gm.getWindow().setIcon();
	}

	@Override
	public void enter(GameManager gm, StateGame game) {
		Logger.debug("Loading state enter!");
		glClearColor(1f, 1f, 1f, 1f);
		text = new BitmapFont(

				new BitmapFormat[] {

						new BitmapFormat("Cargando...", Color.WHITE, true).setSize(new Vector2f(0.6f))

				},

				new Vector3f(-3f, 0f, 0f)

		);
		text.setRenderSpeed(3);
	}

	@Override
	public void update(GameManager gm, StateGame game) {
		text.update();
		if (text.isRenderListEnd()) {
			game.enterState(0, null, new FadeTransition(1));
		}
	}

	@Override
	public void render(GameManager gm, StateGame game) {
		glClearColor(0f, 0f, 0f, 1f);
		camera.render();
		text.render();
	}

	@Override
	public void leave(GameManager gm, StateGame game) {
		Logger.debug("Loading state leave!");
		text.dispose();
	}
}
