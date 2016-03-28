package info.nt5.engine.game.elements.actor;

import java.util.*;

import info.nt5.engine.graphics.Texture;

public class Textures {

	private ArrayList<ArrayList<Texture>> textures = new ArrayList<ArrayList<Texture>>();

	public Textures(String[][] textures) {
		this.addTexture(textures);
	}

	public Textures(String[] textures) {
		this.addTexture(textures);
	}

	public void addTexture(String[][] textures) {
		for (String[] texture_set : textures) {
			addTexture(texture_set);
		}
	}

	public void addTexture(String[] textures) {
		ArrayList<Texture> texture_sprites = new ArrayList<Texture>();
		for (String texture_path : textures) {
			texture_sprites.add(Texture.fromImage(texture_path));
		}
		this.textures.add(texture_sprites);
	}

	public ArrayList<Texture> getTexture(int index) {
		return textures.get(index);
	}

	public ArrayList<ArrayList<Texture>> getTexture() {
		return this.textures;
	}

	public Texture firstInSet() {
		return this.textures.get(0).get(0);
	}

	public void dispose() {
		for (ArrayList<Texture> texture_set : textures) {
			for (Texture texture : texture_set) {
				texture.dispose();
			}
		}
		this.textures.clear();
	}

}
