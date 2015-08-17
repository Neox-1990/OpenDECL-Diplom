package editor;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class xmlFileFilter extends FileFilter {

	@Override
	public boolean accept(File f) {
		String extension = "";

		int i = f.getName().lastIndexOf('.');
		if (i > 0) {
		    extension = f.getName().substring(i+1).toLowerCase();
		}
		if(extension.compareTo("xml")==0) return true;
		else return false;
	}

	@Override
	public String getDescription() {
		return "XML files";
	}

}
