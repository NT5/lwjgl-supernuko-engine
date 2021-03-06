package info.nt5.engine.game.elements.textbox;

import java.util.ArrayList;
import java.util.List;

import info.nt5.engine.game.animation.Animation;
import info.nt5.engine.game.elements.GameObject;
import info.nt5.engine.graphics.Camera;
import info.nt5.engine.graphics.Color;
import info.nt5.engine.graphics.Texture;
import info.nt5.engine.graphics.shader.VertexQuad;
import info.nt5.engine.graphics.text.BitmapFont;
import info.nt5.engine.graphics.text.BitmapFormat;
import info.nt5.engine.graphics.text.FontEventHandler;
import info.nt5.engine.math.Matrix4f;
import info.nt5.engine.math.Vector3f;
import info.nt5.engine.sound.ALPlayer;
import info.nt5.engine.util.FilePaths;

public class Textbox extends GameObject {

	private static final String defaultTexturePath = "assets/img/textbox.png";

	private static final float defaultwidth = 9.8f;
	private static final float defaultheight = 1.3f;
	private static final Vector3f defaultPosition = new Vector3f();

	private float width;
	private float heigth;

	private Textbox textboxHeader;

	FontEventHandler textEventHandler;
	TextboxEventHandler textboxEventHandler;

	Animation actorCallback;

	private int currentTextId;
	private List<BitmapFont> text = new ArrayList<BitmapFont>();

	ALPlayer sound;

	public Textbox() {
		this(defaultwidth, defaultheight, defaultPosition.copy());
	}

	public Textbox(String texturePath) {
		this(texturePath, defaultPosition.copy());
	}

	public Textbox(String texturePath, BitmapFormat text) {
		this(texturePath, defaultPosition.copy(), text);
	}

	public Textbox(String texturePath, Vector3f position) {
		this(texturePath, defaultwidth, defaultheight, position);
	}

	public Textbox(String texturePath, Vector3f position, BitmapFormat text) {
		this(texturePath, defaultwidth, defaultheight, position, text);
	}

	public Textbox(String texturePath, float wd) {
		this(texturePath, wd, defaultPosition.copy());
	}

	public Textbox(String texturePath, float wd, BitmapFormat text) {
		this(texturePath, wd, defaultPosition.copy(), text);
	}

	public Textbox(String texturePath, float wd, Vector3f position) {
		this(texturePath, wd, wd, position);
	}

	public Textbox(String texturePath, float wd, Vector3f position, BitmapFormat text) {
		this(texturePath, wd, wd, position, text);
	}

	public Textbox(String texturePath, float width, float height) {
		this(texturePath, width, height, defaultPosition.copy());
	}

	public Textbox(String texturePath, float width, float height, BitmapFormat text) {
		this(texturePath, width, height, defaultPosition.copy(), text);
	}

	public Textbox(String texturePath, float width, float height, Vector3f position) {
		this(Texture.fromImage(texturePath), width, height, position);
	}

	public Textbox(String texturePath, float width, float height, Vector3f position, BitmapFormat text) {
		this(Texture.fromImage(texturePath), width, height, position, text);
	}

	public Textbox(Color color) {
		this(color, defaultPosition.copy());
	}

	public Textbox(Color color, BitmapFormat text) {
		this(color, defaultPosition.copy(), text);
	}

	public Textbox(Color color, Vector3f position) {
		this(color, defaultwidth, defaultheight, position);
	}

	public Textbox(Color color, Vector3f position, BitmapFormat text) {
		this(color, defaultwidth, defaultheight, position, text);
	}

	public Textbox(Color color, float wd) {
		this(color, wd, defaultPosition.copy());
	}

	public Textbox(Color color, float wd, BitmapFormat text) {
		this(color, wd, defaultPosition.copy(), text);
	}

	public Textbox(Color color, float wd, Vector3f position) {
		this(color, wd, wd, position);
	}

	public Textbox(Color color, float wd, Vector3f position, BitmapFormat text) {
		this(color, wd, wd, position, text);
	}

