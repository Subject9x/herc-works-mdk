package org.hercworks.core.data.file.msn;

import java.util.Arrays;

/**
 * Seen towards end of map script
 * TRAIN5 has 13 of these.
 * 
 */
public class UnkEntity164Bytes {

	private short entityId;
	
	private short unkFlag1;
	
	private short unkFlag2;
	
	private short unkFlag3;
	
	private short[] values = new short[78];
	
	public UnkEntity164Bytes() {}

	public short getEntityId() {
		return entityId;
	}

	public short getUnkFlag1() {
		return unkFlag1;
	}

	public short getUnkFlag2() {
		return unkFlag2;
	}

	public short getUnkFlag3() {
		return unkFlag3;
	}

	public short[] getValues() {
		return values;
	}

	public void setEntityId(short entityId) {
		this.entityId = entityId;
	}

	public void setUnkFlag1(short unkFlag1) {
		this.unkFlag1 = unkFlag1;
	}

	public void setUnkFlag2(short unkFlag2) {
		this.unkFlag2 = unkFlag2;
	}

	public void setUnkFlag3(short unkFlag3) {
		this.unkFlag3 = unkFlag3;
	}

	public void setValues(short[] values) {
		this.values = values;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("{\n");
		sb.append("		entId = ").append(getEntityId()).append("\n");
		sb.append("		f1 = ").append(getUnkFlag1()).append("\n");
		sb.append("		f2 = ").append(getUnkFlag2()).append("\n");
		sb.append("		f3 = ").append(getUnkFlag3()).append("\n");
		sb.append("		vals = ").append(Arrays.toString(getValues())).append("\n");
		
		sb.append("}\n");
		return sb.toString();
	}
	
}
