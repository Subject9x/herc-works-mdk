package org.hercworks.core.data.file.dts.bsp;

import java.util.Arrays;

import org.hercworks.core.data.file.dts.TSChunkHeader;
import org.hercworks.core.data.file.dts.TSGroup;

public class TSBSPGroup extends TSGroup {

	private TSBSPGroupNode[] groupNodes;
	
	public TSBSPGroup() {
		super(TSChunkHeader.TS_BSP_GROUP);
	}
	
	public TSBSPGroup(TSChunkHeader hdr) {
		super(hdr);
	}

	public TSBSPGroupNode[] getGroupNodes() {
		return groupNodes;
	}

	public void setGroupNodes(TSBSPGroupNode[] groupNodes) {
		this.groupNodes = groupNodes;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		str.append(metaInfoString(getClass().getSimpleName()));
		str.append("\"transform\" : ").append(getTransform()).append(",\n");
		str.append("\"IDNumber\" : ").append(getIDNumber()).append(",\n");
		str.append("\"radius\" : ").append(getRadius()).append(",\n");
		str.append("\"center\" : ").append(getCenter().toString()).append(",\n");
		str.append("\"indexes\" : ").append(Arrays.toString(getIndexes())).append(",\n");
		str.append("\"points\" : [\n");
		for(int s=0; s < getPoints().length; s++) {
			str.append(getPoints()[s].toString());
			if(s < getPoints().length - 1) {
				str.append(",");
			}
			str.append("\n");
		}
		str.append("],\n");
		
		str.append("\"colors\" : ");		
		for(int c=0; c < getColors().length; c++) {
			str.append(Arrays.toString(getColors()[c].getRgba()));
			if(c < getPoints().length - 1) {
				str.append(",");
			}
			str.append("\n");
		}
		str.append("],\n");
		
		
		str.append("\"parts\" : [\n");
		for(int s=0; s < getItems().length; s++) {
			str.append(getItems()[s].toString());
			if(s < getItems().length - 1) {
				str.append(",");
			}
			str.append("\n");
		}
		str.append("],\n");
		
		str.append("\"bsp_nodes\" : [\n");
		for(int n=0; n < getGroupNodes().length; n++) {
			str.append(getGroupNodes()[n].toString());
			if(n < getGroupNodes().length - 1) {
				str.append(",");
			}
			str.append("\n");
		}
		str.append("]\n");
		
		str.append("}\n");
		
		
		return str.toString();
	}
}
