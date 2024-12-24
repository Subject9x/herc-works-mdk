package org.hercworks.core.data.file.msn;

/**
 * 14 bytes per entry
 * observed at the top of the MSN file,
 * 	there's a counter for how many,
 * and each has an iterator id.
 * 
 */
public class UnkHeaderEntry {

	private short unkFlag1;
	
	private short unkValue1;
	
	private short unkValue2;
	
	private short unkFlag2;
	
	private short unkValue3;
	
	private short unkFlag3;
	
	
	public UnkHeaderEntry() {}


	public short getUnkFlag1() {
		return unkFlag1;
	}

	public short getUnkValue1() {
		return unkValue1;
	}

	public short getUnkValue2() {
		return unkValue2;
	}

	public short getUnkFlag2() {
		return unkFlag2;
	}

	public short getUnkValue3() {
		return unkValue3;
	}

	public void setUnkFlag1(short unkFlag1) {
		this.unkFlag1 = unkFlag1;
	}

	public void setUnkValue1(short unkValue1) {
		this.unkValue1 = unkValue1;
	}

	public void setUnkValue2(short unkValue2) {
		this.unkValue2 = unkValue2;
	}

	public void setUnkFlag2(short unkFlag2) {
		this.unkFlag2 = unkFlag2;
	}

	public void setUnkValue3(short unkValue3) {
		this.unkValue3 = unkValue3;
	}


	public short getUnkFlag3() {
		return unkFlag3;
	}


	public void setUnkFlag3(short unkFlag3) {
		this.unkFlag3 = unkFlag3;
	}
	
	
}
