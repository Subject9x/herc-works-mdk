package org.hercworks.voln;

import java.util.LinkedHashSet;

/**
 * Programmatic representation of a vol (sub) directory.
 * 	These are defined in the Vol's header section.
 */
public class VolDir {

	private String label;
	private Byte dirIndex;
	
	private LinkedHashSet<VolEntry> files = new LinkedHashSet<VolEntry>();
	
	public VolDir() {}

	public VolDir(String label, Byte idx) {
		this.label = label;
		this.dirIndex = idx;
	}
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Byte getDirIdx() {
		return dirIndex;
	}

	public void setDirIdx(Byte index) {
		this.dirIndex = index;
	}

	public LinkedHashSet<VolEntry> getFiles() {
		return files;
	}

	public void setFiles(LinkedHashSet<VolEntry> files) {
		this.files = files;
	}
	
}
