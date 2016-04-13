package info.nt5.engine.game.elements;

import info.nt5.engine.game.GameObject;
import info.nt5.engine.graphics.Color;

public class Crate extends GameObject {

	private static String texPath = "assets/img/happy.png";

	public static float width = 3.0f;
	public static float height = 3.0f;

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

	static byte[] indices = {
			0, 1, 3,
			3, 1, 2
	};

	public Crate() {
		super(vertices, indices, texCoords, texPath);
	}
	
	public Crate(Color c) {
		super(vertices, indices, texCoords, c);
	}
}
