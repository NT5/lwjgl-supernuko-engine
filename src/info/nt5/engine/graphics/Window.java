package info.nt5.engine.graphics;

import static org.lwjgl.opengl.GL11.glClearColor;

import org.lwjgl.Version;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorEnterCallback;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWFramebufferSizeCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.glfw.GLFWScrollCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryUtil;

import info.nt5.engine.input.Keyboard;
import info.nt5.engine.input.Mouse;
import info.nt5.engine.util.Logger;

public class Window {

	private GLFWErrorCallback errorCallback;
	private GLFWKeyCallback keyCallback;
	private GLFWMouseButtonCallback mouseButtonCallback;
	private GLFWCursorPosCallback cursorPosCallback;
	private GLFWScrollCallback scrollCallback;
	private GLFWCursorEnterCallback cursorEnterCallback;
	private GLFWFramebufferSizeCallback frameBufferCallback;
	private GLFWWindowSizeCallback windowSizeCallback;

	private long window;

	private String title;
	private int width, height;
	private boolean vsync, fullscreen, visible, resizable;

	private int refreshRate;

	private Cursor cursor;
	private Icon icons;

	public Window(String title, int width, int height, boolean vsync, boolean fullscreen, boolean visible,
			boolean resizable) {
		this.title = title;
		this.width = width;
		this.height = height;
		this.vsync = vsync;
		this.fullscreen = fullscreen;
		this.visible = visible;
		this.resizable = resizable;

		this.init();
	}

