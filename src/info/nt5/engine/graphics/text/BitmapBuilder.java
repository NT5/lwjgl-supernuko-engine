package info.nt5.engine.graphics.text;

import java.util.ArrayList;
import java.util.List;

import info.nt5.engine.math.Vector3f;

public class BitmapBuilder {
	private List<BitmapFont> text = new ArrayList<BitmapFont>();
	private Vector3f position;
	private int currentRenderId;

	public BitmapBuilder() {
		this(new Vector3f());
	}

	public BitmapBuilder(Vector3f position) {
		this.position = position;
	}

	public void add(BitmapFormatBuilder[] text) {
		for (BitmapFormatBuilder part : text) {
			add(part);
		}
	}

	public void add(BitmapFormatBuilder text) {
		if (text.text != null) {

			this.text.add(

					new BitmapFont(

							text.text,

							text.offset.x,

							text.offset.y,

							text.size.x,

							text.size.y,

							position,

							text.color,

							text.bold

					));
			this.position = this.text.get((this.text.size() - 1)).getEndCursorPos();
			if (text.text.charAt(text.text.length() - 1) == 10) {
				this.position = new Vector3f(

						this.text.get(0).getPosition().x,

						this.text.get((this.text.size() - 1)).getEndCursorPos().y,

						this.text.get((this.text.size() - 1)).getEndCursorPos().z

				);
			}
		}
	}

	public void translate(Vector3f vector) {
		for (BitmapFont text : this.text) {
			text.translate(vector);
		}
	}

	public void translateX(float x) {
		for (BitmapFont text : this.text) {
			text.translateX(x);
		}
	}

	public void translateY(float y) {
		for (BitmapFont text : this.text) {
			text.translateY(y);
		}
	}

	public void translateZ(float z) {
		for (BitmapFont text : this.text) {
			text.translateZ(z);
		}
	}

	public void render(int speed) {
		for (BitmapFont text : this.text.subList(0,
				currentRenderId < this.text.size() ? currentRenderId + 1 : currentRenderId)) {
			text.render(speed);
		}
		if (currentRenderId < this.text.size() && text.get(currentRenderId).isRenderListEnd()) {
			currentRenderId++;
		}
	}

	public void render() {
		for (BitmapFont text : this.text) {
			text.render();
		}
	}

	public void dispose() {
		for (BitmapFont text : this.text) {
			text.dispose();
		}
		text.clear();
	}

	public int getSize() {
		return text.size();
	}

	public boolean isRenderEnd() {
		return currentRenderId >= this.text.size();
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		for (BitmapFont text : this.text) {
			str.append(text.getText());
		}
		return str.toString();
	}
}
