package info.nt5.engine.game.elements;

import java.util.*;

import info.nt5.engine.graphics.Color;
import info.nt5.engine.math.Vector3f;

public class Stage {

	private Background background;
	private HashMap<String, Actor> actors = new HashMap<String, Actor>();
	private HashMap<String, Textbox> textboxs = new HashMap<String, Textbox>();

	public void addActor(String name) {
		addActor(name, new Actor());
	}

	public void addActor(String name, String texture) {
		addActor(name, new Actor(texture));
	}

	public void addActor(String name, Actor actor) {
		actors.put(name, actor);
	}

	public void addActor(String name, Vector3f position) {
		addActor(name);
		getActor(name).position = position;
	}

	public void addActor(String name, Actor actor, Vector3f position) {
		addActor(name, actor);
		getActor(name).position = position;
	}

	public void addActor(String name, String texture, Vector3f position) {
		addActor(name, texture);
		getActor(name).position = position;
	}

	public void removeActor(String name) {
		actors.remove(name);
	}

	public Actor getActor(String name) {
		return actors.get(name);
	}

	public void addTextbox(String name) {
		addTextbox(name, new Textbox());
	}

	public void addTextbox(String name, String texture) {
		addTextbox(name, new Textbox(texture));
	}

	public void addTextbox(String name, Textbox textbox) {
		textboxs.put(name, textbox);
	}

	public void addTextbox(String name, Vector3f position) {
		addTextbox(name);
		getTextbox(name).position = position;
	}

	public void addTextbox(String name, Textbox textbox, Vector3f position) {
		addTextbox(name, textbox);
		getTextbox(name).position = position;
	}

	public void addTextbox(String name, String texture, Vector3f position) {
		addTextbox(name, texture);
		getTextbox(name).position = position;
	}

	public void removeTextbox(String name) {
		textboxs.remove(name);
	}

	public Textbox getTextbox(String name) {
		return textboxs.get(name);
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

	public Background getBackground() {
		return background;
	}

	public void render() {
		background.render();

		for (Actor actor : actors.values()) {
			actor.render();
		}

		for (Textbox textbox : textboxs.values()) {
			textbox.render();
		}
	}

}
