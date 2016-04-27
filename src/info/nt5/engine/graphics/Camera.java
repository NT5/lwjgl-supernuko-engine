package info.nt5.engine.graphics;

import java.util.ArrayList;
import java.util.List;

import info.nt5.engine.graphics.shader.Shader;
import info.nt5.engine.math.Matrix4f;
import info.nt5.engine.math.Vector3f;

public class Camera {
	private Matrix4f vw_matrix;
	public Vector3f position = new Vector3f();
	public Vector3f scale = new Vector3f(1f);

	public float pitch;
	public float yaw;
	public float roll;

	private List<Shader> shaderList = new ArrayList<Shader>();

	public static Camera defaultCam = new Camera();

	public Camera() {
		this.vw_matrix = Matrix4f.translate(position)
				.multiply(Matrix4f.rotateZ(roll).multiply(Matrix4f.rotateY(yaw)).multiply(Matrix4f.rotateX(pitch)))
				.multiply(Matrix4f.scale(scale));

		shaderList.add(Shader.geometryShader);
		shaderList.add(Shader.textShader);
	}

	public void setPosition(Vector3f pos) {
		this.position = pos;
	}

	public void setScale(Vector3f scale) {
		this.scale = scale;
	}

	public void setPitch(float pitch) {
		this.pitch = pitch;
	}

	public void setYaw(float yaw) {
		this.yaw = yaw;
	}

	public void setRoll(float roll) {
		this.roll = roll;
	}

	public Vector3f getPosition() {
		return this.position;
	}

	public Vector3f getScale() {
		return this.scale;
	}

	public float getPitch() {
		return this.pitch;
	}

	public float getYaw() {
		return this.yaw;
	}

	public float getRoll() {
		return this.roll;
	}

	public void update() {
		this.vw_matrix = Matrix4f.translate(position)
				.multiply(Matrix4f.rotateZ(roll).multiply(Matrix4f.rotateY(yaw)).multiply(Matrix4f.rotateX(pitch)))
				.multiply(Matrix4f.scale(scale));
	}

	public void render() {
		shaderList.stream().forEach((shader) -> {
			shader.bind();
			shader.setUniformMat4f("vw_matrix", vw_matrix);
			shader.unbind();
		});
	}
}
