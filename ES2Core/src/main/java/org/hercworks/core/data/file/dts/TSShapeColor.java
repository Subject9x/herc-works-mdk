package org.hercworks.core.data.file.dts;


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
}
