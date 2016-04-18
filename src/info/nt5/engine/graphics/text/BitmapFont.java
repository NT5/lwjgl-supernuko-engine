package info.nt5.engine.graphics.text;

import java.util.ArrayList;
import java.util.List;

import info.nt5.engine.graphics.Color;
import info.nt5.engine.graphics.Texture;
import info.nt5.engine.math.Vector2f;
import info.nt5.engine.math.Vector3f;

public class BitmapFont {

	private static String defaultFont = "assets/img/font.png";

	private List<BitmapChar> CharList = new ArrayList<BitmapChar>();

	private FontEventHandler eventHandler;

	private int renderSpeed;
	private int delta;
	private int CurrentRenderList;

	private ArrayList<BitmapFormat> text = new ArrayList<BitmapFormat>();
	private Texture texture;
	private Vector3f position = new Vector3f();
	private Vector2f cursorPos = new Vector2f();

	public BitmapFont(BitmapFormat[] text) {
		this(text, Texture.fromImage(defaultFont), new Vector3f());
	}

	public BitmapFont(BitmapFormat[] text, Vector3f position) {
		this(text, Texture.fromImage(defaultFont), position);
	}

	public BitmapFont(BitmapFormat[] text, Texture texture, Vector3f position) {
		for (BitmapFormat bitmapFormat : text) {
			this.text.add(bitmapFormat);
		}
		this.texture = texture;
		this.position = position;

		class FontEventHandleClass implements FontEventHandler {
			@Override
			public void onCreateChar(int asciiCode, Vector3f position, Color color) {
			}

			@Override
			public void onUpdate(int speed, int delta) {
			}

			@Override
			public void onRender(int fromIndex, int toIndex) {
			}

			@Override
			public void onAddToRenderList(int currentRenderIndex) {
			}

			@Override
			public void onRenderListEnd() {
			}
		}

		this.eventHandler = new FontEventHandleClass();

		createChars(0, this.text.size());
	}

	private void createChars(int fromIndex, int toIndex) {
		for (BitmapFormat textPart : this.text.subList(fromIndex, toIndex)) {
			String text = textPart.text;

			Vector2f offset = new Vector2f(textPart.size.x + textPart.offset.x, textPart.size.y + textPart.offset.y);

			for (int i = 0; i < text.length(); i++) {
				char character = text.charAt(i);
				int asciiCode = (int) character;

				if (asciiCode == 10) {
					cursorPos.x = 0f;
					cursorPos.y -= offset.y;
					continue;
				}

				Vector3f position = new Vector3f(

						(this.position.x + cursorPos.x),

						(this.position.y + cursorPos.y),

						this.position.z

				);

				BitmapChar Char = new BitmapChar(

						asciiCode,

						textPart.size.x,

						textPart.size.y,

						this.texture,

						position,

						textPart.color,

						textPart.bold

				);

				CharList.add(Char);

				eventHandler.onCreateChar(asciiCode, position, textPart.color);

				cursorPos.x += offset.x;
			}
		}
	}

	public void setEventHandler(FontEventHandler callback) {
		this.eventHandler = callback;
	}

	public void setRenderSpeed(int speed) {
		this.renderSpeed = speed;
	}

	public int getRenderSpeed() {
		return this.renderSpeed;
	}

	public void addText(BitmapFormat[] text) {
		for (BitmapFormat bitmapFormat : text) {
			addText(bitmapFormat);
		}
	}

	public void addText(BitmapFormat text) {
		this.text.add(text);
		createChars(this.text.size() - 1, this.text.size());
	}

	public Vector3f getPosition() {
		return this.position;
	}

	public Vector2f getCursorPos() {
		return this.cursorPos;
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

	public void update() {
		if (!isRenderListEnd()) {
			if (this.renderSpeed <= 0) {
				CurrentRenderList = CharList.size();
				eventHandler.onRenderListEnd();
			} else {
				if (CurrentRenderList < CharList.size()) {
					if ((delta % this.renderSpeed) == 0) {
						delta = 0;
						CurrentRenderList++;
						eventHandler.onAddToRenderList(CurrentRenderList);
					}
					delta++;
				}
				if (isRenderListEnd()) {
					eventHandler.onRenderListEnd();
				}
			}
			eventHandler.onUpdate(this.renderSpeed, this.delta);
		}
	}

	public void render() {
		for (BitmapChar Char : CharList.subList(0, (this.renderSpeed > 0 ? CurrentRenderList : CharList.size()))) {
			Char.render();
		}
		eventHandler.onRender(0, (this.renderSpeed > 0 ? CurrentRenderList : CharList.size()));
	}

	public boolean isRenderListEnd() {
		return (CurrentRenderList >= CharList.size() ? true : false);
	}

	public int getCurrentRenderList() {
		return CurrentRenderList;
	}

	public void dispose() {
		texture.dispose();
		for (BitmapChar Char : CharList) {
			Char.dispose();
		}
		CharList.clear();
	}

	public List<BitmapChar> getCharList() {
		return CharList;
	}

	@Override
	public String toString() {
		StringBuilder str_new = new StringBuilder();
		for (BitmapFormat str : this.text) {
			str_new.append(str.text);
		}
		return str_new.toString();
	}

}
