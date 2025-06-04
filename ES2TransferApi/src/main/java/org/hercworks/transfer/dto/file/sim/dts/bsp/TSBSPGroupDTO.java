package org.hercworks.transfer.dto.file.sim.dts.bsp;

import org.hercworks.transfer.dto.file.sim.dts.TSGroupDTO;

public class TSBSPGroupDTO extends TSGroupDTO {
	
	private TSBSPGroupNodeDTO[] nodes;

	public TSBSPGroupDTO() {}
	
	public TSBSPGroupNodeDTO[] getNodes() {
		return nodes;
	}

	public void setNodes(TSBSPGroupNodeDTO[] nodes) {
		this.nodes = nodes;
	}

}
