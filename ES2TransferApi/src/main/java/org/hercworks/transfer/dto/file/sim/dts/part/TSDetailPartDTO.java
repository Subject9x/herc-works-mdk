package org.hercworks.transfer.dto.file.sim.dts.part;

import org.hercworks.transfer.dto.file.sim.dts.TSPartListDTO;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("")
public class TSDetailPartDTO extends TSPartListDTO {

	private int[] details;
	
	public TSDetailPartDTO() {}

	public int[] getDetails() {
		return details;
	}

	public void setDetails(int[] details) {
		this.details = details;
	}
}
