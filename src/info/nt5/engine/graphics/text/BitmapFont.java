package info.nt5.engine.graphics.text;

import java.util.*;

import info.nt5.engine.graphics.Texture;
import info.nt5.engine.math.Vector3f;

public class BitmapFont {
	private ArrayList<BitmapChar> CharList = new ArrayList<BitmapChar>();

	private String text;
	
	private Texture texture;
	private static String bitmapFile = BitmapChar.getDefaultFontPath();

	private static float spacing = 0.28f;

	private static float width = 0.2f;
	private static float height = 0.2f;

	public BitmapFont(String text) {
		this(text, spacing);
	}

	public BitmapFont(String text, float spacing) {
		this(text, spacing, width, height);
	}

	public BitmapFont(String text, float spacing, float wd) {
		this(text, spacing, wd, wd);
	}

	public BitmapFont(String text, float spacing, float width, float height) {
		this(text, spacing, width, height, bitmapFile);
	}
	
	public BitmapFont(String text, float spacing, float width, float height, String texturePath) {
		this.text = text;
		this.texture = Texture.fromImage(bitmapFile);
		BitmapFont.spacing = spacing;
		BitmapFont.width = width;
		BitmapFont.height = height;
		createChars();
	}
	
	public void createChars() {
		float space = spacing;
		for (int i = 0; i < text.length(); i++) {
			int asciiCode = (int) text.charAt(i);
			
			BitmapChar Char = new BitmapChar(asciiCode, width, height, texture);
			
			Char.position.x = space;
			CharList.add(Char);
			space += spacing;
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
