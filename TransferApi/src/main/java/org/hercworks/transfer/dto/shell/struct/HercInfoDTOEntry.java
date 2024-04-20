package org.hercworks.transfer.dto.shell.struct;

import org.hercworks.core.data.struct.herc.HercLUT;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("")
public class HercInfoDTOEntry {

	@JsonProperty(value = "id",index = 1)
	private short hercId;

	@JsonProperty(value = "name", index = 2)
	private HercLUT hercName;
	
	@JsonProperty(value = "display_weight",index = 3)
	private short weight;

	@JsonProperty(value = "display_speed",index = 4)
	private	short speed;

	@JsonProperty(value = "total_hardpoints",index = 5)
	private short hardpointTotal;

	@JsonProperty(value = "salvage_cost",index = 6)
	private short salvageReq;

	@JsonProperty(value = "unknown_flag", index = 7)
	private short unknownFlag;
	
	@JsonProperty(value = "build_phases", index = 8)
	private short buildMissionCount;

	@JsonProperty(value = "on_start", index = 9)
	private short flagCampaignStart;
	
	public HercInfoDTOEntry() {}

	public short getHercId() {
		return hercId;
	}
	
	public HercLUT getHercName() {
		return hercName;
	}

	public short getWeight() {
		return weight;
	}

	public short getSpeed() {
		return speed;
	}

	public short getHardpointTotal() {
		return hardpointTotal;
	}

	public short getSalvageReq() {
		return salvageReq;
	}

	public short getUnknownFlag() {
		return unknownFlag;
	}
	
	public short getBuildMissionCount() {
		return buildMissionCount;
	}

	public short getFlagCampaignStart() {
		return flagCampaignStart;
	}

	public void setHercId(short hercId) {
		this.hercId = hercId;
	}

	public void setHercName(HercLUT hercName) {
		this.hercName = hercName;
	}

	public void setWeight(short weight) {
		this.weight = weight;
	}

	public void setSpeed(short speed) {
		this.speed = speed;
	}

	public void setHardpointTotal(short hardpointTotal) {
		this.hardpointTotal = hardpointTotal;
	}

	public void setSalvageReq(short salvageReq) {
		this.salvageReq = salvageReq;
	}

	public void setUnknownFlag(short unknownFlag) {
		this.unknownFlag = unknownFlag;
	}

	public void setBuildMissionCount(short buildMissionCount) {
		this.buildMissionCount = buildMissionCount;
	}

	public void setFlagCampaignStart(short flagCampaignStart) {
		this.flagCampaignStart = flagCampaignStart;
	}
}
