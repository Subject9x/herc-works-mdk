package org.hercworks.transfer.dto.file.sim.dts;

import org.hercworks.transfer.dto.file.TransferObject;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DTSRootDTO extends TransferObject {

	@JsonProperty(index = 2)
	private TSObjectDTO[] shapes;
	
	public DTSRootDTO() {}

	public TSObjectDTO[] getShapes() {
		return shapes;
	}

	public void setShapes(TSObjectDTO[] shapes) {
		this.shapes = shapes;
	}
}
