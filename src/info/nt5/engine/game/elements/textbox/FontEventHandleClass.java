package info.nt5.engine.game.elements.textbox;

import info.nt5.engine.graphics.Color;
import info.nt5.engine.graphics.text.FontEventHandler;
import info.nt5.engine.math.Vector3f;

public class FontEventHandleClass implements FontEventHandler {
	private Textbox textbox;

	public FontEventHandleClass(Textbox textbox) {
		this.textbox = textbox;
	}

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
		if (this.textbox.actorCallback != null && !this.textbox.actorCallback.isAnimating()) {
			this.textbox.actorCallback.set(3, 0, 2, -1);
		}
	}

	@Override
	public void onRenderListEnd() {
		if (this.textbox.actorCallback != null) {
			textbox.actorCallback.end();
		}
	}

}
