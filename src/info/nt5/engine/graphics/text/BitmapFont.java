package info.nt5.engine.graphics.text;

import java.util.*;

import info.nt5.engine.math.Vector3f;

public class BitmapFont {
	private ArrayList<BitmapChar> CharList = new ArrayList<BitmapChar>();
	private String text;
	private float spacing = 0.30f;

	public BitmapFont(String text) {
		this.text = text;
		this.createChars();
	}

	private void createChars() {
		float space = spacing;
		for (int i = 0; i < text.length(); i++) {
			int asciiCode = (int) text.charAt(i);
			BitmapChar Char = new BitmapChar(asciiCode);
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
