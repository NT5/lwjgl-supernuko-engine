package info.nt5.engine.game.elements.actor;

import java.util.*;

import info.nt5.engine.math.Vector3f;

public class Actor {
	private List<Part> Parts = new ArrayList<Part>();;

	public Actor(Part[] parts, Vector3f Position) {
		this(parts);
		translate(Position);
	}

	public Actor(Part[] parts) {
		for (Part part : parts) {
			addPart(part);
		}
	}

	public Part getPart(int index) {
		return Parts.get(index);
	}

	public List<Part> getPart() {
		return Parts;
	}

	public void addPart(Part part) {
		Parts.add(part);
	}

	public void update() {
		for (Part part : Parts) {
			part.updateAnimation();
		}
	}

	public void render() {
		for (Part part : Parts) {
			part.render();
		}
	}

	public void translate(Vector3f vector) {
		for (Part part : Parts) {
			part.translate(vector);
		}
	}

	public void translateX(float x) {
		for (Part part : Parts) {
			part.translateX(x);
		}
	}

	public void translateY(float y) {
		for (Part part : Parts) {
			part.translateY(y);
		}
	}

	public void translateZ(float z) {
		for (Part part : Parts) {
			part.translateZ(z);
		}
	}

	public void dispose() {
		for (Part part : Parts) {
			part.dispose();
		}
		Parts.clear();
	}

}
