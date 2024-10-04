package org.hercworks.core.data.file.dts;

import at.favre.lib.bytes.Bytes;

public class TSBSPPart extends TSPartList implements DTSSegment {

	private TSBSPPartNode[] nodes;
	private short[] transforms;
	

	public TSBSPPart() {}
	
	public TSBSPPartNode[] getNodes() {
		return nodes;
	}
	public short[] getTransforms() {
		return transforms;
	}

	public void setNodes(TSBSPPartNode[] nodes) {
		this.nodes = nodes;
	}

	public void setTransforms(short[] transforms) {
		this.transforms = transforms;
	}

	@Override
	public Bytes getSegmentType() {
		return Bytes.from("0x140015".toCharArray());
	}
	
}
