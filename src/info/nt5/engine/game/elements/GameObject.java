package info.nt5.engine.game.elements;

import info.nt5.engine.graphics.Camera;
import info.nt5.engine.graphics.Color;
import info.nt5.engine.graphics.Texture;
import info.nt5.engine.graphics.shader.Mesh;
import info.nt5.engine.graphics.shader.Shader;
import info.nt5.engine.graphics.shader.VertexQuad;
import info.nt5.engine.math.Matrix4f;
import info.nt5.engine.math.Vector3f;

public class GameObject {

	private Mesh mesh;
	private VertexQuad quad;

	private Texture texture;

	private Vector3f position;
	private Vector3f rotation;
	private Vector3f scale;
	private Matrix4f ml_matrix;
	private Camera camera;

	public GameObject() {
		this.quad = new VertexQuad();
		this.texture = Texture.fromColor(Color.WHITE);
		this.mesh = this.setMeshFromQuad(this.quad).getMesh();
		this.position = new Vector3f();
		this.rotation = new Vector3f();
		this.scale = new Vector3f(1f);
		this.ml_matrix = new Matrix4f();
		this.camera = Camera.worldCamera;
	}

	public GameObject(float[] vertices, int[] indices, float[] texCoords, float[] normals, Texture texture,
			Vector3f position) {
		this(new VertexQuad(vertices, indices, texCoords, normals), texture, position);
	}

	public GameObject(VertexQuad quad, Texture texture, Vector3f position) {
		this.quad = quad;
		this.texture = texture;
		this.mesh = new Mesh(

				this.quad.getVertices(),

				this.quad.getTexCoords(),

				this.quad.getNormals(),

				this.quad.getIndices()

		);
		this.position = position;

		this.rotation = new Vector3f();
		this.scale = new Vector3f(1f);
		this.ml_matrix = new Matrix4f();
		this.camera = Camera.worldCamera;
	}

	public Texture getTexture() {
		return texture;
	}

	public Mesh getMesh() {
		return mesh;
	}

	public Camera getCamera() {
		return camera;
	}

	public VertexQuad getQuad() {
		return quad;
	}

	public Vector3f getPosition() {
		return position;
	}

	public Vector3f getScale() {
		return scale;
	}

	public Vector3f getRotation() {
		return rotation;
	}

	public Matrix4f getMl_matrix() {
		return ml_matrix;
	}

	public GameObject setTexture(Texture texture) {
		this.texture = texture;
		return this;
	}

	public GameObject setMesh(Mesh mesh) {
		if (this.mesh != null)
			this.mesh.dispose();
		this.mesh = mesh;
		return this;
	}

	public GameObject setQuad(VertexQuad quad) {
		this.quad = quad;
		this.setMeshFromQuad();
		return this;
	}

	public GameObject setMeshFromQuad() {
		return setMeshFromQuad(this.quad);
	}

	public GameObject setMeshFromQuad(VertexQuad quad) {
		this.quad = quad;
		this.setMesh(new Mesh(quad.getVertices(), quad.getTexCoords(), quad.getNormals(), quad.getIndices()));
		return this;
	}

	public GameObject setPositionX(float x) {
		this.position.x = x;
		return this;
	}

	public GameObject setPositionY(float y) {
		this.position.y = y;
		return this;
	}

	public GameObject setPositionZ(float z) {
		this.position.z = z;
		return this;
	}

	public GameObject setPosition(Vector3f position) {
		this.position = position;
		return this;
	}

	public GameObject setCamera(Camera camera) {
		this.camera = camera;
		return this;
	}

	public GameObject setMl_matrix(Matrix4f ml_matrix) {
		this.ml_matrix = ml_matrix;
		return this;
	}

	public GameObject setScale(Vector3f scale) {
		this.scale = scale;
		return this;
	}

	public GameObject setRotation(Vector3f rotation) {
		this.rotation = rotation;
		return this;
	}

	public GameObject translate(Vector3f vector) {
		position.x += vector.x;
		position.y += vector.y;
		position.z += vector.z;
		return this;
	}

	public GameObject translateX(float x) {
		position.x += x;
		return this;
	}

	public GameObject translateY(float y) {
		position.y += y;
		return this;
	}

	public GameObject translateZ(float z) {
		position.z += z;
		return this;
	}

	public void render() {
		texture.bind();
		Shader.geometryShader.bind();
		Shader.geometryShader.setUniformMat4f("vw_matrix", camera.getViewMatrix());
		Shader.geometryShader.setUniformMat4f("pr_matrix", camera.getProjectionMatrix());
		Shader.geometryShader.setUniformMat4f("ml_matrix", ml_matrix);
		mesh.render();
		Shader.geometryShader.unbind();
		texture.unbind();
	}

	public void update() {
		ml_matrix = Matrix4f.translate(position).multiply(Matrix4f.rotateX(rotation.x)).multiply(
				Matrix4f.rotateY(rotation.y).multiply(Matrix4f.rotateZ(rotation.z)).multiply(Matrix4f.scale(scale)));
	}

	public void disposeTexture() {
		this.texture.dispose();
	}

	public void disposeMesh() {
		this.mesh.dispose();
	}

	public void dispose() {
		this.texture.dispose();
		this.mesh.dispose();
	}
}
