package org.hercworks.transfer.dto.file.sim.dts.part;

import org.hercworks.transfer.dto.file.sim.dts.TSPartListDTO;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("")
public class TSCellAnimPartDTO extends TSPartListDTO {

	private int animSequenceNum;
	
	public TSCellAnimPartDTO() {}

	public int getAnimSequenceNum() {
		return animSequenceNum;
	}

	public void setAnimSequenceNum(int animSequenceNum) {
		this.animSequenceNum = animSequenceNum;
	}
}
