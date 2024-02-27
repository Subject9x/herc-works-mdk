package com.mech.works.vol.data;

import java.nio.ByteOrder;

import com.mech.works.data.ref.files.DataFile;

import at.favre.lib.bytes.Bytes;

/**
 * Earthsiege 2 Vol entries follow schema
 * 	18 bytes total for listing.
 * 		13 for file name (and wierd trail bytes)
 * 		UINT8 for directory index number - mapped to dir listing
 * 		UINT32 for file's offset in vol.
 * 
 * 
 * 	File Prefix - 9 bytes
 * 		UINT8 - possibly compression type flag
 * 		UINT32 -  FILE SIZE in Bytes, little endian, probably
 * 		UINT32 - unknown 4-byte magic
 */
public class VolEntry extends DataFile{
	
	private Bytes volOffset;	//offset of file if in vol
	private Bytes volListBytes;	//raw bytes (with weird trailing bytes observed on some)
	private Byte dirIdx;
	
	private Bytes unknownPreCompress;
	private Bytes magicPrefix;	//observed in vol.
	private Bytes compressionType;
	
	@Override
	public String toString() {
		return "VolEntry [fileName="+getFileName()  + ", volOffset=" + getVolOffset().toInt() + ", byteSize=" + getRawBytes().length + ", magicPrefix=" + printMagicPrefix() +", dirIdx=" + getDirIdx() +"]";
	}

	public Bytes getVolOffset() {
		return volOffset;
	}

	public void setVolOffset(Bytes volOffset) {
		this.volOffset = volOffset;
	}

	public Byte getDirIdx() {
		return dirIdx;
	}

	public void setDirIdx(Byte dirIdx) {
		this.dirIdx = dirIdx;
	}

	public Bytes getVolListBytes() {
		return volListBytes;
	}

	public void setVolListBytes(Bytes volListBytes) {
		this.volListBytes = volListBytes;
	}

	public void setFileCompressionType(Bytes val) {
		this.compressionType = val;
	}
	
	public Bytes getFileCompressionType() {
		return compressionType;
	}
	
	public Bytes getMagicPrefix() {
		return magicPrefix;
	}
	
	public void setMagicPrefix(Bytes magicPrefix) {
		this.magicPrefix = magicPrefix;
	}
	
	public Bytes getUnknownEoFByte() {
		return unknownPreCompress;
	}

	public void setUnknownEoFByte(Bytes unknownPreCompress) {
		this.unknownPreCompress = unknownPreCompress;
	}

	/**
	 * Sometimes in a vol, the file-list entry has tailing bytes after the filename string, this
	 * method strips the tailing bytes to create a clean file name that can be written to.
	 * @param listBytes
	 * @return String
	 */
	public static String nameFromListByte(byte[] listBytes) {
		
		String clean = "";
		for(byte b : listBytes) {
			if(b == 0x00) {
				break;
			}
			clean = clean.concat(String.valueOf( (char)b));
		}
		
		return clean;
	}
	
	public String printMagicPrefix() {
		
		String listTailByte = "";
		if(getVolListBytes().array().length != getFileName().length()) {
			Bytes tail = Bytes.from(getVolListBytes().array(), getFileName().length() + 1, 
									(getVolListBytes().array().length - getFileName().length() - 1))
									.byteOrder(ByteOrder.LITTLE_ENDIAN);
			
			if(tail.array().length > 1) {
				listTailByte = tail.encodeHex();	
			}
		}
		Bytes compress =  Bytes.from(getFileCompressionType()).byteOrder(ByteOrder.LITTLE_ENDIAN);
		
		String msg = String.format("%s|	(%s)	[%s]	", getFileName(), getFileCompressionType().toByte(), getMagicPrefix().encodeHex());
		
		return msg;
	}
}
