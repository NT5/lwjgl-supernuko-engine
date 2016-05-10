package info.nt5.engine.game.elements;

import info.nt5.engine.graphics.Texture;
import info.nt5.engine.graphics.shader.Mesh;
import info.nt5.engine.graphics.shader.Shader;
import info.nt5.engine.graphics.shader.VertexQuad;
import info.nt5.engine.math.Matrix4f;
import info.nt5.engine.math.Vector3f;

public class GameObject {

	public Mesh mesh;
	public VertexQuad quad;

	public Texture texture;

	public Vector3f position;

	public GameObject(float[] vertices, int[] indices, float[] texCoords, float[] normals, Texture texture,
			Vector3f position) {
		this(new VertexQuad(vertices, indices, texCoords, normals), texture, position);
	}

	public GameObject(VertexQuad quad, Texture texture, Vector3f position) {
		this.quad = quad;
		this.position = position;
		this.texture = texture;

		this.mesh = new Mesh(

				this.quad.getVertices(),

				this.quad.getTexCoords(),

				this.quad.getNormals(),

				this.quad.getIndices()

		);
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
