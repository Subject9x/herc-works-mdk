package org.hercworks.core.data.file.msn;

import org.hercworks.voln.DataFile;

/**
 *	FILE - (Mission).ENG, .GER, .FRE
 *		A variant of observed 'String' files, these provide the string data for missions.
 */
public class MissionStringFile extends DataFile{

	private int totalSize;
	private StringEntry[] strings;
	
	public MissionStringFile() {}

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
	
	public StringEntry createEntry(short guid, short rVal, short rFlag, short len,  String val) {
		
		StringEntry str = new StringEntry();

		str.setGuid(guid);
		str.setResultVal(rVal);
		str.setResultFlag(rFlag);
		str.setLen(len);
		str.setVal(val);
		
		return str;
	}
	
	public class StringEntry{
		
		private short guid;
		
		private short resultVal;
		
		private short resultFlag;
		
		private short len;
		
		private String val;
		
		public StringEntry() {}


		public short getGuid() {
			return guid;
		}

		public void setGuid(short guid) {
			this.guid = guid;
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
		
		public short getResultVal() {
			return resultVal;
		}


		public void setResultVal(short resultVal) {
			this.resultVal = resultVal;
		}


		public short getResultFlag() {
			return resultFlag;
		}


		public void setResultFlag(short resultFlag) {
			this.resultFlag = resultFlag;
		}


		@Override
		public String toString() {
			StringBuilder str = new StringBuilder();
			
			str.append("{")
				.append(" guid = ").append(getGuid())
				.append(", r val = ").append(getResultVal())
				.append(", r flag = ").append(getResultFlag())
				.append(", len = ").append(getLen())
				.append(", val = ").append(getVal())
				.append("}")
				;
			
			return str.toString();
		}
	}
}
