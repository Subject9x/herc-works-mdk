package com.mech.works.data.file;

import com.mech.works.data.ref.files.DataFile;

import at.favre.lib.bytes.Bytes;

/**
 * 
 */
public class VolEntry extends DataFile{


	private Bytes volOffset;	//offset of file if in vol
	
	public Bytes getVolOffset() {
		return volOffset;
	}

	public void setVolOffset(Bytes volOffset) {
		this.volOffset = volOffset;
	}
	
}
