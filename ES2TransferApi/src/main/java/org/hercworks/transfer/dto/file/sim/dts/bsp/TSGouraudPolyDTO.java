package org.hercworks.transfer.dto.file.sim.dts.bsp;

import org.hercworks.transfer.dto.file.sim.dts.poly.TSSolidPolyDTO;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("")
public class TSGouraudPolyDTO extends TSSolidPolyDTO {

	private int normalList;
	
	public TSGouraudPolyDTO() {}

	public int getNormalList() {
		return normalList;
	}

	public void setNormalList(int normalList) {
		this.normalList = normalList;
	}
}
