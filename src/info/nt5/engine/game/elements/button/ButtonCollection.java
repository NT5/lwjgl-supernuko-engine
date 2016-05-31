package info.nt5.engine.game.elements.button;

import java.util.ArrayList;
import java.util.List;

import info.nt5.engine.game.elements.util.Align;
import info.nt5.engine.game.elements.util.MouseAABB;
import info.nt5.engine.graphics.Camera;
import info.nt5.engine.math.Vector2f;
import info.nt5.engine.math.Vector3f;

public class ButtonCollection {

	private List<Button> buttons = new ArrayList<Button>();
	private MouseAABB mouseAABB;
	private Vector2f spacing = new Vector2f(0.1f);

	public ButtonCollection(MouseAABB mouse) {
		this.mouseAABB = mouse;
	}

	public MouseAABB getMouseAABB() {
		return mouseAABB;
	}

	public Button getButton(int index) {
		return this.buttons.get(index);
	}

	public void addButton(Button button) {
		this.buttons.add(button);
	}

	public void addButtonToDown(Button button) {
		Button prev = getButton(buttons.size() - 1);
		button.setTextAlign(Align.UPPER_LEFT);
		button.setPadding(
				new Vector2f((prev.getSize().x - button.getSize().x) + button.getPadding().x, button.getPadding().y));
		button.setPosition(new Vector3f(prev.getPosition().x,
				prev.getPosition().y + (prev.getSize().y * -2f) - this.spacing.x, prev.getPosition().z));
		this.addButton(button);
	}

	public void delButton(int index) {
		this.buttons.get(index).dispose();
		this.buttons.remove(index);
	}

	public void setSpacing(Vector2f spacing) {
		this.spacing = spacing;
	}

	public void setCamera(Camera camera) {
		this.buttons.forEach(button -> button.setCamera(camera));
	}

	public void translate(Vector3f vector) {
		this.buttons.forEach(button -> button.translate(vector));
	}

	public void translateX(float x) {
		this.buttons.forEach(button -> button.translateX(x));
	}

	public void translateY(float y) {
		this.buttons.forEach(button -> button.translateY(y));
	}

	public void update() {
		this.mouseAABB.update();
		this.buttons.forEach(button -> button.update());
	}

	public void render() {
		this.buttons.forEach(button -> button.render());
	}

	public void dispose() {
		this.buttons.forEach(button -> button.dispose());
		this.buttons.clear();
		this.mouseAABB.dispose();
	}

}
