package org.hercworks.transfer.dto.struct.dbsim;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("")
public class PaperDollViewDTO {

	@JsonProperty(value = "origin", index = 0)
	private int[] origin = new int[2];
	
	@JsonProperty(value = "size", index = 1)
	private int[] size = new int[2];

	@JsonProperty(value = "regions", index = 2)
	private PaperDollRegionDTO[] regions;
	
	public PaperDollViewDTO() {}

	public int[] getOrigin() {
		return origin;
	}

	public int[] getSize() {
		return size;
	}

	public PaperDollRegionDTO[] getRegions() {
		return regions;
	}

	public void setOrigin(int[] origin) {
		this.origin = origin;
	}

	public void setSize(int[] size) {
		this.size = size;
	}

	public void setRegions(PaperDollRegionDTO[] regions) {
		this.regions = regions;
	}
}
