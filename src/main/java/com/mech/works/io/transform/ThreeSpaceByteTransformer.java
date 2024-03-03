package com.mech.works.io.transform;

import java.nio.ByteOrder;

import com.mech.works.data.ref.files.DataFile;

import at.favre.lib.bytes.Bytes;


/**
 * Transformer interface to create transformers.
 * Transformers are responsible for converting between
 * 	byte[] and types of {@linkplain DataFile}
 */
public abstract class ThreeSpaceByteTransformer {
	
	protected Bytes bytes;
	protected int index = 0;
	
	public abstract <T extends DataFile> T bytesToObject(byte[] inputArray, T file) throws ClassCastException;
	
	public abstract byte[] objectToBytes(DataFile source) throws ClassCastException;
	
	protected void setBytes(byte[] src) {
		this.bytes = Bytes.from(src);
	}
	
	protected byte indexByte() {
		byte b = bytes.byteAt(index); 
		index +=1;
		return b;
	}
	
	protected short indexShortLE() {
		short s =  Bytes.from(bytes.array(), index, 2).byteOrder(ByteOrder.LITTLE_ENDIAN).toShort();
		index+=2;
		return s;
	}
	
	protected short indexShort() {
		short s =  Bytes.from(bytes.array(), index, 2).byteOrder(ByteOrder.BIG_ENDIAN).toShort();
		index+=2;
		return s;
	}
	
	protected int indexIntLE() {
		int i =  Bytes.from(bytes.array(), index, 4).byteOrder(ByteOrder.LITTLE_ENDIAN).toInt();
		index+=4;
		return i;
	}
	
	protected int indexInt() {
		int i =  Bytes.from(bytes.array(), index, 4).byteOrder(ByteOrder.BIG_ENDIAN).toInt();
		index+=4;
		return i;
	}
}
