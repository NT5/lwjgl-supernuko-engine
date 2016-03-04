package info.nt5.engine.graphics;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.*;
import java.nio.ByteBuffer;

import info.nt5.engine.util.BufferUtil;
import info.nt5.engine.math.Vector2f;
import info.nt5.engine.math.Vector4f;

public class Texture {
	private int id;
	private int width, height;
	private int components;
	private ByteBuffer buffer;
	
	private static int filter = GL_LINEAR;
	private static int wrap = GL_REPEAT;
	
	Texture(int id) {
		this.id = id;
	}
	
	public Texture() {
		this.id = glGenTextures();
	}
	
	public static Texture fromColor(Color c, int width, int height) {
		ByteBuffer buffer = BufferUtil.createByteBuffer(width * height * 4);
		
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				buffer.put((byte) (c.r * 255.0f)).put((byte) (c.g * 255.0f)).put((byte) (c.b * 255.0f)).put((byte) (c.a * 255.0f));
			}
		}
		
		buffer.flip();
		
		return fromByteBuffer(buffer, width, height, 4);
	}
	
	public static Texture fromImage(String path) {
		Image image = new Image(path);
		Texture t = fromByteBuffer(image.getBuffer(), image.getWidth(), image.getHeight(), image.getComponents());
		image.dispose();
		return t;
	}
	
	public static Texture fromByteBuffer(ByteBuffer buffer, int width, int height, int components) {
		Texture texture = new Texture();
		texture.buffer = buffer;
		texture.bind();
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, wrap);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, wrap);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, filter);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, filter);
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, components == 4 ? GL_RGBA : GL_RGB, GL_UNSIGNED_BYTE, buffer);
		texture.unbind();
		
		texture.width = width;
		texture.height = height;
		texture.components = components;
		
		return texture;
	}

	public void bind() {
		glBindTexture(GL_TEXTURE_2D, id);
	}
	
	public void unbind() {
		glBindTexture(GL_TEXTURE_2D, 0);
	}
	
	public void dispose() {
		glDeleteTextures(id);
	}
	
	public static void setFilter(int filter) {
		Texture.filter = filter;
	}
	
	public static void setWrap(int wrap) {
		Texture.wrap = wrap;
	}
	
	public static int getFilter() {
		return filter;
	}
	
	public static int getWrap() {
		return wrap;
	}
	
	public ByteBuffer getImage2D(int format) {
		return getImage2D(format, GL_FLOAT);
	}
	
	public ByteBuffer getImage2D(int format, int type) {
		int size = 4;
		
		switch(format) {
			case GL_RGB:
			case GL_BGR:
				size = 3;
				break;
		}
		
		size = (int) (size * width * height * 4);
		
		ByteBuffer buffer = BufferUtil.createByteBuffer(size);
		return getImage2D(format, type, buffer);
	}
	
	public ByteBuffer getImage2D(int format, int type, ByteBuffer buffer) {
		glGetTexImage(GL_TEXTURE_2D, 0, format, type, buffer);
		return buffer;
	}
	
	public int getID() {
		return id;
	}
	
	public float getWidth() {
		return width;
	}
	
	public float getHeight() {
		return height;
	}
	
	public int getComponents() {
		return components;
	}
	
	public ByteBuffer getBuffer() {
		return buffer;
	}
	
	public float getMinU() {
		return 0;
	}

	public float getMinV() {
		return 0;
	}

	public float getMaxU() {
		return 1;
	}

	public float getMaxV() {
		return 1;
	}
	
	public SubTexture getSubTexture(Vector4f uv) {
		return new SubTexture(this, uv);
	}
	
	public SubTexture getSubTexture(Vector2f min, Vector2f max) {
		return new SubTexture(this, min, max);
	}
	
	public SubTexture getSubTexture(float minU, float minV, float maxU, float maxV) {
		return new SubTexture(this, minU, minV, maxU, maxV);
	}
	
	public SubTexture getSubTexture(float minU, float minV, float maxU, float maxV, float width, float height) {
		return new SubTexture(this, minU, minV, maxU, maxV, width, height);
	}
}
