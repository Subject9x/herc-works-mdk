package com.mech.works.data.struct;

import java.awt.Color;

/**
 * Pure utility class to capture the underlying bytes of any 24bit color value.
 */
public class ColorBytes {

	private Color color;
	private byte[] array = new byte[4];

	public ColorBytes(byte r, byte g, byte b, byte a) {
		this.array[0] = r;
		this.array[1] = g;
		this.array[2] = b;
		this.array[3] = a;
	}
	
	public ColorBytes(byte[] array) {
		this(array[0], array[1], array[2], array[3]);
	}
	
	public ColorBytes() {}
	
	public byte[] getArray() {
		return array;
	}

	public void setArray(byte[] array) {
		this.array = array;
	}
	
	public void replaceVal(int index, byte b) {
		this.array[index] = b;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public byte[] get24bitRGB() {
		byte[] b = new byte[3];
		b[0] = this.array[0];
		b[1] = this.array[1];
		b[2] = this.array[2];
		
		return b;
	}
	
	public int getIntBGR() {
		return (this.array[0]<<16) | (this.array[1]<<8) | this.array[2];
	}
	
	public int getIntRGB() {
		return (this.array[2]<<16) | (this.array[1]<<8) | this.array[0];
	}
}
