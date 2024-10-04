package org.hercworks.core.data.file.dts;

import at.favre.lib.bytes.Bytes;

public class TSSolidPoly implements DTSSegment{

	private short color = 0;
	
	public TSSolidPoly() {}
	
	public short getColor() {
		return color;
	}

	public void setColor(short color) {
		this.color = color;
	}

	@Override
	public Bytes getSegmentType() {
		return Bytes.from("0x140002".toCharArray());
	}
}
