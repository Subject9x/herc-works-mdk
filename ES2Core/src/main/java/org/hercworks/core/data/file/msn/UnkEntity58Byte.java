package org.hercworks.core.data.file.msn;

import java.util.Arrays;

/**
 * observed at end of TRAIN5.MSN, may or may not exist!
 * 
 */
public class UnkEntity58Byte {
	
	private short prefix;
	
	private short id;
	
	private short[] flags = new short[27];
	
	public UnkEntity58Byte() {}

	public short getPrefix() {
		return prefix;
	}

	public void setPrefix(short prefix) {
		this.prefix = prefix;
	}

	public short getId() {
		return id;
	}

	public void setId(short id) {
		this.id = id;
	}

	public short[] getFlags() {
		return flags;
	}

	public void setFlags(short[] flags) {
		this.flags = flags;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new  StringBuilder();
		
		sb.append("{\n");
		
		sb.append("		prefix = ").append(getPrefix()).append("\n");
		sb.append("		id = ").append(getId()).append("\n");
		sb.append("		flags = ").append(Arrays.toString(getFlags())).append("\n");
		
		sb.append("}\n");
		
		return sb.toString();
	}
}
