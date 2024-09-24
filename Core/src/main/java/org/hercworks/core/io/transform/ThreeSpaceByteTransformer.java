package org.hercworks.core.io.transform;

import java.io.IOException;
import java.nio.ByteOrder;

import org.hercworks.voln.DataFile;

import at.favre.lib.bytes.Bytes;


/**
 * Transformer abstract class for all ByteTransformers.
 * Transformers are responsible for converting between byte[] and types of {@linkplain DataFile}, 
 * they essentially wrap {@linkplain Bytes} objects with custom index var and a set of convenience
 * operations for reading/writing chunks of byte arrays.
 */
public abstract class ThreeSpaceByteTransformer {
	
	protected byte[] bytes;
	protected int index = 0;
	
	public abstract DataFile bytesToObject(byte[] inputArray) throws ClassCastException;
	
	public abstract byte[] objectToBytes(DataFile source) throws ClassCastException, IOException ;
	
	protected void setBytes(byte[] src) {
		this.bytes = Bytes.from(src).array();
	}
	
	protected byte indexByte() {
		byte b = bytes[index]; 
		index +=1;
		return b;
	}
	
	protected short indexShortLE() {
		short s =  Bytes.from(bytes, index, 2).byteOrder(ByteOrder.LITTLE_ENDIAN).toShort();
		index += 2;
		return s;
	}
	
	protected short indexShort() {
		short s =  Bytes.from(bytes, index, 2).byteOrder(ByteOrder.BIG_ENDIAN).toShort();
		index+=2;
		return s;
	}
	
	protected int indexIntLE() {
		int i =  Bytes.from(bytes, index, 4).byteOrder(ByteOrder.LITTLE_ENDIAN).toInt();
		index+=4;
		return i;
	}
	
	protected int indexInt() {
		int i =  Bytes.from(bytes, index, 4).toInt();
		index+=4;
		return i;
	}
	
	protected byte[] indexSegment(int len) {
		byte[] arr = Bytes.from(bytes, index, len).array();
		index+=len;
		return arr;
	}
	
	protected byte[] indexSegmentLE(int len) {
		return Bytes.from(indexSegment(len)).byteOrder(ByteOrder.LITTLE_ENDIAN).array();
	}
	
	protected String indexString(int len) {
		return new String(Bytes.from(indexSegment(len)).toCharArray());
	}
	
	protected byte[] writeInt(int i) {
		return Bytes.from(i).array();
	}
	
	protected byte[] writeIntLE(int i) {
		return Bytes.from(i).reverse().array();
	}
	
	protected byte[] writeShort(short s) {
		return Bytes.from(s).byteOrder(ByteOrder.BIG_ENDIAN).array();
	}
	
	protected byte[] writeShortLE(short s) {
		return Bytes.from(s).reverse().array();
	}
	
	public void jumpTo(int jump) {
		if(jump < bytes.length) {
			index = jump;	
		}
	}
	
	public void skip(int skip) {
		if(index + skip < bytes.length) {
			index = index + skip;	
		}
	}
	
	public int peekAt(int at) {
		return index + at;
	}
	
	public void resetIndex() {
		index = 0;
	}
	
	public byte[] getBytes() {
		return bytes;
	}
}
