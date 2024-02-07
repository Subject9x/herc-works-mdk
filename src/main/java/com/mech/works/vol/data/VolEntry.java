package com.mech.works.vol.data;

import com.mech.works.data.ref.files.DataFile;

import at.favre.lib.bytes.Bytes;

/**
 * 
 */
public class VolEntry extends DataFile{
	
	private Bytes volOffset;	//offset of file if in vol
	private byte dirIdx;
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

	public byte getDirIdx() {
		return dirIdx;
	}

	public void setDirIdx(byte dirIdx) {
		this.dirIdx = dirIdx;
	}

	public VolEntry getNextEntry() {
		return nextEntry;
	}

	public void setNextEntry(VolEntry nextEntry) {
		this.nextEntry = nextEntry;
	}
}
