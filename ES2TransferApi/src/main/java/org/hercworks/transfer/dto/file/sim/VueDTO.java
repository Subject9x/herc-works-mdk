package org.hercworks.transfer.dto.file.sim;

import org.hercworks.transfer.dto.file.TransferObject;
import org.hercworks.transfer.dto.struct.dbsim.VueRegionDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("")
public class VueDTO extends TransferObject {


	@JsonProperty(index = 0, value = "regions")
	public VueRegionDTO[] regions = null;
	
	public VueDTO() {}

	public VueRegionDTO[] getRegions() {
		return regions;
	}

	public void setRegions(VueRegionDTO[] regions) {
		this.regions = regions;
	}
}
