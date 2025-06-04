package org.hercworks.transfer.dto.file.sim.dts.part;

import org.hercworks.transfer.dto.file.sim.dts.TSGroupDTO;
import org.hercworks.transfer.dto.file.sim.dts.TSObjectDTO;
import org.hercworks.transfer.dto.file.sim.dts.TSPartListDTO;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
		  use = JsonTypeInfo.Id.NAME, 
		  include = JsonTypeInfo.As.PROPERTY, 
		  property = "type")
@JsonSubTypes({ 
	  @Type(value = TSPartListDTO.class, name = "TSPartList"),
	  @Type(value = TSGroupDTO.class, name = "TSGroup"),
	  @Type(value = TSBitmapPartDTO.class, name = "TSBitmapPart")
})
public class TSBasePartDTO extends TSObjectDTO {

	private int transform;
	
	private int transformId;
	
	private float radius;
	
	private float[] center;
	
	public TSBasePartDTO() {}


	public int getTransform() {
		return transform;
	}

	public void setTransform(int transform) {
		this.transform = transform;
	}

	public int getTransformId() {
		return transformId;
	}

	public void setTransformId(int transformId) {
		this.transformId = transformId;
	}

	public float getRadius() {
		return radius;
	}

	public void setRadius(float radius) {
		this.radius = radius;
	}

	public float[] getCenter() {
		return center;
	}

	public void setCenter(float[] center) {
		this.center = center;
	}

}
