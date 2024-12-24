package org.hercworks.core.data.file.msn;


/**
 * 
 */
public class UnkEntity22Byte {

	private short entityId;
	
	private short[] flags = new short[6];
	
	private short[] values = new short[4];
	
	public UnkEntity22Byte() {}

	public short getEntityId() {
		return entityId;
	}

	public short[] getFlags() {
		return flags;
	}

	public short[] getValues() {
		return values;
	}

	public void setEntityId(short entityId) {
		this.entityId = entityId;
	}

	public void setFlags(short[] flags) {
		this.flags = flags;
	}

	public void setValues(short[] values) {
		this.values = values;
	}
}
