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
import info.nt5.engine.graphics.text.BitmapBuilder;
import info.nt5.engine.graphics.text.BitmapFormatBuilder;
import info.nt5.engine.math.Matrix4f;
import info.nt5.engine.math.Vector3f;

public class Textbox extends GameObject {

	private static final String defaultTexturePath = "assets/img/textbox.png";

	private static final float defaultwidth = 9.8f;
	private static final float defaultheight = 1.3f;
	private static final Vector3f defaultPosition = new Vector3f();

	private float width;
	private float heigth;

	private Textbox textboxHeader;

	private int textSpeed;

	private int currentTextId;
	private List<BitmapBuilder> text = new ArrayList<BitmapBuilder>();

	public Textbox() {
		this(defaultwidth, defaultheight, defaultPosition.copy());
	}

	public Textbox(String texturePath) {
		this(texturePath, defaultPosition.copy());
	}

	public Textbox(String texturePath, BitmapFormatBuilder text) {
		this(texturePath, defaultPosition.copy(), text);
	}

	public Textbox(String texturePath, Vector3f position) {
		this(texturePath, defaultwidth, defaultheight, position);
	}

	public Textbox(String texturePath, Vector3f position, BitmapFormatBuilder text) {
		this(texturePath, defaultwidth, defaultheight, position, text);
	}

	public Textbox(String texturePath, float wd) {
		this(texturePath, wd, defaultPosition.copy());
	}

	public Textbox(String texturePath, float wd, BitmapFormatBuilder text) {
		this(texturePath, wd, defaultPosition.copy(), text);
	}

	public Textbox(String texturePath, float wd, Vector3f position) {
		this(texturePath, wd, wd, position);
	}

	public Textbox(String texturePath, float wd, Vector3f position, BitmapFormatBuilder text) {
		this(texturePath, wd, wd, position, text);
	}

	public Textbox(String texturePath, float width, float height) {
		this(texturePath, width, height, defaultPosition.copy());
	}

	public Textbox(String texturePath, float width, float height, BitmapFormatBuilder text) {
		this(texturePath, width, height, defaultPosition.copy(), text);
	}

	public Textbox(String texturePath, float width, float height, Vector3f position) {
		this(Texture.fromImage(texturePath), width, height, position);
	}

	public Textbox(String texturePath, float width, float height, Vector3f position, BitmapFormatBuilder text) {
		this(Texture.fromImage(texturePath), width, height, position, text);
	}

	public Textbox(Color color) {
		this(color, defaultPosition.copy());
	}

	public Textbox(Color color, BitmapFormatBuilder text) {
		this(color, defaultPosition.copy(), text);
	}

	public Textbox(Color color, Vector3f position) {
		this(color, defaultwidth, defaultheight, position);
	}

	public Textbox(Color color, Vector3f position, BitmapFormatBuilder text) {
		this(color, defaultwidth, defaultheight, position, text);
	}

	public Textbox(Color color, float wd) {
		this(color, wd, defaultPosition.copy());
	}

	public Textbox(Color color, float wd, BitmapFormatBuilder text) {
		this(color, wd, defaultPosition.copy(), text);
	}

	public Textbox(Color color, float wd, Vector3f position) {
		this(color, wd, wd, position);
	}

	public Textbox(Color color, float wd, Vector3f position, BitmapFormatBuilder text) {
		this(color, wd, wd, position, text);
	}

	public Textbox(Color color, float width, float height) {
		this(color, width, height, defaultPosition.copy());
	}

	public Textbox(Color color, float width, float height, BitmapFormatBuilder text) {
		this(color, width, height, defaultPosition.copy(), text);
	}

	public Textbox(Color color, float width, float height, Vector3f position) {
		this(Texture.fromColor(color, 64, 64), width, height, position);
	}

	public Textbox(Color color, float width, float height, Vector3f position, BitmapFormatBuilder text) {
		this(Texture.fromColor(color, 64, 64), width, height, position, text);
	}

	public Textbox(Texture texture) {
		this(texture, defaultPosition.copy());
	}

	public Textbox(Texture texture, BitmapFormatBuilder text) {
		this(texture, defaultPosition.copy(), text);
	}

	public Textbox(Texture texture, Vector3f position) {
		this(texture, defaultwidth, defaultheight, position);
	}

	public Textbox(Texture texture, Vector3f position, BitmapFormatBuilder text) {
		this(texture, defaultwidth, defaultheight, position, text);
	}

	public Textbox(Texture texture, float wd) {
		this(texture, wd, defaultPosition.copy());
	}

	public Textbox(Texture texture, float wd, BitmapFormatBuilder text) {
		this(texture, wd, defaultPosition.copy(), text);
	}

	public Textbox(Texture texture, float wd, Vector3f position) {
		this(texture, wd, wd, position);
	}

	public Textbox(Texture texture, float wd, Vector3f position, BitmapFormatBuilder text) {
		this(texture, wd, wd, position, text);
	}

	public Textbox(Texture texture, float width, float height) {
		this(texture, width, height, defaultPosition.copy());
	}

	public Textbox(Texture texture, float width, float height, BitmapFormatBuilder text) {
		this(texture, width, height, defaultPosition.copy(), text);
	}

	public Textbox(Texture texture, float width, float height, Vector3f position) {
		this(new VertexQuad(width, height), texture, position);
	}

	public Textbox(Texture texture, float width, float height, Vector3f position, BitmapFormatBuilder text) {
		this(new VertexQuad(width, height), texture, position, text);
	}

	public Textbox(Vector3f position) {
		this(defaultwidth, defaultheight, position);
	}

	public Textbox(Vector3f position, BitmapFormatBuilder text) {
		this(defaultwidth, defaultheight, position, text);
	}

