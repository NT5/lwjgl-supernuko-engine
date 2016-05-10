package info.nt5.test.states;

import static org.lwjgl.opengl.GL11.glClearColor;

import info.nt5.engine.game.GameManager;
import info.nt5.engine.game.elements.Model;
import info.nt5.engine.game.elements.Tile;
import info.nt5.engine.game.elements.gui.GUIOverlay;
import info.nt5.engine.game.movement.Movement;
import info.nt5.engine.game.state.State;
import info.nt5.engine.game.state.StateManager;
import info.nt5.engine.game.state.transition.FadeTransition;
import info.nt5.engine.graphics.Color;
import info.nt5.engine.graphics.Texture;
import info.nt5.engine.graphics.shader.VertexQuad;
import info.nt5.engine.input.Keyboard;
import info.nt5.engine.lang.Lang;
import info.nt5.engine.math.Vector3f;
import info.nt5.engine.util.Logger;

public class Intro implements State {

	private Tile tile1, tile2;

	private Tile floor, roof;
	private Tile wall1, wall2;

	private Model model;

	private static final Color clearColor = Color.CYAN;

	@Override
	public int getID() {
		return 0;
	}

	@Override
	public void init(GameManager gm, StateManager game) {
		Logger.debug(Lang.getString("states.init", this.getClass().getSimpleName()));
	}

	@Override
	public void enter(GameManager gm, StateManager game) {
		Logger.debug(Lang.getString("states.enter", this.getClass().getSimpleName()));
		glClearColor(clearColor.r, clearColor.g, clearColor.b, clearColor.a);
		tile1 = new Tile(new VertexQuad(0.5f), Texture.fromColor(Color.PURPLE, 16, 16), new Vector3f());

		tile2 = new Tile(new VertexQuad(0.3f), Texture.fromColor(Color.PINK, 16, 16), new Vector3f());

		model = new Model("assets/models/blend.obj", Texture.fromImage("assets/models/blend.jpg"),
				new Vector3f(0f, -5.5f, 0f));
		model.setScale(new Vector3f(2f));

		floor = new Tile(new VertexQuad(10f, 0.1f), Texture.fromColor(Color.SILVER, 16, 16),
				new Vector3f(0f, -5.5f, 0f));
		roof = new Tile(new VertexQuad(10f, 0.1f), Texture.fromColor(Color.SILVER, 16, 16), new Vector3f(0f, 5.5f, 0f));

		wall1 = new Tile(new VertexQuad(0.1f, 5.5f), Texture.fromColor(Color.ORANGE, 16, 16),
				new Vector3f(10f, 0f, 0f));
		wall2 = new Tile(new VertexQuad(0.1f, 5.5f), Texture.fromColor(Color.ORANGE, 16, 16),
				new Vector3f(-10f, 0f, 0f));

		tile1.move = new Movement(180f, 0.5f);
		tile2.move = new Movement(90f, 0.5f);
	}

	@Override
	public void update(GameManager gm, StateManager game) {

		model.rotation.y = (model.rotation.y <= 360f ? model.rotation.y + 0.5f : 0f);
		model.rotation.x = (model.rotation.x <= 360f ? model.rotation.x + 0.5f : 0f);
		model.rotation.z = (model.rotation.z <= 360f ? model.rotation.z + 0.5f : 0f);

		model.update();
		tile1.update();
		tile2.update();

		floor.update();
		roof.update();
		wall1.update();
		wall2.update();

		if (tile1.aabb.intersects(tile2.aabb)) {
			tile1.move = new Movement(360, 0.5f);
			tile2.move = new Movement(0, 0.5f);
		}

		if (tile1.aabb.intersects(floor.aabb)) {
			tile1.move = new Movement(360, 0.5f);
		} else if (tile1.aabb.intersects(roof.aabb)) {
			tile1.move = new Movement(-360, 0.5f);
		} else if (tile1.aabb.intersects(wall1.aabb)) {
			tile1.move = new Movement(180, 0.5f);
		} else if (tile1.aabb.intersects(wall2.aabb)) {
			tile1.move = new Movement(0, 0.5f);
		}

		if (tile2.aabb.intersects(floor.aabb)) {
			tile2.move = new Movement(360, 0.5f);
		} else if (tile2.aabb.intersects(roof.aabb)) {
			tile2.move = new Movement(-360, 0.5f);
		} else if (tile2.aabb.intersects(wall1.aabb)) {
			tile2.move = new Movement(180, 0.5f);
		} else if (tile2.aabb.intersects(wall2.aabb)) {
			tile2.move = new Movement(0, 0.5f);
		}

		// Camera.defaultCam.position.x = -tile1.position.x;
		// Camera.defaultCam.position.y = -tile1.position.y;

		GUIOverlay.update(gm, game);

		// tile2.setPosition(new Vector3f(MatrixCursor.x, MatrixCursor.y, 0f));

		if (Keyboard.isPressed(Keyboard.KEY_SPACE)) {
			game.enterState(1, new FadeTransition(), new FadeTransition(1));
		}

		// if (Keyboard.isDown(Keyboard.KEY_W)) {
		// tile1.position.y += 0.08f;
		// }
		// if (Keyboard.isDown(Keyboard.KEY_S)) {
		// tile1.position.y -= 0.08f;
		// }
		// if (Keyboard.isDown(Keyboard.KEY_A)) {
		// tile1.position.x -= 0.08f;
		// }
		// if (Keyboard.isDown(Keyboard.KEY_D)) {
		// tile1.position.x += 0.08f;
		// }

		if (Keyboard.isDown(Keyboard.KEY_I)) {
			tile2.position.y += 0.08f;
		}
		if (Keyboard.isDown(Keyboard.KEY_K)) {
			tile2.position.y -= 0.08f;
		}
		if (Keyboard.isDown(Keyboard.KEY_J)) {
			tile2.position.x -= 0.08f;
		}
		if (Keyboard.isDown(Keyboard.KEY_L)) {
			tile2.position.x += 0.08f;
		}

	}

	@Override
	public void render(GameManager gm, StateManager game) {
		model.render();

		floor.render();
		roof.render();
		wall1.render();
		wall2.render();

		tile1.render();
		tile2.render();

		GUIOverlay.render(gm, game);
	}

	@Override
	public void leave(GameManager gm, StateManager game) {
		Logger.debug(Lang.getString("states.leave", this.getClass().getSimpleName()));

		tile1.dispose();
		tile2.dispose();
		model.dispose();

		floor.dispose();
		roof.dispose();
		wall1.dispose();
		wall2.dispose();
	}

}
