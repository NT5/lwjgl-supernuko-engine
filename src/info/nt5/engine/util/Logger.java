package info.nt5.engine.util;

import java.io.PrintStream;
import java.util.Date;

public class Logger {

	private static PrintStream out = System.out;
	private static final boolean DEBUG_MODE = true;

	private Logger() {

	}

	public static void error(Throwable e) {
		StackTraceElement[] elements = Thread.currentThread().getStackTrace();

		out.println(String.format("[ERROR] [%s] [%s] %s", new Date(), elements[2].getClassName(), e.getMessage()));
		e.printStackTrace(out);
	}

	public static void error(String message, Object... args) {
		StackTraceElement[] elements = Thread.currentThread().getStackTrace();

		out.println(String.format("[ERROR] [%s] [%s] %s", new Date(), elements[2].getClassName(),
				String.format(message, args)));
	}

	public static void error(String message, String error) {
		StackTraceElement[] elements = Thread.currentThread().getStackTrace();

		out.println(String.format("[ERROR] [%s] [%s] %s", new Date(), elements[2].getClassName(), message));
		out.println(String.format("[ERROR] [%s] [%s] %s", new Date(), elements[2].getClassName(), error));
	}

	public static void error(String message, Throwable e) {
		error(message, e);
	}

	public static void warn(String message, Object... args) {
		StackTraceElement[] elements = Thread.currentThread().getStackTrace();

		out.println(String.format("[WARNING] [%s] [%s] %s", new Date(), elements[2].getClassName(),
				String.format(message, args)));
	}

	public static void warn(Throwable e) {
		StackTraceElement[] elements = Thread.currentThread().getStackTrace();

		out.println(String.format("[WARNING] [%s] [%s] %s", new Date(), elements[2].getClassName(), e.getMessage()));
		e.printStackTrace(out);
	}

	public static void warn(String message, String warn) {
		StackTraceElement[] elements = Thread.currentThread().getStackTrace();

		out.println(String.format("[WARNING] [%s] [%s] %s", new Date(), elements[2].getClassName(), message));
		out.println(String.format("[WARNING] [%s] [%s] %s", new Date(), elements[2].getClassName(), warn));
	}

	public static void warn(String message, Throwable e) {
		warn(message, e);
	}

	public static void info(String message, Object... args) {
		StackTraceElement[] elements = Thread.currentThread().getStackTrace();

		out.println(String.format("[INFO] [%s] [%s] %s", new Date(), elements[2].getClassName(),
				String.format(message, args)));
	}

	public static void debug(String message, Object... args) {
		if (DEBUG_MODE) {
			StackTraceElement[] elements = Thread.currentThread().getStackTrace();

			out.println(String.format("[DEBUG] [%s] [%s] %s", new Date(), elements[2].getClassName(),
					String.format(message, args)));
		}
	}
}
