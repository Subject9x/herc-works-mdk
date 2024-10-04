package org.hercworks.transfer.dto.file.sys.sav;

import java.util.HashMap;
import java.util.Map;

import org.hercworks.transfer.dto.struct.shell.HercHardpointDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("")
public class HercBayEntryDTO {

	@JsonProperty(value = "herc", index = 0)
	private String id;
	
	@JsonProperty(value = "structure", index = 1)
	private Map<String, Integer> healthExternals = new HashMap<String, Integer>();
	
	@JsonProperty(value = "internals", index = 2)
	private Map<String, Integer> healthInternals = new HashMap<String, Integer>();
	
	@JsonProperty(value = "hardpoints", index = 3)
	private Map<Integer, Integer> hardpoints = new HashMap<Integer, Integer>();
	
	@JsonProperty(value = "build_percent", index = 4)
	private int buildPercent;
	
	@JsonProperty(value = "build_step", index = 5)
	private int buildStepNum;
	
	@JsonProperty(value = "weapons", index = 6)
	private HercHardpointDTO[] weapons;
	
	public HercBayEntryDTO() {}

	public String getId() {
		return id;
	}

	public Map<String, Integer> getHealthExternals() {
		return healthExternals;
	}

	public Map<String, Integer> getHealthInternals() {
		return healthInternals;
	}

	public int getBuildPercent() {
		return buildPercent;
	}

	public int getBuildStepNum() {
		return buildStepNum;
	}

	public Map<Integer, Integer> getHardpoints() {
		return hardpoints;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setHealthExternals(Map<String, Integer> healthExternals) {
		this.healthExternals = healthExternals;
	}

	public void setHealthInternals(Map<String, Integer> healthInternals) {
		this.healthInternals = healthInternals;
	}

	public void setBuildPercent(int buildPercent) {
		this.buildPercent = buildPercent;
	}

	public void setBuildStepNum(int buildStepNum) {
		this.buildStepNum = buildStepNum;
	}

	public void setHardpoints(Map<Integer, Integer> hardpoints) {
		this.hardpoints = hardpoints;
	}

	public HercHardpointDTO[] getWeapons() {
		return weapons;
	}

	public void setWeapons(HercHardpointDTO[] weapons) {
		this.weapons = weapons;
	}
	
	
}
