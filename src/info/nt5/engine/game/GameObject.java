package info.nt5.engine.game;

import info.nt5.engine.graphics.Texture;
import info.nt5.engine.graphics.shader.Shader;
import info.nt5.engine.graphics.shader.VertexArray;
import info.nt5.engine.math.Matrix4f;
import info.nt5.engine.math.Vector3f;

public class GameObject {

	public VertexArray VAO;
	public Texture tex;
	public float[] vertices, texCoords;
	public byte[] indices;

	public Vector3f position = new Vector3f();

	public float delta = 0.01f;

	public GameObject(float[] vertices, byte[] indices, float[] texCoords, String texPath) {
		this.vertices = vertices;
		this.indices = indices;
		this.texCoords = texCoords;
		tex = Texture.fromImage(texPath);
		VAO = new VertexArray(this.vertices, this.indices, this.texCoords);
	}

	public void translate(Vector3f vector) {
		position.x += vector.x;
		position.y += vector.y;
		position.z += vector.z;
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
