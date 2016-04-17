package info.nt5.engine.game.elements.textbox;

import info.nt5.engine.game.animation.Animation;
import info.nt5.engine.graphics.text.BitmapFont;
import info.nt5.engine.graphics.text.BitmapFormat;

public interface TextboxEventHandler {

	public void onCollectionChange(BitmapFont currentCollection);

	public void onAddTextToCurrentCollection(BitmapFormat text);

	public void onAddTextCollection(BitmapFont text);

	public void onRemoveTextCollection(int index);

	public void onSetHeaderText(BitmapFormat text);

	public void onParseText(String text, String parsedText);

	public void onSetGlobalTextSpeed(int speed);

	public void onBindAnimationCallback(Animation actor);

	public void onUnbinAnimationCallback();

	public void onUpdate();

	public void onRender();

}