	void init() {
		Logger.info("LWJGL Version: %s", Version.getVersion());
		Logger.info("OS: %s %s", System.getProperty("os.name"), System.getProperty("os.version"));
		Logger.info("Java: %s", System.getProperty("java.version"));
		if (GLFW.glfwInit() != GL11.GL_TRUE) {
			Logger.error("Failed to initialize GLFW");
			throw new IllegalStateException();
		}

		Keyboard.init();
		Mouse.init();

		GLFW.glfwDefaultWindowHints();
		GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, visible ? GL11.GL_TRUE : GL11.GL_FALSE);
		GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, resizable ? GL11.GL_TRUE : GL11.GL_FALSE);

		GLFWVidMode vidmode = getVidModes();

		window = GLFW.glfwCreateWindow(width, height, title,
				fullscreen ? GLFW.glfwGetPrimaryMonitor() : MemoryUtil.NULL, MemoryUtil.NULL);
		if (window == MemoryUtil.NULL) {
			Logger.error("Failed to create GLFW window.");
			throw new RuntimeException();
		}

		if (!fullscreen) {
			GLFW.glfwSetWindowPos(window, (vidmode.width() - width) / 2, (vidmode.height() - height) / 2);
		} else {
			GLFW.glfwSetWindowSize(window, vidmode.width(), vidmode.height());
		}

		this.refreshRate = vidmode.refreshRate();

		// TODO methods instead
		GLFW.glfwSetWindowAspectRatio(window, 16, 9);
		GLFW.glfwSetWindowSizeLimits(window, 640, 360, vidmode.width(), vidmode.height());

		vidmode.clear();

		GLFW.glfwMakeContextCurrent(window);

		errorCallback = GLFWErrorCallback.createPrint();
		errorCallback.set();

		frameBufferCallback = new GLFWFramebufferSizeCallback() {

			@Override
			public void invoke(long window, int width, int height) {
				GL11.glViewport(0, 0, width, height);
			}
		};
		frameBufferCallback.set(window);

		windowSizeCallback = new GLFWWindowSizeCallback() {

			@Override
			public void invoke(long window, int width, int height) {
				if (!fullscreen) {
					Window.this.width = width;
					Window.this.height = height;
				}
			}
		};
		windowSizeCallback.set(window);

		keyCallback = GLFWKeyCallback.create(Keyboard::glfw_key_callback);
		keyCallback.set(window);

		mouseButtonCallback = GLFWMouseButtonCallback.create(Mouse::glfw_mouse_button_callback);
		mouseButtonCallback.set(window);

		cursorPosCallback = GLFWCursorPosCallback.create(Mouse::glfw_cursor_pos_callback);
		cursorPosCallback.set(window);

		scrollCallback = GLFWScrollCallback.create(Mouse::glfw_scroll_callback);
		scrollCallback.set(window);

		cursorEnterCallback = GLFWCursorEnterCallback.create(Mouse::glfw_cursor_enter_callback);
		cursorEnterCallback.set(window);

		GLFW.glfwSwapInterval(vsync ? 1 : 0);
		GL.createCapabilities();
		// GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);

		Logger.info("OpenGL: %s", GL11.glGetString(GL11.GL_VERSION));
	}

	public void update() {
		GLFW.glfwSwapBuffers(window);
	}

	public void updateInput() {
		Keyboard.update();
		Mouse.update();
		GLFW.glfwPollEvents();
	}

	public void clear() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
	}

	public void dispose() {
		keyCallback.free();
		scrollCallback.free();
		cursorEnterCallback.free();
		mouseButtonCallback.free();
		cursorPosCallback.free();
		frameBufferCallback.free();
		windowSizeCallback.free();
		GLFW.glfwTerminate();
		errorCallback.free();
	}

	public void close() {
		GLFW.glfwSetWindowShouldClose(window, GL11.GL_TRUE);
	}

	public boolean shouldclose() {
		return GLFW.glfwWindowShouldClose(window) == GL11.GL_TRUE;
	}

	public GLFWVidMode getVidModes() {
		return GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
	}

	public int getRefreshRate() {
		return refreshRate;
	}

	public String getTitle() {
		return title;
	}

	public void setClearColor(Color color) {
		glClearColor(color.r, color.g, color.b, color.a);
	}

	public void setTitle(String title) {
		this.title = title;
		GLFW.glfwSetWindowTitle(window, title);
	}

	public void setSize(int width, int height) {
		this.width = width;
		this.height = height;
		GLFW.glfwSetWindowSize(window, width, height);
	}

	public int getWidth() {
		if (fullscreen) {
			GLFWVidMode vidmode = getVidModes();
			int width = vidmode.width();
			vidmode.clear();
			return width;
		} else {
			return width;
		}
	}

	public void setWidth(int width) {
		this.width = width;
		GLFW.glfwSetWindowSize(window, width, height);
	}

	public int getHeight() {
		if (fullscreen) {
			GLFWVidMode vidmode = getVidModes();
			int height = vidmode.height();
			vidmode.clear();
			return height;
		} else {
			return height;
		}
	}

	public void setHeight(int height) {
		this.height = height;
		GLFW.glfwSetWindowSize(window, width, height);
	}

	public boolean vsync() {
		return vsync;
	}

	public void setVsync(boolean vsync) {
		this.vsync = vsync;
		GLFW.glfwSwapInterval(vsync ? 1 : 0);
	}

	public boolean isFullscreen() {
		return fullscreen;
	}

	public void setFullscreen(boolean fullscreen) {
		// TODO : Improve?
		this.fullscreen = fullscreen;
		GLFWVidMode vidmode = getVidModes();
		if (fullscreen) {
			GLFW.glfwSetWindowMonitor(window, GLFW.glfwGetPrimaryMonitor(), 0, 0, vidmode.width(), vidmode.height(),
					vidmode.refreshRate());
		} else {
			GLFW.glfwSetWindowMonitor(window, MemoryUtil.NULL, 0, 0, width, height, 0);
			vidmode = getVidModes();
			GLFW.glfwSetWindowPos(window, (vidmode.width() - width) / 2, (vidmode.height() - height) / 2);
		}
		vidmode.clear();
	}

	public boolean visible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
		if (visible) {
			GLFW.glfwShowWindow(window);
		} else {
			GLFW.glfwHideWindow(window);
		}
	}

	public boolean resizable() {
		return resizable;
	}

	public void setCursor(Cursor cursor) {
		this.cursor = cursor;
		GLFW.glfwSetCursor(window, cursor.getCursor());
	}

	public Cursor getCursor() {
		return cursor;
	}

	public void setIcon() {
		this.icons = new Icon();
		GLFW.glfwSetWindowIcon(window, icons.getIcons());
		this.icons.dispose();
	}
}
