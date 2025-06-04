package org.hercworks.transfer.dto.file.sim.dts;

import org.hercworks.transfer.dto.file.sim.dts.anim.ANShapeDTO;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
	  use = JsonTypeInfo.Id.NAME, 
	  include = JsonTypeInfo.As.PROPERTY, 
	  property = "type")
@JsonSubTypes({ 
  @Type(value = ANShapeDTO.class, name = "ANShape")
})

public class TSShapeDTO extends TSPartListDTO {

	private int[] sequences;
	
	private int[] transforms;
	
	public TSShapeDTO() {}

	public int[] getSequences() {
		return sequences;
	}

	public void setSequences(int[] sequences) {
		this.sequences = sequences;
	}

	public int[] getTransforms() {
		return transforms;
	}

	public void setTransforms(int[] transforms) {
		this.transforms = transforms;
	}
}
