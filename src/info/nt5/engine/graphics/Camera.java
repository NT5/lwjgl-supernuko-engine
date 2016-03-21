package info.nt5.engine.graphics;

import info.nt5.engine.graphics.shader.Shader;
import info.nt5.engine.math.Matrix4f;
import info.nt5.engine.math.Vector3f;

public class Camera {

	public Matrix4f cameraMat = new Matrix4f();
	public Vector3f position = new Vector3f();

	float rot = 0.0f;
	private float pitch;
	private float yaw;
	private float roll;

	public Camera(Matrix4f cameraMat) {
		this.cameraMat = cameraMat;
	}

	public Matrix4f getMatrix() {
		return cameraMat;
	}

	public Matrix4f getCameraMat() {
		return cameraMat;
	}

	public void setCameraMat(Matrix4f cameraMat) {
		this.cameraMat = cameraMat;
	}

	public void setPosition(Vector3f pos) {
		this.position = pos;
	}

	public Vector3f getPosition() {
		return position;
	}

	public float getRot() {
		return rot;
	}

	public float getPitch() {
		return pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public float getRoll() {
		return roll;
	}

	public void update() {

	}

	public void render() {

		Shader.defaultShader.bind();

		Shader.defaultShader.setUniformMat4f("vw_matrix",
				Matrix4f.translate(new Vector3f(-position.x, -position.y, -position.z)).multiply(
						Matrix4f.rotateZ(roll).multiply(Matrix4f.rotateY(yaw)).multiply(Matrix4f.rotateX(pitch))));

		Shader.defaultShader.unbind();
	}
}
