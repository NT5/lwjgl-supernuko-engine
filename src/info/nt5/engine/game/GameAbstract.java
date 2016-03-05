package info.nt5.engine.game;

public abstract class GameAbstract implements Game {
	
	private String title;
	private GameManager manager;

	public GameAbstract( GameManager manager, String title ) {
		this.manager = manager;
		this.title = title;
	}
	
	@Override
	public abstract void init(GameManager gm);

	@Override
	public abstract void enter(GameManager gm);

	@Override
	public abstract void update(GameManager gm);

	@Override
	public abstract void render(GameManager gm);

	@Override
	public abstract void leave(GameManager gm);

	@Override
	public String getTitle() {
		return title;
	}
	
	public GameManager getManager() {
		return manager;
	}

}
