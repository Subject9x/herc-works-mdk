package org.hercworks.transfer.dto.file;

public abstract class TransferObject {
	//meta class for generics.

	private String fileName;
	private String fileExt;
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
	
	//utilities for clean presentation
	public short floatStringToFixedShort(String strFloat) {
		float f = Float.valueOf(strFloat);
		f = f * 100f;
		return (short)f;
	}
	
	public String fixedShortToFloatString(short val) {
		float f = (float)val;
		
		return String.valueOf((f/100f));	
	}

}
