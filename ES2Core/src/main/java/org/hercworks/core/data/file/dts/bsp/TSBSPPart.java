package org.hercworks.core.data.file.dts.bsp;

import org.hercworks.core.data.file.dts.TSChunkHeader;
import org.hercworks.core.data.file.dts.part.TSPartList;

public class TSBSPPart extends TSPartList {
	
	private TSBSPPartNode[] nodes;
	
	//unsigned
	private short[] transforms;
	
	public TSBSPPart() {
		super(TSChunkHeader.BSP_PART);
	}
	
	public TSBSPPart(TSChunkHeader hdr) {
		super(hdr);
	}

	public TSBSPPartNode[] getNodes() {
		return nodes;
	}

	public void setNodes(TSBSPPartNode[] nodes) {
		this.nodes = nodes;
	}

	public short[] getTransforms() {
		return transforms;
	}

	public void setTransforms(short[] transforms) {
		this.transforms = transforms;
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
		
		str.append("\"nodes\" : [");
		for(int s=0; s < getNodes().length; s++) {
			str.append(getNodes()[s].toString());
			if(s < getNodes().length - 1) {
				str.append(",");
			}
			str.append("\n");
		}
		str.append("]\n");
		
		str.append("}\n");
		
		return str.toString();
	}
}
