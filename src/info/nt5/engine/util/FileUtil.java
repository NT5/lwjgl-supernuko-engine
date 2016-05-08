package info.nt5.engine.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

	public static String read(String name) {
		String content = null;
		File file = new File(name);

		try {
			FileReader reader = new FileReader(file);
			char[] chars = new char[(int) file.length()];
			reader.read(chars);
			content = new String(chars);
			reader.close();
		} catch (IOException e) {
			Logger.error(String.format("Failed to load file: %s", name), e);
		}
		return content;
	}

	public static List<String> readAllLines(String fileName) {
		List<String> list = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)))) {
			String line;
			while ((line = br.readLine()) != null) {
				list.add(line);
			}
		} catch (IOException e) {
			Logger.error(String.format("Failed to load file: %s", fileName), e);
		}
		return list;
	}

}
