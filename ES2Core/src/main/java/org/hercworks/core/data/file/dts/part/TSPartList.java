package org.hercworks.core.data.file.dts.part;

import org.hercworks.core.data.file.dts.TSBasePart;
import org.hercworks.core.data.file.dts.TSChunk;
import org.hercworks.core.data.file.dts.TSChunkHeader;

public class TSPartList extends TSBasePart {

	private TSChunk[] parts;
	
	public TSPartList() {
		super(TSChunkHeader.TS_PART_LIST);
	}
	
	public TSPartList(TSChunkHeader hdr) {
		super(hdr);
	}

	public TSChunk[] getParts() {
		return parts;
	}

	public void setParts(TSChunk[] parts) {
		this.parts = parts;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		str.append(metaInfoString(getClass().getSimpleName()));
		str.append("\"transform\" : ").append(getTransform()).append(",\n");
		str.append("\"IDNumber\" : ").append(getIDNumber()).append(",\n");
		str.append("\"radius\" : ").append(getRadius()).append(",\n");
		str.append("\"center\" : ").append(getCenter().toString()).append(",\n");
		
		str.append("\"parts\" : [\n");
		for(int s=0; s < getParts().length; s++) {
			str.append(getParts()[s].toString());
			if(s < getParts().length - 1) {
				str.append(",");
			}
			str.append("\n");
		}
		str.append("]\n");
		str.append("}\n");
		
		return str.toString();
	}
	
}
