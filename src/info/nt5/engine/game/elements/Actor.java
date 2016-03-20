package info.nt5.engine.game.elements;

import info.nt5.engine.game.GameObject;
import info.nt5.engine.graphics.Color;
import info.nt5.engine.graphics.Texture;
import info.nt5.engine.graphics.shader.VertexQuad;
import info.nt5.engine.math.Vector3f;

public class Actor extends GameObject {

	private static final String defaultTexturePath = "assets/img/neko.png";

	protected static final float defaultwidth = 3.3f;
	protected static final float defaultheight = 6f;
	protected static final Vector3f defaultPosition = new Vector3f();

	public Actor() {
		this(defaultwidth, defaultheight, defaultPosition.copy());
	}

	public Actor(String texturePath) {
		this(texturePath, defaultPosition.copy());
	}

	public Actor(String texturePath, Vector3f position) {
		this(texturePath, defaultwidth, defaultheight, position);
	}

	public Actor(String texturePath, float wd) {
		this(texturePath, wd, defaultPosition.copy());
	}

	public Actor(String texturePath, float wd, Vector3f position) {
		this(texturePath, wd, wd, position);
	}

	public Actor(String texturePath, float width, float height) {
		this(texturePath, width, height, defaultPosition.copy());
	}

	public Actor(String texturePath, float width, float height, Vector3f position) {
		this(Texture.fromImage(texturePath), width, height, position);
	}

	public Actor(Color color) {
		this(color, defaultPosition.copy());
	}

	public Actor(Color color, Vector3f position) {
		this(color, defaultwidth, defaultheight, position);
	}

	public Actor(Color color, float wd) {
		this(color, wd, defaultPosition.copy());
	}

	public Actor(Color color, float wd, Vector3f position) {
		this(color, wd, wd, position);
	}

	public Actor(Color color, float width, float height) {
		this(color, width, height, defaultPosition.copy());
	}

	public Actor(Color color, float width, float height, Vector3f position) {
		this(Texture.fromColor(color, 64, 64), width, height, position);
	}

	public Actor(Texture texture) {
		this(texture, defaultPosition.copy());
	}

	public Actor(Texture texture, Vector3f position) {
		this(texture, defaultwidth, defaultheight, position);
	}

	public Actor(Texture texture, float wd) {
		this(texture, wd, defaultPosition.copy());
	}

	public Actor(Texture texture, float wd, Vector3f position) {
		this(texture, wd, wd, position);
	}

	public Actor(Texture texture, float width, float height) {
		this(texture, width, height, defaultPosition.copy());
	}

	public Actor(Texture texture, float width, float height, Vector3f position) {
		this(new VertexQuad(width, height), texture, position);
	}

	public Actor(Vector3f position) {
		this(defaultwidth, defaultheight, position);
	}

	public Actor(float wd) {
		this(wd, defaultPosition.copy());
	}

	public Actor(float wd, Vector3f position) {
		this(wd, wd, position);
	}

	public Actor(float width, float height) {
		this(new VertexQuad(width, height));
	}

	public Actor(float width, float height, Vector3f position) {
		this(new VertexQuad(width, height), position);
	}

	public Actor(VertexQuad quad) {
		this(quad, defaultPosition.copy());
	}

	public Actor(VertexQuad quad, Vector3f position) {
		this(quad, Texture.fromImage(defaultTexturePath), position);
	}

	public Actor(VertexQuad quad, Texture texture) {
		this(quad, texture, defaultPosition.copy());
	}

	public Actor(VertexQuad quad, Texture texture, Vector3f position) {
		super(quad, texture, position);
	}

}
