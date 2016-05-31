package info.nt5.engine.game.elements.util;

import info.nt5.engine.math.Vector2f;

public class Movement {

	private float angle, speed;
	private Vector2f scale, velocity;

	public Movement(float angle, float speed) {
		this.angle = angle;
		this.speed = speed;

		this.scale = new Vector2f((float) Math.cos(this.angle), (float) Math.sin(this.angle));
		this.velocity = new Vector2f(this.speed * scale.x, this.speed * scale.y);
	}

	public Vector2f getVelocity() {
		return this.velocity;
	}

	public Vector2f getScale() {
		return this.scale;
	}

	public float getSpeed() {
		return this.speed;
	}

	public float getAngle() {
		return this.angle;
	}

}
