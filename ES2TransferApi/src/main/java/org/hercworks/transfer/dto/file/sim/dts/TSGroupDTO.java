package org.hercworks.transfer.dto.file.sim.dts;

import org.hercworks.transfer.dto.file.sim.dts.bsp.TSBSPGroupDTO;
import org.hercworks.transfer.dto.file.sim.dts.part.TSBasePartDTO;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
		  use = JsonTypeInfo.Id.NAME, 
		  include = JsonTypeInfo.As.PROPERTY, 
		  property = "type")
@JsonSubTypes({ 
	  @Type(value = TSBSPGroupDTO.class, name = "TSBSPGroup")
})
public class TSGroupDTO extends TSBasePartDTO {

	private int[] indexes;
	
	private float[][] vertices;
	
	private TSSurfaceEntryDTO[] surfaces;
	
	private TSObjectDTO[] polys;
	
	public TSGroupDTO() {}

	public int[] getIndexes() {
		return indexes;
	}

	public void setIndexes(int[] indexes) {
		this.indexes = indexes;
	}

	public float[][] getVertices() {
		return vertices;
	}

	public void setVertices(float[][] vertices) {
		this.vertices = vertices;
	}

	public TSSurfaceEntryDTO[] getSurfaces() {
		return surfaces;
	}

	public void setSurfaces(TSSurfaceEntryDTO[] surfaces) {
		this.surfaces = surfaces;
	}

	public TSObjectDTO[] getPolys() {
		return polys;
	}

	public void setPolys(TSObjectDTO[] polys) {
		this.polys = polys;
	}
}
