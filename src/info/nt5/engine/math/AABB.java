package info.nt5.engine.math;

public class AABB {
	public Vector2f min, max;

	public AABB(Vector2f position, Vector2f size) {
		this.min = new Vector2f(position.getX() - size.getX(), position.getY() - size.getY());
		this.max = new Vector2f(position.getX() + size.getX(), position.getY() + size.getY());
	}

	public void setAabb(Vector2f position, Vector2f size) {
		this.min.x = position.getX() - size.getX();
		this.min.y = position.getY() - size.getY();

		this.max.x = position.getX() + size.getX();
		this.max.y = position.getY() + size.getY();
	}

	public boolean intersects(AABB other) {
		if (this.max.x < other.min.x) {
			return false;
		}

		if (this.max.y < other.min.y) {
			return false;
		}

		if (this.min.x > other.max.x) {
			return false;
		}

		if (this.min.y > other.max.y) {
			return false;
		}
		return true;
	}
}
