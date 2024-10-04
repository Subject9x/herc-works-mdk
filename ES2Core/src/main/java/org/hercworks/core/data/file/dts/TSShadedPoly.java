package org.hercworks.core.data.file.dts;

import at.favre.lib.bytes.Bytes;

public class TSShadedPoly extends TSSolidPoly implements DTSSegment{
	
	@Override
	public Bytes getSegmentType() {
		return Bytes.from("0x140003".toCharArray());
	}
}
