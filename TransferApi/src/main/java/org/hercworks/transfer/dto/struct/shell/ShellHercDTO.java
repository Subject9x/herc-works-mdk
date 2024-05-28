package org.hercworks.transfer.dto.struct.shell;

import org.hercworks.core.data.struct.herc.HercLUT;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("")
public class ShellHercDTO {
	
	@JsonProperty(index = 1)
	private HercLUT hercId;
	
	@JsonProperty(index = 2)
	private short healthRatio;
	
	@JsonProperty(index = 3)
	private short buildCompleteLevel;
	
	@JsonProperty(value = "hardpoints", index = 4)
	private HercHardpointDTO[] hardpoints;
	
	public ShellHercDTO() {}

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
