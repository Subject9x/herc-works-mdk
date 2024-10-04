package org.hercworks.core.data.file.dts;

import at.favre.lib.bytes.Bytes;

public class TSCellAnimPart extends TSPartList implements DTSSegment {

	private short animSequence;
	
	public TSCellAnimPart() {
		this.animSequence = 0;
	}
	
	public TSCellAnimPart(short sequence) {
		this.animSequence = sequence;
	}
	

	public int getAnimSequence() {
		return animSequence;
	}

	public void setAnimSequence(short animSequence) {
		this.animSequence = animSequence;
	}

	@Override
	public Bytes getSegmentType() {
		return Bytes.from("0x14000b".toCharArray());
	}
}
