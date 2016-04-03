package info.nt5.engine.game.elements;

import java.util.*;

import info.nt5.engine.game.elements.actor.Actor;

public class Stage {

	private Background background;
	private List<Actor> actors = new ArrayList<Actor>();
	private List<Textbox> textboxs = new ArrayList<Textbox>();

	public void addActor(Actor actor) {
		actors.add(actor);
	}

	public void removeActor(int index) {
		actors.remove(index);
	}

	public Actor getActor(int index) {
		return actors.get(index);
	}

	public List<Actor> getActors() {
		return actors;
	}

	public void addTextbox() {
		addTextbox(new Textbox());
	}

	public void addTextbox(Textbox textbox) {
		textboxs.add(textbox);
	}

	public void removeTextbox(int index) {
		textboxs.remove(index);
	}

	public Textbox getTextbox(int index) {
		return textboxs.get(index);
	}

	public List<Textbox> getTextboxs() {
		return textboxs;
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

	public void update() {
		for (Actor actor : actors) {
			actor.update();
		}
	}

	public void render() {
		if (background != null) {
			background.render();
		}

		for (Actor actor : actors) {
			actor.render();
		}

		for (Textbox textbox : textboxs) {
			textbox.render();
		}
	}

	public void dispose() {
		if (background != null) {
			background.dispose();
		}

		for (Actor actor : actors) {
			actor.dispose();
		}
		actors.clear();

		for (Textbox textbox : textboxs) {
			textbox.dispose();
		}
		textboxs.clear();
	}

}
