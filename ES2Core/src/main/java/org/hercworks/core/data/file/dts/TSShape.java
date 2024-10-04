package org.hercworks.core.data.file.dts;

import at.favre.lib.bytes.Bytes;

public class TSShape extends TSPartList implements DTSSegment{
	
	private short[] sequence1;
	private short[] sequence2;	//TODO: python example code doesn't write to sequence2 for some reason.
	
	//transformList ??
	
	public TSShape() {}
	
	public short[] getSequence1() {
		return sequence1;
	}

	public short[] getSequence2() {
		return sequence2;
	}

	public void setSequence1(short[] sequence1) {
		this.sequence1 = sequence1;
	}

	public void setSequence2(short[] sequence2) {
		this.sequence2 = sequence2;
	}

	@Override
	public Bytes getSegmentType() {
		return Bytes.from("0x140008".toCharArray());
	}
}
