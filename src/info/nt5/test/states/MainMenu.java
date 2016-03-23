package info.nt5.test.states;

import static org.lwjgl.opengl.GL11.*;

import info.nt5.engine.game.GameManager;
import info.nt5.engine.game.GameObject;
import info.nt5.engine.game.state.State;
import info.nt5.engine.game.state.StateGame;
import info.nt5.engine.graphics.SubTexture;
import info.nt5.engine.graphics.Texture;
import info.nt5.engine.graphics.TextureAtlas;
import info.nt5.engine.input.Keyboard;
import info.nt5.engine.util.Logger;

public class MainMenu implements State {

	private GameObject font;
	private Texture texture;
	private TextureAtlas textureAtlas;
	private SubTexture subTexture;

	private float width = 0.5f;
	private float height = 0.5f;

	private float[] vertices = { -width, height, 0f, // left top : 0
			-width, -height, 0f, // left bottom : 1
			width, -height, 0f, // right bottom : 2
			width, height, 0f, // right left : 3
	};

	private byte[] indices = { 0, 1, 2, 2, 3, 0 };

	private float[] texCoords = { 0, 1, 0, 0, 1, 0, 1, 1 };

	@Override
	public int getID() {
		return 1;
	}

	@Override
	public void init(GameManager gm, StateGame game) {
		Logger.debug("Menu state init!");

		int gridSize = 16;
		int asciiCode = (int) "A".charAt(0);

		int cellX = (int) asciiCode % gridSize;
		int cellY = (int) asciiCode / gridSize;

		texture = Texture.fromImage("assets/img/font.png");

		textureAtlas = new TextureAtlas(texture, 32);

		subTexture = textureAtlas.getCell(cellY, cellX);

		texCoords[0] = subTexture.getMinU();
		texCoords[1] = subTexture.getMinV();

		texCoords[2] = subTexture.getMinU();
		texCoords[3] = subTexture.getMaxV();

		texCoords[4] = subTexture.getMaxU();
		texCoords[5] = subTexture.getMaxV();

		texCoords[6] = subTexture.getMaxU();
		texCoords[7] = subTexture.getMinV();

		font = new GameObject(vertices, indices, texCoords, texture);
	}

	@Override
	public void enter(GameManager gm, StateGame game) {
		Logger.debug("Menu state enter!");
		glEnable(GL_BLEND);
		glBlendFunc(GL_ONE, GL_ONE);
	}

	@Override
	public void update(GameManager gm, StateGame game) {
		if (Keyboard.isPressed(Keyboard.KEY_SPACE)) {
			game.enterState(2);
		}
	}

	@Override
	public void render(GameManager gm, StateGame game) {
		glClearColor(0.0f, 0.0f, 1.0f, 1.0f);

		font.render();
	}

	@Override
	public void leave(GameManager gm, StateGame game) {
		Logger.debug("Menu state leave!");
		glDisable(GL_BLEND);
	}

}
