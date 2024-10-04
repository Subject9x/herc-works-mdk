package org.hercworks.transfer.dto.file.sim;

import org.hercworks.transfer.dto.file.TransferObject;
import org.hercworks.transfer.dto.struct.dbsim.ProjectileEntryDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("")
public class ProjectileDataDTO extends TransferObject {

	@JsonProperty(value = "projectiles", index = 0)
	private ProjectileEntryDTO[] projectiles;
	
	public ProjectileDataDTO() {}

	public ProjectileEntryDTO[] getProjectiles() {
		return projectiles;
	}

	public void setProjectiles(ProjectileEntryDTO[] projectiles) {
		this.projectiles = projectiles;
	}
}
