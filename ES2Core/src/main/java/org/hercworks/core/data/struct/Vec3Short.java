package org.hercworks.core.data.struct;

import java.nio.ByteOrder;

import at.favre.lib.bytes.Bytes;

/**
 * Utility class, some data structures reference a Vec3
 * but most common implementations assume double (float) or
 * int whereas ES2 uses signed shorts.
 * 
 * mostly for data storage, less so actual vector operations...
 */
public class Vec3Short {
	
	private short x = 0;
	private short y = 0;
	private short z = 0;
	
	
	public Vec3Short() {}
	
	public Vec3Short(short x, short y, short z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vec3Short(byte[] values, ByteOrder order) {
		this.x = Bytes.from(values, 0, 2).byteOrder(order).toShort();
		this.y = Bytes.from(values, 2, 2).byteOrder(order).toShort();
		this.z = Bytes.from(values, 4, 2).byteOrder(order).toShort();
	}

	public short getX() {
		return x;
	}

	public void setX(short x) {
		this.x = x;
	}

	public short getY() {
		return y;
	}

	public void setY(short y) {
		this.y = y;
	}

	public short getZ() {
		return z;
	}

	public void setZ(short z) {
		this.z = z;
	}
	
	private String formatfixedPoint(short p) {
		
		double d = (double)p;
		
		d = d / 10;
		
		
		return String.valueOf(d);
	}
	
	@Override
	public String toString(){
		StringBuilder str = new StringBuilder();
		
		str.append("[")
			.append(formatfixedPoint(getX()))
			.append(", ")
			.append(formatfixedPoint(getY()))
			.append(", ")
			.append(formatfixedPoint(getZ()))
			.append("]");
		
		return str.toString();
	}
}
