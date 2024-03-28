package org.hercworks.voln;

import org.hercworks.voln.Voln.FileType;

import at.favre.lib.bytes.Bytes;

/**
 * Genera abstract for all data files.
 */
public abstract class DataFile {

	private byte[] header;
	
	private String fileName;
	private String gameDirPath;
	private String filePath;
	private FileType ext;
	
	private byte[] rawBytes;
	private Bytes fileSize;
	
	public DataFile() {}
	
	public DataFile(String fileName, String dirPath) {
		setFileName(fileName);
		setGameDirPath(dirPath);
	}
	
	public static String makeFileName(String pathName) {
		int idx = pathName.lastIndexOf('\\') + 1;
		if(idx == 0) {
			idx = pathName.lastIndexOf('/') + 1;
		}
		return pathName.substring(idx, idx + (pathName.length() - idx));
	}
	
	public void assignDir(String path) {
		setFilePath(path.substring(0, path.lastIndexOf('\\') + 1));
	}
	
	public String originNameNoExt() {
		return getFileName().substring(0, getFileName().lastIndexOf("."));
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

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public byte[] header() {
		return header;
	}

	public void setHeader(byte[] header) {
		this.header = header;
	}
}
