package info.nt5.engine.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileUtil {
	
	public static String read(String name) {
		String content = null;
		File file = new File(name);
		
		try {
			FileReader reader = new FileReader(file);
			char[] chars = new char[ (int) file.length() ];
			reader.read(chars);
			content = new String(chars);
			reader.close();
		}
		catch(IOException e) {
			Logger.error(String.format("Failed to load file: %s", name), e);
		}
		return content;
	}

}
