package org.hercworks.core.data.file;

import java.util.Arrays;

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
	private StringEntry[] strings;
	
	public StringFile() {}

	public int getTotalSize() {
		return totalSize;
	}

	public StringEntry[] getStrings() {
		return strings;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

	public void setStrings(StringEntry[] strings) {
		this.strings = strings;
	}
	
	public StringEntry createEntry(short guid, short[] flags, short len,  String val) {
		
		StringEntry str = new StringEntry();

		str.setGuid(guid);
		str.setFlags(flags);
		str.setLen(len);
		str.setVal(val);
		
		return str;
	}
	
	public class StringEntry{
		
		private short guid;
		
		private short[] flags;
		
		private short len;
		
		private String val;
		
		public StringEntry() {}


		public short getGuid() {
			return guid;
		}

		public void setGuid(short guid) {
			this.guid = guid;
		}

		public short[] getFlags() {
			return flags;
		}

		public void setFlags(short[] flags) {
			this.flags = flags;
		}

		public short getLen() {
			return len;
		}

		public void setLen(short len) {
			this.len = len;
		}

		public String getVal() {
			return val;
		}

		public void setVal(String val) {
			this.val = val;
		}
		
		@Override
		public String toString() {
			StringBuilder str = new StringBuilder();
			
			str.append("{")
				.append(" guid = ").append(getGuid())
				.append(", flags = ").append(Arrays.toString(getFlags()))
				.append(", len = ").append(getLen())
				.append(", val = ").append(getVal())
				.append("}")
				;
			
			return str.toString();
		}
	}
}
