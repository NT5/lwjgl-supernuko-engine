package info.nt5.engine.input;

import info.nt5.engine.math.Matrix4f;
import info.nt5.engine.math.Vector2f;
import info.nt5.engine.math.Vector4f;

public class Mouse {
	/* @formatter:off */
	public static final int 
			MOUSE_BUTTON_1 = 0x0,
			MOUSE_BUTTON_2 = 0x1,
			MOUSE_BUTTON_3 = 0x2,
			MOUSE_BUTTON_4 = 0x3,
			MOUSE_BUTTON_5 = 0x4,
			MOUSE_BUTTON_6 = 0x5,
			MOUSE_BUTTON_7 = 0x6,
			MOUSE_BUTTON_8 = 0x7,
			MOUSE_BUTTON_LAST = MOUSE_BUTTON_8,
			MOUSE_BUTTON_LEFT = MOUSE_BUTTON_1,
			MOUSE_BUTTON_RIGHT = MOUSE_BUTTON_2,
			MOUSE_BUTTON_MIDDLE = MOUSE_BUTTON_3,
			RELEASE = 0x0;
	/* @formatter:on */

	private static int[] buttonState = new int[MOUSE_BUTTON_LAST];
	private static int[] buttonDown = new int[MOUSE_BUTTON_LAST];

	private static Vector2f position = new Vector2f(0f);
	private static Vector2f scrolloffset = new Vector2f(0f);
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
		return (isScroll && scrolloffset.y >= 1 ? true : false);
	}

	public static boolean isScrollDown() {
		return (isScroll && scrolloffset.y <= 0 ? true : false);
	}

	public static boolean isEntered() {
		return isEntered;
	}

	public static Vector2f getPosition() {
		return position;
	}

	public static Vector4f unProject(Matrix4f pr_matrix, Matrix4f vw_matrix, Vector2f viewport) {
		Matrix4f cursorMatrix = Matrix4f.identity();
		cursorMatrix.multiply(pr_matrix).multiply(vw_matrix).invert();

		float winZ = 1.0f;
		/* @formatter:off */
		Vector4f inVector = new Vector4f(
				(2.0f * ((float) (Mouse.getPosition().x - 0f) / (viewport.x - 0f))) - 1.0f,
				1.0f - (2.0f * ((float) (Mouse.getPosition().y - 0f) / (viewport.y - 0f))),
				2.0f * winZ - 1.0f,
				1f
		);
		/* @formatter:on */

		Vector4f pos = cursorMatrix.multiply(inVector);
		pos.w = 1.0f / pos.w;

		pos.x *= pos.w;
		pos.y *= pos.w;
		pos.z *= pos.w;

		return pos;
	}

	public static Vector2f getScrolloffset() {
		return scrolloffset;
	}

	public static void glfw_mouse_button_callback(long window, int button, int action, int mods) {
		if (button <= MOUSE_BUTTON_LAST) {
			buttonState[button] = action;
			buttonDown[button] = action != RELEASE ? 1 : 0;
		}
	}

	public static void glfw_cursor_pos_callback(long window, double xpos, double ypos) {
		Mouse.position.x = (float) xpos;
		Mouse.position.y = (float) ypos;
	}

	public static void glfw_scroll_callback(long window, double xoffset, double yoffset) {
		Mouse.scrolloffset.x = (float) xoffset;
		Mouse.scrolloffset.y = (float) yoffset;
		Mouse.isScroll = true;
	}

	public static void glfw_cursor_enter_callback(long window, int entered) {
		Mouse.isEntered = (entered == 1 ? true : false);
	}

}
