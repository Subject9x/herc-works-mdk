package org.hercworks.transfer.dto.shell.struct;

import org.hercworks.core.data.struct.HercLUT;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("")
public class StartHercsEntryDTO {
	
	@JsonProperty(index = 1)
	private short bayId;
	
	@JsonProperty(index = 2)
	private HercLUT hercId;
	
	@JsonProperty(index = 3)
	private short healthRatio;
	
	@JsonProperty(index = 4)
	private short buildCompleteLevel;
	
	@JsonProperty(value = "hardpoints", index = 6)
	private HercHardpointDTO[] hardpoints;
	
	public StartHercsEntryDTO() {}

	public StartHercsEntryDTO(int hardpointCount) {
		this.hardpoints = new HercHardpointDTO[hardpointCount];
	}
	
	public short getBayId() {
		return bayId;
	}

	public HercLUT getHercId() {
		return hercId;
	}

	public short getHealthRatio() {
		return healthRatio;
	}

	public short getBuildCompleteLevel() {
		return buildCompleteLevel;
	}

	public HercHardpointDTO[] getHardpoints() {
		return hardpoints;
	}

	public void setBayId(short bayId) {
		this.bayId = bayId;
	}

	public void setHercId(HercLUT hercId) {
		this.hercId = hercId;
	}

	public void setHealthRatio(short healthRatio) {
		this.healthRatio = healthRatio;
	}

	public void setBuildCompleteLevel(short buildCompleteLevel) {
		this.buildCompleteLevel = buildCompleteLevel;
	}


	public void setHardpoints(HercHardpointDTO[] data) {
		this.hardpoints = data;
	}
}
