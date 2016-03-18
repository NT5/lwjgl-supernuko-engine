package info.nt5.engine.game.elements;

import info.nt5.engine.game.GameObject;
import info.nt5.engine.graphics.Color;
import info.nt5.engine.graphics.Texture;
import info.nt5.engine.graphics.shader.VertexQuad;
import info.nt5.engine.math.Vector3f;

public class Actor extends GameObject {

	private static String defaultTexturePath = "assets/img/neko.png";

	private static final float defaultwidth = 3.3f;
	private static final float defaultheight = 6f;

	public Actor() {
		this(defaultwidth, defaultheight, defaultTexturePath);
	}

	public Actor(Vector3f position) {
		this(defaultwidth, defaultheight, defaultTexturePath, position);
	}

	public Actor(String texturePath) {
		this(defaultwidth, defaultheight, texturePath);
	}

	public Actor(String texturePath, Vector3f position) {
		this(defaultwidth, defaultheight, texturePath, position);
	}

	public Actor(String texturePath, float wd) {
		this(texturePath, wd, wd);
	}

	public Actor(String texturePath, float wd, Vector3f position) {
		this(texturePath, wd, wd, position);
	}

	public Actor(String texturePath, float width, float height) {
		this(width, height, texturePath);
	}

	public Actor(String texturePath, float width, float height, Vector3f position) {
		this(width, height, texturePath, position);
	}

	public Actor(Color color) {
		this(defaultwidth, defaultheight, color);
	}

	public Actor(Color color, Vector3f position) {
		this(defaultwidth, defaultheight, color, position);
	}

	public Actor(Color color, float wd) {
		this(color, wd, wd);
	}

	public Actor(Color color, float wd, Vector3f position) {
		this(color, wd, wd, position);
	}

	public Actor(Color color, float width, float height) {
		this(width, height, color);
	}

	public Actor(Color color, float width, float height, Vector3f position) {
		this(width, height, color, position);
	}

	public Actor(float wd) {
		this(wd, wd);
	}

	public Actor(float wd, Vector3f position) {
		this(wd, wd, position);
	}

	public Actor(float width, float height) {
		this(width, height, defaultTexturePath);
	}

	public Actor(float width, float height, Vector3f position) {
		this(width, height, defaultTexturePath, position);
	}

	public Actor(float width, float height, String texturePath) {
		this(width, height, Texture.fromImage(texturePath));
	}

	public Actor(float width, float height, String texturePath, Vector3f position) {
		this(width, height, Texture.fromImage(texturePath), position);
	}

	public Actor(float width, float height, Color color) {
		this(width, height, Texture.fromColor(color, 64, 64));
	}

	public Actor(float width, float height, Color color, Vector3f position) {
		this(width, height, Texture.fromColor(color, 64, 64), position);
	}

	public Actor(float width, float height, Texture texture) {
		this(new VertexQuad(width, height), texture);
	}

	public Actor(float width, float height, Texture texture, Vector3f position) {
		this(new VertexQuad(width, height), texture, position);
	}

	public Actor(VertexQuad quad, Texture texture) {
		this(quad, texture, new Vector3f(0f, 0f, 0f));
	}

	public Actor(VertexQuad quad, Texture texture, Vector3f position) {
		super(quad, texture, position);
	}

}
