package org.hercworks.core.data.file.msn;

import java.util.Arrays;

/**
 *  in TRAIN5.MSN these seem to follow map coords
 *  
 */
public class UnkEntity10Byte extends MapObject {

	private short[] values;
	
	public UnkEntity10Byte() {}

	public short[] getValues() {
		return values;
	}

	public void setValues(short[] vals) {
		this.values = vals;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("\n{\n");
		sb.append("	guid = ").append(getGUID()).append("\n");
		sb.append("	vals = ").append(Arrays.toString(getValues())).append("\n");
		
		sb.append("}\n");
		return sb.toString();
	}
	
}
