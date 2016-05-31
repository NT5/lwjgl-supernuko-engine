package info.nt5.engine.game.elements;

import info.nt5.engine.graphics.Color;
import info.nt5.engine.graphics.Texture;
import info.nt5.engine.graphics.shader.VertexQuad;
import info.nt5.engine.math.Vector2f;
import info.nt5.engine.math.Vector3f;

public class Tile extends GameObject {

	private static Texture defaultTexture = Texture.fromColor(Color.BLACK, 64, 64);

	public static float width = 3f;
	public static float height = 3f;

	private static float[] vertices = {

			-width, -height, 0.2f,

			-width, height, 0.2f,

			width, height, 0.2f,

			width, -height, 0.2f

	};

	private static float[] texCoords = {

			0, 1,

			0, 0,

			1, 0,

			1, 1

	};

	private static int[] indices = {

			0, 1,

			3, 3,

			1, 2

	};

	private static float[] normals = new float[0];

	public Tile() {
		super(vertices, indices, texCoords, normals, defaultTexture, new Vector3f());
	}

	public Tile(Texture texture) {
		super(vertices, indices, texCoords, normals, texture, new Vector3f());
	}

	public Tile(VertexQuad quad, Texture texture, Vector3f position) {
		super(quad, texture, position);
	}

	public Tile(Color c) {
		super(vertices, indices, texCoords, normals, Texture.fromColor(c, 1, 1), new Vector3f());
	}

	public Tile(Color c, Vector2f size) {
		super(new VertexQuad(size.x, size.y), Texture.fromColor(c, 1, 1), new Vector3f());
	}

	public Tile(Color c, Vector3f pos) {
		super(vertices, indices, texCoords, normals, Texture.fromColor(c, 1, 1), pos);
	}
}
