package info.nt5.engine.graphics;

public class TextureAtlas {
	private Texture parent;

	private int cellWidth, cellHeight;

	public TextureAtlas(Texture parent, int cellSize) {
		this(parent, cellSize, cellSize);
	}

	public TextureAtlas(Texture parent, int cellWidth, int cellHeight) {
		this.parent = parent;

		this.cellWidth = cellWidth;
		this.cellHeight = cellHeight;
	}

	public SubTexture getCell(int row, int column) {
		float minU = (column * cellWidth) / parent.getWidth();
		float minV = (row * cellHeight) / parent.getHeight();
		float maxU = ((column + 1) * cellWidth) / parent.getWidth();
		float maxV = ((row + 1) * cellHeight) / parent.getHeight();

		return new SubTexture(parent, minU, minV, maxU, maxV, cellWidth, cellHeight);
	}

	public void dispose() {
		parent.dispose();
	}

}
