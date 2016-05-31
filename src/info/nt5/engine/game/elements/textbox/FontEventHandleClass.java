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
		if (this.textbox.actorCallback != null && !this.textbox.sound.isPlaying()) {
			this.textbox.sound.play();
			if (Math.random() > 0.80) {
				this.textbox.sound.setPitch(0.9f);
			} else {
				this.textbox.sound.setPitch(1f);
			}
		}
	}

	@Override
	public void onRenderListEnd() {
		if (this.textbox.actorCallback != null) {
			this.textbox.actorCallback.end();
			this.textbox.sound.stop();
		}
	}

}
