package org.hercworks.core.data.ref.constants;

public enum DTSBoneId {  
    
	NONE("NONE", (short)-1, -1),
	ORIGIN("ORIGIN", (short)0x00, 0),
	
	CENTER_TORSO("CENTER_TORSO", (short)0x05, 1),
	
	HEAD("HEAD", (short)0x08, 5),
	CAMERA("CAMERA", (short)0x09, 4),
	CENTER1("CENTER1", (short)0x0A, 2),
	CENTER2("CENTER2", (short)0x0B, 3),
	UNK_13("UNK_13", (short)0x0D, 6),
	UNK_66("UNK_66", (short)0x42, 7),
	UNK_77("UNK_77", (short)0x4D, 8),
	
	PELVIS("PELVIS", (short)0x0C, 12),
	
	THIGH_RIGHT("THIGH_RIGHT", (short)0x04, 13),
	CALF_RIGHT("CALF_RIGHT", (short)0x02, 14),
	ANKLE_RIGHT("ANKLE_RIGHT", (short)0x0E, 15),
	FOOT_RIGHT("FOOT_RIGHT", (short)0x06, 16),
	TOE_RIGHT("TOE_RIGHT", (short)0x11, 17),

	THIGH_LEFT("THIGH_LEFT", (short)0x03, 18),
	CALF_LEFT("CALF_LEFT", (short)0x01, 19),
	ANKLE_LEFT("ANKLE_LEFT", (short)0x0F, 20),
	FOOT_LEFT("FOOT_LEFT", (short)0x07, 21),
	TOE_LEFT("TOE_LEFT", (short)0x10, 22)
	;
	
    
    private String name;
	private short val;
	private int order;
	
	private DTSBoneId(String name, short val, int order) {
		this.name = name;
		this.val = val;
		this.order = order;
	}

	public String getName() {
		return name;
	}

	public short getVal() {
		return val;
	}

	public int order() {
		return order;
	}
	
	
	public static DTSBoneId forVal(short val) {
		for(DTSBoneId bone : DTSBoneId.values()) {
			if(bone.getVal() == val) {
				return bone;
			}
		}
		return DTSBoneId.ORIGIN;
	}
	
}