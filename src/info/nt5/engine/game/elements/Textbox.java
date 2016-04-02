package info.nt5.engine.game.elements;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;

import java.util.ArrayList;
import java.util.List;

import info.nt5.engine.game.GameObject;
import info.nt5.engine.graphics.Color;
import info.nt5.engine.graphics.Texture;
import info.nt5.engine.graphics.shader.Shader;
import info.nt5.engine.graphics.shader.VertexQuad;
import info.nt5.engine.graphics.text.BitmapFont;
import info.nt5.engine.math.Matrix4f;
import info.nt5.engine.math.Vector3f;

public class Textbox extends GameObject {

	private static final String defaultTexturePath = "assets/img/textbox.png";

	private static final float defaultwidth = 9.8f;
	private static final float defaultheight = 1.3f;
	private static final Vector3f defaultPosition = new Vector3f();

	private float width;
	private float heigth;

	private BitmapFont text;
	private Textbox textboxHeader;

	private int textSpeed;

	private int currentTextId;
	private List<String> textCollection = new ArrayList<String>();

	public Textbox() {
		this(defaultwidth, defaultheight, defaultPosition.copy());
	}

	public Textbox(String texturePath) {
		this(texturePath, defaultPosition.copy());
	}

	public Textbox(String texturePath, String text) {
		this(texturePath, defaultPosition.copy(), text);
	}

	public Textbox(String texturePath, Vector3f position) {
		this(texturePath, defaultwidth, defaultheight, position);
	}

	public Textbox(String texturePath, Vector3f position, String text) {
		this(texturePath, defaultwidth, defaultheight, position, text);
	}

	public Textbox(String texturePath, float wd) {
		this(texturePath, wd, defaultPosition.copy());
	}

	public Textbox(String texturePath, float wd, String text) {
		this(texturePath, wd, defaultPosition.copy(), text);
	}

	public Textbox(String texturePath, float wd, Vector3f position) {
		this(texturePath, wd, wd, position);
	}

	public Textbox(String texturePath, float wd, Vector3f position, String text) {
		this(texturePath, wd, wd, position, text);
	}

	public Textbox(String texturePath, float width, float height) {
		this(texturePath, width, height, defaultPosition.copy());
	}

	public Textbox(String texturePath, float width, float height, String text) {
		this(texturePath, width, height, defaultPosition.copy(), text);
	}

	public Textbox(String texturePath, float width, float height, Vector3f position) {
		this(Texture.fromImage(texturePath), width, height, position);
	}

	public Textbox(String texturePath, float width, float height, Vector3f position, String text) {
		this(Texture.fromImage(texturePath), width, height, position, text);
	}

	public Textbox(Color color) {
		this(color, defaultPosition.copy());
	}

	public Textbox(Color color, String text) {
		this(color, defaultPosition.copy(), text);
	}

	public Textbox(Color color, Vector3f position) {
		this(color, defaultwidth, defaultheight, position);
	}

	public Textbox(Color color, Vector3f position, String text) {
		this(color, defaultwidth, defaultheight, position, text);
	}

	public Textbox(Color color, float wd) {
		this(color, wd, defaultPosition.copy());
	}

	public Textbox(Color color, float wd, String text) {
		this(color, wd, defaultPosition.copy(), text);
	}

	public Textbox(Color color, float wd, Vector3f position) {
		this(color, wd, wd, position);
	}

	public Textbox(Color color, float wd, Vector3f position, String text) {
		this(color, wd, wd, position, text);
	}

	public Textbox(Color color, float width, float height) {
		this(color, width, height, defaultPosition.copy());
	}

	public Textbox(Color color, float width, float height, String text) {
		this(color, width, height, defaultPosition.copy(), text);
	}

	public Textbox(Color color, float width, float height, Vector3f position) {
		this(Texture.fromColor(color, 64, 64), width, height, position);
	}

	public Textbox(Color color, float width, float height, Vector3f position, String text) {
		this(Texture.fromColor(color, 64, 64), width, height, position, text);
	}

	public Textbox(Texture texture) {
		this(texture, defaultPosition.copy());
	}

	public Textbox(Texture texture, String text) {
		this(texture, defaultPosition.copy(), text);
	}

	public Textbox(Texture texture, Vector3f position) {
		this(texture, defaultwidth, defaultheight, position);
	}

