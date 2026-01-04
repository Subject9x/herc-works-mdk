package org.hercworks.core.data.file.msn;

import java.util.Arrays;

/**
 * Observed following unit info segments, 
 * in TRAIN5.MSN there's only 2 o them
 */
public class UnkEntity102Bytes extends MapObject{
	
	private short[] flags = new short[49];
	
	private short unkVal_100;
	
	public UnkEntity102Bytes() {}

	public short[] getFlags() {
		return flags;
	}

	public short getUnkVal_100() {
		return unkVal_100;
	}


	public void setFlags(short[] flags) {
		this.flags = flags;
	}

	public void setUnkVal_100(short unkVal_100) {
		this.unkVal_100 = unkVal_100;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		str.append("{\n");
		str.append("	guid = ").append(getGUID()).append("\n");
		str.append("	flags = \n");
		str.append("		").append(Arrays.toString(getFlags())).append("\n");
		str.append("	unk 100 = ").append(getUnkVal_100()).append("\n");
		str.append("}\n");
		
		return str.toString();
	}
}
