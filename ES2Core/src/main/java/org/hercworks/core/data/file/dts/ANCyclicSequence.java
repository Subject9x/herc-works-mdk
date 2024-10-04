package org.hercworks.core.data.file.dts;

import at.favre.lib.bytes.Bytes;

public class ANCyclicSequence extends ANSequence implements DTSSegment{


	@Override
	public Bytes getSegmentType() {
		return Bytes.from("0x1e0004".toCharArray());
	}
	
}
