package info.nt5.engine.util;

import java.io.File;

public class FilePaths {
	public static final String SEPARATOR = File.separator;
	public static final String ASSETS_PATH = "assets" + SEPARATOR;
	public static final String IMG_PATH = ASSETS_PATH + "img" + SEPARATOR;
	public static final String MODEL_PATH = ASSETS_PATH + "models" + SEPARATOR;
	public static final String WAV_PATH = ASSETS_PATH + "wav" + SEPARATOR;
	public static final String ACTOR_PATH = ASSETS_PATH + "actors" + SEPARATOR + "actors" + SEPARATOR;

	public static String getImg(String img) {
		return IMG_PATH + img;
	}

	public static String getModel(String model) {
		return MODEL_PATH + model;
	}

	public static String getWav(String wav) {
		return WAV_PATH + wav;
	}

	public static String Actor(String actor) {
		return ACTOR_PATH + actor;
	}
}
