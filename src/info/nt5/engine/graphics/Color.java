package info.nt5.engine.graphics;

import info.nt5.engine.math.Vector4f;

public class Color {

	public float r, g, b, a;

	public static final Color WHITE = new Color(0xFFFFFF);
	public static final Color BLACK = new Color(0x000000);
	public static final Color RED = new Color(0xFF0000);
	public static final Color MAROON = new Color(0x800000);
	public static final Color GREEN = new Color(0x008000);
	public static final Color LIME = new Color(0x00FF00);
	public static final Color BLUE = new Color(0x0000FF);
	public static final Color CYAN = new Color(0x00C4CC);
	public static final Color SILVER = new Color(0xC0C0C0);
	public static final Color YELLOW = new Color(0xFFFF00);
	public static final Color GRAY = new Color(0x808080);
	public static final Color PURPLE = new Color(0x800080);
	public static final Color PINK = new Color(0xFFC0CB);

	public Color(float red, float green, float blue) {
		this.r = red;
		this.g = green;
		this.b = blue;
	}

	public Color(float red, float green, float blue, float alpha) {
		this.r = red;
		this.g = green;
		this.b = blue;
		this.a = alpha;
	}

	public Color(Color color, float a) {
		this(color.r, color.g, color.b, a);
	}

	public Color(Vector4f color) {
		this.r = color.x;
		this.g = color.y;
		this.b = color.z;
		this.a = color.w;
	}

	public Color withNewAlpha(float a) {
		this.a = a;

		return this;
	}

	public Color withAlpha(float a) {
		return new Color(this.r, this.g, this.b, a);
	}

	public Color(int rgba) {
		set(rgba);
	}

	public void set(int rgba) {
		this.r = ((rgba & 0x00FF0000) >> 16) / 255.0f;
		this.g = ((rgba & 0x0000FF00) >> 8) / 255.0f;
		this.b = (rgba & 0x000000FF) / 255.0f;
		this.a = 1 - ((rgba & 0xFF000000) >> 24) / 255.0f;
	}

	public static int toInt(Color color) {
		int r = (int) (color.r * 255);
		int g = (int) (color.g * 255);
		int b = (int) (color.b * 255);
		int a = (int) (color.a * 255);

		return ((r << 0) | (g << 8) | (b << 16) | (a << 24));
	}

}