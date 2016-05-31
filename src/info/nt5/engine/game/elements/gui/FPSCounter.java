package info.nt5.engine.game.elements.gui;

import java.util.ArrayList;
import java.util.List;

import info.nt5.engine.game.GameManager;
import info.nt5.engine.game.elements.Tile;
import info.nt5.engine.game.state.StateManager;
import info.nt5.engine.graphics.Camera;
import info.nt5.engine.graphics.Color;
import info.nt5.engine.graphics.Texture;
import info.nt5.engine.graphics.shader.VertexQuad;
import info.nt5.engine.graphics.text.BitmapFont;
import info.nt5.engine.graphics.text.BitmapFormat;
import info.nt5.engine.math.Vector2f;
import info.nt5.engine.math.Vector3f;

public class FPSCounter {

	private static List<BitmapFont> text = new ArrayList<BitmapFont>();
	private static Tile box;

	public static void init(GameManager gm, StateManager game) {
		text.add(

				new BitmapFont(

						new BitmapFormat("FPS: ", Color.PURPLE).setSize(new Vector2f(0.15f)),

						new Vector3f(8.9f, -5.6f, 0f)

				)

		);
		text.add(

				new BitmapFont(

						new BitmapFormat("60", Color.GREEN).setSize(new Vector2f(0.15f)),

						new Vector3f(9.5f, -5.6f, 0f)

				)

		);
		box = new Tile(new VertexQuad(0.55f, 0.2f), Texture.fromColor(Color.CYAN.withAlpha(0.75f), 32, 32),
				new Vector3f(9.45f, -5.45f, 0f));

		box.setCamera(Camera.guiCamera);
		text.forEach(t -> t.setCamera(Camera.guiCamera));
	}

	public static void update(GameManager gm, StateManager game) {
		Color fpsColor;

		if (gm.getFrames() >= gm.getWindow().getRefreshRate()) {
			fpsColor = Color.GREEN;
		} else if (gm.getFrames() >= (gm.getWindow().getRefreshRate() / 2)) {
			fpsColor = Color.YELLOW;
		} else if (gm.getFrames() >= (gm.getWindow().getRefreshRate() / 3)) {
			fpsColor = Color.ORANGE;
		} else {
			fpsColor = Color.RED;
		}

		text.get(1).setText(new BitmapFormat(String.valueOf(gm.getFrames()), fpsColor).setSize(new Vector2f(0.15f)));

		text.forEach(text -> text.update());
		box.update();
	}

	public static void translate(Vector3f position) {
		box.translate(position);
		text.forEach(text -> text.translate(position));
	}

	public static void render(GameManager gm, StateManager game) {
		box.render();
		text.forEach(text -> text.render());
	}

	public static void dispose() {
		box.dispose();
		text.forEach(text -> text.dispose());
	}

}
