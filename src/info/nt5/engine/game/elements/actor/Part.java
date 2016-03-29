package info.nt5.engine.game.elements.actor;

import info.nt5.engine.game.GameObject;
import info.nt5.engine.graphics.Texture;
import info.nt5.engine.graphics.shader.VertexQuad;
import info.nt5.engine.math.Vector3f;

public class Part extends Animation {

	private GameObject Object;
	private Textures Textures;

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
	}

	public Part(VertexQuad quad, String[][] textureset) {
		this.Textures = new Textures(textureset);
		this.Object = new GameObject(quad, this.Textures.firstInSet());
	}

	public Part(VertexQuad quad, Textures textures) {
		this(new GameObject(quad, textures.firstInSet()), textures);
	}

	public Part(GameObject object, Textures textures) {
		this.Object = object;
		this.Textures = textures;
	}

	public int getSpriteSetSize() {
		return this.Textures.getTexture().size();
	}

	public int getSpriteSize() {
		return this.Textures.getTexture(currentTextureSet).size();
	}

	public int getSetID() {
		return currentTextureSet;
	}

	public int getSpriteID() {
		return currentTextureSprite;
	}

	public Texture getCurrentTexture() {
		return this.Textures.getTexture(currentTextureSet).get(currentTextureSprite);
	}

	public void setCurrentTextureToObject() {
		setObjectTexture(getCurrentTexture());
	}

	public void nextTextureSet() {
		if (currentTextureSet != Textures.getTexture().size() - 1) {
			currentTextureSet++;
			currentTextureSprite = 0;
		} else {
			currentTextureSet = 0;
		}
		setCurrentTextureToObject();
	}

	public void nextTextureSprite() {
		if (currentTextureSprite != Textures.getTexture(currentTextureSet).size() - 1) {
			currentTextureSprite++;
		} else {
			currentTextureSprite = 0;
		}
		setCurrentTextureToObject();
	}

	public void prevTextureSet() {
		if (currentTextureSet != 0) {
			currentTextureSet--;
			currentTextureSprite = 0;
		} else {
			currentTextureSet = (Textures.getTexture().size() - 1);
		}
		setCurrentTextureToObject();
	}

	public void prevTextureSprite() {
		if (currentTextureSprite != 0) {
			currentTextureSprite--;
		} else {
			currentTextureSprite = (Textures.getTexture(currentTextureSet).size() - 1);
		}
		setCurrentTextureToObject();
	}

	@Override
	public void setAnimation(int speed, double rate, double wait, double duration) {
		this.animationSpeed = speed;
		this.animationRate = rate;
		this.animationSleep = wait;
		this.animationDuration = duration;
	}

	@Override
	public void updateAnimation() {
		if (this.duration < animationDuration || animationDuration <= 0) {
			if (!activeWaiting) {
				if (activeAnimation) {
					if (animationSpeed == 0 || (deltaAnimation % animationSpeed) == 0) {
						this.nextTextureSprite();
						deltaAnimation = 0;
					}
					deltaAnimation++;
				}

				if ((this.getSpriteSize() - 1) != this.getSpriteID()) {
					activeAnimation = (animationRate == 0 || Math.random() > animationRate ? true : false);
					this.duration++;
					activeWaiting = true;
				}
			} else {
				if ((deltaWaiting % animationSleep) == 0) {
					deltaWaiting = 0;
					activeWaiting = false;
				}
				deltaWaiting++;
			}
		}
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
