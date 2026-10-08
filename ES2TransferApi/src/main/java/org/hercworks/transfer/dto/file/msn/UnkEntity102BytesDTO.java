package org.hercworks.transfer.dto.file.msn;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Observed following unit info segments, 
 * in TRAIN5.MSN there's only 2 o them
 */
@JsonRootName("UnkEntity102BytesDTO")
public class UnkEntity102BytesDTO extends MapObjectDTO{
	
	private short[] flags = new short[49];
	
	private short unkVal_100;
	
	public UnkEntity102BytesDTO() {}


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
