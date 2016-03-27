package info.nt5.engine.game.elements.actor;

import java.util.*;

import info.nt5.engine.graphics.Texture;

public class Textures {

	private ArrayList<ArrayList<Texture>> textures = new ArrayList<ArrayList<Texture>>();

	private int currentIdTexture, currentTextureSprite;

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

	public Texture getCurrentTexture() {
		return textures.get(currentIdTexture).get(currentTextureSprite);
	}

	public void nextTextureId() {
		if (currentIdTexture != textures.size() - 1) {
			currentIdTexture++;
			currentTextureSprite = 0;
		} else {
			currentIdTexture = 0;
		}
	}

	public void nextTextureSprite() {
		if (currentTextureSprite != textures.get(currentIdTexture).size() - 1) {
			currentTextureSprite++;
		} else {
			currentTextureSprite = 0;
		}
	}

	public void prevEyeId() {
		if (currentIdTexture != 0) {
			currentIdTexture--;
			currentTextureSprite = 0;
		} else {
			currentIdTexture = (textures.size() - 1);
		}
	}

	public void prevEyeSprite() {
		if (currentTextureSprite != 0) {
			currentTextureSprite--;
		} else {
			currentTextureSprite = (textures.get(currentIdTexture).size() - 1);
		}
	}

	public void dispose() {
		for (ArrayList<Texture> texture_list : textures) {
			for (Texture texture : texture_list) {
				texture.dispose();
			}
		}
		textures.clear();
	}

}
