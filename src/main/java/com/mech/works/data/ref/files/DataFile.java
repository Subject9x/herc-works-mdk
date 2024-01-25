package com.mech.works.data.ref.files;

/**
 * Genera abstract for all data files.
 */
public abstract class DataFile {

	private String fileName;
	private String gameDirPath;
	
	private byte[] rawBytes;
	
	public DataFile() {}
	
	public DataFile(String fileName, String dirPath) {
		setFileName(fileName);
		setGameDirPath(dirPath);
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getGameDirPath() {
		return gameDirPath;
	}
	
	public void setGameDirPath(String gameDirPath) {
		this.gameDirPath = gameDirPath;
	}

	public byte[] getRawBytes() {
		return rawBytes;
	}

	public void setRawBytes(byte[] rawBytes) {
		this.rawBytes = rawBytes;
	}
	
	
}
