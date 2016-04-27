package info.nt5.engine.game.state.transition;

import info.nt5.engine.game.GameManager;
import info.nt5.engine.game.state.State;
import info.nt5.engine.game.state.StateManager;
import info.nt5.engine.graphics.Color;
import info.nt5.engine.graphics.shader.Mesh;
import info.nt5.engine.graphics.shader.Shader;

public class FadeTransition implements Transition {

	private Mesh fadeMesh;

	private Color fadeColor = Color.BLACK;

	private int fadeType;

	private float fadeSpeed = 0.01f;
	private float time;

	private boolean isComplete = false;

	public FadeTransition() {
		this.setFadeTimeType();
	}

	public FadeTransition(int fadeType) {
		this.fadeType = fadeType;

		this.setFadeTimeType();
	}

	public FadeTransition(Color fadeColor) {
		this.fadeColor = fadeColor;

		this.setFadeTimeType();
	}

	public FadeTransition(int fadeType, Color fadeColor) {
		this.fadeType = fadeType;
		this.fadeColor = fadeColor;

		this.setFadeTimeType();
	}

	private void setFadeTimeType() {
		switch (this.fadeType) {
		case 1:
			this.time = 0.0f;
			break;
		default:
			this.time = 1.0f;
			break;
		}
	}

	@Override
	public void init(State current, State next) {
		fadeMesh = new Mesh(6);
	}

	@Override
	public void preRender(GameManager gm, StateManager game) {
	}

	@Override
	public void postRender(GameManager gm, StateManager game) {
		Shader.fadeShader.bind();
		Shader.fadeShader.setUniform1f("time", time);
		Shader.fadeShader.setUniform4f("color", fadeColor);
		fadeMesh.render();
		Shader.fadeShader.unbind();
	}

	@Override
	public void update(GameManager gm, StateManager game) {
		switch (this.fadeType) {
		case 1:
			if (time <= 1f) {
				time += fadeSpeed;
			} else {
				isComplete = true;
			}
			break;
		default:
			if (time >= 0f) {
				time -= fadeSpeed;
			} else {
				isComplete = true;
			}
			break;
		}
	}

	@Override
	public boolean isComplete() {
		return isComplete;
	}

}