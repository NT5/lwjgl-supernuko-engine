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

	public BitmapFont(String text) {
		this(text, Xoffset, Yoffset);
	}

	public BitmapFont(String text, float Xoffset, float Yoffset) {
		this(text, Xoffset, Yoffset, width, height);
	}

	public BitmapFont(String text, float Xoffset, float Yoffset, float width, float height) {
		this(text, Xoffset, Yoffset, width, height, bitmapFile);
	}

	public BitmapFont(String text, float Xoffset, float Yoffset, float width, float height, String texturePath) {
		this(text, Xoffset, Yoffset, width, width, Texture.fromImage(bitmapFile));
	}

	public BitmapFont(String text, float Xoffset, float Yoffset, float width, float height, Texture texture) {
		this.text = text;
		this.texture = texture;
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
			
			Char.position.x = XPos;
			Char.position.y = YPos;
			
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

	public void render() {
		for (BitmapChar Char : CharList) {
			Char.render();
		}
	}
}
