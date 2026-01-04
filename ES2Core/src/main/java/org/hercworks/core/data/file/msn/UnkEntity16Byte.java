package org.hercworks.core.data.file.msn;

import java.util.Arrays;

/**
 * 	Observed to follow on after UnkEntity10Byte array, 2nd after MapCoords
 */
public class UnkEntity16Byte extends MapObject {
	
	private short[] values;
	
	public UnkEntity16Byte() {}

	public short[] getValues() {
		return values;
	}

	public void setValues(short[] values) {
		this.values = values;
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
