package org.hercworks.transfer.dto.file.sim.dts.bsp;

import org.hercworks.transfer.dto.file.sim.dts.TSPartListDTO;

import com.fasterxml.jackson.annotation.JsonRootName;


@JsonRootName("")
public class TSBSPPartDTO extends TSPartListDTO {

	private TSBSPPartNodeDTO[] nodes;
	
	private int[] transformIds;
	
	public TSBSPPartDTO() {}

	public TSBSPPartNodeDTO[] getNodes() {
		return nodes;
	}

	public void setNodes(TSBSPPartNodeDTO[] nodes) {
		this.nodes = nodes;
	}

	public int[] getTransformIds() {
		return transformIds;
	}

	public void setTransformIds(int[] transformIds) {
		this.transformIds = transformIds;
	}
}
