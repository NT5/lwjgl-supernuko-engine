package info.nt5.engine.game.elements;

import info.nt5.engine.graphics.Color;
import info.nt5.engine.graphics.Texture;
import info.nt5.engine.graphics.shader.VertexQuad;

public class Background extends GameObject {

	private static final String defaultTexturePath = "assets/img/background.jpg";

	private static final float defaultwidth = 10.0f;
	private static final float defaultheight = 10.0f * 9.0f / 16.0f;

	public Background() {
		this(defaultTexturePath, defaultwidth, defaultheight);
	}

	public Background(String texturePath) {
		this(texturePath, defaultwidth, defaultheight);
	}

	public Background(String texturePath, float wd) {
		this(texturePath, wd, wd);
	}

	public Background(String texturePath, float width, float height) {
		this(Texture.fromImage(texturePath), width, height);
	}

	public Background(Color color) {
		this(color, defaultwidth, defaultheight);
	}

	public Background(Color color, float wd) {
		this(color, wd, wd);
	}

	public Background(Color color, float width, float height) {
		this(Texture.fromColor(color, 64, 64), width, height);
	}

	public Background(float wd) {
		this(wd, wd);
	}

	public Background(float width, float height) {
		this(defaultTexturePath, width, height);
	}

	public Background(Texture texture, float width, float height) {
		this(new VertexQuad(width, height), texture);
	}

	public Background(VertexQuad quad, Texture texture) {
		super(quad, texture);
	}
}
