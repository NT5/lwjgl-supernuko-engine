package info.nt5.engine.game.elements;

import static org.lwjgl.opengl.GL11.GL_BACK;
import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.glCullFace;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;

import info.nt5.engine.graphics.Camera;
import info.nt5.engine.graphics.Color;
import info.nt5.engine.graphics.Texture;
import info.nt5.engine.graphics.shader.Mesh;
import info.nt5.engine.graphics.shader.ObjLoader;
import info.nt5.engine.graphics.shader.Shader;
import info.nt5.engine.math.Matrix4f;
import info.nt5.engine.math.Vector3f;

public class Model {
	private Texture texture;
	private Mesh mesh;

	public Vector3f rotation = new Vector3f();
	public Vector3f scale = new Vector3f(1f);
	public Vector3f position;
	private Matrix4f ml_matrix;
	private Camera camera;

	public Model(String modelPath, Color color, Vector3f position) {
		this(modelPath, Texture.fromColor(color, 16, 16), position);
	}

	public Model(String modelPath, Texture texture, Vector3f position) {
		this.texture = texture;
		this.position = position;
		this.mesh = ObjLoader.loadMesh(modelPath);

		this.camera = Camera.worldCamera;

		this.update();
	}

	public void render() {
		glEnable(GL_DEPTH_TEST);
		glEnable(GL_CULL_FACE);
		glCullFace(GL_BACK);

		texture.bind();
		Shader.geometryShader.bind();
		Shader.geometryShader.setUniformMat4f("ml_matrix", ml_matrix);
		Shader.geometryShader.setUniformMat4f("vw_matrix", camera.getViewMatrix());
		Shader.geometryShader.setUniformMat4f("pr_matrix", camera.getProjectionMatrix());
		mesh.render();
		Shader.geometryShader.unbind();
		texture.unbind();

		glDisable(GL_DEPTH_TEST);
		glDisable(GL_CULL_FACE);
	}

	public void update() {
		this.ml_matrix = Matrix4f.translate(position).multiply(Matrix4f.rotateX(rotation.x)).multiply(
				Matrix4f.rotateY(rotation.y).multiply(Matrix4f.rotateZ(rotation.z)).multiply(Matrix4f.scale(scale)));
	}

	public void dispose() {
		this.texture.dispose();
		this.mesh.dispose();
	}

	public void setScale(Vector3f scale) {
		this.scale = scale;
	}

	public void setRotation(Vector3f rotation) {
		this.rotation = rotation;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public Mesh getMesh() {
		return this.mesh;
	}
}