	public Textbox(Color color, float width, float height) {
		this(color, width, height, defaultPosition.copy());
	}

	public Textbox(Color color, float width, float height, BitmapFormat text) {
		this(color, width, height, defaultPosition.copy(), text);
	}

	public Textbox(Color color, float width, float height, Vector3f position) {
		this(Texture.fromColor(color, 64, 64), width, height, position);
	}

	public Textbox(Color color, float width, float height, Vector3f position, BitmapFormat text) {
		this(Texture.fromColor(color, 64, 64), width, height, position, text);
	}

	public Textbox(Texture texture) {
		this(texture, defaultPosition.copy());
	}

	public Textbox(Texture texture, BitmapFormat text) {
		this(texture, defaultPosition.copy(), text);
	}

	public Textbox(Texture texture, Vector3f position) {
		this(texture, defaultwidth, defaultheight, position);
	}

	public Textbox(Texture texture, Vector3f position, BitmapFormat text) {
		this(texture, defaultwidth, defaultheight, position, text);
	}

	public Textbox(Texture texture, float wd) {
		this(texture, wd, defaultPosition.copy());
	}

	public Textbox(Texture texture, float wd, BitmapFormat text) {
		this(texture, wd, defaultPosition.copy(), text);
	}

	public Textbox(Texture texture, float wd, Vector3f position) {
		this(texture, wd, wd, position);
	}

	public Textbox(Texture texture, float wd, Vector3f position, BitmapFormat text) {
		this(texture, wd, wd, position, text);
	}

	public Textbox(Texture texture, float width, float height) {
		this(texture, width, height, defaultPosition.copy());
	}

	public Textbox(Texture texture, float width, float height, BitmapFormat text) {
		this(texture, width, height, defaultPosition.copy(), text);
	}

	public Textbox(Texture texture, float width, float height, Vector3f position) {
		this(new VertexQuad(width, height), texture, position);
	}

	public Textbox(Texture texture, float width, float height, Vector3f position, BitmapFormat text) {
		this(new VertexQuad(width, height), texture, position, text);
	}

	public Textbox(Vector3f position) {
		this(defaultwidth, defaultheight, position);
	}

	public Textbox(Vector3f position, BitmapFormat text) {
		this(defaultwidth, defaultheight, position, text);
	}

	public Textbox(float wd) {
		this(wd, defaultPosition.copy());
	}

	public Textbox(float wd, BitmapFormat text) {
		this(wd, defaultPosition.copy(), text);
	}

	public Textbox(float wd, Vector3f position) {
		this(wd, wd, position);
	}

	public Textbox(float wd, Vector3f position, BitmapFormat text) {
		this(wd, wd, position, text);
	}

	public Textbox(float width, float height) {
		this(new VertexQuad(width, height));
	}

	public Textbox(float width, float height, BitmapFormat text) {
		this(new VertexQuad(width, height), text);
	}

	public Textbox(float width, float height, Vector3f position) {
		this(new VertexQuad(width, height), position);
	}

	public Textbox(float width, float height, Vector3f position, BitmapFormat text) {
		this(new VertexQuad(width, height), position, text);
	}

	public Textbox(VertexQuad quad) {
		this(quad, defaultPosition.copy());
	}

	public Textbox(VertexQuad quad, BitmapFormat text) {
		this(quad, defaultPosition.copy(), text);
	}

	public Textbox(VertexQuad quad, Vector3f position) {
		this(quad, Texture.fromImage(defaultTexturePath), position);
	}

	public Textbox(VertexQuad quad, Vector3f position, BitmapFormat text) {
		this(quad, Texture.fromImage(defaultTexturePath), position, text);
	}

	public Textbox(VertexQuad quad, Texture texture) {
		this(quad, texture, defaultPosition.copy());
	}

	public Textbox(VertexQuad quad, Texture texture, BitmapFormat text) {
		this(quad, texture, defaultPosition.copy(), text);
	}

	public Textbox(VertexQuad quad, Texture texture, Vector3f position) {
		this(quad, texture, position, new BitmapFormat(""));
	}