	public Textbox(float wd) {
		this(wd, defaultPosition.copy());
	}

	public Textbox(float wd, BitmapFormatBuilder text) {
		this(wd, defaultPosition.copy(), text);
	}

	public Textbox(float wd, Vector3f position) {
		this(wd, wd, position);
	}

	public Textbox(float wd, Vector3f position, BitmapFormatBuilder text) {
		this(wd, wd, position, text);
	}

	public Textbox(float width, float height) {
		this(new VertexQuad(width, height));
	}

	public Textbox(float width, float height, BitmapFormatBuilder text) {
		this(new VertexQuad(width, height), text);
	}

	public Textbox(float width, float height, Vector3f position) {
		this(new VertexQuad(width, height), position);
	}

	public Textbox(float width, float height, Vector3f position, BitmapFormatBuilder text) {
		this(new VertexQuad(width, height), position, text);
	}

	public Textbox(VertexQuad quad) {
		this(quad, defaultPosition.copy());
	}

	public Textbox(VertexQuad quad, BitmapFormatBuilder text) {
		this(quad, defaultPosition.copy(), text);
	}

	public Textbox(VertexQuad quad, Vector3f position) {
		this(quad, Texture.fromImage(defaultTexturePath), position);
	}

	public Textbox(VertexQuad quad, Vector3f position, BitmapFormatBuilder text) {
		this(quad, Texture.fromImage(defaultTexturePath), position, text);
	}

	public Textbox(VertexQuad quad, Texture texture) {
		this(quad, texture, defaultPosition.copy());
	}

	public Textbox(VertexQuad quad, Texture texture, BitmapFormatBuilder text) {
		this(quad, texture, defaultPosition.copy(), text);
	}

	public Textbox(VertexQuad quad, Texture texture, Vector3f position) {
		super(quad, texture, position);
		this.heigth = quad.height;
		this.width = quad.width;
	}

	public Textbox(VertexQuad quad, Texture texture, Vector3f position, BitmapFormatBuilder text) {
		super(quad, texture, position);
		this.heigth = quad.height;
		this.width = quad.width;
		this.text.add(

				new BitmapBuilder(calcFontPosition(), text.setText(parseText(text.text)))

		);
	}

	public List<BitmapBuilder> getTextCollection() {
		return this.text;
	}

	public BitmapBuilder getTextCollection(int index) {
		return this.text.get(index);
	}

	public BitmapBuilder getCollectionCurrent() {
		return getTextCollection(currentTextId);
	}

	public void setNextCollection() {
		if (currentTextId != this.text.size() - 1) {
			currentTextId++;
		} else {
			currentTextId = 0;
		}
	}

	public void setPrevCollection() {
		if (currentTextId != 0) {
			currentTextId--;
		} else {
			currentTextId = (this.text.size() - 1);
		}
	}

	public void addTextCollection(BitmapBuilder text) {
		text.translate(calcFontPosition());
		this.text.add(text);
	}

	public void addTextCollection(BitmapBuilder[] texts) {
		for (BitmapBuilder text : texts) {
			addTextCollection(text);
		}
	}

	public void removeTextCollection(int index) {
		this.text.remove(index);
	}

	public void addTextToCurrentCollection(BitmapFormatBuilder text) {
		this.getCollectionCurrent().add(text.setText(parseText(text.text)));
	}

	public void addTextToCurrentCollection(BitmapFormatBuilder[] text) {
		for (BitmapFormatBuilder bitmapFormatBuilder : text) {
			addTextToCurrentCollection(bitmapFormatBuilder);
		}
	}

	public Vector3f calcFontPosition() {
		return new Vector3f((position.x - (this.width - 0.20f)), (position.y + (this.heigth - 0.70f)), position.z);
	}

	public int calcTextRenderTime() {
		return (int) (((this.getText().length() * this.getTextSpeed()) / 3) + 3);
	}

	private String parseText(String text) {
		StringBuilder str_new = new StringBuilder();
		float limit = ((this.width * 2) - 0.95f);
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
		return this.text.toString();
	}

	public void setTextSpeed(int speed) {
		this.textSpeed = speed;
	}

	public void setHeaderText(BitmapFormatBuilder text) {
		this.textboxHeader = new Textbox(

				this.texture, this.width, 0.35f,

				new Vector3f((position.x), (position.y + (0.5f + this.heigth)), position.z),

				text.setBold(true)

		);
	}

	@Override
	public void translate(Vector3f vector) {
		position.x += vector.x;
		position.y += vector.y;
		position.z += vector.z;

		if (this.getCollectionCurrent() != null) {
			this.getCollectionCurrent().translate(vector);
		}

		if (textboxHeader != null) {
			textboxHeader.translate(vector);
		}
	}

	@Override
	public void translateX(float x) {
		position.x += x;

		if (this.getCollectionCurrent() != null) {
			this.getCollectionCurrent().translateX(x);
		}

		if (textboxHeader != null) {
			textboxHeader.translateX(x);
		}
	}

	@Override
	public void translateY(float y) {
		position.y += y;

		if (this.getCollectionCurrent() != null) {
			this.getCollectionCurrent().translateY(y);
		}

		if (textboxHeader != null) {
			textboxHeader.translateY(y);
		}
	}

	@Override
	public void translateZ(float z) {
		position.z += z;

		if (this.getCollectionCurrent() != null) {
			this.getCollectionCurrent().translateZ(z);
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

		if (this.getCollectionCurrent() != null) {
			if (textSpeed > 0) {
				this.getCollectionCurrent().render(textSpeed);
			} else {
				this.getCollectionCurrent().render();
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

		for (BitmapBuilder text : this.text) {
			text.dispose();
		}
		this.text.clear();
	}
}
