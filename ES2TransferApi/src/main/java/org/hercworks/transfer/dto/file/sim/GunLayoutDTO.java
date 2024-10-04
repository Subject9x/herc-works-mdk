package org.hercworks.transfer.dto.file.sim;

import org.hercworks.transfer.dto.file.TransferObject;
import org.hercworks.transfer.dto.struct.dbsim.GunLayoutEntryDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("")
public class GunLayoutDTO extends TransferObject  {
	
	@JsonProperty(value = "hardpoints", index = 1, defaultValue = "[]")
	private GunLayoutEntryDTO[] hardpoints;
	
	public GunLayoutDTO() {}

	public GunLayoutEntryDTO[] getHardpoints() {
		return hardpoints;
	}

	public void setHardpoints(GunLayoutEntryDTO[] hardpoints) {
		this.hardpoints = hardpoints;
	}
}
