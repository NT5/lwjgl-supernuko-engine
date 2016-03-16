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
	private final int gridSize = 16;
	private final int cellSize = 32;

	private static float width = 0.2f;
	private static float height = 0.2f;

	private int asciiCode;

	private static String defaultFontPath = "assets/img/font.png";

	private Texture texture;
	private TextureAtlas textureAtlas;
	private SubTexture subTexture;

	private VertexArray VAO;
	public Vector3f position = new Vector3f();

	public BitmapChar(int ascii) {
		this(ascii, width, height);
	}

	public BitmapChar(int ascii, float wd) {
		this(ascii, wd, wd);
	}

	public BitmapChar(int ascii, float width, float height) {
		this(ascii, width, height, defaultFontPath);
	}
	
	public BitmapChar(int ascii, float width, float height, String texturePath) {
		this(ascii, width, height, Texture.fromImage(texturePath));
	}
	
	public BitmapChar(int ascii, float width, float height, Texture texture) {

		this.texture = texture;
		textureAtlas = new TextureAtlas(this.texture, this.cellSize);

		this.asciiCode = ascii;
		BitmapChar.width = width;
		BitmapChar.height = height;

		int cellX = (int) asciiCode % this.gridSize;
		int cellY = (int) asciiCode / this.gridSize;

		float[] vertices = { -width, height, 0f, -width, -height, 0f, width, -height, 0f, width, height, 0f };

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

	public static float getWidth() {
		return BitmapChar.width;
	}

	public static float getHeight() {
		return BitmapChar.height;
	}

	public int getAsciiCode() {
		return asciiCode;
	}
	
	public static String getDefaultFontPath() {
		return defaultFontPath;
	}

	public static void setWidth(float width) {
		BitmapChar.width = width;
	}

	public static void setHeight(float height) {
		BitmapChar.height = height;
	}
}
