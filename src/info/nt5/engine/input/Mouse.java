package info.nt5.engine.input;

import org.lwjgl.glfw.GLFW;

import info.nt5.engine.game.GameManager;
import info.nt5.engine.graphics.Camera;
import info.nt5.engine.math.Matrix4f;
import info.nt5.engine.math.Vector4f;

public class Mouse {

	public static final int MOUSE_BUTTON_1 = 0x0, MOUSE_BUTTON_2 = 0x1, MOUSE_BUTTON_3 = 0x2, MOUSE_BUTTON_4 = 0x3,
			MOUSE_BUTTON_5 = 0x4, MOUSE_BUTTON_6 = 0x5, MOUSE_BUTTON_7 = 0x6, MOUSE_BUTTON_8 = 0x7,
			MOUSE_BUTTON_LAST = MOUSE_BUTTON_8, MOUSE_BUTTON_LEFT = MOUSE_BUTTON_1, MOUSE_BUTTON_RIGHT = MOUSE_BUTTON_2,
			MOUSE_BUTTON_MIDDLE = MOUSE_BUTTON_3;

	private static int[] buttonState = new int[MOUSE_BUTTON_LAST];
	private static int[] buttonDown = new int[MOUSE_BUTTON_LAST];

	private static double x = 0, y = 0;
	private static double yoffset = 0, xoffset = 0;
	private static boolean isScroll = false;
	private static boolean isEntered = true;

	private Mouse() {
	}

	public static void init() {
		for (int i = 0; i < buttonState.length; i++) {
			buttonState[i] = -1;
		}
	}

	public static void update() {
		for (int i = 0; i < buttonState.length; i++) {
			buttonState[i] = -1;
		}
		isScroll = false;
	}

	public static boolean isDown(int key) {
		if (key <= MOUSE_BUTTON_LAST) {
			return buttonDown[key] == 1;
		}
		return false;
	}

	public static boolean isClicked(int key) {
		if (key <= MOUSE_BUTTON_LAST) {
			return buttonState[key] == 1;
		}
		return false;
	}

	public static boolean isRelased(int key) {
		if (key <= MOUSE_BUTTON_LAST) {
			return buttonState[key] == 0;
		}
		return false;
	}

	public static boolean isScroll() {
		return isScroll;
	}

	public static boolean isScrollUp() {
		return (isScroll && yoffset >= 1 ? true : false);
	}

	public static boolean isScrollDown() {
		return (isScroll && yoffset <= 0 ? true : false);
	}

	public static boolean isEntered() {
		return isEntered;
	}

	public static double getX() {
		return x;
	}

	public static double getY() {
		return y;
	}

	public static Vector4f getMatrixAxis(GameManager gm) {
		Matrix4f cursorMatrix = Matrix4f.identity();
		cursorMatrix.multiply(

				Camera.defaultCam.getProjectionMatrix()

		).multiply(

				Camera.defaultCam.getViewMatrix()

		).invert();

		float winZ = 1.0f;
		Vector4f inVector = new Vector4f(

				(2.0f * ((float) (Mouse.getX() - 0) / (gm.getWindow().getWidth() - 0))) - 1.0f,

				1.0f - (2.0f * ((float) (Mouse.getY() - 0) / (gm.getWindow().getHeight() - 0))),

				2.0f * winZ - 1.0f,

				1f

		);

		Vector4f pos = cursorMatrix.multiply(inVector);
		pos.w = 1.0f / pos.w;

		pos.x *= pos.w;
		pos.y *= pos.w;
		pos.z *= pos.w;

		return pos;
	}

	public static double getXoffset() {
		return xoffset;
	}

	public static double getYoffset() {
		return yoffset;
	}

	public static void glfw_mouse_button_callback(long window, int button, int action, int mods) {
		if (button <= MOUSE_BUTTON_LAST) {
			buttonState[button] = action;
			buttonDown[button] = action != GLFW.GLFW_RELEASE ? 1 : 0;
		}
	}

	public static void glfw_cursor_pos_callback(long window, double xpos, double ypos) {
		Mouse.x = xpos;
		Mouse.y = ypos;
	}

	public static void glfw_scroll_callback(long window, double xoffset, double yoffset) {
		Mouse.xoffset = xoffset;
		Mouse.yoffset = yoffset;

		Mouse.isScroll = true;
	}

	public static void glfw_cursor_enter_callback(long window, int entered) {
		Mouse.isEntered = (entered == 1 ? true : false);
	}

}
