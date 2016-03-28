package info.nt5.engine.game.elements.actor;

import java.util.*;

import info.nt5.engine.graphics.shader.VertexQuad;
import info.nt5.engine.math.Vector3f;

public class Actor {
	public List<Part> Parts = new ArrayList<Part>();;

	public Actor() {

		String[] body_normal_sprites = { "assets/img/actors/actor_1/a_base.png" };

		String[][] eye_normal_sprites = {
				{ "assets/img/actors/actor_1/a_normal_eye1.png", "assets/img/actors/actor_1/a_normal_eye2.png" },
				{ "assets/img/actors/actor_1/a_normal_eye1.png", "assets/img/actors/actor_1/a_tere_eye1.png" },
				{ "assets/img/actors/actor_1/a_naki_eye1.png", "assets/img/actors/actor_1/a_naki_eye2.png" },
				{ "assets/img/actors/actor_1/a_odoroki_eye1.png", "assets/img/actors/actor_1/a_odoroki_eye2.png" } };

		String[][] mouth_normal_sprites = {
				{ "assets/img/actors/actor_1/a_normal_mo2.png", "assets/img/actors/actor_1/a_normal_mo1.png" } };

		Part Body = new Part(new VertexQuad(4f, 6f), body_normal_sprites);
		Part Eye = new Part(new VertexQuad(1.50f, 1.35f), eye_normal_sprites, new Vector3f(-1.40f, 2.3f, 0f));
		Part Mouth = new Part(new VertexQuad(0.3f), mouth_normal_sprites, new Vector3f(-1.40f, 1.40f, 0f));

		Parts.add(Body);
		Parts.add(Eye);
		Parts.add(Mouth);
	}

	public void update() {
		Parts.get(1).animate(10, 0.80, 5, -1);
		Parts.get(2).animate(10, 0, 1, 35);
	}

	public void render() {
		for (Part part : Parts) {
			part.render();
		}
	}

	public void translate(Vector3f vector) {
		for (Part part : Parts) {
			part.translate(vector);
		}
	}

	public void translateX(float x) {
		for (Part part : Parts) {
			part.translateX(x);
		}
	}

	public void translateY(float y) {
		for (Part part : Parts) {
			part.translateY(y);
		}
	}

	public void translateZ(float z) {
		for (Part part : Parts) {
			part.translateZ(z);
		}
	}

	public void dispose() {
		for (Part part : Parts) {
			part.dispose();
		}
		Parts.clear();
	}

}
