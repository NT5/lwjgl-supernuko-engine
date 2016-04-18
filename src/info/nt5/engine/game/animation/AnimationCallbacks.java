package info.nt5.engine.game.animation;

import info.nt5.engine.graphics.Texture;

public interface AnimationCallbacks {
	public int getSpriteSize();

	public int getSetID();

	public int getSpriteID();

	public Texture getCurrentTexture();

	public void setCurrentTextureToObject();

	public void setFirtsTextureObject();

	public void nextTextureSet();

	public void nextTextureSprite();

	public void prevTextureSet();

	public void prevTextureSprite();
}
