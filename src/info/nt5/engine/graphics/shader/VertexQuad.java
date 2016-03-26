package info.nt5.engine.graphics.shader;

public class VertexQuad {

	public float width = 10.0f;
	public float height = 10.0f;

	private float[] vertices = { -width, -height, 0f, -width, height, 0f, width, height, 0f, width, -height, 0f };

	private float[] texCoords = { 0, 1, 0, 0, 1, 0, 1, 1 };

	private byte[] indices = { 0, 1, 3, 1, 2, 3 };

	public VertexQuad() {
	}

	public VertexQuad(float wd) {
		this(wd, wd);
	}

	public VertexQuad(float width, float height) {
		this.height = height;
		this.width = width;
		this.set();
	}

	private void set() {
		this.vertices[0] = -width;
		this.vertices[1] = -height;

		this.vertices[3] = -width;
		this.vertices[4] = height;

		this.vertices[6] = width;
		this.vertices[7] = height;

		this.vertices[9] = width;
		this.vertices[10] = -height;
	}

	public float[] getVertices() {
		return vertices;
	}

	public void setVertices(float[] vertices) {
		this.vertices = vertices;
	}

	public byte[] getIndices() {
		return indices;
	}

	public void setIndices(byte[] indices) {
		this.indices = indices;
	}

	public float[] getTexCoords() {
		return texCoords;
	}

	public void setTexCoords(float[] texCoords) {
		this.texCoords = texCoords;
	}

}
