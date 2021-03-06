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

	private Fade fadeType;

	private float fadeSpeed = 0.01f;
	private float time;

	private boolean isComplete = false;

	public enum Fade {
		IN, OUT;
	}

	public FadeTransition() {
		this.setFadeTimeType();
	}

	public FadeTransition(Fade fadeType, float fadeSpeed) {
		this.fadeType = fadeType;
		this.fadeSpeed = fadeSpeed;

		this.setFadeTimeType();
	}

	public FadeTransition(Color fadeColor, float fadeSpeed) {
		this.fadeColor = fadeColor;
		this.fadeSpeed = fadeSpeed;

		this.setFadeTimeType();
	}

	public FadeTransition(Fade fadeType, Color fadeColor, float fadeSpeed) {
		this.fadeType = fadeType;
		this.fadeColor = fadeColor;
		this.fadeSpeed = fadeSpeed;

		this.setFadeTimeType();
	}

	private void setFadeTimeType() {
		switch (this.fadeType) {
		case IN:
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
		case IN:
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