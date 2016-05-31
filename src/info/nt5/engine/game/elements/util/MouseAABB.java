package info.nt5.engine.game.elements.util;

import info.nt5.engine.game.GameManager;
import info.nt5.engine.game.elements.Tile;
import info.nt5.engine.graphics.Camera;
import info.nt5.engine.graphics.Color;
import info.nt5.engine.input.Mouse;
import info.nt5.engine.math.AABB;
import info.nt5.engine.math.Vector2f;
import info.nt5.engine.math.Vector3f;
import info.nt5.engine.math.Vector4f;

public class MouseAABB {
	private Vector4f mousePos;
	private AABB mouseAabb;
	private Vector2f cursorSquare;

	private GameManager gm;
	private Camera camera;

	private Tile tile;

	public MouseAABB(GameManager gm, Vector2f cursorSquare, Camera camera) {
		this.tile = new Tile(Color.MAROON, cursorSquare);
		this.cursorSquare = cursorSquare;
		this.camera = camera;
		this.gm = gm;

		update();
	}

	public void render() {
		this.tile.render();
	}

	public void update() {
		mousePos = Mouse.unProject(camera.getProjectionMatrix(), camera.getViewMatrix(),
				new Vector2f(gm.getWindow().getWidth(), gm.getWindow().getHeight()));

		mouseAabb = new AABB(new Vector2f(mousePos.x, mousePos.y), cursorSquare);

		tile.setPositionX(mousePos.x);
		tile.setPositionX(mousePos.y);

		this.tile.update();
	}

	public void dispose() {
		this.tile.dispose();
	}

	public Vector4f getPosition() {
		return mousePos;
	}

	public Vector3f getPosition3f() {
		return new Vector3f(mousePos.x, mousePos.y, mousePos.z);
	}

	public Vector2f getCursorSquare() {
		return cursorSquare;
	}

	public AABB getAABB() {
		return mouseAabb;
	}
}