	public Textbox(Texture texture, Vector3f position, String text) {
		this(texture, defaultwidth, defaultheight, position, text);
	}

	public Textbox(Texture texture, float wd) {
		this(texture, wd, defaultPosition.copy());
	}

	public Textbox(Texture texture, float wd, String text) {
		this(texture, wd, defaultPosition.copy(), text);
	}

	public Textbox(Texture texture, float wd, Vector3f position) {
		this(texture, wd, wd, position);
	}

	public Textbox(Texture texture, float wd, Vector3f position, String text) {
		this(texture, wd, wd, position, text);
	}

	public Textbox(Texture texture, float width, float height) {
		this(texture, width, height, defaultPosition.copy());
	}

	public Textbox(Texture texture, float width, float height, String text) {
		this(texture, width, height, defaultPosition.copy(), text);
	}

	public Textbox(Texture texture, float width, float height, Vector3f position) {
		this(new VertexQuad(width, height), texture, position);
	}

	public Textbox(Texture texture, float width, float height, Vector3f position, String text) {
		this(new VertexQuad(width, height), texture, position, text);
	}

	public Textbox(Vector3f position) {
		this(defaultwidth, defaultheight, position);
	}

	public Textbox(Vector3f position, String text) {
		this(defaultwidth, defaultheight, position, text);
	}

	public Textbox(float wd) {
		this(wd, defaultPosition.copy());
	}

	public Textbox(float wd, String text) {
		this(wd, defaultPosition.copy(), text);
	}

	public Textbox(float wd, Vector3f position) {
		this(wd, wd, position);
	}

	public Textbox(float wd, Vector3f position, String text) {
		this(wd, wd, position, text);
	}

	public Textbox(float width, float height) {
		this(new VertexQuad(width, height));
	}

	public Textbox(float width, float height, String text) {
		this(new VertexQuad(width, height), text);
	}

	public Textbox(float width, float height, Vector3f position) {
		this(new VertexQuad(width, height), position);
	}

	public Textbox(float width, float height, Vector3f position, String text) {
		this(new VertexQuad(width, height), position, text);
	}

	public Textbox(VertexQuad quad) {
		this(quad, defaultPosition.copy());
	}

	public Textbox(VertexQuad quad, String text) {
		this(quad, defaultPosition.copy(), text);
	}

	public Textbox(VertexQuad quad, Vector3f position) {
		this(quad, Texture.fromImage(defaultTexturePath), position);
	}

	public Textbox(VertexQuad quad, Vector3f position, String text) {
		this(quad, Texture.fromImage(defaultTexturePath), position, text);
	}

	public Textbox(VertexQuad quad, Texture texture) {
		this(quad, texture, defaultPosition.copy());
	}

	public Textbox(VertexQuad quad, Texture texture, String text) {
		this(quad, texture, defaultPosition.copy(), text);
	}

	public Textbox(VertexQuad quad, Texture texture, Vector3f position) {
		super(quad, texture, position);
		this.heigth = quad.height;
		this.width = quad.width;
	}

	public Textbox(VertexQuad quad, Texture texture, Vector3f position, String text) {
		super(quad, texture, position);
		this.heigth = quad.height;
		this.width = quad.width;
		this.text = new BitmapFont(parseText(text), calcFontPosition());
	}

	public Vector3f calcFontPosition() {
		return new Vector3f((position.x - (this.width - 0.20f)), (position.y + (this.heigth - 0.70f)), position.z);
	}

	public int calcTextRenderTime() {
		return (int) ((this.getText().length() * this.getTextSpeed()) / 3) + 3;
	}

	private String parseText(String text) {
		StringBuilder str_new = new StringBuilder();
		float limit = (this.width * 2) - 0.95f;
		float current = 0;
		for (int i = 0; i < text.length(); i++) {
			if (current >= limit) {
				if (text.charAt(i) != 32 && text.charAt(i - 1) != 32) {
					str_new.append("_");
				}
				str_new.append("\n");
				current = 0;
			}
			current += 0.2f;
			str_new.append(text.charAt(i));
		}
		return str_new.toString();
	}

	public float getwidth() {
		return this.width;
	}

	public float getheigth() {
		return this.heigth;
	}

	public int getTextSpeed() {
		return textSpeed;
	}

	public String getText() {
		return this.text.getText();
	}

