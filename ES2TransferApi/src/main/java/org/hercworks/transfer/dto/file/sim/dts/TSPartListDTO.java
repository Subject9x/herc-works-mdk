package org.hercworks.transfer.dto.file.sim.dts;

import org.hercworks.transfer.dto.file.sim.dts.bsp.TSBSPPartDTO;
import org.hercworks.transfer.dto.file.sim.dts.part.TSBasePartDTO;
import org.hercworks.transfer.dto.file.sim.dts.part.TSCellAnimPartDTO;
import org.hercworks.transfer.dto.file.sim.dts.part.TSDetailPartDTO;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
		  use = JsonTypeInfo.Id.NAME, 
		  include = JsonTypeInfo.As.PROPERTY, 
		  property = "type")
@JsonSubTypes({ 
	  @Type(value = TSBSPPartDTO.class, name = "TSBSPPart"),
	  @Type(value = TSDetailPartDTO.class, name = "TSDetailPart"),
	  @Type(value = TSCellAnimPartDTO.class, name = "TSCellAnimPart"),
	  @Type(value = TSShapeDTO.class, name = "TSShape")
})
public class TSPartListDTO extends TSBasePartDTO {

	private TSObjectDTO[] parts;
	
	public TSPartListDTO() {}

	public TSObjectDTO[] getParts() {
		return parts;
	}

	public void setParts(TSObjectDTO[] parts) {
		this.parts = parts;
	}
}
