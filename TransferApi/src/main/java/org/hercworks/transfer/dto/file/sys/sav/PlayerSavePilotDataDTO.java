package org.hercworks.transfer.dto.file.sys.sav;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("")
public class PlayerSavePilotDataDTO {

	@JsonProperty(value = "id", index = 0)
	private int squadmateId;

	@JsonProperty(value = "name", index = 1)
	private String name;

	@JsonProperty(value = "assigned_herc_bay", index = 2)
	private int bayId;

	@JsonProperty(value = "active", index = 3)
	private boolean activeFlag;

	@JsonProperty(value = "rank", index = 4)
	private String rank;

	@JsonProperty(value = "crew_row_id", index = 5)
	private int crewRowNum;

	@JsonProperty(value = "unk2_uint16", index = 6)
	private int unk2_uint16;

	@JsonProperty(value = "health", index = 7)
	private int probablyHealth;

	@JsonProperty(value = "kills", index = 8)
	private Map<String, Integer> kills = new HashMap<String, Integer>();
	
	@JsonProperty(value = "total_kills", index = 9)
	private Map<String, Integer> totalKills = new HashMap<String, Integer>();

	@JsonProperty(value = "missions_total", index = 10)
	private int missionCount;
	
	@JsonProperty(value = "unk5_uint16", index = 11)
	private int unk5_uint16;
	
	public PlayerSavePilotDataDTO() {}

	public int getSquadmateId() {
		return squadmateId;
	}

	public String getName() {
		return name;
	}

	public int getBayId() {
		return bayId;
	}

	public boolean getActiveFlag() {
		return activeFlag;
	}

	public String getRank() {
		return rank;
	}

	public int getCrewRowNum() {
		return crewRowNum;
	}

	public int getUnk2_uint16() {
		return unk2_uint16;
	}

	public int getProbablyHealth() {
		return probablyHealth;
	}

	public Map<String, Integer> getKills() {
		return kills;
	}

	public Map<String, Integer> getTotalKills() {
		return totalKills;
	}

	public int getMissionCount() {
		return missionCount;
	}

	public int getUnk5_uint16() {
		return unk5_uint16;
	}

	public void setSquadmateId(int squadmateId) {
		this.squadmateId = squadmateId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBayId(int bayId) {
		this.bayId = bayId;
	}

	public void setActiveFlag(boolean activeFlag) {
		this.activeFlag = activeFlag;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public void setCrewRowNum(int crewRowNum) {
		this.crewRowNum = crewRowNum;
	}

	public void setUnk2_uint16(int unk2_uint16) {
		this.unk2_uint16 = unk2_uint16;
	}

	public void setProbablyHealth(int probablyHealth) {
		this.probablyHealth = probablyHealth;
	}

	public void setKills(Map<String, Integer> kills) {
		this.kills = kills;
	}

	public void setTotalKills(Map<String, Integer> totalKills) {
		this.totalKills = totalKills;
	}

	public void setMissionCount(int missionCount) {
		this.missionCount = missionCount;
	}

	public void setUnk5_uint16(int unk5_uint16) {
		this.unk5_uint16 = unk5_uint16;
	}
	
}
