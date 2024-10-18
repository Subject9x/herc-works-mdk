package org.hercworks.core.data.file;

import org.hercworks.voln.DataFile;

/**
 * UInt32: Number of strings stored in this file
UInt32: Total data in bytes for that text, counting null terminators
[That number of UInt16 indexes, indicating the start position in bytes of each of those strings; first entry is 0]
[That number of null-terminated strings]

WPN_DESC.BIN has 111 strings, 3 strings per weapon, for 37 weapons.  All 33 weapons are included, in the same order as in e.g. GAM\WEAPONS.DAT, plus an entry for each of the missile types.

WPN_INFO.BIN has 130 strings, 5 strings per weapon, for 26 weapons.  Only the 26 normally obtainable weapons are included, and they are listed in the same order as they are listed in the Armory tab.
 */
public class StringBinaryFile extends DataFile {

	private int totalStrings;
	
	private short indexTotal;
	
	private short[] stringOffset;
	
	private String[] values;
	
	public StringBinaryFile() {}

	public int getTotalStrings() {
		return totalStrings;
	}

	public short getIndexTotal() {
		return indexTotal;
	}

	public short[] getStringOffset() {
		return stringOffset;
	}

	public String[] getValues() {
		return values;
	}

	public void setTotalStrings(int totalStrings) {
		this.totalStrings = totalStrings;
	}

	public void setIndexTotal(short indexTotal) {
		this.indexTotal = indexTotal;
	}

	public void setStringOffset(short[] stringOffset) {
		this.stringOffset = stringOffset;
	}

	public void setValues(String[] values) {
		this.values = values;
	}
}
