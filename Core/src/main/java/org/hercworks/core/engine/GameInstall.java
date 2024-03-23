package org.hercworks.core.engine;

import java.util.List;

import org.hercworks.voln.Voln;

/**
 * Top-level class and entry to accessing all data from an installation of the game.
 */
public class GameInstall {

	private String rootDir;
	
	private List<Voln> volFiles;
	private List<GameFolder> folders;
	
	public GameInstall() {}

	public String getRootDir() {
		return rootDir;
	}

	public void setRootDir(String rootDir) {
		this.rootDir = rootDir;
	}

	public List<Voln> getVolFiles() {
		return volFiles;
	}

	public void setVolFiles(List<Voln> volFiles) {
		this.volFiles = volFiles;
	}

	public List<GameFolder> getFolders() {
		return folders;
	}

	public void setFolders(List<GameFolder> folders) {
		this.folders = folders;
	}
}
