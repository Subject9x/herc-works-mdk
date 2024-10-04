package org.hercworks.transfer.dto.file;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("file")
public class FileInfo {

	@JsonProperty(value = "file", index = 0)
	private String fileName;
	
	@JsonProperty(value = "ext", index = 1)
	private String fileExt;
	
	@JsonProperty(value = "dir", index = 2)
	private String dir;
	
	
	public String getFileName() {
		return fileName;
	}

	public String getFileExt() {
		return fileExt;
	}

	public String getDir() {
		return dir;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}
	
}
