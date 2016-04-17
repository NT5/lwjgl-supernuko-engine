package info.nt5.engine.game.animation;

public class Animation {
	private int deltaAnimation, deltaWaiting;
	private int animationSpeed;
	private int duration;

	private double animationRate, animationSleep, animationDuration;

	private boolean activeAnimation, activeWaiting;

	private AnimationInterface callback;

	private boolean isAnimating;

	public Animation(AnimationInterface cb) {
		this.callback = cb;
	}

	public void reset() {
		this.activeAnimation = true;
		this.activeWaiting = false;
		this.duration = this.deltaAnimation = this.deltaWaiting = 0;
		this.callback.setFirtsTextureObject();
	}

	public void end() {
		this.animationDuration = this.duration;
		this.callback.setFirtsTextureObject();
		this.isAnimating = false;
	}

	public void set(int speed, double rate, double wait, double duration) {
		this.animationSpeed = speed;
		this.animationRate = rate;
		this.animationSleep = wait;
		this.animationDuration = duration;
		this.isAnimating = true;
	}

	public void update() {
		if (this.duration < animationDuration || animationDuration <= 0) {
			if (!activeWaiting) {
				if (activeAnimation) {
					if (animationSpeed == 0 || (deltaAnimation % animationSpeed) == 0) {
						this.callback.nextTextureSprite();
						deltaAnimation = 0;
					}
					deltaAnimation++;
				}

				if ((this.callback.getSpriteSize() - 1) != this.callback.getSpriteID()) {
					activeAnimation = (animationRate == 0 || Math.random() > animationRate ? true : false);
					this.duration++;
					activeWaiting = true;
				}
			} else {
				if ((deltaWaiting % animationSleep) == 0) {
					deltaWaiting = 0;
					activeWaiting = false;
				}
				deltaWaiting++;
			}
		} else if (!isAnimating) {
			end();
		}
	}

	public int getDelta() {
		return deltaAnimation;
	}

	public void setDelta(int deltaAnimation) {
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

	public boolean isAnimating() {
		return isAnimating;
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
