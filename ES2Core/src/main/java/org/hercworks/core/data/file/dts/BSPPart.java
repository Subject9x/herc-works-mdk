package org.hercworks.core.data.file.dts;

import java.util.LinkedList;

public class BSPPart extends PartList implements DTSSegment {

	private LinkedList<BSPPartNode> nodes = new LinkedList<BSPPartNode>();
	private LinkedList<Short> transforms = new LinkedList<Short>();
	
	public BSPPart() {}
	
	public LinkedList<BSPPartNode> getNodes() {
		return nodes;
	}
	public LinkedList<Short> getTransforms() {
		return transforms;
	}

	public void setNodes(LinkedList<BSPPartNode> nodes) {
		this.nodes = nodes;
	}

	public void setTransforms(LinkedList<Short> transforms) {
		this.transforms = transforms;
	}

	@Override
	public DTSChunkTypes getSegType() {
		return DTSChunkTypes.BSP_PART;
	}
}
