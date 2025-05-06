package org.hercworks.core.data.file.dts.anim;

import java.util.Arrays;

import org.hercworks.core.data.file.dts.TSObjectHeader;

public class ANCyclicSequence extends ANSequence {

	
	public ANCyclicSequence() {
		super(TSObjectHeader.AN_CYCLIC_SEQUENCE);
	}
	
	public ANCyclicSequence(TSObjectHeader hdr) {
		super(hdr);
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		str.append(metaInfoString(getClass().getSimpleName()));
		
		str.append("\"tick\" : ").append(getTick()).append(",\n");
		str.append("\"priority\" : ").append(getPriority()).append(",\n");
		str.append("\"groundMove\" : ").append(getGroundMovement()).append(",\n");
		
		str.append("\"frames\" : [\n");
		for(int s=0; s < getFrames().length; s++) {
			str.append(getFrames()[s].toString());
			if(s < getFrames().length - 1) {
				str.append(",");
			}
			str.append("\n");
		}
		str.append("],\n");
		
		str.append("\"partIds\" : ").append(Arrays.toString(getPartIds())).append(",\n");
		str.append("\"transformIndices\" : ").append(Arrays.toString(getTransformIndices())).append("\n");
		str.append("}\n");
		
		return str.toString();
	}
}
