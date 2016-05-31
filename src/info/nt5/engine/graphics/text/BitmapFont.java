package info.nt5.engine.graphics.text;

import java.util.ArrayList;
import java.util.List;

import info.nt5.engine.graphics.Camera;
import info.nt5.engine.graphics.SubTexture;
import info.nt5.engine.graphics.Texture;
import info.nt5.engine.graphics.TextureAtlas;
import info.nt5.engine.graphics.shader.Mesh;
import info.nt5.engine.graphics.shader.Shader;
import info.nt5.engine.math.Matrix4f;
import info.nt5.engine.math.Vector2f;
import info.nt5.engine.math.Vector3f;
import info.nt5.engine.util.BufferUtil;

public class BitmapFont {
	private static final String DEFAULT_BITMAP_PATH = "assets/img/font.png";
	private static final float ZPOS = 0.0f;
	private static final int VERTICES_PER_QUAD = 4;

	private static final int GRID_SIZE = 16;
	private static final int CELL_SIZE = 32;

	private static final float BOLD_OFFSET = 0.02f;

	private Mesh mesh;
	private BitmapFormat format;
	private Texture texture;

	private Vector3f position;
	private Vector3f rotation;
	private Vector3f scale;

	private Vector2f cursorPos;
	private Vector2f cursorMax;

	private Matrix4f ml_matrix;
	private Camera camera;

	private int renderSpeed;
	private int delta;
	private int currentCharId;

	private FontEventHandler eventHandler;

	public BitmapFont(BitmapFormat format) {
		this(format, Texture.fromImage(DEFAULT_BITMAP_PATH));
	}

	public BitmapFont(BitmapFormat format, Vector3f position) {
		this(format, Texture.fromImage(DEFAULT_BITMAP_PATH), position);
	}

	public BitmapFont(BitmapFormat format, Texture texture) {
		this(format, texture, new Vector3f());
	}

	public BitmapFont(BitmapFormat format, Texture texture, Vector3f position) {
		this.format = format;
		this.texture = texture;
		this.position = position;

		this.rotation = new Vector3f();
		this.scale = new Vector3f(1f);

		this.ml_matrix = new Matrix4f();
		this.camera = Camera.worldCamera;

		this.cursorMax = new Vector2f();
		this.cursorPos = new Vector2f();

		class FontEventHandleClass implements FontEventHandler {
			@Override
			public void onCreateChar(int asciiCode) {
			}

			@Override
			public void onUpdate(int speed, int delta) {
			}

			@Override
			public void onRender() {
			}

			@Override
			public void onAddToRenderList(int currentRenderIndex) {
			}

			@Override
			public void onRenderListEnd() {
			}
		}

		this.eventHandler = new FontEventHandleClass();

		this.buildMesh(this.format.text.length());
	}

	private void buildMesh(int range) {
		if (this.mesh != null) {
			this.mesh.dispose();
		}
		this.cursorPos = new Vector2f();

		TextureAtlas textureAtlas = new TextureAtlas(this.texture, CELL_SIZE);
		SubTexture charTexture;
		List<Float> positions = new ArrayList<Float>();
		List<Float> textCoords = new ArrayList<Float>();
		float[] normals = new float[0];
		List<Integer> indices = new ArrayList<Integer>();

		float width = this.format.size.x;
		float height = this.format.size.y;
		char[] characters = this.format.text.substring(0, range).toCharArray();

		Vector2f realCursor = new Vector2f(width, height);

		for (int i = 0; i < characters.length; i++) {
			char character = characters[i];

			if ((int) character == 10) {
				cursorPos.x = -((i * width) + width);
				cursorPos.y -= (height * 2);

				realCursor.x = width;
				realCursor.y += height;
			}

			int rowX = (int) character / GRID_SIZE;
			int rowY = (int) character % GRID_SIZE;

			charTexture = textureAtlas.getCell(rowX, rowY);
			positions.add((float) (i * width) + cursorPos.x);
			positions.add(0.0f + cursorPos.y);
			positions.add(ZPOS);
			textCoords.add(charTexture.getMinU());
			textCoords.add(charTexture.getMaxV());
			indices.add(i * VERTICES_PER_QUAD);

			positions.add((float) (i * width) + cursorPos.x);
			positions.add((height * 2) + cursorPos.y);
			positions.add(ZPOS);
			textCoords.add(charTexture.getMinU());
			textCoords.add(charTexture.getMinV());
			indices.add(i * VERTICES_PER_QUAD + 1);

			positions.add((float) ((i * width) + width * 2) + cursorPos.x);
			positions.add((height * 2) + cursorPos.y);
			positions.add(ZPOS);
			textCoords.add(charTexture.getMaxU());
			textCoords.add(charTexture.getMinV());
			indices.add(i * VERTICES_PER_QUAD + 2);

			positions.add((float) ((i * width) + width * 2) + cursorPos.x);
			positions.add(0.0f + cursorPos.y);
			positions.add(ZPOS);
			textCoords.add(charTexture.getMaxU());
			textCoords.add(charTexture.getMaxV());
			indices.add(i * VERTICES_PER_QUAD + 3);

			indices.add(i * VERTICES_PER_QUAD);
			indices.add(i * VERTICES_PER_QUAD + 2);

			realCursor.x += width;

			cursorMax.x = Math.max(Math.abs(realCursor.x / 2f), Math.abs(cursorMax.x));
			cursorMax.y = Math.max(Math.abs(realCursor.y), Math.abs(cursorMax.y));

			eventHandler.onCreateChar(character);
		}

		float[] posArr = BufferUtil.listFloatToArray(positions);
		float[] textCoordsArr = BufferUtil.listFloatToArray(textCoords);
		int[] indicesArr = indices.stream().mapToInt(i -> i).toArray();
		this.mesh = new Mesh(posArr, textCoordsArr, normals, indicesArr);
	}

