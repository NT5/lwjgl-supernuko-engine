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

	private static final String defaultTexturePath = "assets/img/textbox.png";

	private static final float defaultwidth = 9.8f;
	private static final float defaultheight = 1.3f;
	private static final Vector3f defaultPosition = new Vector3f();

	private float width;
	private float heigth;

	public BitmapFont text;

	public Textbox() {
		this(defaultwidth, defaultheight, defaultPosition.copy());
	}

	public Textbox(String texturePath) {
		this(texturePath, defaultPosition.copy());
	}

	public Textbox(String texturePath, Vector3f position) {
		this(texturePath, defaultwidth, defaultheight, position);
	}

	public Textbox(String texturePath, float wd) {
		this(texturePath, wd, defaultPosition.copy());
	}

	public Textbox(String texturePath, float wd, Vector3f position) {
		this(texturePath, wd, wd, position);
	}

	public Textbox(String texturePath, float width, float height) {
		this(texturePath, width, height, defaultPosition.copy());
	}

	public Textbox(String texturePath, float width, float height, Vector3f position) {
		this(Texture.fromImage(texturePath), width, height, position);
	}

	public Textbox(Color color) {
		this(color, defaultPosition.copy());
	}

	public Textbox(Color color, Vector3f position) {
		this(color, defaultwidth, defaultheight, position);
	}

	public Textbox(Color color, float wd) {
		this(color, wd, defaultPosition.copy());
	}

	public Textbox(Color color, float wd, Vector3f position) {
		this(color, wd, wd, position);
	}

	public Textbox(Color color, float width, float height) {
		this(color, width, height, defaultPosition.copy());
	}

	public Textbox(Color color, float width, float height, Vector3f position) {
		this(Texture.fromColor(color, 64, 64), width, height, position);
	}

	public Textbox(Texture texture) {
		this(texture, defaultPosition.copy());
	}

	public Textbox(Texture texture, Vector3f position) {
		this(texture, defaultwidth, defaultheight, position);
	}

	public Textbox(Texture texture, float wd) {
		this(texture, wd, defaultPosition.copy());
	}

	public Textbox(Texture texture, float wd, Vector3f position) {
		this(texture, wd, wd, position);
	}

	public Textbox(Texture texture, float width, float height) {
		this(texture, width, height, defaultPosition.copy());
	}

	public Textbox(Texture texture, float width, float height, Vector3f position) {
		this(new VertexQuad(width, height), texture, position);
	}

	public Textbox(Vector3f position) {
		this(defaultwidth, defaultheight, position);
	}

	public Textbox(float wd) {
		this(wd, defaultPosition.copy());
	}

	public Textbox(float wd, Vector3f position) {
		this(wd, wd, position);
	}

	public Textbox(float width, float height) {
		this(new VertexQuad(width, height));
	}

	public Textbox(float width, float height, Vector3f position) {
		this(new VertexQuad(width, height), position);
	}

	public Textbox(VertexQuad quad) {
		this(quad, defaultPosition.copy());
	}

	public Textbox(VertexQuad quad, Vector3f position) {
		this(quad, Texture.fromImage(defaultTexturePath), position);
	}

	public Textbox(VertexQuad quad, Texture texture) {
		this(quad, texture, defaultPosition.copy());
	}

	public Textbox(VertexQuad quad, Texture texture, Vector3f position, String text) {
		super(quad, texture, position);
		this.heigth = quad.height;
		this.width = quad.width;
		this.text = new BitmapFont(text, position);
	}

	public Textbox(VertexQuad quad, Texture texture, Vector3f position) {
		super(quad, texture, position);
		this.heigth = quad.height;
		this.width = quad.width;
		this.text = new BitmapFont("default text", calcFontPosition());
	}

	public Vector3f calcFontPosition() {
		return new Vector3f((position.x - (this.width - 0.30f)), (position.y + (this.heigth - 0.80f)), position.z);
	}

	public void setText(String text) {
		this.text.dispose();
		this.text = new BitmapFont(text, calcFontPosition());
	}

	public void setText(BitmapFont text) {
		this.text.dispose();
		this.text = text;
		this.text.translate(calcFontPosition());
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

		texture.bind();
		Shader.defaultShader.bind();
		Shader.defaultShader.setUniformMat4f("ml_matrix", Matrix4f.translate(position));
		VAO.render();
		Shader.defaultShader.unbind();
		texture.unbind();

		glDisable(GL_BLEND);

		text.render();
	}

	@Override
	public void dispose() {
		texture.dispose();
		text.dispose();
		VAO.dispose();
	}
}
