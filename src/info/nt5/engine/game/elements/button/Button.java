package info.nt5.engine.game.elements.button;

import info.nt5.engine.game.elements.GameObject;
import info.nt5.engine.game.elements.util.Align;
import info.nt5.engine.game.elements.util.MouseAABB;
import info.nt5.engine.graphics.Camera;
import info.nt5.engine.graphics.Color;
import info.nt5.engine.graphics.Texture;
import info.nt5.engine.graphics.shader.VertexQuad;
import info.nt5.engine.graphics.text.BitmapFont;
import info.nt5.engine.graphics.text.BitmapFormat;
import info.nt5.engine.input.Mouse;
import info.nt5.engine.math.AABB;
import info.nt5.engine.math.Vector2f;
import info.nt5.engine.math.Vector3f;

public class Button extends GameObject {

	private AABB aabb;
	private MouseAABB mouseAabb;

	private BitmapFont text;

	private boolean overMouse;
	private boolean isCursorEnter;
	private boolean clicked;

	private int clickedButton;

	private Vector2f padding;

	private Texture overTexture;
	private Texture clickedTexture;
	private Texture normalTexture;

	private ButtonCallback buttonCallback;
	private Align align;

	public Button(MouseAABB mouseAabb) {
		this.text = new BitmapFont(new BitmapFormat("Button", Color.BLACK, new Vector2f(0.2f)));
		this.mouseAabb = mouseAabb;
		this.clickedButton = Mouse.MOUSE_BUTTON_1;
		this.padding = new Vector2f(0.2f);
		this.normalTexture = super.getTexture();
		this.overTexture = super.getTexture();
		this.clickedTexture = super.getTexture();
		this.buttonCallback = new ButtonCallbackClass();

		super.setQuad(new VertexQuad(this.text.getCursorMax().x + this.padding.x,
				this.text.getCursorMax().y + this.padding.y));
		this.setTextAlign(Align.CENTER);
	}

	public Button setTextAlign() {
		return this.setTextAlign(this.align);
	}

	public Button setTextAlign(Align align) {
		switch (align) {
		case UPPER_LEFT:
			super.setPositionX((text.getCursorMax().x + padding.x) + text.getPosition().x);
			super.setPositionY(
					(text.getFormat().size.y * 2f) - (text.getCursorMax().y + padding.y) + text.getPosition().y);
			break;

		default:
			super.setPositionX(((text.getCursorMax().x / 2f) + text.getCursorMax().x / 2f) + text.getPosition().x);
			super.setPositionY((text.getFormat().size.y * 2f)
					+ (-(text.getCursorMax().y / 2f) - text.getCursorMax().y / 2f) + text.getPosition().y);
			break;
		}
		this.align = align;
		return this;
	}

	public Button setPadding(Vector2f padding) {
		this.padding = padding;
		super.setQuad(new VertexQuad(this.text.getCursorMax().x + this.padding.x,
				this.text.getCursorMax().y + this.padding.y));
		this.setTextAlign();
		return this;
	}

	public Button setMouseAabb(MouseAABB mouseAabb) {
		this.mouseAabb = mouseAabb;
		return this;
	}

	public Button setClickedButton(int clickedButton) {
		this.clickedButton = clickedButton;
		return this;
	}

	public Button setText(BitmapFont text) {
		if (this.text != null) {
			this.text.dispose();
		}
		this.text = text;
		super.setQuad(new VertexQuad(this.text.getCursorMax().x + this.padding.x,
				this.text.getCursorMax().y + this.padding.y));
		this.setTextAlign();
		return this;
	}

	public Button setTextures(Texture normalTexture, Texture overTexture, Texture clickedTexture) {
		this.setNormalTexture(normalTexture);
		this.setOverTexture(overTexture);
		this.setClickedTexture(clickedTexture);
		return this;
	}

	public Button setNormalTexture(Texture normalTexture) {
		if (this.normalTexture != null)
			this.normalTexture.dispose();
		this.normalTexture = normalTexture;
		super.disposeTexture();
		super.setTexture(this.normalTexture);
		return this;
	}

	public Button setOverTexture(Texture overTexture) {
		if (this.overTexture != null)
			this.overTexture.dispose();
		this.overTexture = overTexture;
		return this;
	}

	public Button setClickedTexture(Texture clickedTexture) {
		if (this.clickedTexture != null)
			this.clickedTexture.dispose();
		this.clickedTexture = clickedTexture;
		return this;
	}

	public Button setButtonCallback(ButtonCallback callback) {
		this.buttonCallback = callback;
		return this;
	}

	public boolean isOverCursor() {
		return overMouse;
	}

	public boolean isClicked() {
		return clicked;
	}

	public Vector2f getPadding() {
		return padding;
	}

	public Texture getNormalTexture() {
		return normalTexture;
	}

	public Texture getOverTexture() {
		return overTexture;
	}

	public Texture getClickedTexture() {
		return clickedTexture;
	}

	public int getClickedButton() {
		return clickedButton;
	}

	public Vector2f getSize() {
		return new Vector2f(super.getQuad().width, super.getQuad().height);
	}

	@Override
	public Vector3f getPosition() {
		return this.text.getPosition();
	}

	@Override
	public void update() {
		super.update();

		this.text.update();
		this.aabb = new AABB(new Vector2f(super.getPosition().x, super.getPosition().y),
				new Vector2f(this.getQuad().width, this.getQuad().height));

		this.overMouse = (this.aabb.intersects(this.mouseAabb.getAABB()) ? true : false);
		if (this.overMouse) {
			this.buttonCallback.onOver();
			if (this.isCursorEnter == false) {
				this.isCursorEnter = true;
				super.setTexture(this.overTexture);
				this.buttonCallback.onCursorEnter();
			}
			if (Mouse.isClicked(this.clickedButton)) {
				this.buttonCallback.onClick();
				this.clicked = true;
				super.setTexture(this.clickedTexture);
			} else {
				this.clicked = false;
			}
		} else {
			if (this.isCursorEnter == true) {
				this.isCursorEnter = false;
				super.setTexture(this.normalTexture);
				this.buttonCallback.onCursorLeave();
			}
		}
	}

	@Override
	public Button setCamera(Camera camera) {
		this.text.setCamera(camera);
		super.setCamera(camera);
		return this;
	}

	@Override
	public Button setPosition(Vector3f position) {
		this.text.setPosition(position);
		this.setTextAlign();
		return this;
	}

	@Override
	public Button translate(Vector3f vector) {
		this.text.translate(vector);
		super.translate(vector);
		return this;
	}

	@Override
	public Button translateX(float x) {
		super.translateX(x);
		this.text.translateX(x);
		return this;
	}

	@Override
	public Button translateY(float y) {
		super.translateY(y);
		this.text.translateY(y);
		return this;
	}

	@Override
	public Button translateZ(float z) {
		super.translateZ(z);
		this.text.translateZ(z);
		return this;
	}

	@Override
	public void render() {
		super.render();
		this.text.render();
	}

	@Override
	public void dispose() {
		super.dispose();
		this.normalTexture.dispose();
		this.clickedTexture.dispose();
		this.overTexture.dispose();
		this.text.dispose();
	}
}
