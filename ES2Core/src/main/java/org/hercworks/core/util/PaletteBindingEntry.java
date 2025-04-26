package org.hercworks.core.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Solves 2 issues:
 * 	1) ES2 executables are coded to know which DPL file is matched to which DBA and DBM files.
 *  2) ES2 executables seem aware of whether the corresponding DBM/DBA needs index 0 of DPL file to be 'alpha transparent pixel'.
 */
public class PaletteBindingEntry {

	private String fileName;
	private List<String> files;
	private boolean index0Alpha = false;
	
	
	public PaletteBindingEntry() {
		files = new ArrayList<String>();
	}

	public PaletteBindingEntry(String file, List<String> bindings, boolean alphaIndex) {
		this.fileName = file;
		this.files = bindings;
		this.index0Alpha = alphaIndex;
	}
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public List<String> getFiles() {
		return files;
	}

	public void setFiles(List<String> files) {
		this.files = files;
	}

	public boolean isIndex0Alpha() {
		return index0Alpha;
	}
	
	public void setIndex0Alpha(boolean index0Alpha) {
		this.index0Alpha = index0Alpha;
	}
	
	
}
