package org.hercworks.extract.util;

/**
 * Convenience class for bundling file name as string and its guessed file type from
 * command line to processors.
 */
public class FileItemDBM extends FileItem {

	private boolean index0Alpha = false;
	
	public FileItemDBM() {}

	public boolean isIndex0Alpha() {
		return index0Alpha;
	}

	public void setIndex0Alpha(boolean index0Alpha) {
		this.index0Alpha = index0Alpha;
	}

}
