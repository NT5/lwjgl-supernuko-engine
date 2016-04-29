package info.nt5.engine.lang;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class Lang {

	private static final String baseName = "info.nt5.engine.lang.StringBundle";

	private static ResourceBundle bundle;
	private static Locale locale;

	public static void init(String language, String country) {
		locale = new Locale(language, country);
		bundle = ResourceBundle.getBundle(baseName, locale);
	}

	public static String getString(String key, Object... args) {
		return MessageFormat.format(bundle.getString(key), args);
	}

}
