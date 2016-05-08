package info.nt5.engine.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.channels.FileChannel;
import java.util.List;

import static org.lwjgl.system.jemalloc.JEmalloc.*;

import org.lwjgl.BufferUtils;

public class BufferUtil {

	public static FloatBuffer toFloatBuffer(float[] array) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(array.length);
		buffer.put(array);
		buffer.flip();

		return buffer;
	}

	public static ByteBuffer createByteBuffer(long size) {
		return je_malloc(size);
	}

	public static ByteBuffer createByteBuffer(byte[] array) {
		ByteBuffer result = ByteBuffer.allocateDirect(array.length).order(ByteOrder.nativeOrder());
		result.put(array).flip();
		return result;
	}

	public static ByteBuffer fileToByteBuffer(String path) throws IOException {
		ByteBuffer buffer;

		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		FileChannel fc = fis.getChannel();

		buffer = createByteBuffer((int) fc.size() + 1);

		while (fc.read(buffer) != -1)
			;

		fis.close();
		fc.close();

		buffer.flip();
		return buffer;
	}

	public static float[] listFloatToArray(List<Float> list) {
		int size = list != null ? list.size() : 0;
		float[] floatArr = new float[size];
		for (int i = 0; i < size; i++) {
			floatArr[i] = list.get(i);
		}
		return floatArr;
	}

	public static int[] listIntToArray(List<Integer> list) {
		int[] result = list.stream().mapToInt((Integer v) -> v).toArray();
		return result;
	}
}
