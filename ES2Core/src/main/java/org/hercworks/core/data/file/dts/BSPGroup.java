package org.hercworks.core.data.file.dts;

import java.util.LinkedList;

public class BSPGroup extends Group implements DTSSegment {

	private LinkedList<BSPGroupNode> nodes = new LinkedList<BSPGroupNode>();
	
	public BSPGroup() {}
	
	public LinkedList<BSPGroupNode> getNodes() {
		return nodes;
	}

	public void setNodes(LinkedList<BSPGroupNode> nodes) {
		this.nodes = nodes;
	}

	@Override
	public DTSChunkTypes getSegType() {
		return DTSChunkTypes.BSP_GROUP;
	}
}