	public void setTextSpeed(int speed) {
		this.textSpeed = speed;
	}

	public void setTextBold(boolean bold) {
		this.text.setBold(bold);
	}

	public void setTextColor(Color color) {
		this.text.setColor(color);
	}

	public void setHeaderTextColor(Color color) {
		this.textboxHeader.setTextColor(color);
	}

	public void setHeaderTextBold(boolean bold) {
		this.textboxHeader.setTextBold(bold);
	}

	public void setHeaderText(String text) {
		this.textboxHeader = new Textbox(this.texture, this.width, 0.35f,
				new Vector3f((position.x), (position.y + (0.5f + this.heigth)), position.z), text);
		this.setHeaderTextBold(true);
		this.setHeaderTextColor(this.text.getColor());
	}

	public void setText(String text) {
		if (this.text != null) {
			this.text.dispose();
		}
		this.text = new BitmapFont(parseText(text), calcFontPosition());
	}

	public void setText(String text, Color color) {
		if (this.text != null) {
			this.text.dispose();
		}
		this.text = new BitmapFont(parseText(text), calcFontPosition(), color);
	}

	public void setText(String text, Color color, boolean bold) {
		if (this.text != null) {
			this.text.dispose();
		}
		this.text = new BitmapFont(parseText(text), calcFontPosition(), color, bold);
	}

	public void setText(String text, boolean bold) {
		if (this.text != null) {
			this.text.dispose();
		}
		this.text = new BitmapFont(parseText(text), calcFontPosition(), bold);
	}

	public void setText(BitmapFont text) {
		if (this.text != null) {
			this.text.dispose();
		}
		this.text = text;
		this.text.translate(calcFontPosition());
	}

	public String getTextCollection(int index) {
		return textCollection.get(index);
	}

	public List<String> getTextCollection() {
		return textCollection;
	}

	public String getCollectionCurrent() {
		return getTextCollection(currentTextId);
	}

	public void setCurrentCollection() {
		setText(getCollectionCurrent());
	}

	public void setNextCollection() {
		if (currentTextId != textCollection.size() - 1) {
			currentTextId++;
		} else {
			currentTextId = 0;
		}
		setCurrentCollection();
	}

	public void setPrevCollection() {
		if (currentTextId != 0) {
			currentTextId--;
		} else {
			currentTextId = (textCollection.size() - 1);
		}
		setCurrentCollection();
	}

	public void addTextCollection(String text) {
		textCollection.add(text);
	}

	public void addTextCollection(String[] texts) {
		for (String text : texts) {
			textCollection.add(text);
		}
	}

	public void removeTextCollection(int index) {
		textCollection.remove(index);
	}

	@Override
	public void translate(Vector3f vector) {
		position.x += vector.x;
		position.y += vector.y;
		position.z += vector.z;

		if (this.text != null) {
			text.translate(vector);
		}

		if (textboxHeader != null) {
			textboxHeader.translate(vector);
		}
	}

	@Override
	public void translateX(float x) {
		position.x += x;

		if (this.text != null) {
			text.translateX(x);
		}

		if (textboxHeader != null) {
			textboxHeader.translateX(x);
		}
	}

	@Override
	public void translateY(float y) {
		position.y += y;
		if (this.text != null) {
			text.translateY(y);
		}

		if (textboxHeader != null) {
			textboxHeader.translateY(y);
		}
	}

	@Override
	public void translateZ(float z) {
		position.z += z;

		if (this.text != null) {
			text.translateZ(z);
		}

		if (textboxHeader != null) {
			textboxHeader.translateZ(z);
		}

	}

	@Override
	public void render() {
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

		texture.bind();
		Shader.geometryShader.bind();
		Shader.geometryShader.setUniformMat4f("ml_matrix", Matrix4f.translate(position));
		VAO.render();
		Shader.geometryShader.unbind();
		texture.unbind();

		glDisable(GL_BLEND);

		if (this.text != null) {
			if (textSpeed > 0) {
				this.text.render(textSpeed);
			} else {
				this.text.render();
			}
		}

		if (textboxHeader != null) {
			textboxHeader.render();
		}
	}

	@Override
	public void dispose() {

		texture.dispose();
		VAO.dispose();

		if (textboxHeader != null) {
			textboxHeader.dispose();
		}

		if (this.text != null) {
			text.dispose();
		}
	}
}