	public void setText(BitmapFormat newText) {
		if (this.format.text != newText.text) {
			this.format = newText;
			buildMesh(this.format.text.length());
		}
	}

	public void addText(String text) {
		this.format.text = this.format.text.concat(text);
	}

	public void setCamera(Camera camera) {
		this.camera = camera;
	}

	public void setMl_matrix(Matrix4f ml_matrix) {
		this.ml_matrix = ml_matrix;
	}

	public void setScale(Vector3f scale) {
		this.scale = scale;
	}

	public void setRotation(Vector3f rotation) {
		this.rotation = rotation;
	}

	public BitmapFormat getFormat() {
		return this.format;
	}

	public Vector3f getPosition() {
		return this.position;
	}

	public Vector2f getCursorPos() {
		return this.cursorPos;
	}

	public Vector2f getCursorMax() {
		return this.cursorMax;
	}

	public void setEventHandler(FontEventHandler callback) {
		this.eventHandler = callback;
	}

	public void setRenderSpeed(int speed) {
		if (speed > 0) {
			buildMesh(0);
		}
		this.renderSpeed = speed;
	}

	public int getRenderSpeed() {
		return this.renderSpeed;
	}

	public void setRenderListIndex(int index) {
		currentCharId = index;
	}

	public boolean isRenderListEnd() {
		return (currentCharId >= format.text.length() ? true : false);
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public void translate(Vector3f vector) {
		this.position.x += vector.x;
		this.position.y += vector.y;
		this.position.z += vector.z;
	}

	public void translateX(float x) {
		this.position.x += x;
	}

	public void translateY(float y) {
		this.position.y += y;
	}

	public void translateZ(float z) {
		this.position.z += z;
	}

	public void update() {
		ml_matrix = Matrix4f.translate(this.position).multiply(Matrix4f.rotateX(this.rotation.x))
				.multiply(Matrix4f.rotateY(this.rotation.y).multiply(Matrix4f.rotateZ(this.rotation.z))
						.multiply(Matrix4f.scale(this.scale)));

		if (!isRenderListEnd()) {
			if (this.renderSpeed <= 0) {
				this.currentCharId = this.format.text.length();
				buildMesh(currentCharId);
				eventHandler.onRenderListEnd();
			} else {
				if (currentCharId < this.format.text.length()) {
					if ((delta % this.renderSpeed) == 0) {
						delta = 0;
						currentCharId++;
						buildMesh(currentCharId);
						eventHandler.onAddToRenderList(currentCharId);
					}
					delta++;
				}
				if (isRenderListEnd()) {
					eventHandler.onRenderListEnd();
				}
			}
			eventHandler.onUpdate(this.renderSpeed, this.delta);
		}
	}

	public void render() {
		this.texture.bind();
		Shader.textShader.bind();
		Shader.textShader.setUniformMat4f("vw_matrix", camera.getViewMatrix());
		Shader.textShader.setUniformMat4f("pr_matrix", camera.getProjectionMatrix());
		Shader.textShader.setUniformMat4f("ml_matrix", ml_matrix);
		Shader.textShader.setUniform4f("vColor", this.format.color);
		this.mesh.render();
		if (this.format.bold) {
			Shader.textShader.setUniformMat4f("ml_matrix",
					Matrix4f.translate(new Vector3f(this.position.x + BOLD_OFFSET, this.position.y, this.position.z)));
			this.mesh.render();
		}
		this.texture.unbind();
		Shader.textShader.unbind();
		eventHandler.onRender();
	}

	public void dispose() {
		this.texture.dispose();
		this.mesh.dispose();
	}

	@Override
	public String toString() {
		return this.format.text;
	}

}
