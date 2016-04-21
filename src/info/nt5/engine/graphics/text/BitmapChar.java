package info.nt5.engine.graphics.text;

import info.nt5.engine.graphics.Color;
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
	private static final Color defaultColor = Color.WHITE;
	private static final String defaultFontPath = "assets/img/font.png";

	private int asciiCode;
	private float width;
	private float height;
	private Color color;
	private boolean bold;

	private Texture texture;
	private TextureAtlas textureAtlas;
	private SubTexture subTexture;

	private VertexArray VAO;
	public Vector3f position;

	public BitmapChar(int ascii) {
		this(ascii, defaultwidth, defaultheight);
	}

	public BitmapChar(int ascii, boolean bold) {
		this(ascii, defaultwidth, defaultheight, bold);
	}

	public BitmapChar(int ascii, Color color) {
		this(ascii, defaultwidth, defaultheight, color);
	}

	public BitmapChar(int ascii, Color color, boolean bold) {
		this(ascii, defaultwidth, defaultheight, color, bold);
	}

	public BitmapChar(int ascii, Vector3f position) {
		this(ascii, defaultwidth, defaultheight, position);
	}

	public BitmapChar(int ascii, Vector3f position, boolean bold) {
		this(ascii, defaultwidth, defaultheight, position, bold);
	}

	public BitmapChar(int ascii, Vector3f position, Color color) {
		this(ascii, defaultwidth, defaultheight, position, color);
	}

	public BitmapChar(int ascii, Vector3f position, Color color, boolean bold) {
		this(ascii, defaultwidth, defaultheight, position, color, bold);
	}

	public BitmapChar(int ascii, float wd) {
		this(ascii, wd, wd);
	}

	public BitmapChar(int ascii, float wd, boolean bold) {
		this(ascii, wd, wd, bold);
	}

	public BitmapChar(int ascii, float wd, Color color) {
		this(ascii, wd, wd, color);
	}

	public BitmapChar(int ascii, float wd, Color color, boolean bold) {
		this(ascii, wd, wd, color, bold);
	}

	public BitmapChar(int ascii, float wd, Vector3f position) {
		this(ascii, wd, wd, position);
	}

	public BitmapChar(int ascii, float wd, Vector3f position, boolean bold) {
		this(ascii, wd, wd, position, bold);
	}

	public BitmapChar(int ascii, float wd, Vector3f position, Color color) {
		this(ascii, wd, wd, position, color);
	}

	public BitmapChar(int ascii, float wd, Vector3f position, Color color, boolean bold) {
		this(ascii, wd, wd, position, color, bold);
	}

	public BitmapChar(int ascii, float width, float height) {
		this(ascii, width, height, defaultFontPath);
	}

	public BitmapChar(int ascii, float width, float height, boolean bold) {
		this(ascii, width, height, defaultFontPath, bold);
	}

	public BitmapChar(int ascii, float width, float height, Color color) {
		this(ascii, width, height, defaultFontPath, color);
	}

	public BitmapChar(int ascii, float width, float height, Color color, boolean bold) {
		this(ascii, width, height, defaultFontPath, color, bold);
	}

	public BitmapChar(int ascii, float width, float height, Vector3f position) {
		this(ascii, width, height, defaultFontPath, position);
	}

	public BitmapChar(int ascii, float width, float height, Vector3f position, boolean bold) {
		this(ascii, width, height, defaultFontPath, position, bold);
	}

	public BitmapChar(int ascii, float width, float height, Vector3f position, Color color) {
		this(ascii, width, height, defaultFontPath, position, color);
	}

	public BitmapChar(int ascii, float width, float height, Vector3f position, Color color, boolean bold) {
		this(ascii, width, height, defaultFontPath, position, color, bold);
	}

	public BitmapChar(int ascii, float width, float height, String texturePath) {
		this(ascii, width, height, Texture.fromImage(texturePath));
	}

	public BitmapChar(int ascii, float width, float height, String texturePath, boolean bold) {
		this(ascii, width, height, Texture.fromImage(texturePath), bold);
	}

	public BitmapChar(int ascii, float width, float height, String texturePath, Color color) {
		this(ascii, width, height, Texture.fromImage(texturePath), color);
	}

	public BitmapChar(int ascii, float width, float height, String texturePath, Color color, boolean bold) {
		this(ascii, width, height, Texture.fromImage(texturePath), color, bold);
	}

	public BitmapChar(int ascii, float width, float height, String texturePath, Vector3f position) {
		this(ascii, width, height, Texture.fromImage(texturePath), position);
	}

	public BitmapChar(int ascii, float width, float height, String texturePath, Vector3f position, boolean bold) {
		this(ascii, width, height, Texture.fromImage(texturePath), position, bold);
	}

	public BitmapChar(int ascii, float width, float height, String texturePath, Vector3f position, Color color) {
		this(ascii, width, height, Texture.fromImage(texturePath), position, color);
	}

	public BitmapChar(int ascii, float width, float height, String texturePath, Vector3f position, Color color,
			boolean bold) {
		this(ascii, width, height, Texture.fromImage(texturePath), position, color, bold);
	}

	public BitmapChar(int ascii, float width, float height, Texture texture) {
		this(ascii, width, height, texture, new Vector3f());
	}

	public BitmapChar(int ascii, float width, float height, Texture texture, boolean bold) {
		this(ascii, width, height, texture, new Vector3f(), bold);
	}

	public BitmapChar(int ascii, float width, float height, Texture texture, Color color) {
		this(ascii, width, height, texture, new Vector3f(), color);
	}

	public BitmapChar(int ascii, float width, float height, Texture texture, Color color, boolean bold) {
		this(ascii, width, height, texture, new Vector3f(), color, bold);
	}

	public BitmapChar(int ascii, float width, float height, Texture texture, Vector3f position) {
		this(ascii, width, height, texture, new Vector3f(), defaultColor);
	}

	public BitmapChar(int ascii, float width, float height, Texture texture, Vector3f position, boolean bold) {
		this(ascii, width, height, texture, new Vector3f(), defaultColor, bold);
	}

	public BitmapChar(int ascii, float width, float height, Texture texture, Vector3f position, Color color) {
		this(ascii, width, height, texture, position, color, false);
	}

	public BitmapChar(int ascii, float width, float height, Texture texture, Vector3f position, Color color,
			boolean bold) {

		this.texture = texture;
		this.textureAtlas = new TextureAtlas(this.texture, BitmapChar.cellSize);
		this.position = position;

		this.asciiCode = ascii;
		this.width = width;
		this.height = height;
		this.color = color;
		this.bold = bold;

		int cellX = (int) asciiCode % BitmapChar.gridSize;
		int cellY = (int) asciiCode / BitmapChar.gridSize;

		float[] vertices = {

				-this.width, this.height, 0f,

				-this.width, -this.height, 0f,

				this.width, -this.height, 0f,

				this.width, this.height, 0f

		};

		byte[] indices = {

				0, 1, 3,

				1, 2, 3

		};

		subTexture = textureAtlas.getCell(cellY, cellX);

		float[] texCoords = new float[] {

				subTexture.getMinU(), subTexture.getMinV(),

				subTexture.getMinU(), subTexture.getMaxV(),

				subTexture.getMaxU(), subTexture.getMaxV(),

				subTexture.getMaxU(), subTexture.getMinV()

		};

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
		texture.bind();
		Shader.textShader.bind();
		Shader.textShader.setUniformMat4f("ml_matrix", Matrix4f.translate(position));
		Shader.textShader.setUniform4f("vColor", color);
		VAO.render();
		if (this.bold) {
			this.position.x += 0.02f;
			Shader.textShader.setUniformMat4f("ml_matrix", Matrix4f.translate(position));
			VAO.render();
			this.position.x -= 0.02f;
		}
		Shader.textShader.unbind();
		VAO.unbind();
		texture.unbind();
	}

	public void dispose() {
		textureAtlas.dispose();
		texture.dispose();
		VAO.dispose();
	}

	public VertexArray getVAO() {
		return this.VAO;
	}

	public TextureAtlas getTextureAtlas() {
		return textureAtlas;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return this.color;
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
