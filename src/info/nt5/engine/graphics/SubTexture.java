package info.nt5.engine.graphics;

import info.nt5.engine.math.Vector2f;
import info.nt5.engine.math.Vector4f;

public class SubTexture extends Texture {
	private Texture parent;
	private float width, height;

	private float minU, minV;
	private float maxU, maxV;

	public SubTexture(Texture parent, Vector4f uv) {
		this(parent, uv.x, uv.y, uv.z, uv.w);
	}

	public SubTexture(Texture parent, Vector2f min, Vector2f max) {
		this(parent, min.x, min.y, max.x, max.y);
	}

	public SubTexture(Texture parent, float minU, float minV, float maxU, float maxV) {
		this(parent, minU, minV, maxU, maxV, (maxU - minU) * parent.getWidth(), (maxV - minV) * parent.getHeight());
	}

	public SubTexture(Texture parent, float minU, float minV, float maxU, float maxV, float width, float height) {
		super(parent.getID());

		this.parent = parent;
		this.minU = minU;
		this.minV = minV;
		this.maxU = maxU;
		this.maxV = maxV;

		this.width = width;
		this.height = height;
	}

	@Override
	public float getMinU() {
		return minU;
	}

	@Override
	public float getMinV() {
		return minV;
	}

	@Override
	public float getMaxU() {
		return maxU;
	}

	@Override
	public float getMaxV() {
		return maxV;
	}

	@Override
	public float getWidth() {
		return width;
	}

	@Override
	public float getHeight() {
		return height;
	}

	@Override
	public SubTexture getSubTexture(float minU, float minV, float maxU, float maxV) {
		minU = ((minU * width) / parent.getWidth()) + this.minU;
		minV = ((minV * height) / parent.getHeight()) + this.minV;
		maxU = ((maxU * width) / parent.getWidth()) + minU;
		maxV = ((maxV * height) / parent.getHeight()) + minV;

		return new SubTexture(parent, minU, minV, maxU, maxV);
	}

	public Texture getParent() {
		return parent;
	}

	@Override
	public void dispose() {
		throw new RuntimeException("Failed to dispose of the texture. SubTextures cannot be disposed of.");
	}
}
