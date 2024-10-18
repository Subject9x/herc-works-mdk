package org.hercworks.core.data.file;

import org.hercworks.voln.DataFile;

/**
 * 	FILE - .STR
 * 		found in multiple locations in the game data, and they follow a specific format.
 * 
 * 0- UINT32 - Total size in bytes of file.
 * 4- UINT16 - total strings in file.
 * 
 * SEQ_0 - String entry.
 * 	0_0- UINT16 - char len of string.
 *  0_2- String segment.
 *  	after segment there seems to be some meta data in some entries that could be used by the game binaries (probably DBSIM).
 *  
 */
public class StringFile extends DataFile{

	private int totalSize;
	private String[] strings;
	
	public StringFile() {}

	public int getTotalSize() {
		return totalSize;
	}

	public String[] getStrings() {
		return strings;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

	public void setStrings(String[] strings) {
		this.strings = strings;
	}
}
