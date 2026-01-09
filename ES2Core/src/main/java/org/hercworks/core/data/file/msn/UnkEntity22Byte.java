package org.hercworks.core.data.file.msn;

import java.util.Arrays;

/**
 * 
 */
public class UnkEntity22Byte extends MapObject{
	
	private short[] flags = new short[10];
	
	
	private UnkEntity164Bytes unkEntity164;
	
	public UnkEntity22Byte() {}


	public short[] getFlags() {
		return flags;
	}

	public void setFlags(short[] flags) {
		this.flags = flags;
	}

	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("\n{\n");
		sb.append("	guid = ").append(getGUID()).append("\n");
		sb.append("	flags = ").append(Arrays.toString(getFlags())).append("\n");
		
		sb.append("}\n");
		
		return sb.toString();
	}
}
