package info.nt5.engine.game.elements;

import info.nt5.engine.graphics.Color;
import info.nt5.engine.graphics.Texture;
import info.nt5.engine.graphics.shader.VertexQuad;
import info.nt5.engine.math.Vector3f;

public class Crate extends GameObject {

	private static Texture defaultTexture = Texture.fromColor(Color.BLACK, 64, 64);

	public static float width = 3f;
	public static float height = 3f;

	static float[] vertices = {

			-width, -height, 0.2f,

			-width, height, 0.2f,

			width, height, 0.2f,

			width, -height, 0.2f

	};

	static float[] texCoords = {

			0, 1,

			0, 0,

			1, 0,

			1, 1

	};

	static int[] indices = {

			0, 1,

			3, 3,

			1, 2

	};

	static float[] normals = new float[0];

	public Crate() {
		super(vertices, indices, texCoords, normals, defaultTexture, new Vector3f());
	}

	public Crate(Texture texture) {
		super(vertices, indices, texCoords, normals, texture, new Vector3f());
	}

	public Crate(VertexQuad quad, Texture texture) {
		super(quad, texture, new Vector3f());
	}

	public Crate(Color c) {
		super(vertices, indices, texCoords, normals, Texture.fromColor(c, 64, 64), new Vector3f());
	}
}
