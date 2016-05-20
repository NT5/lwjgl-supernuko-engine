package info.nt5.engine.sound;

import static org.lwjgl.openal.ALC10.alcCloseDevice;
import static org.lwjgl.openal.ALC10.alcCreateContext;
import static org.lwjgl.openal.ALC10.alcDestroyContext;
import static org.lwjgl.openal.ALC10.alcMakeContextCurrent;
import static org.lwjgl.openal.ALC10.alcOpenDevice;

import java.nio.ByteBuffer;
import java.util.List;

import org.lwjgl.openal.AL;
import org.lwjgl.openal.ALC;
import org.lwjgl.openal.ALC11;
import org.lwjgl.openal.ALCCapabilities;
import org.lwjgl.openal.ALUtil;
import org.lwjgl.system.MemoryUtil;

import info.nt5.engine.util.Logger;

public class SoundManager {

	private static long context;
	private static long device;
	private static List<String> devices;

	public static void create() {
		device = alcOpenDevice((ByteBuffer) null);
		if (device == MemoryUtil.NULL) {
			throw new IllegalStateException("Failed to open the default audio device.");
		}
		ALCCapabilities deviceCaps = ALC.createCapabilities(device);

		if (deviceCaps.OpenALC11) {
			devices = ALUtil.getStringList(MemoryUtil.NULL, ALC11.ALC_ALL_DEVICES_SPECIFIER);
			for (String device : devices) {
				Logger.info("[%s]", device);
			}
		}

		context = alcCreateContext(device, (ByteBuffer) null);
		alcMakeContextCurrent(context);
		AL.createCapabilities(deviceCaps);
	}

	public static void setAudioDivice(int index) {
		close();
		Logger.info("Change Audio device to: %s", devices.get(index));
		device = alcOpenDevice(devices.get(index));
		if (device == MemoryUtil.NULL) {
			throw new IllegalStateException("Failed to open the default audio device.");
		}
		context = alcCreateContext(device, (ByteBuffer) null);
		alcMakeContextCurrent(context);

		ALCCapabilities deviceCaps = ALC.createCapabilities(device);
		AL.createCapabilities(deviceCaps);
	}

	public static List<String> getDeviceList() {
		return devices;
	}

	public static void close() {
		alcDestroyContext(context);
		alcCloseDevice(device);
	}

}
