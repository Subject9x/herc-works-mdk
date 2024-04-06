package org.hercworks.core.data.struct.vshell.hercs;

import org.hercworks.core.data.file.dat.shell.HercInf;

/***
 *  Utility class, abstracted out from {@linkplain HercInf},
 *  	separate class makes working with this data-class easier.
 */
public class HercInfEntry {

	private short hercId;
	private short weight;
	private	short speed;
	private short hardpointTotal;
	private short salvageReq;
	private short unknownFlag;
	private short buildMissionCount;
	private short flagCampaignStart;
	
	public HercInfEntry() {}

	public short getHercId() {
		return hercId;
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
