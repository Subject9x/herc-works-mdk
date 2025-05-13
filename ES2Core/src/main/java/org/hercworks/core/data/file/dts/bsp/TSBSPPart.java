package org.hercworks.core.data.file.dts.bsp;

import org.hercworks.core.data.file.dts.TSObjectHeader;
import org.hercworks.core.data.file.dts.TSRootObject;
import org.hercworks.core.data.file.dts.part.TSPartList;

public class TSBSPPart extends TSPartList implements TSRootObject{
	
	private TSBSPPartNode[] nodes;
	
	//unsigned
	private short[] transforms;
	
	public TSBSPPart() {
		super(TSObjectHeader.BSP_PART);
	}
	
	public TSBSPPart(TSObjectHeader hdr) {
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
		
		str = jsonString(str);
		
		str.append("\n");
		str.append("}\n");
		
		return str.toString();
	}	
	
	@Override
	public StringBuilder jsonString(StringBuilder str) {

		str = super.jsonString(str);
		
		str.append(",\n");
		str.append("\"nodes\" : [");
		for(int s=0; s < getNodes().length; s++) {
			str.append(getNodes()[s].toString());
			if(s < getNodes().length - 1) {
				str.append(",");
			}
			str.append("\n");
		}
		str.append("]");
		
		return str;
	}
}
