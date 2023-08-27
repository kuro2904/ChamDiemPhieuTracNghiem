package custom;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class CustomFileFilter extends FileFilter{
	
	private String[] extension;
	private String description;
	
	public CustomFileFilter (String[] extension, String description) {
		this.extension = extension;
		this.description = description;
	}

	@Override
	public boolean accept(File f) {
		String fileName = f.getName().toLowerCase();
		if(f.isDirectory()) {
			return true;
		}
		for(String ext: extension) {
			if(fileName.endsWith("."+ext)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String getDescription() {
		return description;
	}
	
}
