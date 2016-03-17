package info.nt5.engine.game.elements;

import info.nt5.engine.game.GameObject;
import info.nt5.engine.graphics.shader.VertexQuad;

public class Actor extends GameObject {

	private static String texturePath = "assets/img/neko.png";

	private static float width = 3.3f;
	private static float height = 6f;

	public Actor() {
		this(width, height, texturePath);
	}

	public Actor(String texturePath) {
		this(width, height, texturePath);
	}

	public Actor(String texturePath, float wd) {
		this(texturePath, wd, wd);
	}

	public Actor(String texturePath, float width, float height) {
		this(width, height, texturePath);
	}

	public Actor(float wd) {
		this(wd, wd);
	}

	public Actor(float width, float height) {
		this(width, height, texturePath);
	}

	public Actor(float width, float height, String texturePath) {
		this(new VertexQuad(width, height), texturePath);
	}

	public Actor(VertexQuad quad, String texturePath) {
		super(quad, texturePath);
	}
}
