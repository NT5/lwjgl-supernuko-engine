package info.nt5.engine.game.elements.actor.actors;

import info.nt5.engine.game.elements.actor.Actor;
import info.nt5.engine.game.elements.actor.Part;
import info.nt5.engine.graphics.shader.VertexQuad;
import info.nt5.engine.math.Vector3f;

public class Kanon extends Actor {
	private static float[][] quad_size = {
			{ 4f, 6f },
			{ 1.50f, 1.35f },
			{ 0.3f }
	};

	private static String[][][] sprites = {
			// Body
			{ { "assets/img/actors/actor_1/a_base.png" } },
			// Eye
			{
				// :)
					{ "assets/img/actors/actor_1/a_normal_eye1.png", "assets/img/actors/actor_1/a_normal_eye2.png" },
					// >_<
					{ "assets/img/actors/actor_1/a_normal_eye1.png", "assets/img/actors/actor_1/a_tere_eye1.png" },
					// T_T
					{ "assets/img/actors/actor_1/a_naki_eye1.png", "assets/img/actors/actor_1/a_naki_eye2.png" },
					// O_O
					{ "assets/img/actors/actor_1/a_odoroki_eye1.png",
							"assets/img/actors/actor_1/a_odoroki_eye2.png" } },
			// Mouth
			{
					// Normal
					{ "assets/img/actors/actor_1/a_normal_mo2.png", "assets/img/actors/actor_1/a_normal_mo1.png" } }
			};

	private static Vector3f[] positions = {
			new Vector3f(),
			new Vector3f(-1.40f, 2.3f, 0f),
			new Vector3f(-1.40f, 1.40f, 0f)
	};

	public Kanon() {
		super(new Part[] {
				// Body
				new Part(new VertexQuad(quad_size[0][0], quad_size[0][1]), sprites[0], positions[0].copy()),
				// Eye
				new Part(new VertexQuad(quad_size[1][0], quad_size[1][1]), sprites[1], positions[1].copy()),
				// Mouth
				new Part(new VertexQuad(quad_size[2][0]), sprites[2], positions[2].copy()),
		});
	}

	public Kanon(Vector3f position) {
		super(new Part[] {
				// Body
				new Part(new VertexQuad(quad_size[0][0], quad_size[0][1]), sprites[0], positions[0].copy()),
				// Eye
				new Part(new VertexQuad(quad_size[1][0], quad_size[1][1]), sprites[1], positions[1].copy()),
				// Mouth
				new Part(new VertexQuad(quad_size[2][0]), sprites[2], positions[2].copy()),
		});
		this.translate(position);
	}

}
