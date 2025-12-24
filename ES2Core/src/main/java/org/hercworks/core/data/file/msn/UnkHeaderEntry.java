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
	
	public UnkHeaderEntry(short f1, short v1, short v2, short f2, short v3, short f3) {
		this.unkFlag1 = f1;
		this.unkValue1 = v1;
		this.unkValue2 = v2;
		this.unkFlag2 = f2;
		this.unkValue3 = v3;
		this.unkFlag3 = f3;
	}


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
	
	@Override
	public String toString() {
		StringBuilder b =new StringBuilder();
		b.append("[ ").append(getUnkFlag1()).append(", ").append(getUnkValue1()).append(", ")
						.append(getUnkValue2()).append(", ").append(getUnkFlag2()).append(", ")
						.append(", ").append(getUnkValue3()).append(", ").append(getUnkFlag3())
						.append("]");
		
		return b.toString();
	}
	
}