	public Textbox(VertexQuad quad, Texture texture, Vector3f position, BitmapFormat text) {
		super(quad, texture, position);
		this.heigth = quad.height;
		this.width = quad.width;

		this.textboxEventHandler = new TextboxEventHandlerClass(this);
		this.textEventHandler = new FontEventHandleClass(this);

		this.text.add(

				new BitmapFont(

						text.setText(parseText(text.size.x, text.text)),

						calcFontPosition()

				)

		);

		this.getCollectionCurrent().setEventHandler(this.textEventHandler);
		this.sound = new ALPlayer(FilePaths.getWav("click1.wav"));
		// this.sound.setLoop(true);
	}

	public void setEventHandler(TextboxEventHandler cb) {
		this.textboxEventHandler = cb;
	}

	public void bindAnimationCallback(Animation cb) {
		this.actorCallback = cb;
		this.textboxEventHandler.onBindAnimationCallback(cb);
	}

	public void unbindAnimationCallback() {
		this.actorCallback = null;
		this.textboxEventHandler.onUnbinAnimationCallback();
	}

	public void setGlobalTextSpeed(int speed) {
		this.textboxEventHandler.onSetGlobalTextSpeed(speed);
		for (BitmapFont text : this.text) {
			text.setRenderSpeed(speed);
		}
	}

	public List<BitmapFont> getTextCollection() {
		return this.text;
	}

	public BitmapFont getTextCollection(int index) {
		return this.text.get(index);
	}

	public BitmapFont getCollectionCurrent() {
		return getTextCollection(currentTextId);
	}

	public void setNextCollection() {
		if (currentTextId != this.text.size() - 1) {
			currentTextId++;
		} else {
			currentTextId = 0;
		}
		this.textboxEventHandler.onCollectionChange(getCollectionCurrent());
	}

	public void setPrevCollection() {
		if (currentTextId != 0) {
			currentTextId--;
		} else {
			currentTextId = (this.text.size() - 1);
		}
		this.textboxEventHandler.onCollectionChange(getCollectionCurrent());
	}

	public void addTextCollection(BitmapFont text) {
		text.translate(calcFontPosition());
		this.text.add(text);
		this.textboxEventHandler.onAddTextCollection(text);
	}

	public void addTextCollection(BitmapFont[] texts) {
		for (BitmapFont text : texts) {
			addTextCollection(text);
		}
	}

	public void removeTextCollection(int index) {
		this.text.remove(index);
		this.textboxEventHandler.onRemoveTextCollection(index);
	}

	public void addTextToCurrentCollection(String text) {
		this.getCollectionCurrent().addText(parseText(getCollectionCurrent().getFormat().size.x, text));
		this.textboxEventHandler.onAddTextToCurrentCollection(text);
	}

	public void addTextToCurrentCollection(String[] text) {
		for (String str : text) {
			addTextToCurrentCollection(str);
		}
	}

	private Vector3f calcFontPosition() {
		return new Vector3f((getPosition().x - (this.width - 0.10f)), (getPosition().y + (this.heigth - 0.55f)),
				getPosition().z);
	}

	private String parseText(float charWidth, String text) {
		StringBuilder str_new = new StringBuilder();
		float limit = ((this.width * 2) - 0.95f);
		float current = 0;
		if (!this.text.isEmpty()) {
			String[] prev_str = this.getCollectionCurrent().toString().split("\n");
			if (prev_str.length > 1) {
				current += charWidth * prev_str[prev_str.length - 1].length();
			}
		}
		for (int i = 0; i < text.length(); i++) {
			if (current >= limit) {
				if (text.charAt(i) != 32 && text.charAt(i - 1) != 32) {
					str_new.append("_");
				}
				str_new.append("\n");
				current = 0;
			}
			current += charWidth;
			str_new.append(text.charAt(i));
		}
		this.textboxEventHandler.onParseText(text, str_new.toString());
		return str_new.toString();
	}

	public float getwidth() {
		return this.width;
	}

	public float getheigth() {
		return this.heigth;
	}

