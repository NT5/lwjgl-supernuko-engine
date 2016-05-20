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
import static org.lwjgl.openal.AL10.alSourcePause;
import static org.lwjgl.openal.AL10.alSourcePlay;
import static org.lwjgl.openal.AL10.alSourceStop;
import static org.lwjgl.openal.AL10.alSourcef;
import static org.lwjgl.openal.AL10.alSourcei;

public class SoundPlayer {

	private int buffer;
	private int source;

	public SoundPlayer(String path) {
		WaveData data = WaveData.create(path);

		this.buffer = alGenBuffers();
		alBufferData(this.buffer, data.format, data.data, data.samplerate);
		data.dispose();

		this.source = alGenSources();
	}

	private void bind() {
		alSourcei(source, AL_BUFFER, buffer);
	}

	public boolean isPlaying() {
		int state = alGetSourcei(source, AL_SOURCE_STATE);
		return (state == AL_PLAYING);
	}

	public void play() {
		this.bind();
		alSourcePlay(source);
	}

	public void stop() {
		this.bind();
		alSourceStop(source);
	}

	public void pause() {
		alSourcePause(source);
	}

	public void setLoop(boolean flag) {
		this.bind();
		if (flag == true) {
			alSourcei(source, AL_LOOPING, AL_TRUE);
		} else {
			alSourcei(source, AL_LOOPING, AL_FALSE);
		}
	}

	public void setPitch(float pitch) {
		this.bind();
		alSourcef(this.source, AL_PITCH, pitch);
	}

	public void setGain(float gain) {
		this.bind();
		alSourcef(source, AL_GAIN, gain);
	}

	public void dispose() {
		this.bind();
		alDeleteSources(source);
		alDeleteBuffers(buffer);
	}

}
