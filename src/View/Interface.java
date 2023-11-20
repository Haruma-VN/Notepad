package View;

import java.awt.Dimension;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Interface {
	private JFrame window = null;
	private JTextArea textField = null;
	private JScrollPane scrollPane = null;
	private JMenuBar menuBar = null;
	private JMenu file = null, view = null, format = null, color = null;
	private JMenuItem fileNew, fileOpen, fileSave, fileSaveAs, fileExit;
	private FileHelper fileHelper;
	private String filePath = null;
	private boolean isDebug;
	
	public FileHelper getFileHelper() {
		return fileHelper;
	}

	public void setFileHelper(FileHelper fileHelper) {
		this.fileHelper = fileHelper;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public void initHelper() {
		this.fileHelper = new FileHelper(this);
		return;
	}
	
	public JFrame getWindow() {
		return window;
	}

	public void setWindow(JFrame window) {
		this.window = window;
	}

	public JTextArea getTextField() {
		return textField;
	}

	public void setTextField(JTextArea textField) {
		this.textField = textField;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}

	public JMenuBar getMenuBar() {
		return menuBar;
	}

	public void setMenuBar(JMenuBar menuBar) {
		this.menuBar = menuBar;
	}

	public JMenu getFile() {
		return file;
	}

	public void setFile(JMenu file) {
		this.file = file;
	}

	public JMenu getView() {
		return view;
	}

	public void setView(JMenu view) {
		this.view = view;
	}

	public JMenu getFormat() {
		return format;
	}

	public void setFormat(JMenu format) {
		this.format = format;
	}

	public JMenu getColor() {
		return color;
	}

	public void setColor(JMenu color) {
		this.color = color;
	}

	public JMenuItem getFileNew() {
		return fileNew;
	}

	public void setFileNew(JMenuItem fileNew) {
		this.fileNew = fileNew;
	}

	public JMenuItem getFileOpen() {
		return fileOpen;
	}

	public void setFileOpen(JMenuItem fileOpen) {
		this.fileOpen = fileOpen;
	}

	public JMenuItem getFileSave() {
		return fileSave;
	}

	public void setFileSave(JMenuItem fileSave) {
		this.fileSave = fileSave;
	}

	public JMenuItem getFileSaveAs() {
		return fileSaveAs;
	}

	public void setFileSaveAs(JMenuItem fileSaveAs) {
		this.fileSaveAs = fileSaveAs;
	}

	public JMenuItem getFileExit() {
		return fileExit;
	}

	public void setFileExit(JMenuItem fileExit) {
		this.fileExit = fileExit;
	}
	
	private void isDebugMode(boolean isDebug) {
		this.isDebug = isDebug;
	}

	public Interface(boolean isDebug) {
		this.isDebugMode(isDebug);
		this.createWindow();
		this.createTextArea();
		this.createMenuBar();
		this.createMenuItem();
		this.initHelper();
		this.setVisible(true);
	}
	
	private void createWindow() {
		if(this.window == null) {
			this.window = new JFrame(Text.defaultTitle);
		}
		this.window.setSize(new Dimension(800, 800));
		this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		return;
	}
	
	private void displayDialog(String message) {
		JOptionPane.showMessageDialog(this.window, message);
		return;
	}
	
	private void createTextArea() {
		if(this.textField == null) {
			this.textField = new JTextArea();
		}
		if(this.scrollPane == null) {
			this.scrollPane = new JScrollPane(this.textField, 
					JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
					JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
					);
		}
		this.scrollPane.setBorder(BorderFactory.createEmptyBorder());
		this.window.add(this.scrollPane);
		return;
	}
	
	private void createMenuBar() {
		if(this.menuBar == null) {
			this.menuBar = new JMenuBar();
		}
		this.window.setJMenuBar(this.menuBar);
		this.file = new JMenu(Text.fileTitle);
		this.view = new JMenu(Text.viewTitle);
		this.format = new JMenu(Text.formatTitle);
		this.color = new JMenu(Text.colorTitle);
		this.menuBar.add(this.file);
		this.menuBar.add(this.view);
		this.menuBar.add(this.format);
		this.menuBar.add(this.color);
		return;
	}
	
	private void createMenuItem() {
		this.fileNew = new JMenuItem(Text.iFileNew);
		this.fileNew.addActionListener(e -> this.fileHelper.newFile());
		this.fileOpen = new JMenuItem(Text.iFileOpen);
		this.fileOpen.addActionListener(e -> {
			try {
				var path = this.fileHelper.openFile();
				this.filePath = path;
				var fileContent = FileSystem.readFileByUtf8Charset(filePath);
				this.textField.setText(fileContent);
			}
			catch(IOException exception) {
				this.displayDialog(exception.getMessage());
				if(this.isDebug) {
					exception.printStackTrace();
				}
			}
		});
		this.fileExit = new JMenuItem(Text.iFileExit);
		this.fileExit.addActionListener(e -> System.exit(0));
		this.fileSave = new JMenuItem(Text.iFileSave);
		this.fileSave.addActionListener(e -> {
			try {
				this.fileHelper.saveFile(this.textField.getText());
			} catch (IOException exception) {
				this.displayDialog(exception.getMessage());
				if(this.isDebug) {
					exception.printStackTrace();
				}
			}
		});
		this.fileSaveAs = new JMenuItem(Text.iFileSaveAs);
		this.fileSaveAs.addActionListener(e -> {
			try {
				this.fileHelper.saveFileAs(this.textField.getText());
			} catch (IOException exception) {
				this.displayDialog(exception.getMessage());
				if(this.isDebug) {
					exception.printStackTrace();
				}
			}
		});
		this.file.add(this.fileNew);
		this.file.add(this.fileOpen);
		this.file.add(this.fileSave);
		this.file.add(this.fileSaveAs);
		this.file.addSeparator();
		this.file.add(this.fileExit);
		return;
	}
	
	private void setVisible(boolean visibility) {
		this.window.setVisible(visibility);
		return;
	}
}
