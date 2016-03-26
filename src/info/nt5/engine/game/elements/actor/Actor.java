package info.nt5.engine.game.elements.actor;

import java.util.*;

import info.nt5.engine.game.GameObject;
import info.nt5.engine.graphics.shader.VertexQuad;
import info.nt5.engine.math.Vector3f;

public class Actor {
	public List<GameObject> Parts = new ArrayList<GameObject>();

	public ActorTextures textures = new ActorTextures();

	public Actor() {

		String[] body_normal_sprites = { "assets/img/actors/actor_1/a_base.png" };

		String[][] eye_normal_sprites = {
				{ "assets/img/actors/actor_1/a_normal_eye1.png", "assets/img/actors/actor_1/a_normal_eye2.png" },
				{ "assets/img/actors/actor_1/a_tere_eye1.png" } };

		String[][] mouth_normal_sprites = {
				{ "assets/img/actors/actor_1/a_normal_mo1.png", "assets/img/actors/actor_1/a_normal_mo2.png" } };

		for (String body_textures : body_normal_sprites) {
			textures.addBody(body_textures);
		}

		for (String[] eye_textures : eye_normal_sprites) {
			textures.addEye(eye_textures);
		}

		for (String[] mouth_textures : mouth_normal_sprites) {
			textures.addMouth(mouth_textures);
		}

		VertexQuad body_quad = new VertexQuad(4f, 6f);
		GameObject body = new GameObject(body_quad, textures.getBody(0));

		VertexQuad eye_quad = new VertexQuad(1.50f, 1.35f);
		GameObject eye = new GameObject(eye_quad, textures.getEye(0).get(0), new Vector3f(-1.40f, 2.3f, 0f));

		VertexQuad mouth_quad = new VertexQuad(0.3f);
		GameObject mouth = new GameObject(mouth_quad, textures.getMouth(0).get(0), new Vector3f(-1.40f, 1.40f, 0f));

		Parts.add(body);
		Parts.add(eye);
		Parts.add(mouth);
	}

	public void render() {
		for (GameObject part : Parts) {
			part.render();
		}
	}

	public void translate(Vector3f vector) {
		for (GameObject part : Parts) {
			part.translate(vector);
		}
	}

	public void translateX(float x) {
		for (GameObject part : Parts) {
			part.translateX(x);
		}
	}

	public void translateY(float y) {
		for (GameObject part : Parts) {
			part.translateY(y);
		}
	}

	public void translateZ(float z) {
		for (GameObject part : Parts) {
			part.translateZ(z);
		}
	}

	public void dispose() {
		for (GameObject part : Parts) {
			part.dispose();
		}
		Parts.clear();

		textures.dispose();
	}

}
