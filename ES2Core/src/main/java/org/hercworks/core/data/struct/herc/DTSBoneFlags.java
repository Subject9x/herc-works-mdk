package org.hercworks.core.data.struct.herc;

public enum DTSBoneFlags {

	
	LEG_LEFT_CALF((short)256),
	LEG_RIGHT_CALF((short)512),
	LEG_LEFT_THIGH((short)768),
	LEG_RIGHT_THIGH((short)1024),
	LEG_RIGHT_FOOT((short)1280),
	LEG_LEFT_FOOT((short)1536);
	
	private short flag;
	
	private DTSBoneFlags(short flagNum) {
		
	}
	
	public short flag() {
		return this.flag;
	}
	
}
