package info.nt5.engine.game.elements.actor;

import info.nt5.engine.game.animation.Animation;
import info.nt5.engine.game.animation.AnimationCallbacks;
import info.nt5.engine.game.elements.GameObject;
import info.nt5.engine.graphics.Texture;
import info.nt5.engine.graphics.shader.VertexQuad;
import info.nt5.engine.math.Vector3f;

public class Part implements AnimationCallbacks {

	private GameObject Object;
	private Textures Textures;
	public Animation animation;

	private int currentTextureSet, currentTextureSprite;

	public Part(VertexQuad quad, String[][] textureset, Vector3f position) {
		this(quad, textureset);
		this.Object.translate(position);
	}

	public Part(VertexQuad quad, String[] texturese, Vector3f position) {
		this(quad, texturese);
		this.Object.translate(position);
	}

	public Part(VertexQuad quad, String[] textureset) {
		this.Textures = new Textures(textureset);
		this.Object = new GameObject(quad, this.Textures.firstInSet());
		this.animation = new Animation(this);
	}

	public Part(VertexQuad quad, String[][] textureset) {
		this.Textures = new Textures(textureset);
		this.Object = new GameObject(quad, this.Textures.firstInSet());
		this.animation = new Animation(this);
	}

	public Part(VertexQuad quad, Textures textures) {
		this(new GameObject(quad, textures.firstInSet()), textures);
	}

	public Part(GameObject object, Textures textures) {
		this.Object = object;
		this.Textures = textures;
		this.animation = new Animation(this);
	}

	public int getSpriteSetSize() {
		return this.Textures.getTexture().size();
	}

	@Override
	public int getSpriteSize() {
		return this.Textures.getTexture(currentTextureSet).size();
	}

	@Override
	public int getSetID() {
		return currentTextureSet;
	}

	@Override
	public int getSpriteID() {
		return currentTextureSprite;
	}

	@Override
	public Texture getCurrentTexture() {
		return this.Textures.getTexture(currentTextureSet).get(currentTextureSprite);
	}

	@Override
	public void setCurrentTextureToObject() {
		setObjectTexture(getCurrentTexture());
	}

	@Override
	public void setFirtsTextureObject() {
		setObjectTexture(this.Textures.getTexture(currentTextureSet).get(0));
	}

	@Override
	public void nextTextureSet() {
		if (currentTextureSet != Textures.getTexture().size() - 1) {
			currentTextureSet++;
			currentTextureSprite = 0;
		} else {
			currentTextureSet = 0;
		}
		setCurrentTextureToObject();
	}

	@Override
	public void nextTextureSprite() {
		if (currentTextureSprite != Textures.getTexture(currentTextureSet).size() - 1) {
			currentTextureSprite++;
		} else {
			currentTextureSprite = 0;
		}
		setCurrentTextureToObject();
	}

	@Override
	public void prevTextureSet() {
		if (currentTextureSet != 0) {
			currentTextureSet--;
			currentTextureSprite = 0;
		} else {
			currentTextureSet = (Textures.getTexture().size() - 1);
		}
		setCurrentTextureToObject();
	}

	@Override
	public void prevTextureSprite() {
		if (currentTextureSprite != 0) {
			currentTextureSprite--;
		} else {
			currentTextureSprite = (Textures.getTexture(currentTextureSet).size() - 1);
		}
		setCurrentTextureToObject();
	}

	public void translate(Vector3f vector) {
		Object.translate(vector);
	}

	public void translateX(float x) {
		Object.translateX(x);
	}

	public void translateY(float y) {
		Object.translateY(y);
	}

	public void translateZ(float z) {
		Object.translateZ(z);
	}

	public GameObject getObject() {
		return Object;
	}

	public Textures getTextures() {
		return Textures;
	}

	public void setObjectTexture(Texture texture) {
		Object.texture = texture;
	}

	public void render() {
		Object.render();
	}

	public void dispose() {
		Object.dispose();
		Textures.dispose();
	}

}
