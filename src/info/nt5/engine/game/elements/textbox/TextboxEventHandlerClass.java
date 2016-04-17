package info.nt5.engine.game.elements.textbox;

import info.nt5.engine.game.animation.Animation;
import info.nt5.engine.graphics.text.BitmapFont;
import info.nt5.engine.graphics.text.BitmapFormat;

public class TextboxEventHandlerClass implements TextboxEventHandler {

	private Textbox textbox;

	public TextboxEventHandlerClass(Textbox textbox) {
		this.textbox = textbox;
	}

	public Textbox getTextbox() {
		return this.textbox;
	}

	@Override
	public void onCollectionChange(BitmapFont currentCollection) {
		this.textbox.getCollectionCurrent().setEventHandler(this.textbox.textEventHandler);
	}

	@Override
	public void onAddTextToCurrentCollection(BitmapFormat text) {
	}

	@Override
	public void onAddTextCollection(BitmapFont text) {
	}

	@Override
	public void onRemoveTextCollection(int index) {
	}

	@Override
	public void onSetHeaderText(BitmapFormat text) {
	}

	@Override
	public void onParseText(String text, String parsedText) {
	}

	@Override
	public void onSetGlobalTextSpeed(int speed) {
	}

	@Override
	public void onBindAnimationCallback(Animation actor) {
	}

	@Override
	public void onUnbinAnimationCallback() {
	}

	@Override
	public void onUpdate() {
	}

	@Override
	public void onRender() {
	}

}
