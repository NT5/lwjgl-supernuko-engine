package info.nt5.engine.graphics.text;

import info.nt5.engine.graphics.Color;
import info.nt5.engine.math.Vector2f;

public class BitmapFormat {
	public String text;
	public Color color = Color.WHITE;
	public boolean bold;
	public Vector2f size = new Vector2f(0.25f, 0.25f);

	public BitmapFormat(String text) {
		this.text = text;
	}

	public BitmapFormat(String text, Color color) {
		this.text = text;
		this.color = color;
	}

	public BitmapFormat(String text, Color color, Vector2f size) {
		this.text = text;
		this.color = color;
		this.size = size;
	}

	public BitmapFormat(String text, Color color, Vector2f size, boolean bold) {
		this.text = text;
		this.color = color;
		this.bold = bold;
		this.size = size;
	}

	public BitmapFormat setText(String text) {
		this.text = text;
		return this;
	}

	public BitmapFormat setBold(boolean bold) {
		this.bold = bold;
		return this;
	}

	public BitmapFormat setSize(Vector2f size) {
		this.size = size;
		return this;
	}

	public BitmapFormat setColor(Color color) {
		this.color = color;
		return this;
	}
}
