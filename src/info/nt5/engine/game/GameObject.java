package info.nt5.engine.game;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;

import java.nio.ByteBuffer;

import info.nt5.engine.graphics.Color;
import info.nt5.engine.graphics.Texture;
import info.nt5.engine.graphics.shader.Shader;
import info.nt5.engine.graphics.shader.VertexArray;
import info.nt5.engine.graphics.shader.VertexQuad;
import info.nt5.engine.math.Matrix4f;
import info.nt5.engine.math.Vector3f;

public class GameObject {

	public VertexArray VAO;
	public Texture texture;
	public float[] vertices, texCoords;
	public byte[] indices;

	public Vector3f position;

	public float delta = 0.01f;

	public GameObject(VertexQuad quad, String texPath) {
		this(quad.getVertices(), quad.getIndices(), quad.getTexCoords(), texPath);
	}

	public GameObject(VertexQuad quad, String texPath, Vector3f position) {
		this(quad.getVertices(), quad.getIndices(), quad.getTexCoords(), texPath, position);
	}

	public GameObject(VertexQuad quad, Color color) {
		this(quad.getVertices(), quad.getIndices(), quad.getTexCoords(), color);
	}

	public GameObject(VertexQuad quad, Color color, Vector3f position) {
		this(quad.getVertices(), quad.getIndices(), quad.getTexCoords(), color, position);
	}

	public GameObject(VertexQuad quad, ByteBuffer buffer) {
		this(quad.getVertices(), quad.getIndices(), quad.getTexCoords(), buffer);
	}

	public GameObject(VertexQuad quad, ByteBuffer buffer, Vector3f position) {
		this(quad.getVertices(), quad.getIndices(), quad.getTexCoords(), buffer, position);
	}

	public GameObject(VertexQuad quad, Texture texture) {
		this(quad.getVertices(), quad.getIndices(), quad.getTexCoords(), texture);
	}

	public GameObject(VertexQuad quad, Texture texture, Vector3f position) {
		this(quad.getVertices(), quad.getIndices(), quad.getTexCoords(), texture, position);
	}

	public GameObject(float[] vertices, byte[] indices, float[] texCoords, String texPath) {
		this(vertices, indices, texCoords, Texture.fromImage(texPath));
	}

	public GameObject(float[] vertices, byte[] indices, float[] texCoords, String texPath, Vector3f position) {
		this(vertices, indices, texCoords, Texture.fromImage(texPath), position);
	}

	public GameObject(float[] vertices, byte[] indices, float[] texCoords, Color color) {
		this(vertices, indices, texCoords, Texture.fromColor(color, 64, 64));
	}

	public GameObject(float[] vertices, byte[] indices, float[] texCoords, Color color, Vector3f position) {
		this(vertices, indices, texCoords, Texture.fromColor(color, 64, 64), position);
	}

	public GameObject(float[] vertices, byte[] indices, float[] texCoords, ByteBuffer buffer) {
		this(vertices, indices, texCoords, Texture.fromByteBuffer(buffer, 64, 64, 4));
	}

	public GameObject(float[] vertices, byte[] indices, float[] texCoords, ByteBuffer buffer, Vector3f position) {
		this(vertices, indices, texCoords, Texture.fromByteBuffer(buffer, 64, 64, 4), position);
	}

	public GameObject(float[] vertices, byte[] indices, float[] texCoords, Texture texture) {
		this(vertices, indices, texCoords, texture, new Vector3f(0f, 0f, 0f));
	}

	public GameObject(float[] vertices, byte[] indices, float[] texCoords, Texture texture, Vector3f position) {
		this.vertices = vertices;
		this.indices = indices;
		this.texCoords = texCoords;
		this.position = position;

		this.texture = texture;
		this.VAO = new VertexArray(this.vertices, this.indices, this.texCoords);
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

	public void sinUpdate() {
		position.y += (float) Math.sin(delta) / 105.0f;
	}

	public void render() {
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		texture.bind();
		Shader.geometryShader.bind();
		Shader.geometryShader.setUniformMat4f("ml_matrix", Matrix4f.translate(position));
		VAO.render();
		VAO.unbind();
		Shader.geometryShader.unbind();
		texture.unbind();
		glDisable(GL_BLEND);
	}

	public void update() {

	}

	public void dispose() {
		this.texture.dispose();
		this.VAO.dispose();
	}
}