	public String getCurrentText() {
		return this.getCollectionCurrent().toString();
	}

	public void setHeaderText(BitmapFormat text) {
		this.textboxHeader = new Textbox(

				this.getTexture(), this.width, 0.35f,

				new Vector3f((getPosition().x), (getPosition().y + (0.5f + this.heigth)), getPosition().z),

				text.setBold(true)

		);
		this.textboxEventHandler.onSetHeaderText(text);
	}

	@Override
	public Textbox setCamera(Camera camera) {
		super.setCamera(camera);

		if (this.getCollectionCurrent() != null) {
			this.getCollectionCurrent().setCamera(camera);
		}

		if (textboxHeader != null) {
			textboxHeader.setCamera(camera);
		}
		return this;
	}

	@Override
	public Textbox setMl_matrix(Matrix4f ml_matrix) {
		super.setMl_matrix(ml_matrix);

		if (this.getCollectionCurrent() != null) {
			this.getCollectionCurrent().setMl_matrix(ml_matrix);
		}

		if (textboxHeader != null) {
			textboxHeader.setMl_matrix(ml_matrix);
		}
		return this;
	}

	@Override
	public Textbox setScale(Vector3f scale) {
		super.setScale(scale);

		if (this.getCollectionCurrent() != null) {
			this.getCollectionCurrent().setScale(scale);
		}

		if (textboxHeader != null) {
			textboxHeader.setScale(scale);
		}
		return this;
	}

	@Override
	public Textbox setRotation(Vector3f rotation) {
		super.setRotation(rotation);

		if (this.getCollectionCurrent() != null) {
			this.getCollectionCurrent().setRotation(rotation);
		}

		if (textboxHeader != null) {
			textboxHeader.setRotation(rotation);
		}
		return this;
	}

	@Override
	public Textbox setPosition(Vector3f position) {
		super.setPosition(position);

		if (this.getCollectionCurrent() != null) {
			this.getCollectionCurrent().setPosition(this.calcFontPosition());
		}

		if (textboxHeader != null) {
			textboxHeader.setPosition(position);
		}
		return this;
	}

	@Override
	public Textbox translate(Vector3f vector) {
		super.translate(vector);

		if (this.getCollectionCurrent() != null) {
			this.getCollectionCurrent().translate(vector);
		}

		if (textboxHeader != null) {
			textboxHeader.translate(vector);
		}
		return this;
	}

	@Override
	public Textbox translateX(float x) {
		super.translateX(x);

		if (this.getCollectionCurrent() != null) {
			this.getCollectionCurrent().translateX(x);
		}

		if (textboxHeader != null) {
			textboxHeader.translateX(x);
		}
		return this;
	}

	@Override
	public Textbox translateY(float y) {
		super.translateY(y);

		if (this.getCollectionCurrent() != null) {
			this.getCollectionCurrent().translateY(y);
		}

		if (textboxHeader != null) {
			textboxHeader.translateY(y);
		}
		return this;
	}

	@Override
	public Textbox translateZ(float z) {
		super.translateZ(z);

		if (this.getCollectionCurrent() != null) {
			this.getCollectionCurrent().translateZ(z);
		}

		if (textboxHeader != null) {
			textboxHeader.translateZ(z);
		}
		return this;
	}

	@Override
	public void update() {
		super.update();

		if (textboxHeader != null) {
			textboxHeader.update();
		}

		if (this.getCollectionCurrent() != null) {
			this.getCollectionCurrent().update();
		}
		this.textboxEventHandler.onUpdate();
	}

	@Override
	public void render() {
		super.render();

		if (this.getCollectionCurrent() != null) {
			this.getCollectionCurrent().render();
		}

		if (textboxHeader != null) {
			textboxHeader.render();
		}
		this.textboxEventHandler.onRender();
	}

	@Override
	public void dispose() {
		super.dispose();

		sound.dispose();

		if (textboxHeader != null) {
			textboxHeader.dispose();
		}

		for (BitmapFont text : this.text) {
			text.dispose();
		}
		this.text.clear();
	}
}
