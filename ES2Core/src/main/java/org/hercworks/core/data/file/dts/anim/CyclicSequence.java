package org.hercworks.core.data.file.dts.anim;

import org.hercworks.core.data.file.dts.DTSChunkTypes;
import org.hercworks.core.data.file.dts.DTSSegment;

public class CyclicSequence extends Sequence implements DTSSegment{

	public CyclicSequence() {}

	@Override
	public DTSChunkTypes getSegType() {
		return DTSChunkTypes.CYCLIC_SEQ;
	}
}
