package org.hercworks.core.data.file.msn;

/**
 * Observed following unit info segments, 
 * in TRAIN5.MSN there's only 2 o them
 */
public class UnkEntity102Bytes {

	private short entityId;
	
	private short[] flags = new short[49];
	
	private short unkVal_100;
	
	public UnkEntity102Bytes() {}

	public short getEntityId() {
		return entityId;
	}

	public short[] getFlags() {
		return flags;
	}

	public short getUnkVal_100() {
		return unkVal_100;
	}

	public void setEntityId(short entityId) {
		this.entityId = entityId;
	}

	public void setFlags(short[] flags) {
		this.flags = flags;
	}

	public void setUnkVal_100(short unkVal_100) {
		this.unkVal_100 = unkVal_100;
	}
}
