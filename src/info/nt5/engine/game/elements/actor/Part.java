package info.nt5.engine.game.elements.actor;

import info.nt5.engine.game.GameObject;
import info.nt5.engine.graphics.shader.VertexQuad;
import info.nt5.engine.math.Vector3f;

public class Part {

	private GameObject Object;
	private Textures Textures;

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
		this.Object = new GameObject(quad, this.Textures.getCurrentTexture());
	}

	public Part(VertexQuad quad, String[][] textureset) {
		this.Textures = new Textures(textureset);
		this.Object = new GameObject(quad, this.Textures.getCurrentTexture());
	}

	public Part(VertexQuad quad, Textures textures) {
		this(new GameObject(quad, textures.getCurrentTexture()), textures);
	}

	public Part(GameObject object, Textures textures) {
		this.Object = object;
		this.Textures = textures;
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

	public void setObject(GameObject object) {
		Object = object;
	}

	public Textures getTextures() {
		return Textures;
	}

	public void setTextures(Textures textures) {
		Textures = textures;
	}

	public void render() {
		Object.render();
	}

	public void dispose() {
		Object.dispose();
		Textures.dispose();
	}

}
