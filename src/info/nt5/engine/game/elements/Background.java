package info.nt5.engine.game.elements;

import info.nt5.engine.game.GameObject;
import info.nt5.engine.graphics.Color;
import info.nt5.engine.graphics.Texture;
import info.nt5.engine.graphics.shader.VertexQuad;

public class Background extends GameObject {

	private static String texturePath = "assets/img/background.jpg";

	private static float width = 10.0f;
	private static float height = 10.0f * 9.0f / 16.0f;

	public Background() {
		this(width, height, texturePath);
	}

	public Background(String texturePath) {
		this(width, height, texturePath);
	}

	public Background(String texturePath, float wd) {
		this(texturePath, wd, wd);
	}

	public Background(String texturePath, float width, float height) {
		this(width, height, texturePath);
	}

	public Background(Color color) {
		this(width, height, color);
	}

	public Background(Color color, float wd) {
		this(color, wd, wd);
	}

	public Background(Color color, float width, float height) {
		this(width, height, color);
	}

	public Background(float wd) {
		this(wd, wd);
	}

	public Background(float width, float height) {
		this(width, height, texturePath);
	}

	public Background(float width, float height, String texturePath) {
		this(width, height, Texture.fromImage(texturePath));
	}

	public Background(float width, float height, Color color) {
		this(width, height, Texture.fromColor(color, 64, 64));
	}

	public Background(float width, float height, Texture texture) {
		this(new VertexQuad(width, height), texture);
	}

	public Background(VertexQuad quad, Texture texture) {
		super(quad, texture);
	}
}
