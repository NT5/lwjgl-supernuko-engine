package info.nt5.engine.sound;

import static org.lwjgl.openal.AL10.AL_BUFFER;
import static org.lwjgl.openal.AL10.AL_FALSE;
import static org.lwjgl.openal.AL10.AL_GAIN;
import static org.lwjgl.openal.AL10.AL_LOOPING;
import static org.lwjgl.openal.AL10.AL_PITCH;
import static org.lwjgl.openal.AL10.AL_PLAYING;
import static org.lwjgl.openal.AL10.AL_SOURCE_STATE;
import static org.lwjgl.openal.AL10.AL_TRUE;
import static org.lwjgl.openal.AL10.alBufferData;
import static org.lwjgl.openal.AL10.alDeleteBuffers;
import static org.lwjgl.openal.AL10.alDeleteSources;
import static org.lwjgl.openal.AL10.alGenBuffers;
import static org.lwjgl.openal.AL10.alGenSources;
import static org.lwjgl.openal.AL10.alGetSourcei;
import static org.lwjgl.openal.AL10.alListenerf;
import static org.lwjgl.openal.AL10.alSourcePause;
import static org.lwjgl.openal.AL10.alSourcePlay;
import static org.lwjgl.openal.AL10.alSourceStop;
import static org.lwjgl.openal.AL10.alSourcef;
import static org.lwjgl.openal.AL10.alSourcei;

public class ALPlayer {

	private int buffer;
	private int source;
	private float gain, pitch;
	private boolean loop;

	public ALPlayer(String path) {
		WaveData data = WaveData.create(path);

		this.buffer = alGenBuffers();
		alBufferData(this.buffer, data.format, data.data, data.samplerate);
		data.dispose();

		this.source = alGenSources();
	}

	private void bind() {
		alSourcei(this.source, AL_BUFFER, this.buffer);
	}

	public float getPitch() {
		return pitch;
	}

	public float getGain() {
		return gain;
	}

	public boolean getLoop() {
		return loop;
	}

	public boolean isPlaying() {
		int state = alGetSourcei(source, AL_SOURCE_STATE);
		return (state == AL_PLAYING);
	}

	public void play() {
		this.bind();
		alSourcePlay(this.source);
	}

	public void stop() {
		this.bind();
		alSourceStop(this.source);
	}

	public void pause() {
		alSourcePause(this.source);
	}

	public void setLoop(boolean flag) {
		this.bind();
		this.loop = flag;
		if (flag == true) {
			alSourcei(this.source, AL_LOOPING, AL_TRUE);
		} else {
			alSourcei(this.source, AL_LOOPING, AL_FALSE);
		}
	}

	public void setPitch(float pitch) {
		if (this.pitch != pitch) {
			this.bind();
			this.pitch = pitch;
			alSourcef(this.source, AL_PITCH, pitch);
		}
	}

	public void setGain(float gain) {
		if (this.gain != gain) {
			this.bind();
			this.gain = gain;
			alSourcef(source, AL_GAIN, gain);
		}
	}

	public static void setListenerGain(float gain) {
		alListenerf(AL_GAIN, gain);
	}

	public static void setListenerPitch(float pitch) {
		alListenerf(AL_PITCH, pitch);
	}

	public void dispose() {
		this.bind();
		alDeleteSources(source);
		alDeleteBuffers(buffer);
	}

}
