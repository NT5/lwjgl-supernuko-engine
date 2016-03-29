package info.nt5.engine.game.elements.actor;

public abstract class Animation {
	public int deltaAnimation, deltaWaiting;

	public int animationSpeed;
	public double animationRate, animationSleep, animationDuration;

	public boolean activeAnimation, activeWaiting;

	public int duration;

	public abstract void setAnimation(int speed, double rate, double wait, double duration);

	public abstract void updateAnimation();

}
