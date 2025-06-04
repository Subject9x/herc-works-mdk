package org.hercworks.transfer.dto.file.sim.dts.poly;

import org.hercworks.transfer.dto.file.sim.dts.bsp.TSGouraudPolyDTO;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
		  use = JsonTypeInfo.Id.NAME, 
		  include = JsonTypeInfo.As.PROPERTY, 
		  property = "type")
@JsonSubTypes({ 
	  @Type(value = TSShadedPolyDTO.class, name = "TSShadedPoly"),
	  @Type(value = TSTexture4PolyDTO.class, name = "TSTexture4Poly"),
	  @Type(value = TSGouraudPolyDTO.class, name = "TSGouradPoly")
})
public class TSSolidPolyDTO extends TSPolyDTO {

	private int surfaceNum;
	
	public TSSolidPolyDTO() {}

	public int getSurfaceNum() {
		return surfaceNum;
	}

	public void setSurfaceNum(int surfaceNum) {
		this.surfaceNum = surfaceNum;
	}
}
