package org.hercworks.extract.util;

import org.hercworks.voln.FileType;

/**
 * Convenience class for bundling file name as string and its guessed file type from
 * command line to processors.
 */
public class FileItem {

	private FileType type;
	private String name;
	
	public FileItem() {}
	
	public FileItem(String name, FileType type) {
		this.name = name;
		this.type = type;
	}

	public FileType getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public void setType(FileType type) {
		this.type = type;
	}

	public void setName(String name) {
		this.name = name;
	}
}
