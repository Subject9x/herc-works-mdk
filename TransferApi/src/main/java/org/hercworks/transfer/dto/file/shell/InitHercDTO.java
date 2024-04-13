package org.hercworks.transfer.dto.file.shell;

import org.hercworks.core.data.struct.HercLUT;
import org.hercworks.core.data.struct.vshell.hercs.ShellHercData;
import org.hercworks.transfer.dto.file.TransferObject;
import org.hercworks.transfer.dto.shell.struct.HercHardpointDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * 	Really just a JSON wrapper for {@linkplain ShellHercData}
 */
@JsonRootName("")
public class InitHercDTO extends TransferObject  {

	@JsonProperty(value = "herc", index = 1)
	private HercLUT hercId;
	
	@JsonProperty(value="health_perc", index = 2)
	private int healthRatio;
	
	@JsonProperty(index = 3)
	private int buildCompleteLevel;
	
	@JsonProperty(value="", index = 4)
	private HercHardpointDTO[] hardpoints;
	
	public InitHercDTO() {}
	
	public HercLUT getHercId() {
		return hercId;
	}

	public int getHealthRatio() {
		return healthRatio;
	}

	public int getBuildCompleteLevel() {
		return buildCompleteLevel;
	}

	public HercHardpointDTO[] getHardpoints() {
		return hardpoints;
	}

	public void setHercId(HercLUT hercId) {
		this.hercId = hercId;
	}

	public void setHealthRatio(int healthRatio) {
		this.healthRatio = healthRatio;
	}

	public void setBuildCompleteLevel(int buildCompleteLevel) {
		this.buildCompleteLevel = buildCompleteLevel;
	}
	
	public void setHardpoints(HercHardpointDTO[] hardpoints) {
		this.hardpoints = hardpoints;
	}
	
}
