package info.nt5.engine.graphics.text;

import java.util.ArrayList;
import java.util.List;

import info.nt5.engine.math.Vector3f;

public class BitmapBuilder {
	private List<BitmapFont> text = new ArrayList<BitmapFont>();
	private Vector3f position;

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
			Vector3f position = (

			this.text.size() == 0 ? this.position : this.text.get((this.text.size() - 1)).getCursorPos()

			);

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
}
