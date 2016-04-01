package info.nt5.engine.graphics.shader;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

import info.nt5.engine.graphics.Color;
import info.nt5.engine.math.Matrix4f;
import info.nt5.engine.math.Vector2f;
import info.nt5.engine.math.Vector3f;
import info.nt5.engine.math.Vector4f;
import info.nt5.engine.util.FileUtil;
import info.nt5.engine.util.Logger;

public class Shader {

	private int id;
	private static String vertexSource, fragmentSource;

	public static Shader geometryShader;
	public static Shader textShader;

	private Shader(int id) {
		this.id = id;
	}

	public static Shader fromPath(String vertPath, String fragPath) {
		vertexSource = FileUtil.read(vertPath);
		fragmentSource = FileUtil.read(fragPath);

		return load();
	}

	public static Shader fromSource(String vertSource, String fragSource) {
		vertexSource = vertSource;
		fragmentSource = fragSource;

		return load();
	}

	public static void LoadAllShaders() {
		geometryShader = Shader.fromPath("assets/shaders/geometry.vs", "assets/shaders/geometry.fs");
		textShader = Shader.fromPath("assets/shaders/geometry.vs", "assets/shaders/text.fs");
	}

	private static Shader load() {
		int program = glCreateProgram();
		int vertex = glCreateShader(GL_VERTEX_SHADER);
		int fragment = glCreateShader(GL_FRAGMENT_SHADER);

		glShaderSource(vertex, vertexSource);
		glCompileShader(vertex);

		int compiled = glGetShaderi(vertex, GL_COMPILE_STATUS);

		if (compiled == GL_FALSE) {
			Logger.error("Failed to compile the vertex shader!", glGetShaderInfoLog(vertex));

			glDeleteShader(vertex);
			return null;
		}

		glShaderSource(fragment, fragmentSource);
		glCompileShader(fragment);

		compiled = glGetShaderi(fragment, GL_COMPILE_STATUS);

		if (compiled == GL_FALSE) {
			Logger.error("Failed to compile the fragment shader!", glGetShaderInfoLog(fragment));

			glDeleteShader(fragment);
			return null;
		}

		glAttachShader(program, vertex);
		glAttachShader(program, fragment);

		glLinkProgram(program);
		glValidateProgram(program);

		glDeleteShader(vertex);
		glDeleteShader(fragment);

		return new Shader(program);
	}

	public int getID() {
		return id;
	}

	public String getVertexSource() {
		return vertexSource;
	}

	public String getFragmentource() {
		return fragmentSource;
	}

	public void dispose() {
		glDeleteProgram(id);
	}

	public void bind() {
		glUseProgram(id);
	}

	public void unbind() {
		glUseProgram(0);
	}

	private int getUniformLocation(String name) {
		return glGetUniformLocation(id, name);
	}

	public void setUniform1f(String name, float value) {
		glUniform1f(getUniformLocation(name), value);
	}

	public void setUniform1i(String name, int value) {
		glUniform1i(getUniformLocation(name), value);
	}

	public void setUniform2f(String name, Vector2f value) {
		glUniform2f(getUniformLocation(name), value.x, value.y);
	}

	public void setUniform3f(String name, Vector3f value) {
		glUniform3f(getUniformLocation(name), value.x, value.y, value.z);
	}

	public void setUniform4f(String name, Color value) {
		glUniform4f(getUniformLocation(name), value.r, value.g, value.b, value.a);
	}

	public void setUniform4f(String name, Vector4f value) {
		glUniform4f(getUniformLocation(name), value.x, value.y, value.z, value.w);
	}

	public void setUniformMat4f(String name, Matrix4f value) {
		glUniformMatrix4fv(getUniformLocation(name), false, value.getBuffer());
	}
}
