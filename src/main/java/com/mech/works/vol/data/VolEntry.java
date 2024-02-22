package com.mech.works.vol.data;

import java.nio.ByteOrder;

import com.mech.works.data.ref.files.DataFile;
import com.mech.works.vol.util.CRC16;

import at.favre.lib.bytes.Bytes;

/**
 * Earthsiege 2 Vol entries follow schema
 * 	18 bytes total for listing.
 * 		13 for file name (and wierd trail bytes)
 * 		1 for directory index number - mapped to dir listing
 * 		4 for file's offset in vol.
 * 
 * 
 * 	File Prefix - 9 bytes
 * 		2 bytes - UNKNOWN at this time.
 * 		4 bytes-  FILE SIZE in Bytes, little endian, probably
 * 		
 */
public class VolEntry extends DataFile{
	
	private Bytes volOffset;	//offset of file if in vol
	private Bytes volListBytes;	//raw bytes (with weird trailing bytes observed on some)
	private Byte dirIdx;
	
	private Bytes magicPrefix;	//observed in vol.
	private Bytes compressionType;
	private VolEntry nextEntry;	//file mem offset in vol is bounded by volOffset + nextEntry.volOffset-1;
	
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

	public VolEntry getNextEntry() {
		return nextEntry;
	}

	public void setNextEntry(VolEntry nextEntry) {
		this.nextEntry = nextEntry;
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
	
	public String printMagicPrefix() {
		
		String listTailByte = "";
		if(getVolListBytes().array().length != getFileName().length()) {
			Bytes tail = Bytes.from(getVolListBytes().array(), getFileName().length() + 1, (getVolListBytes().array().length - getFileName().length() - 1))
					.byteOrder(ByteOrder.LITTLE_ENDIAN);
			if(tail.array().length > 1) {
				listTailByte = "(tail=" + tail.encodeHex() + ")";	
			}
		}
		
		return "	[" + Bytes.from(getFileCompressionType()).byteOrder(ByteOrder.LITTLE_ENDIAN).encodeHex() + 
				"] [" +  getFileSize().byteOrder(ByteOrder.LITTLE_ENDIAN).encodeHex() +
				"] [" + getMagicPrefix().byteOrder(ByteOrder.LITTLE_ENDIAN).encodeHex() + "]" + listTailByte;
	}
}
