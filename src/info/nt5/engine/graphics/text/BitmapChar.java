package info.nt5.engine.graphics.text;

import static org.lwjgl.opengl.GL11.*;

import info.nt5.engine.graphics.SubTexture;
import info.nt5.engine.graphics.Texture;
import info.nt5.engine.graphics.TextureAtlas;
import info.nt5.engine.graphics.shader.Shader;
import info.nt5.engine.graphics.shader.VertexArray;
import info.nt5.engine.math.Matrix4f;
import info.nt5.engine.math.Vector3f;

public class BitmapChar {
	private static final int gridSize = 16;
	private static final int cellSize = 32;

	private static final float defaultwidth = 0.2f;
	private static final float defaultheight = 0.2f;
	private static final String defaultFontPath = "assets/img/font.png";

	private int asciiCode;
	private float width;
	private float height;
	private Texture texture;
	private TextureAtlas textureAtlas;
	private SubTexture subTexture;

	private VertexArray VAO;
	public Vector3f position;

	public BitmapChar(int ascii) {
		this(ascii, defaultwidth, defaultheight);
	}

	public BitmapChar(int ascii, Vector3f position) {
		this(ascii, defaultwidth, defaultheight, position);
	}

	public BitmapChar(int ascii, float wd) {
		this(ascii, wd, wd);
	}

	public BitmapChar(int ascii, float wd, Vector3f position) {
		this(ascii, wd, wd, position);
	}

	public BitmapChar(int ascii, float width, float height) {
		this(ascii, width, height, defaultFontPath);
	}

	public BitmapChar(int ascii, float width, float height, Vector3f position) {
		this(ascii, width, height, defaultFontPath, position);
	}

	public BitmapChar(int ascii, float width, float height, String texturePath) {
		this(ascii, width, height, Texture.fromImage(texturePath));
	}

	public BitmapChar(int ascii, float width, float height, String texturePath, Vector3f position) {
		this(ascii, width, height, Texture.fromImage(texturePath), position);
	}

	public BitmapChar(int ascii, float width, float height, Texture texture) {
		this(ascii, width, height, texture, new Vector3f());
	}

	public BitmapChar(int ascii, float width, float height, Texture texture, Vector3f position) {

		this.texture = texture;
		this.textureAtlas = new TextureAtlas(this.texture, BitmapChar.cellSize);
		this.position = position;

		this.asciiCode = ascii;
		this.width = width;
		this.height = height;

		int cellX = (int) asciiCode % BitmapChar.gridSize;
		int cellY = (int) asciiCode / BitmapChar.gridSize;

		float[] vertices = { -this.width, this.height, 0f, -this.width, -this.height, 0f, this.width, -this.height, 0f,
				this.width, this.height, 0f };

		byte[] indices = { 0, 1, 2, 2, 3, 0 };

		float[] texCoords = { 0, 1, 0, 0, 1, 0, 1, 1 };

		subTexture = textureAtlas.getCell(cellY, cellX);

		texCoords[0] = subTexture.getMinU();
		texCoords[1] = subTexture.getMinV();

		texCoords[2] = subTexture.getMinU();
		texCoords[3] = subTexture.getMaxV();

		texCoords[4] = subTexture.getMaxU();
		texCoords[5] = subTexture.getMaxV();

		texCoords[6] = subTexture.getMaxU();
		texCoords[7] = subTexture.getMinV();

		VAO = new VertexArray(vertices, indices, texCoords);
	}

	public void translate(Vector3f vector) {
		position.x += vector.x;
		position.y += vector.y;
		position.z += vector.z;
	}

	public void translateX(float x) {
		position.x += x;
	}

	public void translateY(float y) {
		position.y += y;
	}

	public void translateZ(float z) {
		position.z += z;
	}

	public void render() {
		glEnable(GL_BLEND);
		glBlendFunc(GL_ONE, GL_ONE);
		texture.bind();
		Shader.defaultShader.bind();
		Shader.defaultShader.setUniformMat4f("ml_matrix", Matrix4f.translate(position));
		VAO.render();
		Shader.defaultShader.unbind();
		texture.unbind();
		glDisable(GL_BLEND);
	}

	public void dispose() {
		textureAtlas.dispose();
		texture.dispose();
	}

	public float getWidth() {
		return this.width;
	}

	public float getHeight() {
		return this.height;
	}

	public int getAsciiCode() {
		return asciiCode;
	}

	public static String getDefaultFontPath() {
		return defaultFontPath;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public void setHeight(float height) {
		this.height = height;
	}
}
