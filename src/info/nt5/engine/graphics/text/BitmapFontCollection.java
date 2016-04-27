package info.nt5.engine.graphics.text;

import java.util.ArrayList;
import java.util.List;

public class BitmapFontCollection {

	private List<BitmapFont> textCollection = new ArrayList<BitmapFont>();
	private int currentTextId;

	public List<BitmapFont> getTextCollection() {
		return this.textCollection;
	}

	public BitmapFont getTextCollection(int index) {
		return this.textCollection.get(index);
	}

	public BitmapFont getCollectionCurrent() {
		return getTextCollection(currentTextId);
	}

	public void setNextCollection() {
		if (currentTextId != this.textCollection.size() - 1) {
			currentTextId++;
		} else {
			currentTextId = 0;
		}
	}

	public void setPrevCollection() {
		if (currentTextId != 0) {
			currentTextId--;
		} else {
			currentTextId = (this.textCollection.size() - 1);
		}
	}

	public void addTextCollection(BitmapFont text) {
		this.textCollection.add(text);
	}

	public void addTextCollection(BitmapFont[] texts) {
		for (BitmapFont text : texts) {
			addTextCollection(text);
		}
	}

	public void removeTextCollection(int index) {
		this.textCollection.remove(index);
	}

	public void addTextToCurrentCollection(String text) {
		this.getCollectionCurrent().addText(text);
	}

	public void addTextToCurrentCollection(String[] text) {
		for (String str : text) {
			addTextToCurrentCollection(str);
		}
	}

	public String getCurrentText() {
		return this.getCollectionCurrent().toString();
	}

	public void update() {
		if (this.getCollectionCurrent() != null) {
			this.getCollectionCurrent().update();
		}
	}

	public void render() {
		if (this.getCollectionCurrent() != null) {
			this.getCollectionCurrent().render();
		}
	}

	public void dispose() {
		for (BitmapFont text : this.textCollection) {
			text.dispose();
		}
		this.textCollection.clear();
	}

}
