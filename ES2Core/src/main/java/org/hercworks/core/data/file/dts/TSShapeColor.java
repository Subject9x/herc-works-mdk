package org.hercworks.core.data.file.dts;

import java.util.Arrays;

/**
 * UTILITY
 * 
 * used in {@linkplain TSGroup}, colors seem to be expressed
 * as direct RGBA values in byte[4] format instead of being palette index...
 * interesting.
 */
public class TSShapeColor {

	private byte[] rgba;
	
	public TSShapeColor() {}
	
	public TSShapeColor(byte[] val) {
		this.rgba = val;
	}

	public byte[] getRgba() {
		return rgba;
	}

	public void setRgba(byte[] rgba) {
		this.rgba = rgba;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		
//		str.append("{ TSShapeColor=[\n");
		
		str.append("").append(Arrays.toString(getRgba())).append("\n");
		
//		str.append("]");
		
		return str.toString();
	}
}
