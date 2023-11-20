package View;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileSystem {

	
	public static String readFile(String path, Charset encoding) throws IOException {
		var byteListView = (byte[]) Files.readAllBytes(Paths.get(path));
		return new String(byteListView, encoding);
	}
	
	public static String readFileByUtf8Charset(String path) throws IOException {
		return readFile(path, StandardCharsets.UTF_8);
	}
	
	public static void saveFileByUtf8Charset(String path, String content) throws IOException {
		var fileWriter = new FileWriter(path);
		fileWriter.write(content);
		fileWriter.close();
		return;
	}
	
}
