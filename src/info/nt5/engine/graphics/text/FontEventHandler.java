package info.nt5.engine.graphics.text;

import info.nt5.engine.graphics.Color;
import info.nt5.engine.math.Vector3f;

public interface FontEventHandler {

	public void onCreateChar(int asciiCode, Vector3f position, Color color);

	public void onUpdate(int speed, int delta);

	public void onRender(int fromIndex, int toIndex);

	public void onAddToRenderList(int currentRenderIndex);

	public void onRenderListEnd();
}
