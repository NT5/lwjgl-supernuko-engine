package info.nt5.engine.game.state;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL13.GL_TEXTURE1;
import static org.lwjgl.opengl.GL13.glActiveTexture;

import java.util.ArrayList;
import java.util.Collection;

import info.nt5.engine.game.GameManager;
import info.nt5.engine.game.elements.Stage;
import info.nt5.engine.game.elements.gui.GUIOverlay;
import info.nt5.engine.game.state.transition.FadeTransition;
import info.nt5.engine.graphics.Camera;
import info.nt5.engine.graphics.Color;
import info.nt5.engine.graphics.Cursor;
import info.nt5.engine.graphics.Texture;
import info.nt5.engine.graphics.shader.Shader;
import info.nt5.engine.graphics.text.BitmapFont;
import info.nt5.engine.graphics.text.BitmapFontCollection;
import info.nt5.engine.graphics.text.BitmapFormat;
import info.nt5.engine.math.Matrix4f;
import info.nt5.engine.math.Vector2f;
import info.nt5.engine.math.Vector3f;
import info.nt5.engine.util.Logger;

public class LoadingState implements State {

	private ArrayList<State> states;
	private int currentStateLoading;

	private Stage stage;
	private Camera camera = new Camera(new Matrix4f());

	private float delta = 0f;

	public LoadingState(Collection<State> collection) {
		this.states = new ArrayList<State>(collection);
	}

	@Override
	public int getID() {
		return -1;
	}

	@Override
	public void init(GameManager gm, StateManager game) {
		Logger.debug("Loading state init!");

		glActiveTexture(GL_TEXTURE1);

		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

		Shader.LoadAllShaders();
		GUIOverlay.init(gm, game);

		stage = new Stage();

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
	public void enter(GameManager gm, StateManager game) {
		Logger.debug("Loading state enter!");

		stage.addTextCollection(new BitmapFontCollection());
		stage.getTextCollecion(0).addTextCollection(

				new BitmapFont(

						new BitmapFormat("Cargando...", Color.WHITE, true), new Vector3f(-1.5f, -5f, 0f)

				)

		);
		stage.getTextCollecion(0).addTextCollection(

				new BitmapFont(

						new BitmapFormat("Cargado!", Color.WHITE, true), new Vector3f(-1.5f, -5f, 0f)

				)

		);
		stage.getTextCollecion(0).getTextCollection(1).setRenderSpeed(3);
		stage.addText(

				new BitmapFont(

						new BitmapFormat(this.getClass().getSimpleName() + " loaded!\n", Color.WHITE)
								.setSize(new Vector2f(0.15f)),
						new Vector3f(-9.5f, 5f, 0f)

				)

		);
	}

	@Override
	public void update(GameManager gm, StateManager game) {
		stage.update();

		if (this.currentStateLoading < states.size()) {
			stage.getText(0).addText(

					new BitmapFormat(

							this.states.get(currentStateLoading).getClass().getSimpleName() + " loaded!\n", Color.WHITE

					).setSize(new Vector2f(0.15f))

			);

			this.states.get(this.currentStateLoading).init(gm, game);
			this.currentStateLoading++;

			if (this.currentStateLoading >= this.states.size()) {
				stage.getTextCollecion(0).setNextCollection();
				stage.getText(0).addText(new BitmapFormat("\nAll done!", Color.WHITE).setSize(new Vector2f(0.15f)));
			}
		} else {
			if (delta >= 0.5f) {
				game.enterState(0, null, new FadeTransition(1));
			}
			delta += 0.01f;
		}
	}

	@Override
	public void render(GameManager gm, StateManager game) {
		glClearColor(0f, 0f, 0f, 1f);
		camera.render();
		stage.render();
	}

	@Override
	public void leave(GameManager gm, StateManager game) {
		Logger.debug("Loading state leave!");
		stage.dispose();
	}
}
