package info.nt5.engine.graphics.text;

import info.nt5.engine.graphics.Color;
import info.nt5.engine.math.Vector2f;

public class BitmapFormatBuilder {
	public String text;
	public Color color = Color.WHITE;
	public boolean bold;
	public Vector2f size = new Vector2f(0.2f, 0.2f);
	public Vector2f offset = new Vector2f(0.0f, 0.20f);

	public BitmapFormatBuilder(String text) {
		this.text = text;
	}

	public BitmapFormatBuilder(String text, Color color) {
		this.text = text;
		this.color = color;
	}

	public BitmapFormatBuilder(String text, boolean bold) {
		this.text = text;
		this.bold = bold;
	}

	public BitmapFormatBuilder(String text, Color color, boolean bold) {
		this.text = text;
		this.color = color;
		this.bold = bold;
	}

	public BitmapFormatBuilder(String text, Color color, boolean bold, Vector2f size, Vector2f offset) {
		this.text = text;
		this.color = color;
		this.bold = bold;
		this.size = size;
		this.offset = offset;
	}

	public BitmapFormatBuilder setText(String text) {
		this.text = text;
		return this;
	}

	public BitmapFormatBuilder setBold(boolean bold) {
		this.bold = bold;
		return this;
	}

	public BitmapFormatBuilder setOffset(Vector2f offset) {
		this.offset = offset;
		return this;
	}

	public BitmapFormatBuilder setSize(Vector2f size) {
		this.size = size;
		return this;
	}

	public BitmapFormatBuilder setColor(Color color) {
		this.color = color;
		return this;
	}
}
