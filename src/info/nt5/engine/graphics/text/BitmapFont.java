package info.nt5.engine.graphics.text;

import java.util.*;

import info.nt5.engine.graphics.Texture;
import info.nt5.engine.math.Vector3f;

public class BitmapFont {
	private ArrayList<BitmapChar> CharList = new ArrayList<BitmapChar>();

	private String text;

	private Texture texture;
	private static String bitmapFile = BitmapChar.getDefaultFontPath();

	private static float width = 0.2f;
	private static float height = 0.2f;

	private static float Xoffset = 0.0f;
	private static float Yoffset = 0.20f;
	
	private Vector3f position = new Vector3f();

	public BitmapFont(String text, Vector3f position) {
		this(text, Xoffset, Yoffset, position);
	}

	public BitmapFont(String text, float Xoffset, float Yoffset, Vector3f position) {
		this(text, Xoffset, Yoffset, width, height, position);
	}

	public BitmapFont(String text, float Xoffset, float Yoffset, float width, float height, Vector3f position) {
		this(text, Xoffset, Yoffset, width, height, bitmapFile, position);
	}

	public BitmapFont(String text, float Xoffset, float Yoffset, float width, float height, String texturePath, Vector3f position) {
		this(text, Xoffset, Yoffset, width, width, Texture.fromImage(bitmapFile), position);
	}

	public BitmapFont(String text, float Xoffset, float Yoffset, float width, float height, Texture texture, Vector3f position) {
		this.text = text;
		this.texture = texture;
		this.position = position;
		
		BitmapFont.Xoffset = Xoffset;
		BitmapFont.Yoffset = Yoffset;
		BitmapFont.width = width;
		BitmapFont.height = height;

		createChars();
	}

	public void createChars() {
		float Xoffset = width + BitmapFont.Xoffset;
		float Yoffset = height + BitmapFont.Yoffset;

		float XPos = Xoffset;
		float YPos = Yoffset;
		for (int i = 0; i < text.length(); i++) {
			char character = text.charAt(i);
			int asciiCode = (int) character;

			if (asciiCode == 10) {
				XPos = Xoffset;
				YPos -= Yoffset;
				continue;
			}

			BitmapChar Char = new BitmapChar(asciiCode, width, height, texture);

			Char.translate(this.position);

			Char.translateX(XPos);
			Char.translateY(YPos);

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

	public ArrayList<BitmapChar> getCharList() {
		return CharList;
	}
}
