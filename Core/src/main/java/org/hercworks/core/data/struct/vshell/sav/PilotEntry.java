package org.hercworks.core.data.struct.vshell.sav;

import org.hercworks.core.data.file.sav.PlayerSave;

/**
 * Specifically bound to {@linkplain PlayerSave},
 * 	there's a chunk of save data dealing just with squadmate state, and player state.
 * 36-byte segments.
 * Squadmate Entry
 * 	0- UINT16 - name length
 *  2- String - null-terminated string
 *  0+X- UINT16 - Herc Bay Id assignment - FF FF is 'empty'
 *  X+2- UINT8 - some kind of flag
 *  X+3- UINT16 - Rank - see {@linkplain PilotRank}
 *  X+5- UINT16 - Crew row number - FF FF is 'empty'
 *  X+7- UINT16 - unk2_uint16
 *  X+9- UINT16 - unk3_uint16_hp - probably pilot health which would increase chance of KIA.
 */
public class PilotEntry {

	private short squadmateId;
	private String name;
	private short bayId;
	private byte active;
	private PilotRank rank;
	private short crewRowNum;
	private short unk2_uint16;
	private short probablyHealth;
	private short killsHercs;
	private short killsFlyers;
	private short killsBuilding;
	private short totalKillHerc;
	private short totalKillFlyer;
	private short totalKillBldng;
	private short missionCount;
	private short unk5_uint16;
	
	public PilotEntry() {}

	public String getName() {
		return name;
	}

	public short getBayId() {
		return bayId;
	}

	public byte getActive() {
		return active;
	}

	public PilotRank getRank() {
		return rank;
	}

	public short getCrewRowNum() {
		return crewRowNum;
	}

	public short getUnk2_uint16() {
		return unk2_uint16;
	}

	public short getProbablyHealth() {
		return probablyHealth;
	}

	public short getMissionCount() {
		return missionCount;
	}

	public short getUnk5_uint16() {
		return unk5_uint16;
	}

	public short getSquadmateId() {
		return squadmateId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBayId(short bayId) {
		this.bayId = bayId;
	}

	public void setActive(byte active) {
		this.active = active;
	}

	public void setRank(PilotRank rank) {
		this.rank = rank;
	}

	public void setCrewRowNum(short crewRowNum) {
		this.crewRowNum = crewRowNum;
	}

	public void setUnk2_uint16(short unk2_uint16) {
		this.unk2_uint16 = unk2_uint16;
	}

	public void setProbablyHealth(short probablyHealth) {
		this.probablyHealth = probablyHealth;
	}

	public void setMissionCount(short missionCount) {
		this.missionCount = missionCount;
	}

	public void setUnk5_uint16(short unk5_uint16) {
		this.unk5_uint16 = unk5_uint16;
	}

	public void setSquadmateId(short squadmateId) {
		this.squadmateId = squadmateId;
	}

	public short getKillsHercs() {
		return killsHercs;
	}

	public short getKillsFlyers() {
		return killsFlyers;
	}

	public short getKillsBuilding() {
		return killsBuilding;
	}

	public short getTotalKillHerc() {
		return totalKillHerc;
	}

	public short getTotalKillFlyer() {
		return totalKillFlyer;
	}

	public short getTotalKillBldng() {
		return totalKillBldng;
	}

	public void setKillsHercs(short killsHercs) {
		this.killsHercs = killsHercs;
	}

	public void setKillsFlyers(short killsFlyers) {
		this.killsFlyers = killsFlyers;
	}

	public void setKillsBuilding(short killsBuilding) {
		this.killsBuilding = killsBuilding;
	}

	public void setTotalKillHerc(short totalKillHerc) {
		this.totalKillHerc = totalKillHerc;
	}

	public void setTotalKillFlyer(short totalKillFlyer) {
		this.totalKillFlyer = totalKillFlyer;
	}

	public void setTotalKillBldng(short totalKillBldng) {
		this.totalKillBldng = totalKillBldng;
	}
}
