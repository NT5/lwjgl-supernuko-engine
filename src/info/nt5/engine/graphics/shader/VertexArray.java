package info.nt5.engine.graphics.shader;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import info.nt5.engine.util.BufferUtil;

public class VertexArray {

	public static final int SHADER_VERTEX_ATTRIB = 0;
	public static final int SHADER_TCOORD_ATTRIB = 1;

	private int vao, vbo, ibo, tbo;
	private int count;

	public VertexArray(int count) {
		this.count = count;
		vao = glGenVertexArrays();
	}

	public VertexArray(float[] vertices, byte[] indices, float[] textureCoordinates) {
		count = indices.length;

		vao = glGenVertexArrays();
		glBindVertexArray(vao);

		vbo = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glBufferData(GL_ARRAY_BUFFER, BufferUtil.toFloatBuffer(vertices), GL_STATIC_DRAW);
		glVertexAttribPointer(SHADER_VERTEX_ATTRIB, 3, GL_FLOAT, false, 0, 0);
		glEnableVertexAttribArray(SHADER_VERTEX_ATTRIB);

		tbo = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, tbo);
		glBufferData(GL_ARRAY_BUFFER, BufferUtil.toFloatBuffer(textureCoordinates), GL_STATIC_DRAW);
		glVertexAttribPointer(SHADER_TCOORD_ATTRIB, 2, GL_FLOAT, false, 0, 0);
		glEnableVertexAttribArray(SHADER_TCOORD_ATTRIB);

		ibo = glGenBuffers();
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, BufferUtil.createByteBuffer(indices), GL_STATIC_DRAW);

		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		glBindVertexArray(0);
	}

	public void bind() {
		glBindVertexArray(vao);
		if (ibo > 0) {
			glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
		}
	}

	public void unbind() {
		if (ibo > 0) {
			glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		}
		glBindVertexArray(0);
	}

	public void draw() {
		if (ibo > 0) {
			glDrawElements(GL_TRIANGLES, count, GL_UNSIGNED_BYTE, 0);
		} else {
			glDrawArrays(GL_TRIANGLES, 0, count);
		}
	}

	public void render() {
		bind();
		draw();
	}

}
