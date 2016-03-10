package info.nt5.test;

import static org.lwjgl.opengl.GL11.*;

import info.nt5.engine.game.GameAbstract;
import info.nt5.engine.game.GameManager;

public class Main extends GameAbstract {

	private static GameManager manager;

	public Main(String title) {
		super(manager, title);
	}

	public static void main(String[] args) {
		manager = new GameManager(new Main("A life with..."), 1280, 720, true, false, true, false);
		manager.start();
	}

	@Override
	public void init(GameManager gm) {
		glClearColor(0.0f, 1.0f, 0.0f, 1.0f);
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, manager.getWindow().getWidth(), 0, manager.getWindow().getHeight(), -1, 1);
		glMatrixMode(GL_MODELVIEW);
		
		glDisable(GL_DEPTH_TEST);
	}

	@Override
	public void enter(GameManager gm) {
	}

	@Override
	public void update(GameManager gm) {
	}

	@Override
	public void render(GameManager gm) {
		manager.getWindow().clear();
		
		glBegin(GL_QUADS);
		{
			glColor3d(0.7, 0.8, 0.9);
			glVertex2d(0, 0);
			glVertex2d(manager.getWindow().getWidth(), 0);

			glColor3d(0.5, 0.6, 0.9);
			glVertex2d(manager.getWindow().getWidth(), manager.getWindow().getHeight());
			glVertex2d(0, manager.getWindow().getHeight());
		}
		glEnd();
		
		glBegin(GL_QUADS);
		{
			glColor3d(0.6, 0.2, 0.1);
			glVertex2d(0, 0);
			glVertex2d(manager.getWindow().getWidth(), 0);

			glVertex2d(manager.getWindow().getWidth(), 32);
			glVertex2d(0, 32);
		}
		glEnd();
		
		glBegin(GL_QUADS);
		{
			glColor3d(0.2, 0.8, 0.2);
			glVertex2d(0, 25);
			glVertex2d(manager.getWindow().getWidth(), 25);

			glVertex2d(manager.getWindow().getWidth(), 32);
			glVertex2d(0, 32);
		}
		glEnd();
	}

	@Override
	public void leave(GameManager gm) {
	}

}
