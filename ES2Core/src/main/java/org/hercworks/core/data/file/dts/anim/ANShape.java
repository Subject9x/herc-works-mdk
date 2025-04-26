package org.hercworks.core.data.file.dts.anim;

import java.util.Arrays;

import org.hercworks.core.data.file.dts.TSChunk;
import org.hercworks.core.data.file.dts.TSChunkHeader;
import org.hercworks.core.data.file.dts.TSShape;
import org.hercworks.core.data.file.dts.part.TSPartList;

public class ANShape extends TSShape {

	private TSPartList part;
	
	public ANShape() {
		super(TSChunkHeader.AN_SHAPE);
	}
	
	public ANShape(TSChunkHeader hdr) {
		super(hdr);
	}

	public TSPartList getPart() {
		return part;
	}

	public void setPart(TSPartList part) {
		this.part = part;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		str.append(metaInfoString(getClass().getSimpleName()));
		str.append("\"transform\" : ").append(getTransform()).append(",\n");
		str.append("\"IDNumber\" : ").append(getIDNumber()).append(",\n");
		str.append("\"radius\" : ").append(getRadius()).append(",\n");
		str.append("\"center\" : ").append(getCenter().toString()).append(",\n");
		str.append("\"parts\" : [");
		for(int s=0; s < getParts().length; s++) {
			str.append(getParts()[s].toString());
			if(s < getParts().length - 1) {
				str.append(",");
			}
			str.append("\n");
		}
		str.append("],\n");
		
		str.append("\"seq1\" : ").append(Arrays.toString(getSequenceList())).append(",\n");
		
		str.append("\"rootPart\" : ").append(getPart().toString()).append("\n");		
		str.append("}\n");
		
		return str.toString();
	}
}
