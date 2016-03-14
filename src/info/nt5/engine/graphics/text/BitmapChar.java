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

	private float width = 0.2f;
	private float height = 0.2f;

	private String texturePath = "assets/img/font.png";

	private Texture texture;
	private TextureAtlas textureAtlas;
	private SubTexture subTexture;

	private VertexArray VAO;
	public Vector3f position = new Vector3f();

	private float[] vertices = { -width, height, 0f, -width, -height, 0f, width, -height, 0f, width, height, 0f, };

	private byte[] indices = { 0, 1, 2, 2, 3, 0 };

	private float[] texCoords = { 0, 1, 0, 0, 1, 0, 1, 1 };

	public BitmapChar(int ascii) {

		this.texture = Texture.fromImage(this.texturePath);
		this.textureAtlas = new TextureAtlas(this.texture, this.cellSize);

		int asciiCode = ascii;

		int cellX = (int) asciiCode % this.gridSize;
		int cellY = (int) asciiCode / this.gridSize;

		subTexture = textureAtlas.getCell(cellY, cellX);

		texCoords[0] = subTexture.getMinU();
		texCoords[1] = subTexture.getMinV();

		texCoords[2] = subTexture.getMinU();
		texCoords[3] = subTexture.getMaxV();

		texCoords[4] = subTexture.getMaxU();
		texCoords[5] = subTexture.getMaxV();

		texCoords[6] = subTexture.getMaxU();
		texCoords[7] = subTexture.getMinV();

		this.VAO = new VertexArray(this.vertices, this.indices, this.texCoords);
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
}
