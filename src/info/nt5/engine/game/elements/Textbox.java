package info.nt5.engine.game.elements;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glDisable;

import info.nt5.engine.game.GameObject;
import info.nt5.engine.graphics.Color;
import info.nt5.engine.graphics.Texture;
import info.nt5.engine.graphics.shader.Shader;
import info.nt5.engine.graphics.shader.VertexQuad;
import info.nt5.engine.graphics.text.BitmapFont;
import info.nt5.engine.math.Matrix4f;
import info.nt5.engine.math.Vector3f;

public class Textbox extends GameObject {

	private static final String defaultTexture = "assets/img/textbox.png";

	private static final float defaultwidth = 9.8f;
	private static final float defaultheight = 1.3f;

	private float width;
	private float heigth;

	public BitmapFont text;

	public Textbox() {
		this(defaultwidth, defaultheight, defaultTexture);
	}

	public Textbox(Vector3f position) {
		this(defaultwidth, defaultheight, defaultTexture, position);
	}

	public Textbox(String texturePath) {
		this(defaultwidth, defaultheight, texturePath);
	}

	public Textbox(String texturePath, Vector3f position) {
		this(defaultwidth, defaultheight, texturePath, position);
	}

	public Textbox(String texturePath, float wd) {
		this(texturePath, wd, wd);
	}

	public Textbox(String texturePath, float wd, Vector3f position) {
		this(texturePath, wd, wd, position);
	}

	public Textbox(String texturePath, float width, float height) {
		this(width, height, texturePath);
	}

	public Textbox(String texturePath, float width, float height, Vector3f position) {
		this(width, height, texturePath, position);
	}

	public Textbox(Color color) {
		this(defaultwidth, defaultheight, color);
	}

	public Textbox(Color color, Vector3f position) {
		this(defaultwidth, defaultheight, color, position);
	}

	public Textbox(Color color, float wd) {
		this(color, wd, wd);
	}

	public Textbox(Color color, float wd, Vector3f position) {
		this(color, wd, wd, position);
	}

	public Textbox(Color color, float width, float height) {
		this(width, height, color);
	}

	public Textbox(Color color, float width, float height, Vector3f position) {
		this(width, height, color, position);
	}

	public Textbox(float wd) {
		this(wd, wd);
	}

	public Textbox(float wd, Vector3f position) {
		this(wd, wd, position);
	}

	public Textbox(float width, float height) {
		this(width, height, defaultTexture);
	}

	public Textbox(float width, float height, Vector3f position) {
		this(width, height, defaultTexture, position);
	}

	public Textbox(float width, float height, String texturePath) {
		this(width, height, Texture.fromImage(texturePath));
	}

	public Textbox(float width, float height, String texturePath, Vector3f position) {
		this(width, height, Texture.fromImage(texturePath), position);
	}

	public Textbox(float width, float height, Color color) {
		this(width, height, Texture.fromColor(color, 64, 64));
	}

	public Textbox(float width, float height, Color color, Vector3f position) {
		this(width, height, Texture.fromColor(color, 64, 64), position);
	}

	public Textbox(float width, float height, Texture texture) {
		this(new VertexQuad(width, height), texture);
	}

	public Textbox(float width, float height, Texture texture, Vector3f position) {
		this(new VertexQuad(width, height), texture, position);
	}

	public Textbox(VertexQuad quad, Texture texture) {
		this(quad, texture, new Vector3f(0f, 0f, 0f));
	}

	public Textbox(VertexQuad quad, Texture texture, Vector3f position) {
		super(quad, texture, position);
		this.heigth = quad.height;
		this.width = quad.width;
		this.text = new BitmapFont("", position);
	}

	public void setText(String text) {
		this.text.dispose();
		this.text = new BitmapFont(text,
				new Vector3f((position.x - (this.width - 0.30f)), (position.y + (this.heigth - 0.80f)), position.z));
	}

	public void setText(BitmapFont text) {
		this.text.dispose();
		this.text = text;
		this.text.translate(
				new Vector3f((position.x - (this.width - 0.30f)), (position.y + (this.heigth - 0.80f)), position.z));
	}

	@Override
	public void translate(Vector3f vector) {
		position.x += vector.x;
		position.y += vector.y;
		position.z += vector.z;

		text.translate(vector);
	}

	@Override
	public void translateX(float x) {
		position.x += x;

		text.translateX(x);
	}

	@Override
	public void translateY(float y) {
		position.y += y;

		text.translateY(y);
	}

	@Override
	public void translateZ(float z) {
		position.z += z;

		text.translateZ(z);
	}

	@Override
	public void render() {
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

		tex.bind();
		Shader.defaultShader.bind();
		Shader.defaultShader.setUniformMat4f("ml_matrix", Matrix4f.translate(position));
		VAO.render();
		Shader.defaultShader.unbind();
		tex.unbind();

		glDisable(GL_BLEND);

		text.render();
	}

	@Override
	public void dispose() {
		tex.dispose();

		text.dispose();
	}
}
