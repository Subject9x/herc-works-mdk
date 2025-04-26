package org.hercworks.core.data.struct;

import java.nio.ByteOrder;

import at.favre.lib.bytes.Bytes;

public class Vec2Short {
	
	private short x = 0;
	private short y = 0;
	
	public Vec2Short() {}
	
	public Vec2Short(short x, short y) {
		this.x = x;
		this.y = y;
	}
	
	public Vec2Short(byte[] values, ByteOrder order) {
		this.x = Bytes.from(values, 0, 2).byteOrder(order).toShort();
		this.y = Bytes.from(values, 2, 2).byteOrder(order).toShort();
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
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		str.append("[")
			.append(getX())
			.append(",")
			.append(getY())
			.append("]");
		
		return str.toString();
	}
}
