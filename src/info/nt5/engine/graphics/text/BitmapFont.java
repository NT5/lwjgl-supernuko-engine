package info.nt5.engine.graphics.text;

import java.util.*;

import info.nt5.engine.graphics.Color;
import info.nt5.engine.graphics.Texture;
import info.nt5.engine.math.Vector3f;

public class BitmapFont {
	private ArrayList<BitmapChar> CharList = new ArrayList<BitmapChar>();

	private int delta;
	private int CurrentRenderList;

	private String text;
	private Texture texture;
	private Vector3f position;
	private boolean textBold;
	private float width, height, Xoffset, Yoffset, boldOffset = 0.02f;
	private Color color;

	private static final String defaultBitmapFile = BitmapChar.getDefaultFontPath();

	private static final float defaultWidth = 0.2f;
	private static final float defaultHeight = 0.2f;
	private static final Color defaultColor = Color.WHITE;

	private static final float defaultXoffset = 0.0f;
	private static final float defaultYoffset = 0.20f;

	private static final Vector3f defaultPosition = new Vector3f();

	public BitmapFont(String text) {
		this(text, defaultXoffset, defaultYoffset);
	}

	public BitmapFont(String text, boolean bold) {
		this(text, defaultXoffset, defaultYoffset, bold);
	}

	public BitmapFont(String text, Color color) {
		this(text, defaultXoffset, defaultYoffset, color);
	}

	public BitmapFont(String text, Color color, boolean bold) {
		this(text, defaultXoffset, defaultYoffset, color, bold);
	}

	public BitmapFont(String text, float Xoffset, float Yoffset) {
		this(text, Xoffset, Yoffset, defaultWidth, defaultHeight);
	}

	public BitmapFont(String text, float Xoffset, float Yoffset, boolean bold) {
		this(text, Xoffset, Yoffset, defaultWidth, defaultHeight, bold);
	}

	public BitmapFont(String text, float Xoffset, float Yoffset, Color color) {
		this(text, Xoffset, Yoffset, defaultWidth, defaultHeight, color);
	}

	public BitmapFont(String text, float Xoffset, float Yoffset, Color color, boolean bold) {
		this(text, Xoffset, Yoffset, defaultWidth, defaultHeight, color, bold);
	}

	public BitmapFont(String text, float Xoffset, float Yoffset, float width, float height) {
		this(text, Xoffset, Yoffset, width, height, defaultBitmapFile);
	}

	public BitmapFont(String text, float Xoffset, float Yoffset, float width, float height, boolean bold) {
		this(text, Xoffset, Yoffset, width, height, defaultBitmapFile, bold);
	}

	public BitmapFont(String text, float Xoffset, float Yoffset, float width, float height, Color color) {
		this(text, Xoffset, Yoffset, width, height, defaultBitmapFile, color);
	}

	public BitmapFont(String text, float Xoffset, float Yoffset, float width, float height, Color color, boolean bold) {
		this(text, Xoffset, Yoffset, width, height, defaultBitmapFile, color, bold);
	}

	public BitmapFont(String text, float Xoffset, float Yoffset, float width, float height, String texturePath) {
		this(text, Xoffset, Yoffset, width, width, Texture.fromImage(defaultBitmapFile), defaultPosition.copy());
	}

	public BitmapFont(String text, float Xoffset, float Yoffset, float width, float height, String texturePath,
			boolean bold) {
		this(text, Xoffset, Yoffset, width, width, Texture.fromImage(defaultBitmapFile), defaultPosition.copy(), bold);
	}

	public BitmapFont(String text, float Xoffset, float Yoffset, float width, float height, String texturePath,
			Color color) {
		this(text, Xoffset, Yoffset, width, width, Texture.fromImage(defaultBitmapFile), defaultPosition.copy(), color);
	}

	public BitmapFont(String text, float Xoffset, float Yoffset, float width, float height, String texturePath,
			Color color, boolean bold) {
		this(text, Xoffset, Yoffset, width, width, Texture.fromImage(defaultBitmapFile), defaultPosition.copy(), color,
				bold);
	}

	public BitmapFont(String text, Vector3f position) {
		this(text, defaultXoffset, defaultYoffset, position);
	}

	public BitmapFont(String text, Vector3f position, boolean bold) {
		this(text, defaultXoffset, defaultYoffset, position, bold);
	}

	public BitmapFont(String text, Vector3f position, Color color) {
		this(text, defaultXoffset, defaultYoffset, position, color);
	}

	public BitmapFont(String text, Vector3f position, Color color, boolean bold) {
		this(text, defaultXoffset, defaultYoffset, position, color, bold);
	}

	public BitmapFont(String text, float Xoffset, float Yoffset, Vector3f position) {
		this(text, Xoffset, Yoffset, defaultWidth, defaultHeight, position);
	}

	public BitmapFont(String text, float Xoffset, float Yoffset, Vector3f position, boolean bold) {
		this(text, Xoffset, Yoffset, defaultWidth, defaultHeight, position, bold);
	}

