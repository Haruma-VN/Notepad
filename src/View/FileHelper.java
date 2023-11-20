package View;

import java.awt.FileDialog;
import java.io.IOException;

public class FileHelper {
	private Interface app;
	
	public FileHelper(Interface app) {
		this.app = app;
	}
	
	public void newFile() {
		this.app.getTextField().setText(Text.emptyString);
		this.app.getWindow().setTitle(Text.defaultTitle);
		return;
	}
	
	public String openFile() {
		var fileDialog = new FileDialog(this.app.getWindow(), Text.iFileOpen, FileDialog.LOAD);
		fileDialog.setVisible(true);
		if(fileDialog.getFile() == null) {
			return null;
		}
		this.app.getWindow().setTitle(fileDialog.getFile());
		return String.format("%s%s", fileDialog.getDirectory(), fileDialog.getFile())
				.replaceAll("\\\\", "/");
	}
	
	public void saveFile(String content) throws IOException {
		if(this.app.getFilePath() == null) {
			this.saveFileAs(content);
		}
		else {
			FileSystem.saveFileByUtf8Charset(this.app.getFilePath(), content);
		}
		return;
	}
	
	public void saveFileAs(String content) throws IOException {
		var fileDialog = new FileDialog(this.app.getWindow(), Text.iFileSaveAs, FileDialog.SAVE);
		fileDialog.setVisible(true);
		if(fileDialog.getFile() == null) {
			return;
		}
		else {
			this.app.getWindow().setTitle(fileDialog.getFile());
			FileSystem.saveFileByUtf8Charset(String.format("%s%s", fileDialog.getDirectory(), fileDialog.getFile())
				.replaceAll("\\\\", "/"), content);
		}
		return;
	}
	
}
