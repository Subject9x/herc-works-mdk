package org.hercworks.core.data.file.cfg;

import org.hercworks.voln.DataFile;

/**
 * FILE
 * 		[ROOT]/DATA/DRIVE.CFG
 */
/**
	E:\ES2_OS\dev\earthsiege2
	E:\ES2_OS\dev\earthsiege2
 */
public class Drive extends DataFile{

	private String[] driveLines;
	
	public Drive() {
		super("DRIVE.CFG", "DATA/");
	}

	public String[] getDriveLines() {
		return driveLines;
	}

	public void setDriveLines(String[] driveLines) {
		this.driveLines = driveLines;
	}

}
