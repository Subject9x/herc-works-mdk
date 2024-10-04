package org.hercworks.transfer.dto.file.sim;

import org.hercworks.transfer.dto.file.TransferObject;
import org.hercworks.transfer.dto.struct.dbsim.ProjMissileDatDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("")
public class MissileDatDTO extends TransferObject {

	@JsonProperty(value ="missiles", index = 0)
	private ProjMissileDatDTO[] missiles;
	
	public MissileDatDTO() {}
	
	public ProjMissileDatDTO[] getMissiles() {
		return missiles;
	}
	
	public void setMissiles(ProjMissileDatDTO[] missiles) {
		this.missiles = missiles;
	}
}
