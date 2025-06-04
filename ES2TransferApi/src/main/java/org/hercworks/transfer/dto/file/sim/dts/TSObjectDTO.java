package org.hercworks.transfer.dto.file.sim.dts;

import org.hercworks.transfer.dto.file.sim.dts.anim.ANAnimListDTO;
import org.hercworks.transfer.dto.file.sim.dts.anim.ANSequenceDTO;
import org.hercworks.transfer.dto.file.sim.dts.anim.ANShapeDTO;
import org.hercworks.transfer.dto.file.sim.dts.part.TSBasePartDTO;
import org.hercworks.transfer.dto.file.sim.dts.poly.TSPolyDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
  use = JsonTypeInfo.Id.NAME, 
  include = JsonTypeInfo.As.PROPERTY, 
  property = "type")
@JsonSubTypes({ 
  @Type(value = ANAnimListDTO.class, name = "ANAnimList"),
  @Type(value = ANShapeDTO.class, name = "ANShape"),
  @Type(value = ANSequenceDTO.class, name = "ANSequence"),
  @Type(value = TSBasePartDTO.class, name = "TSBasePart"),
  @Type(value = TSShapeDTO.class, name = "TSShape"),
  @Type(value = TSPolyDTO.class, name = "TSPoly")
  
})
public class TSObjectDTO {

	@JsonProperty(required = false, index = 0)
	private int index;
	
	public TSObjectDTO() {}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
