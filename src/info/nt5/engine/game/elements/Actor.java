package info.nt5.engine.game.elements;

import info.nt5.engine.game.GameObject;
import info.nt5.engine.graphics.shader.VertexQuad;

public class Actor {

	private VertexQuad quad;
	private GameObject object;

	private static String texturePath = "assets/img/neko.png";

	public static float width = 3.3f;
	public static float height = 6f;

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
		quad = new VertexQuad(width, height);
		object = new GameObject(quad.getVertices(), quad.getIndices(), quad.getTexCoords(), texturePath);
	}

	public void render() {
		object.render();
	}

	public GameObject get() {
		return object;
	}
}
