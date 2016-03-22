package info.nt5.engine.graphics.text;

import java.util.*;

import info.nt5.engine.graphics.Texture;
import info.nt5.engine.math.Vector3f;

public class BitmapFont {
	private ArrayList<BitmapChar> CharList = new ArrayList<BitmapChar>();

	private String text;
	private Texture texture;
	private Vector3f position;

	private float width, height, Xoffset, Yoffset;

	private static final String defaultBitmapFile = BitmapChar.getDefaultFontPath();

	private static final float defaultWidth = 0.2f;
	private static final float defaultHeight = 0.2f;

	private static final float defaultXoffset = 0.0f;
	private static final float defaultYoffset = 0.20f;

	private static final Vector3f defaultPosition = new Vector3f();

	public BitmapFont(String text) {
		this(text, defaultXoffset, defaultYoffset);
	}

	public BitmapFont(String text, float Xoffset, float Yoffset) {
		this(text, Xoffset, Yoffset, defaultWidth, defaultHeight);
	}

	public BitmapFont(String text, float Xoffset, float Yoffset, float width, float height) {
		this(text, Xoffset, Yoffset, width, height, defaultBitmapFile);
	}

	public BitmapFont(String text, float Xoffset, float Yoffset, float width, float height, String texturePath) {
		this(text, Xoffset, Yoffset, width, width, Texture.fromImage(defaultBitmapFile), defaultPosition.copy());
	}

	public BitmapFont(String text, Vector3f position) {
		this(text, defaultXoffset, defaultYoffset, position);
	}

	public BitmapFont(String text, float Xoffset, float Yoffset, Vector3f position) {
		this(text, Xoffset, Yoffset, defaultWidth, defaultHeight, position);
	}

	public BitmapFont(String text, float Xoffset, float Yoffset, float width, float height, Vector3f position) {
		this(text, Xoffset, Yoffset, width, height, defaultBitmapFile, position);
	}

	public BitmapFont(String text, float Xoffset, float Yoffset, float width, float height, String texturePath,
			Vector3f position) {
		this(text, Xoffset, Yoffset, width, width, Texture.fromImage(defaultBitmapFile), position);
	}

	public BitmapFont(String text, float Xoffset, float Yoffset, float width, float height, Texture texture,
			Vector3f position) {
		this.text = text;
		this.texture = texture;
		this.position = position;

		this.Xoffset = Xoffset;
		this.Yoffset = Yoffset;
		this.width = width;
		this.height = height;

		createChars();
	}

	public void createChars() {
		float Xoffset = (this.width + this.Xoffset);
		float Yoffset = (this.height + this.Yoffset);

		float XPos = Xoffset;
		float YPos = Yoffset;
		for (int i = 0; i < this.text.length(); i++) {
			char character = this.text.charAt(i);
			int asciiCode = (int) character;

			if (asciiCode == 10) {
				XPos = Xoffset;
				YPos -= Yoffset;
				continue;
			}

			Vector3f position = new Vector3f((this.position.x + XPos), (this.position.y + YPos), this.position.z);

			BitmapChar Char = new BitmapChar(asciiCode, this.width, this.height, this.texture, position);

			CharList.add(Char);

			XPos += Xoffset;
		}
	}

	public void translate(Vector3f vector) {
		for (BitmapChar Char : CharList) {
			Char.position.x += vector.x;
			Char.position.y += vector.y;
			Char.position.z += vector.z;
		}
	}

	public void translateX(float x) {
		for (BitmapChar Char : CharList) {
			Char.position.x += x;
		}
	}

	public void translateY(float y) {
		for (BitmapChar Char : CharList) {
			Char.position.y += y;
		}
	}

	public void translateZ(float z) {
		for (BitmapChar Char : CharList) {
			Char.position.z += z;
		}
	}

	public void render() {
		for (BitmapChar Char : CharList) {
			Char.render();
		}
	}

	public void dispose() {
		texture.dispose();
		for (BitmapChar Char : CharList) {
			Char.dispose();
		}
		CharList.clear();
	}

	public ArrayList<BitmapChar> getCharList() {
		return CharList;
	}

	public String getText() {
		return this.text;
	}
}
