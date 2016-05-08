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
import info.nt5.engine.game.elements.Model;
import info.nt5.engine.game.elements.Stage;
import info.nt5.engine.game.elements.gui.GUIOverlay;
import info.nt5.engine.game.state.transition.FadeTransition;
import info.nt5.engine.game.state.transition.Transition;
import info.nt5.engine.graphics.Camera;
import info.nt5.engine.graphics.Color;
import info.nt5.engine.graphics.Cursor;
import info.nt5.engine.graphics.Texture;
import info.nt5.engine.graphics.shader.Shader;
import info.nt5.engine.graphics.text.BitmapFont;
import info.nt5.engine.graphics.text.BitmapFontCollection;
import info.nt5.engine.graphics.text.BitmapFormat;
import info.nt5.engine.lang.Lang;
import info.nt5.engine.math.Matrix4f;
import info.nt5.engine.math.Vector2f;
import info.nt5.engine.math.Vector3f;
import info.nt5.engine.util.Logger;

public class LoadingState implements State {

	private ArrayList<State> states;
	private int currentStateLoading;

	private Stage stage;

	private float delta = 0f;

	private int FIRST_STATE = 0;
	private Transition JOIN_TRASITION = null;
	private Transition LEAVE_TRANSITION = new FadeTransition(1);

	private Model model;

	public LoadingState(Collection<State> collection) {
		this.states = new ArrayList<State>(collection);
	}

	@Override
	public int getID() {
		return -1;
	}

	@Override
	public void init(GameManager gm, StateManager game) {
		Lang.init("en", "EN");
		Logger.debug(Lang.getString("states.init", this.getClass().getSimpleName()));

		glActiveTexture(GL_TEXTURE1);

		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

		Shader.LoadAllShaders();
		GUIOverlay.init(gm, game);

		stage = new Stage();

		Shader.geometryShader.bind();

		Shader.geometryShader.setUniformMat4f("vw_matrix", Matrix4f.translate(Camera.defaultCam.position));
		Shader.geometryShader.setUniformMat4f("pr_matrix", Camera.defaultCam.getProjectionMatrix());
		Shader.geometryShader.setUniform1i("tex", 1);
		Shader.geometryShader.unbind();

		Shader.textShader.bind();
		Shader.textShader.setUniformMat4f("vw_matrix", Matrix4f.translate(Camera.defaultCam.position));
		Shader.textShader.setUniformMat4f("pr_matrix", Camera.defaultCam.getProjectionMatrix());
		Shader.textShader.setUniform1i("tex", 1);
		Shader.textShader.unbind();

		gm.getWindow().setCursor(new Cursor(Texture.fromImage("assets/img/cursor.png")));
		gm.getWindow().setIcon();
	}

	@Override
	public void enter(GameManager gm, StateManager game) {
		Logger.debug(Lang.getString("states.enter", this.getClass().getSimpleName()));

		model = new Model("assets/models/logo.obj", Color.WHITE, new Vector3f());
		model.setScale(new Vector3f(0.01f));

		stage.addTextCollection(new BitmapFontCollection());
		stage.getTextCollecion(0).addTextCollection(

				new BitmapFont(

						new BitmapFormat(Lang.getString("state.loading.loading"), Color.WHITE, true),
						new Vector3f(-1.5f, -5f, 0f)

				)

		);
		stage.getTextCollecion(0).addTextCollection(

				new BitmapFont(

						new BitmapFormat(Lang.getString("state.loading.loaded"), Color.WHITE, true),
						new Vector3f(-1.5f, -5f, 0f)

				)

		);
		stage.getTextCollecion(0).getTextCollection(1).setRenderSpeed(3);
		stage.addText(

				new BitmapFont(

						new BitmapFormat(
								Lang.getString("state.loading.statelistloaded", this.getClass().getSimpleName()) + "\n",
								Color.WHITE).setSize(new Vector2f(0.2f)),
						new Vector3f(-9.5f, 5f, 0f)

				)

		);
	}

	@Override
	public void update(GameManager gm, StateManager game) {
		stage.update();

		model.rotation.y = (model.rotation.y <= 360 ? model.rotation.y + 3f : 0f);
		model.update();

		if (this.currentStateLoading < states.size()) {
			stage.getText(0).addText(Lang.getString("state.loading.statelistloaded",
					this.states.get(currentStateLoading).getClass().getSimpleName()) + "\n");

			this.states.get(this.currentStateLoading).init(gm, game);
			this.currentStateLoading++;

			if (this.currentStateLoading >= this.states.size()) {
				stage.getTextCollecion(0).setNextCollection();
				stage.getText(0).addText(Lang.getString("state.loading.statelistend"));
			}
		} else {
			if (delta >= 1f) {
				if (game.getState(FIRST_STATE) != null) {
					game.enterState(FIRST_STATE, JOIN_TRASITION, LEAVE_TRANSITION);
				} else {
					Logger.warn(Lang.getString("states.error.invalidId", FIRST_STATE));
					game.enterState(this.states.get(0).getID(), JOIN_TRASITION, LEAVE_TRANSITION);
				}
			}
			delta += 0.01f;
		}
	}

	@Override
	public void render(GameManager gm, StateManager game) {
		glClearColor(0f, 0f, 0f, 1f);
		Camera.defaultCam.render();
		stage.render();

		model.render();
	}

	@Override
	public void leave(GameManager gm, StateManager game) {
		Logger.debug(Lang.getString("states.leave", this.getClass().getSimpleName()));
		stage.dispose();

		model.dispose();
	}
}
