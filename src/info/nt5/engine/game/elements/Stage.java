package info.nt5.engine.game.elements;

import java.util.*;

import info.nt5.engine.graphics.Color;
import info.nt5.engine.math.Vector3f;

public class Stage {

	private Background background;
	private HashMap<String, Actor> actors = new HashMap<String, Actor>();

	public void addActor(String name, Vector3f position) {
		addActor(name);
		getActor(name).position = position;
	}

	public void addActor(String name) {
		actors.put(name, new Actor());
	}

	public void addActor(String name, Actor actor) {
		actors.put(name, actor);
	}

	public void addActor(String name, String texture) {
		actors.put(name, new Actor(texture));
	}

	public void removeActor(String name) {
		actors.remove(name);
	}

	public void setBackground() {
		background = new Background();
	}

	public void setBackground(Color color) {
		background = new Background(color);
	}

	public void setBackground(String path) {
		background = new Background(path);
	}

	public Actor getActor(String name) {
		return actors.get(name);
	}

	public Background getBackground() {
		return background;
	}

	public void render() {
		background.render();
		for (Actor actor : actors.values()) {
			actor.render();
		}
	}

}
