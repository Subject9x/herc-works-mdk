package org.hercworks.core.data.file;

import org.hercworks.voln.DataFile;

/**
 * FILE
 * 		/LANG0/[lang]/foo.BIN
 *
 *	Optimized strings file, contains null-terminated strings with pre-computed offsets
 *	and lookup index for each string.
 *
 *	Mainly used for shell descriptions, and for swapping languages.
 * 
 *  0 - UINT32 - Number of strings stored in this file
 *  4 - UINT32 - Total data in bytes for that text, counting null terminators
 *  8 - SEQ 0 - file-offset of each string, allowing engine to jump to the string.
UInt32: 
[That number of UInt16 indexes, indicating the start position in bytes of each of those strings; first entry is 0]
[That number of null-terminated strings]

WPN_DESC.BIN has 111 strings, 3 strings per weapon, for 37 weapons.  All 33 weapons are included, in the same order as in e.g. GAM\WEAPONS.DAT, plus an entry for each of the missile types.

WPN_INFO.BIN has 130 strings, 5 strings per weapon, for 26 weapons.  Only the 26 normally obtainable weapons are included, and they are listed in the same order as they are listed in the Armory tab. 


 * thanks to Crow! for the work mapping this.
 */
public class StringBinaryFile extends DataFile {
	
	private String[] values;
	
	public StringBinaryFile() {}


	public String[] getValues() {
		return values;
	}

	public void setValues(String[] values) {
		this.values = values;
	}

}
