package org.hercworks.transfer.dto.struct.dbsim;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("")
public class HercDmgPieceInternalsDTO {

	@JsonProperty(value = "crit_chance", index = 0)
	private float critChance = 0.0f;

	@JsonProperty(value = "internal_id", index = 1)
	private String internalsId;
	
	public HercDmgPieceInternalsDTO(){}

	public float getCritChance() {
		return critChance;
	}

	public String getInternalsId() {
		return internalsId;
	}

	public void setCritChance(float critChance) {
		this.critChance = critChance;
	}

	public void setInternalsId(String internals) {
		this.internalsId = internals;
	}
}
