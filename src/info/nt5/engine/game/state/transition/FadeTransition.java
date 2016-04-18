package info.nt5.engine.game.state.transition;

import info.nt5.engine.game.GameManager;
import info.nt5.engine.game.state.State;
import info.nt5.engine.game.state.StateGame;
import info.nt5.engine.graphics.Color;
import info.nt5.engine.graphics.shader.Shader;
import info.nt5.engine.graphics.shader.VertexArray;

public class FadeTransition implements Transition {

	private VertexArray fade;
	private Color fadeColor = Color.BLACK;
	private float time = 0.0f;

	private State next;

	private boolean isComplete = false;
	private boolean isEnterStage = false;

	@Override
	public void init(State current, State next) {
		fade = new VertexArray(6);
		this.next = current;
	}

	@Override
	public void preRender(GameManager gm, StateGame game) {
		if (!isEnterStage) {
			this.next.enter(gm, game);
			isEnterStage = true;
		}
		this.next.render(gm, game);
	}

	@Override
	public void postRender(GameManager gm, StateGame game) {
		Shader.fadeShader.bind();
		Shader.fadeShader.setUniform1f("time", time);
		Shader.fadeShader.setUniform4f("color", fadeColor);
		fade.render();
		Shader.fadeShader.unbind();
	}

	@Override
	public void update(GameManager gm, StateGame game) {
		time += 0.01f;
		if (time >= 1f) {
			isComplete = true;
		}
	}

	@Override
	public boolean isComplete() {
		return isComplete;
	}

}
