package org.hercworks.core.data.file.dts.anim;

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
		
		str = jsonString(str);
		str.append("\n");
		str.append("}\n");
		
		return str.toString();
	}
	
	@Override
	public StringBuilder jsonString(StringBuilder str) {
		
		str = super.jsonString(str);
		
		return str;
	}
}
