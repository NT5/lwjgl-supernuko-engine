package info.nt5.engine.game.elements.actor;

import java.util.*;

import info.nt5.engine.graphics.Texture;

public class ActorTextures {

	private List<Texture> body = new ArrayList<Texture>();
	private ArrayList<ArrayList<Texture>> eye = new ArrayList<ArrayList<Texture>>();
	private ArrayList<ArrayList<Texture>> mouth = new ArrayList<ArrayList<Texture>>();

	private int currentBodySprite, currentIdEye, currentEyeSprite, currentIdMouth, currentMouthSprite;

	public void addBody(String path) {
		body.add(Texture.fromImage(path));
	}

	public void addEye(String[] eye) {
		ArrayList<Texture> eye_sprites = new ArrayList<Texture>();
		for (String eye_path : eye) {
			eye_sprites.add(Texture.fromImage(eye_path));
		}
		this.eye.add(eye_sprites);
	}

	public void addMouth(String[] mouth) {
		ArrayList<Texture> mouth_sprites = new ArrayList<Texture>();
		for (String mouth_path : mouth) {
			mouth_sprites.add(Texture.fromImage(mouth_path));
		}
		this.mouth.add(mouth_sprites);
	}

	public Texture getBody(int index) {
		return body.get(index);
	}

	public List<Texture> getBody() {
		return body;
	}

	public ArrayList<Texture> getEye(int index) {
		return eye.get(index);
	}

	public ArrayList<ArrayList<Texture>> getEye() {
		return eye;
	}

	public ArrayList<Texture> getMouth(int index) {
		return mouth.get(index);
	}

	public ArrayList<ArrayList<Texture>> getMouth() {
		return mouth;
	}

	public void nextBodySprite() {
		if (currentBodySprite != body.size() - 1) {
			currentBodySprite++;
		} else {
			currentBodySprite = 0;
		}
	}

	public Texture getCurrentBody() {
		return body.get(currentBodySprite);
	}

	public void prevBodySprite() {
		if (currentBodySprite != 0) {
			currentBodySprite--;
		} else {
			currentBodySprite = (body.size() - 1);
		}
	}

	public Texture getCurrentEye() {
		return eye.get(currentIdEye).get(currentEyeSprite);
	}

	public void nextEyeId() {
		if (currentIdEye != eye.size() - 1) {
			currentIdEye++;
			currentEyeSprite = 0;
		} else {
			currentIdEye = 0;
		}
	}

	public void nextEyeSprite() {
		if (currentEyeSprite != eye.get(currentIdEye).size() - 1) {
			currentEyeSprite++;
		} else {
			currentEyeSprite = 0;
		}
	}

	public void prevEyeId() {
		if (currentIdEye != 0) {
			currentIdEye--;
			currentEyeSprite = 0;
		} else {
			currentIdEye = (eye.size() - 1);
		}
	}

	public void prevEyeSprite() {
		if (currentEyeSprite != 0) {
			currentEyeSprite--;
		} else {
			currentEyeSprite = (eye.get(currentIdEye).size() - 1);
		}
	}

	public Texture getCurrentMouth() {
		return mouth.get(currentIdMouth).get(currentMouthSprite);
	}

	public void nextMouthId() {
		if (currentIdMouth != mouth.size() - 1) {
			currentIdMouth++;
			currentMouthSprite = 0;
		} else {
			currentIdMouth = 0;
		}
	}

	public void nextMouthSprite() {
		if (currentMouthSprite != mouth.get(currentIdMouth).size() - 1) {
			currentMouthSprite++;
		} else {
			currentMouthSprite = 0;
		}
	}

	public void prevMouthId() {
		if (currentIdMouth != 0) {
			currentIdMouth--;
			currentMouthSprite = 0;
		} else {
			currentIdMouth = (mouth.size() - 1);
		}
	}

	public void prevMouthSprite() {
		if (currentMouthSprite != 0) {
			currentMouthSprite--;
		} else {
			currentMouthSprite = (mouth.get(currentIdMouth).size() - 1);
		}
	}

	public void dispose() {
		for (Texture tex : body) {
			tex.dispose();
		}
		body.clear();

		for (ArrayList<Texture> eye_list : eye) {
			for (Texture eye_texture : eye_list) {
				eye_texture.dispose();
			}
		}
		eye.clear();

		for (ArrayList<Texture> mouth_list : mouth) {
			for (Texture mouth_texture : mouth_list) {
				mouth_texture.dispose();
			}
		}
		mouth.clear();
	}

}
