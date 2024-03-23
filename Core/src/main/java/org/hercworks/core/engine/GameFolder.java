package org.hercworks.core.engine;

import java.util.List;

import org.hercworks.voln.DataFile;


public abstract class GameFolder {

	private List<DataFile> files;
	private String name;
	
	public GameFolder(String name) {
		this.name = name;
	}

	public List<DataFile> getFiles() {
		return files;
	}

	public void setFiles(List<DataFile> files) {
		this.files = files;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
