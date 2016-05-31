package info.nt5.engine.game.elements;

import java.util.ArrayList;
import java.util.List;

import info.nt5.engine.game.elements.actor.Actor;
import info.nt5.engine.game.elements.textbox.Textbox;
import info.nt5.engine.graphics.Camera;
import info.nt5.engine.graphics.text.BitmapFont;
import info.nt5.engine.graphics.text.BitmapFontCollection;

public class Stage {

	private Background background;
	private List<Actor> actors = new ArrayList<Actor>();
	private List<Textbox> textboxs = new ArrayList<Textbox>();
	private List<BitmapFont> texts = new ArrayList<BitmapFont>();
	private List<BitmapFontCollection> textsCollection = new ArrayList<BitmapFontCollection>();

	public void addTextCollection(BitmapFontCollection text) {
		textsCollection.add(text);
	}

	public void removeTextCollecion(int index) {
		textsCollection.remove(index);
	}

	public BitmapFontCollection getTextCollecion(int index) {
		return textsCollection.get(index);
	}

	public List<BitmapFontCollection> getTextsCollecion() {
		return textsCollection;
	}

	public void addText(BitmapFont text) {
		texts.add(text);
	}

	public void removeText(int index) {
		texts.remove(index);
	}

	public BitmapFont getText(int index) {
		return texts.get(index);
	}

	public List<BitmapFont> getTexts() {
		return texts;
	}

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

		if (background != null) {
			background.update();
		}

		actors.forEach(actor -> actor.update());

		texts.forEach(text -> text.update());

		textsCollection.forEach(collection -> collection.update());

		textboxs.forEach(textbox -> textbox.update());
	}

	public void render() {
		if (background != null) {
			background.render();
		}

		actors.forEach(actor -> actor.render());

		texts.forEach(text -> text.render());

		textsCollection.forEach(collection -> collection.render());

		textboxs.forEach(textbox -> textbox.render());
	}

	public void dispose() {
		if (background != null) {
			background.dispose();
		}

		for (Actor actor : actors) {
			actor.dispose();
		}
		actors.clear();

		for (BitmapFont text : texts) {
			text.dispose();
		}
		texts.clear();

		for (BitmapFontCollection collection : textsCollection) {
			collection.dispose();
		}
		textsCollection.clear();

		for (Textbox textbox : textboxs) {
			textbox.dispose();
		}
		textboxs.clear();
	}

	public void setCamera(Camera camera) {
		if (background != null) {
			background.setCamera(camera);
		}

		actors.forEach(actor -> actor.setCamera(camera));

		texts.forEach(text -> text.setCamera(camera));

		textsCollection.forEach(collection -> collection.setCamera(camera));

		textboxs.forEach(textbox -> textbox.setCamera(camera));
	}

}
