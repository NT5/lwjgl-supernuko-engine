package info.nt5.engine.game.elements;

import info.nt5.engine.graphics.Texture;
import info.nt5.engine.graphics.shader.Mesh;
import info.nt5.engine.graphics.shader.Shader;
import info.nt5.engine.graphics.shader.VertexQuad;
import info.nt5.engine.math.Matrix4f;
import info.nt5.engine.math.Vector3f;

public class GameObject {

	public Mesh mesh;
	public Texture texture;
	public float[] vertices, texCoords, normals;
	public int[] indices;

	public Vector3f position;

	public float delta = 0.01f;

	public GameObject(VertexQuad quad, Texture texture, Vector3f position) {
		this(quad.getVertices(), quad.getIndices(), quad.getTexCoords(), quad.getNormals(), texture, position);
	}

	public GameObject(float[] vertices, int[] indices, float[] texCoords, float[] normals, Texture texture,
			Vector3f position) {
		this.vertices = vertices;
		this.indices = indices;
		this.texCoords = texCoords;
		this.position = position;
		this.normals = normals;

		this.texture = texture;
		this.mesh = new Mesh(this.vertices, this.texCoords, this.normals, this.indices);
	}

	public void setPosition(Vector3f position) {
		this.position = position;
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
		texture.bind();
		Shader.geometryShader.bind();
		Shader.geometryShader.setUniformMat4f("ml_matrix", Matrix4f.translate(position));
		mesh.render();
		Shader.geometryShader.unbind();
		texture.unbind();
	}

	public void update() {

	}

	public void dispose() {
		this.texture.dispose();
		this.mesh.dispose();
	}
}
