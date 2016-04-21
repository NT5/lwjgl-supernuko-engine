package info.nt5.engine.game.elements;

import java.util.ArrayList;
import java.util.List;

import info.nt5.engine.game.GameManager;
import info.nt5.engine.game.state.StateManager;
import info.nt5.engine.graphics.Color;
import info.nt5.engine.graphics.text.BitmapFont;
import info.nt5.engine.graphics.text.BitmapFormat;
import info.nt5.engine.math.Vector2f;
import info.nt5.engine.math.Vector3f;

public class FPSCounter {

	private static List<BitmapFont> text = new ArrayList<BitmapFont>();

	public static void init(GameManager gm, StateManager game) {
		text.add(

				new BitmapFont(

						new BitmapFormat[] {

								new BitmapFormat("FPS: ", Color.PURPLE).setSize(new Vector2f(0.15f))

						},

						new Vector3f(9f, -5.5f, 0f)

				)

		);
		text.add(

				new BitmapFont(

						new BitmapFormat[] {

								new BitmapFormat("60", Color.GREEN).setSize(new Vector2f(0.15f))

						},

						new Vector3f(9.6f, -5.5f, 0f)

				)

		);
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
	}

	public static void render(GameManager gm, StateManager game) {
		text.forEach(text -> text.render());
	}

}
