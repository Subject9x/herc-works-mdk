package com.mech.works.data.ref.files;

import com.mech.works.vol.data.Voln.FileType;

import at.favre.lib.bytes.Bytes;

/**
 * Genera abstract for all data files.
 */
public abstract class DataFile {

	private String fileName;
	private String gameDirPath;
	private FileType ext;
	
	private byte[] rawBytes;
	private Bytes fileSize;
	
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

	public FileType getExt() {
		return ext;
	}

	public void setExt(FileType ext) {
		this.ext = ext;
	}

	public Bytes getFileSize() {
		return fileSize;
	}

	public void setFileSize(Bytes fileSize) {
		this.fileSize = fileSize;
	}
}
