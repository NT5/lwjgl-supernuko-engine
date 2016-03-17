package info.nt5.engine.game;

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
	public Texture tex;
	public float[] vertices, texCoords;
	public byte[] indices;

	public Vector3f position = new Vector3f();

	public float delta = 0.01f;

	public GameObject(VertexQuad quad, String texPath) {
		this(quad.getVertices(), quad.getIndices(), quad.getTexCoords(), texPath);
	}

	public GameObject(VertexQuad quad, Color color) {
		this(quad.getVertices(), quad.getIndices(), quad.getTexCoords(), color);
	}

	public GameObject(VertexQuad quad, ByteBuffer buffer) {
		this(quad.getVertices(), quad.getIndices(), quad.getTexCoords(), buffer);
	}

	public GameObject(VertexQuad quad, Texture texture) {
		this(quad.getVertices(), quad.getIndices(), quad.getTexCoords(), texture);
	}

	public GameObject(float[] vertices, byte[] indices, float[] texCoords, String texPath) {
		this(vertices, indices, texCoords, Texture.fromImage(texPath));
	}

	public GameObject(float[] vertices, byte[] indices, float[] texCoords, Color color) {
		this(vertices, indices, texCoords, Texture.fromColor(color, 64, 64));
	}

	public GameObject(float[] vertices, byte[] indices, float[] texCoords, ByteBuffer buffer) {
		this(vertices, indices, texCoords, Texture.fromByteBuffer(buffer, 64, 64, 4));
	}

	public GameObject(float[] vertices, byte[] indices, float[] texCoords, Texture texture) {
		this.vertices = vertices;
		this.indices = indices;
		this.texCoords = texCoords;
		tex = texture;
		VAO = new VertexArray(this.vertices, this.indices, this.texCoords);
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
		tex.bind();
		Shader.defaultShader.bind();
		Shader.defaultShader.setUniformMat4f("ml_matrix", Matrix4f.translate(position));
		VAO.render();
		Shader.defaultShader.unbind();
		tex.unbind();
	}

	public void update() {

	}
}
