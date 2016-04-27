package info.nt5.engine.game.elements.textbox;

import info.nt5.engine.graphics.text.FontEventHandler;

public class FontEventHandleClass implements FontEventHandler {
	private Textbox textbox;

	public FontEventHandleClass(Textbox textbox) {
		this.textbox = textbox;
	}

	@Override
	public void onCreateChar(int asciiCode) {
	}

	@Override
	public void onUpdate(int speed, int delta) {
	}

	@Override
	public void onRender() {
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
