package info.nt5.engine.game.elements.actor;

public abstract class Animation {

	public int deltaAnimation, deltaWaiting;

	public boolean activeAnimation, activeWaiting;
	
	public int duration;

	public abstract void animate(int speed, double rate, double wait, float duration);

}
