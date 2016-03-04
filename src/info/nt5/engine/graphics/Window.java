package info.nt5.engine.graphics;


import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWFramebufferSizeCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GL;

import info.nt5.engine.input.Keyboard;
import info.nt5.engine.input.Mouse;
import info.nt5.engine.util.Logger;

public class Window {
	
	private GLFWErrorCallback errorCallback;
	private GLFWKeyCallback keyCallback;
	private GLFWMouseButtonCallback mouseButtonCallback;
	private GLFWCursorPosCallback cursorPosCallback;
	private GLFWFramebufferSizeCallback frameBufferCallback;
	private GLFWWindowSizeCallback windowSizeCallback;
	
	private long window;
	
	private String title;
	private int width, height;
	private boolean vsync, fullscreen, visible, resizable;
	
	private Cursor cursor;
	
	public Window(String title, int width, int height, boolean vsync, boolean fullscreen, boolean visible, boolean resizable) {
		this.title = title;
		this.width = width;
		this.height = height;
		this.vsync = vsync;
		this.fullscreen = fullscreen;
		this.visible = visible;
		this.resizable = resizable;
		
		init();
	}

	void init() {
		if (glfwInit() != GL_TRUE) {
			Logger.error("Failed to initialize GLFW");
			throw new IllegalStateException();
		}
		
		Keyboard.init();
		Mouse.init();
		
		glfwDefaultWindowHints();
		glfwWindowHint( GLFW_VISIBLE, visible ? GL_TRUE : GL_FALSE );
		glfwWindowHint( GLFW_RESIZABLE, resizable ? GL_TRUE : GL_FALSE );
		
		window = glfwCreateWindow( width, height, title, fullscreen ? glfwGetPrimaryMonitor() : NULL, NULL );
		if ( window == NULL ) {
			Logger.error("Failed to create GLFW window.");
			throw new RuntimeException();
		}
		
		GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		//TODO vidmode.width() ....
		glfwSetWindowPos( window, (vidmode.width() - width) / 2, (vidmode.height() - height) / 2 );
		
		glfwMakeContextCurrent(window);
		
		errorCallback = GLFWErrorCallback.createPrint();
		errorCallback.set();
		
		frameBufferCallback = new GLFWFramebufferSizeCallback() {

			@Override
			public void invoke(long window, int width, int height) {
				glViewport(0, 0, width, height);
			}
		};
		frameBufferCallback.set(window);
		
		windowSizeCallback = new GLFWWindowSizeCallback() {

			@Override
			public void invoke(long window, int width, int height) {
				Window.this.width = width;
				Window.this.height = height;
			}
		};
		windowSizeCallback.set(window);
		
		//Java 8 -- Keyboard::glfw_key_callback
		keyCallback = GLFWKeyCallback.create(Keyboard::glfw_key_callback);
		keyCallback.set(window);
		
		mouseButtonCallback = GLFWMouseButtonCallback.create(Mouse::glfw_mouse_button_callback);
		mouseButtonCallback.set(window);
		
		cursorPosCallback = GLFWCursorPosCallback.create(Mouse::glfw_cursor_pos_callback);
		cursorPosCallback.set(window);
		
		glfwSwapInterval(vsync ? 1 : 0);
		
		GL.createCapabilities();
		
		/*cursor =  new Cursor(Cursor.Standard.HAND);
		glfwSetCursor(window, cursor.getCursor());*/
	}
	
	public void update() {
		glfwSwapBuffers(window);
	}
	
	public void updateInput() {
		Keyboard.update();
		Mouse.update();
		glfwPollEvents();
	}
	
	public void clear() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}
	
	public void dispose() {
		keyCallback.release();
		mouseButtonCallback.release();
		cursorPosCallback.release();
		frameBufferCallback.release();
		windowSizeCallback.release();
		glfwTerminate();
		errorCallback.release();
	}
	
	public void close() {
		glfwSetWindowShouldClose(window, GL_TRUE);
	}
	
	public boolean shouldclose() {
		return glfwWindowShouldClose(window) == GL_TRUE;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean vsync() {
		return vsync;
	}

	public void setVsync(boolean vsync) {
		this.vsync = vsync;
		glfwSwapInterval( vsync ? 1 : 0 );
	}

	public boolean fullscreen() {
		return fullscreen;
	}

	public void setFullscreen(boolean fullscreen) {
		//TODO
		this.fullscreen = fullscreen;
	}

	public boolean visible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
		if ( visible ) {
			glfwShowWindow(window);
		}
		else {
			glfwHideWindow(window);
		}
	}

	public boolean resizable() {
		return resizable;
	}
	
	public void setCursor(Cursor cursor) {
		this.cursor = cursor;
		glfwSetCursor(window, cursor.getCursor());
	}
	
	public Cursor getCursor() {
		return cursor;
	}
}
