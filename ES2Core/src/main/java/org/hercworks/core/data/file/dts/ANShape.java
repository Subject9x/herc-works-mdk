package org.hercworks.core.data.file.dts;

import at.favre.lib.bytes.Bytes;

public class ANShape extends TSShape implements DTSSegment {

	private TSPartList part;
	
	public ANShape() {}
	
	public TSPartList getPart() {
		return part;
	}
	
	public void setPart(TSPartList part) {
		this.part = part;
	}

	@Override
	public Bytes getSegmentType() {
		return Bytes.from("0x1e0003".toCharArray());
	}
	
}
