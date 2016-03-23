package info.nt5.engine.graphics;

import org.lwjgl.glfw.GLFWImage;

public class Icon {
	private Image icon32, icon16;
	private GLFWImage.Buffer icons;
	
	private static final String defaulticon16 = "assets/img/icon/16.png";
	private static final String defaulticon32 = "assets/img/icon/32.png";

	public Icon() {
		this(defaulticon32, defaulticon16);
	}

	public Icon(String icon32, String icon16) {
		this.icon32 = new Image(icon32);
		this.icon16 = new Image(icon16);

		this.icons = GLFWImage.malloc(2);

		this.icons.position(0)
				.width(this.icon16.getWidth())
				.height(this.icon16.getHeight())
				.pixels(this.icon16.getBuffer());

		this.icons.position(1)
				.width(this.icon32.getWidth())
				.height(this.icon32.getHeight())
				.pixels(this.icon32.getBuffer());

		this.icons.position(1);
	}

	public GLFWImage.Buffer getIcons() {
		return this.icons;
	}

	public void dispose() {
		this.icon32.dispose();
		this.icon16.dispose();
		this.icons.free();
	}
}
