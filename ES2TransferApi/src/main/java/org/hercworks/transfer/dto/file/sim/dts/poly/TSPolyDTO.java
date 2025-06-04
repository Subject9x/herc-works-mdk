package org.hercworks.transfer.dto.file.sim.dts.poly;

import org.hercworks.transfer.dto.file.sim.dts.TSObjectDTO;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
		  use = JsonTypeInfo.Id.NAME, 
		  include = JsonTypeInfo.As.PROPERTY, 
		  property = "type")
@JsonSubTypes({ 
	  @Type(value = TSSolidPolyDTO.class, name = "TSSolidPoly")
})
public class TSPolyDTO extends TSObjectDTO {

	
	private int normal;
	
	private int center;
	
	private int vertexTotal;
	
	private int vertixListStartNum;
	
	public TSPolyDTO() {}

	public int getNormal() {
		return normal;
	}

	public void setNormal(int normal) {
		this.normal = normal;
	}

	public int getCenter() {
		return center;
	}

	public void setCenter(int center) {
		this.center = center;
	}

	public int getVertexTotal() {
		return vertexTotal;
	}

	public void setVertexTotal(int vertexTotal) {
		this.vertexTotal = vertexTotal;
	}

	public int getVertixListStartNum() {
		return vertixListStartNum;
	}

	public void setVertixListStartNum(int vertixListStartNum) {
		this.vertixListStartNum = vertixListStartNum;
	}
}
