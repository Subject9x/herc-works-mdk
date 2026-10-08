package org.hercworks.transfer.dto.file.msn;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * observed at end of TRAIN5.MSN, may or may not exist!
 * 
 */
@JsonRootName("UnkEntity58ByteDTO")
public class UnkEntity58ByteDTO extends MapObjectDTO {
	
	private short[] flags = new short[28];
	
	public UnkEntity58ByteDTO() {}

	public short[] getFlags() {
		return flags;
	}

	public void setFlags(short[] flags) {
		this.flags = flags;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new  StringBuilder();
		
		sb.append("\n{\n");
		
		sb.append(" guid = ").append(getGUID()).append("\n");
		sb.append(" flags = ").append(Arrays.toString(getFlags())).append("\n");
		
		sb.append("}\n");
		
		return sb.toString();
	}
}