	public BitmapFont(String text, float Xoffset, float Yoffset, Vector3f position, Color color) {
		this(text, Xoffset, Yoffset, defaultWidth, defaultHeight, position, color);
	}

	public BitmapFont(String text, float Xoffset, float Yoffset, Vector3f position, Color color, boolean bold) {
		this(text, Xoffset, Yoffset, defaultWidth, defaultHeight, position, color, bold);
	}

	public BitmapFont(String text, float Xoffset, float Yoffset, float width, float height, Vector3f position) {
		this(text, Xoffset, Yoffset, width, height, defaultBitmapFile, position);
	}

	public BitmapFont(String text, float Xoffset, float Yoffset, float width, float height, Vector3f position,
			boolean bold) {
		this(text, Xoffset, Yoffset, width, height, defaultBitmapFile, position, bold);
	}

	public BitmapFont(String text, float Xoffset, float Yoffset, float width, float height, Vector3f position,
			Color color) {
		this(text, Xoffset, Yoffset, width, height, defaultBitmapFile, position, color);
	}

	public BitmapFont(String text, float Xoffset, float Yoffset, float width, float height, Vector3f position,
			Color color, boolean bold) {
		this(text, Xoffset, Yoffset, width, height, defaultBitmapFile, position, color, bold);
	}

	public BitmapFont(String text, float Xoffset, float Yoffset, float width, float height, String texturePath,
			Vector3f position) {
		this(text, Xoffset, Yoffset, width, width, Texture.fromImage(defaultBitmapFile), position);
	}

	public BitmapFont(String text, float Xoffset, float Yoffset, float width, float height, String texturePath,
			Vector3f position, boolean bold) {
		this(text, Xoffset, Yoffset, width, width, Texture.fromImage(defaultBitmapFile), position, bold);
	}

	public BitmapFont(String text, float Xoffset, float Yoffset, float width, float height, String texturePath,
			Vector3f position, Color color) {
		this(text, Xoffset, Yoffset, width, width, Texture.fromImage(defaultBitmapFile), position, color);
	}

	public BitmapFont(String text, float Xoffset, float Yoffset, float width, float height, String texturePath,
			Vector3f position, Color color, boolean bold) {
		this(text, Xoffset, Yoffset, width, width, Texture.fromImage(defaultBitmapFile), position, color, bold);
	}

	public BitmapFont(String text, float Xoffset, float Yoffset, float width, float height, Texture texture,
			Vector3f position) {
		this(text, Xoffset, Yoffset, width, width, texture, position, defaultColor);
	}

	public BitmapFont(String text, float Xoffset, float Yoffset, float width, float height, Texture texture,
			Vector3f position, boolean bold) {
		this(text, Xoffset, Yoffset, width, width, texture, position, defaultColor, bold);
	}

	public BitmapFont(String text, float Xoffset, float Yoffset, float width, float height, Texture texture,
			Vector3f position, Color color) {
		this(text, Xoffset, Yoffset, width, height, texture, position, color, false);
	}

	public BitmapFont(String text, float Xoffset, float Yoffset, float width, float height, Texture texture,
			Vector3f position, Color color, boolean bold) {
		this.text = text;
		this.texture = texture;
		this.position = position;

		this.Xoffset = Xoffset;
		this.Yoffset = Yoffset;
		this.width = width;
		this.height = height;
		this.color = color;
		this.textBold = bold;

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

			BitmapChar Char = new BitmapChar(asciiCode, this.width, this.height, this.texture, position, color);

			CharList.add(Char);

			XPos += Xoffset;
		}
	}

	public void setBold() {
		this.textBold = (textBold ? false : true);
	}

	public void setBold(boolean b) {
		this.textBold = b;
	}

	public boolean getBold() {
		return this.textBold;
	}

	public Color getColor() {
		return this.color;
	}

	public void setColor(Color color) {
		this.color = color;
		for (BitmapChar Char : CharList) {
			Char.setColor(color);
		}
	}

	public void translate(Vector3f vector) {
		for (BitmapChar Char : CharList) {
			Char.translate(vector);
		}
	}

	public void translateX(float x) {
		for (BitmapChar Char : CharList) {
			Char.translateX(x);
		}
	}

	public void translateY(float y) {
		for (BitmapChar Char : CharList) {
			Char.translateY(y);
		}
	}

	public void translateZ(float z) {
		for (BitmapChar Char : CharList) {
			Char.translateZ(z);
		}
	}

	public void render() {
		render(-1);
	}

	public void render(int speed) {
		if (isRenderListEnd()) {
		} else if (speed <= 0) {
			CurrentRenderList = CharList.size();
		} else {
			if (CurrentRenderList < CharList.size()) {
				if ((delta % speed) == 0) {
					delta = 0;
					CurrentRenderList++;
				}
				delta++;
			}
		}

		for (BitmapChar Char : CharList.subList(0, CurrentRenderList)) {
			Char.render();
			if (textBold) {
				Char.position.x += boldOffset;
				Char.render();
				Char.position.x = Char.position.x - boldOffset;
			}
		}
	}

	public boolean isRenderListEnd() {
		return (CurrentRenderList >= CharList.size() ? true : false);
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
