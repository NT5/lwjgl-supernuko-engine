package info.nt5.test.states;

import java.util.ArrayList;
import java.util.List;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

import info.nt5.engine.game.GameManager;
import info.nt5.engine.game.elements.gui.GUIOverlay;
import info.nt5.engine.game.elements.jbox.Tile;
import info.nt5.engine.game.state.State;
import info.nt5.engine.game.state.StateManager;
import info.nt5.engine.graphics.Camera;
import info.nt5.engine.graphics.Color;
import info.nt5.engine.graphics.text.BitmapFont;
import info.nt5.engine.graphics.text.BitmapFormat;
import info.nt5.engine.input.Keyboard;
import info.nt5.engine.input.Mouse;
import info.nt5.engine.lang.Lang;
import info.nt5.engine.math.Vector2f;
import info.nt5.engine.math.Vector3f;
import info.nt5.engine.math.Vector4f;
import info.nt5.engine.util.Logger;

public class jboxTest implements State {

	private World world;

	private List<Tile> bodies = new ArrayList<Tile>();

	private Color clearColor = Color.SILVER;
	private BitmapFont txtCount;

	@Override
	public int getID() {
		return 4;
	}

	@Override
	public void init(GameManager gm, StateManager game) {
		Logger.debug(Lang.getString("states.init", this.getClass().getSimpleName()));
	}

	@Override
	public void enter(GameManager gm, StateManager game) {
		Logger.debug(Lang.getString("states.enter", this.getClass().getSimpleName()));
		gm.getWindow().setClearColor(clearColor);

		world = new World(new Vec2(0f, -9.8f));

		Vector2f groundSize = new Vector2f(100f, 0.1f);

		BodyDef groundDef = new BodyDef();
		groundDef.position.set(new Vec2(0f, -5.5f));
		groundDef.type = BodyType.STATIC;

		PolygonShape groundShape = new PolygonShape();
		groundShape.setAsBox(groundSize.x, groundSize.y);

		FixtureDef groundFixture = new FixtureDef();
		groundFixture.density = 1;
		groundFixture.restitution = 0.3f;
		groundFixture.shape = groundShape;

		bodies.add(new Tile(Color.ORANGE, groundSize, world, groundDef, groundFixture));
		bodies.add(new Tile(Color.BLACK, new Vector2f(0.5f), new Vector2f(1f), world));
		bodies.add(new Tile(Color.PURPLE, new Vector2f(0.5f), new Vector2f(2f), world));

		txtCount = new BitmapFont(new BitmapFormat("Count: " + world.getBodyCount()), new Vector3f(4f, 4f, 0f));
		txtCount.setCamera(Camera.guiCamera);
		bodies.get(2).getBody().setType(BodyType.DYNAMIC);
		bodies.get(2).getBody().m_fixtureList.setDensity(0.3f);
		bodies.get(2).getBody().resetMassData();
	}

	@Override
	public void update(GameManager gm, StateManager game) {
		GUIOverlay.update(gm, game);

		if (Keyboard.isPressed(Keyboard.KEY_SPACE)) {
			game.enterState(0);
		}

		Vector4f mousePos = Mouse.unProject(Camera.worldCamera.getProjectionMatrix(), Camera.worldCamera.getViewMatrix(),
				new Vector2f(gm.getWindow().getWidth(), gm.getWindow().getHeight()));
		Vector2f cursorPos = new Vector2f(mousePos.x, mousePos.y);

		if (Keyboard.isDown(Keyboard.KEY_Z)) {
			bodies.add(new Tile(Color.RED, new Vector2f(0.5f), cursorPos, world));
		}

		if (Keyboard.isDown(Keyboard.KEY_LEFT_CONTROL)) {
			Vec2 mousePosition = new Vec2(cursorPos.x, cursorPos.y);
			Vec2 bodyPosition = bodies.get(1).getBody().getPosition();

			Vec2 force = mousePosition.sub(bodyPosition);
			bodies.get(1).getBody().applyForce(force.mul(4f), bodyPosition);
		}

		if (Mouse.isClicked(Mouse.MOUSE_BUTTON_1)) {
			Tile body = new Tile(Color.BLUE, new Vector2f(0.5f), cursorPos, world);
			if (Keyboard.isDown(Keyboard.KEY_S)) {
				body.getBody().setType(BodyType.KINEMATIC);
				body.getBody().m_fixtureList.m_restitution = 1f;
				body.getBody().resetMassData();
			}
			bodies.add(body);
		}

		if (Mouse.isClicked(Mouse.MOUSE_BUTTON_2)) {
			Tile body = new Tile(Color.PINK, new Vector2f(0.5f), cursorPos, world);
			if (Keyboard.isDown(Keyboard.KEY_S)) {
				body.getBody().setType(BodyType.KINEMATIC);
			}
			bodies.add(body);
		}

		if (Keyboard.isPressed(Keyboard.KEY_C)) {
			for (Tile tileBody : bodies) {
				tileBody.getBody().applyAngularImpulse(1f);
			}
		}

		if (Keyboard.isDown(Keyboard.KEY_A)) {
			bodies.get(2).getBody().applyForce(new Vec2(-5f, 0f), bodies.get(2).getBody().getPosition());
		} else if (Keyboard.isDown(Keyboard.KEY_D)) {
			bodies.get(2).getBody().applyForce(new Vec2(5f, 0f), bodies.get(2).getBody().getPosition());
		}
		if (Keyboard.isPressed(Keyboard.KEY_W)) {
			bodies.get(2).getBody().applyForce(new Vec2(0f, 120f), bodies.get(2).getBody().getPosition());
		}

		if (Keyboard.isPressed(Keyboard.KEY_V)) {
			if (world.getGravity().y < 0f) {
				world.setGravity(new Vec2(0f, 9.8f));
			} else {
				world.setGravity(new Vec2(0f, -9.8f));
			}
			for (Tile tileBody : bodies) {
				if (!tileBody.getBody().isAwake()) {
					tileBody.getBody().setAwake(true);
				}
			}
		}

		world.step(1 / 60f, 8, 3);

		for (Tile tileBody : bodies) {
			tileBody.update();
		}
		txtCount.update();
		txtCount.setText(new BitmapFormat("Count: " + world.getBodyCount()));
	}

	@Override
	public void render(GameManager gm, StateManager game) {
		for (Tile tileBody : bodies) {
			tileBody.render();
		}
		txtCount.render();

		GUIOverlay.render(gm, game);
	}

	@Override
	public void leave(GameManager gm, StateManager game) {
		Logger.debug(Lang.getString("states.leave", this.getClass().getSimpleName()));

		for (Tile tileBody : bodies) {
			tileBody.dispose();
		}
		bodies.clear();
		txtCount.dispose();
	}

}
