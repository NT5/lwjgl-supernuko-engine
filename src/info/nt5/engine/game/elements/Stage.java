package info.nt5.engine.game.elements;

import java.util.*;

public class Stage {

	private Background background;
	private HashMap<String, Actor> actors = new HashMap<String, Actor>();
	private HashMap<String, Textbox> textboxs = new HashMap<String, Textbox>();

	public void addActor(String name) {
		addActor(name, new Actor());
	}

	public void addActor(String name, Actor actor) {
		actors.put(name, actor);
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

	public void addTextbox(String name, Textbox textbox) {
		textboxs.put(name, textbox);
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

	public void setBackground(Background background) {
		this.background = background;
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
	
	public void dispose() {
		background.dispose();

		for (Actor actor : actors.values()) {
			actor.dispose();
		}

		for (Textbox textbox : textboxs.values()) {
			textbox.dispose();
		}
	}

}
