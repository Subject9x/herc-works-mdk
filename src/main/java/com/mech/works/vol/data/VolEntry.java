package com.mech.works.vol.data;

import com.mech.works.data.ref.files.DataFile;

import at.favre.lib.bytes.Bytes;

/**
 * Earthsiege 2 Vol entries follow schema
 * 	18 bytes total for listing.
 * 		12 for file name (and wierd trail bytes)
 * 		2 for directory index number - mapped to dir listing
 * 		4 for file's offset in vol.
 */
public class VolEntry extends DataFile{
	
	private Bytes volOffset;	//offset of file if in vol
	private Bytes volListBytes;	//raw bytes (with weird trailing bytes observed on some)
	private Byte dirIdx;
	private VolEntry nextEntry;	//file mem offset in vol is bounded by volOffset + nextEntry.volOffset-1;
	
	@Override
	public String toString() {
		return "VolEntry [fileName="+getFileName() + ", dirIdx=" + getDirIdx() + ", fileOffset=" + getVolOffset().toInt() +"]";
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
}
