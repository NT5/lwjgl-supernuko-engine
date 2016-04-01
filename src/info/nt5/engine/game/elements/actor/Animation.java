package info.nt5.engine.game.elements.actor;

public abstract class Animation {
	public int deltaAnimation, deltaWaiting;

	public int animationSpeed;
	public double animationRate, animationSleep, animationDuration;

	public boolean activeAnimation, activeWaiting;

	public int duration;

	public abstract void setAnimation(int speed, double rate, double wait, double duration);

	public abstract void endAnimation();

	public abstract void updateAnimation();

	public int getDeltaAnimation() {
		return deltaAnimation;
	}

	public void setDeltaAnimation(int deltaAnimation) {
		this.deltaAnimation = deltaAnimation;
	}

	public int getDeltaWaiting() {
		return deltaWaiting;
	}

	public void setDeltaWaiting(int deltaWaiting) {
		this.deltaWaiting = deltaWaiting;
	}

	public int getAnimationSpeed() {
		return animationSpeed;
	}

	public void setAnimationSpeed(int animationSpeed) {
		this.animationSpeed = animationSpeed;
	}

	public double getAnimationRate() {
		return animationRate;
	}

	public void setAnimationRate(double animationRate) {
		this.animationRate = animationRate;
	}

	public double getAnimationSleep() {
		return animationSleep;
	}

	public void setAnimationSleep(double animationSleep) {
		this.animationSleep = animationSleep;
	}

	public double getAnimationDuration() {
		return animationDuration;
	}

	public void setAnimationDuration(double animationDuration) {
		this.animationDuration = animationDuration;
	}

	public boolean isActiveAnimation() {
		return activeAnimation;
	}

	public void setActiveAnimation(boolean activeAnimation) {
		this.activeAnimation = activeAnimation;
	}

	public boolean isActiveWaiting() {
		return activeWaiting;
	}

	public void setActiveWaiting(boolean activeWaiting) {
		this.activeWaiting = activeWaiting;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

}
