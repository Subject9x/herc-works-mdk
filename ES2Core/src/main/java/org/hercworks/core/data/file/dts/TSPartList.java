package org.hercworks.core.data.file.dts;

import at.favre.lib.bytes.Bytes;

public class TSPartList extends TSBasePart implements DTSSegment{

	private TSBasePart[] parts;

	public TSPartList() {}
	
	public TSPartList(TSBasePart[] parts) {
		this.parts = parts;
	}
	
	public TSBasePart[] getParts() {
		return parts;
	}

	public void setParts(TSBasePart[] parts) {
		this.parts = parts;
	}

	@Override
	public Bytes getSegmentType() {
		return Bytes.from("0x140007".toCharArray());
	}
}
