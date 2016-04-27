package info.nt5.engine.graphics.text;

public interface FontEventHandler {

	public void onCreateChar(int asciiCode);

	public void onUpdate(int speed, int delta);

	public void onRender();

	public void onAddToRenderList(int currentRenderIndex);

	public void onRenderListEnd();
}
