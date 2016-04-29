package info.nt5.engine.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import info.nt5.engine.graphics.Window;

public class Screenshot {
	public static BufferedImage take(Window window) {
		GL11.glReadBuffer(GL11.GL_FRONT);
		int width = window.getWidth();
		int height = window.getHeight();
		int bpp = 4;

		ByteBuffer buffer = BufferUtils.createByteBuffer(width * height * bpp);
		GL11.glReadPixels(0, 0, width, height, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer);

		String format = "png";

		String name = String.format("Screenshot_%s.%s",
				LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy_MM_dd_h_m_s")), format);

		File file = new File(name);
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		Logger.info("New screenshot taken %s", name);

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				int i = (x + (width * y)) * bpp;
				int r = buffer.get(i) & 0xFF;
				int g = buffer.get(i + 1) & 0xFF;
				int b = buffer.get(i + 2) & 0xFF;
				image.setRGB(x, height - (y + 1), (0xFF << 24) | (r << 16) | (g << 8) | b);
			}
		}

		try {
			ImageIO.write(image, format, file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}
}
