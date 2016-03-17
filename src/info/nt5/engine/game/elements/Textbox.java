package info.nt5.engine.game.elements;

import info.nt5.engine.game.GameObject;
import info.nt5.engine.graphics.Color;
import info.nt5.engine.graphics.Texture;
import info.nt5.engine.graphics.shader.VertexQuad;

public class Textbox extends GameObject {

	private static String texturePath = "assets/img/textbox.png";

	private static float width = 9.8f;
	private static float height = 1.3f;

	public Textbox() {
		this(width, height, texturePath);
	}

	public Textbox(String texturePath) {
		this(width, height, texturePath);
	}

	public Textbox(String texturePath, float wd) {
		this(texturePath, wd, wd);
	}

	public Textbox(String texturePath, float width, float height) {
		this(width, height, texturePath);
	}

	public Textbox(Color color) {
		this(width, height, color);
	}

	public Textbox(Color color, float wd) {
		this(color, wd, wd);
	}

	public Textbox(Color color, float width, float height) {
		this(width, height, color);
	}

	public Textbox(float wd) {
		this(wd, wd);
	}

	public Textbox(float width, float height) {
		this(width, height, texturePath);
	}

	public Textbox(float width, float height, String texturePath) {
		this(width, height, Texture.fromImage(texturePath));
	}

	public Textbox(float width, float height, Color color) {
		this(width, height, Texture.fromColor(color, 64, 64));
	}

	public Textbox(float width, float height, Texture texture) {
		this(new VertexQuad(width, height), texture);
	}

	public Textbox(VertexQuad quad, Texture texture) {
		super(quad, texture);
	}
}
