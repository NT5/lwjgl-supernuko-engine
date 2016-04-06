package info.nt5.engine.game.elements.actor.actors;

import info.nt5.engine.game.elements.actor.Actor;
import info.nt5.engine.game.elements.actor.Part;
import info.nt5.engine.graphics.shader.VertexQuad;
import info.nt5.engine.math.Vector3f;

public class Maru extends Actor {

	private static float[][] quad_size = {
			{ 4f, 6f },
			{ 1.50f, 1.25f },
			{ 0.44f, 0.44f }
	};

	private static String[][][] sprites = {
			// Body
			{ { "assets/img/actors/actor_2/a_base.png" } },
			// Eye
			{
					// :)
					{ "assets/img/actors/actor_2/a_normal_eye1.png", "assets/img/actors/actor_2/a_normal_eye2.png" }
			},
			// Mouth
			{
					// Normal
					{ "assets/img/actors/actor_2/a_normal_mo1.png", "assets/img/actors/actor_2/a_normal_mo2.png" } }
			};

	private static Vector3f[] positions = {
			new Vector3f(),
			new Vector3f(-0.27f, 3.332f, 0.0f),
			new Vector3f(-0.26f, 2.67f, 0.0f)
	};

	public static void flipActor() {
		quad_size[0][0] = -quad_size[0][0];
		quad_size[1][0] = -quad_size[1][0];
		quad_size[2][0] = -quad_size[2][0];

		positions[1].setX(-positions[1].getX());
		positions[2].setX(-positions[2].getX());
	}

	public Maru(Vector3f position) {
		this();
		this.translate(position);
	}

	public Maru() {
		super(new Part[] {
				// Body
				new Part(new VertexQuad(quad_size[0][0], quad_size[0][1]), sprites[0], positions[0].copy()),
				// Eye
				new Part(new VertexQuad(quad_size[1][0], quad_size[1][1]), sprites[1], positions[1].copy()),
				// Mouth
				new Part(new VertexQuad(quad_size[2][0], quad_size[2][1]), sprites[2], positions[2].copy()),
		});
	}

}
