package com.mech.works.vol.util;

import java.nio.ByteOrder;

import at.favre.lib.bytes.Bytes;

public final class ByteOps {

	public ByteOps() {}
	
	public static Byte int4ToByteLittleEndian(int i) {
		
		return Bytes.from(i).byteOrder(ByteOrder.LITTLE_ENDIAN).array()[3];
		
	}
	
	public static int bytes2LEToInt(Bytes b) {
		return Bytes.from(b.array()[0], b.array()[1]).toChar();
	}
	
	public static int byteLEToInt(byte b) {
		return Bytes.from(b).toChar();
	}
	

}
