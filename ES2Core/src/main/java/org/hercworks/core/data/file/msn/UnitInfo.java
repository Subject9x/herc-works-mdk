package org.hercworks.core.data.file.msn;

import org.hercworks.core.data.struct.herc.HercLUT;

/**
 * 144 Byte segment
 * 
 * defines Herc unit spawn info.
 */
public class UnitInfo {

	
	
	private short indexId;
	
	private short headerFlags[] = new short[22];
	
	private HercLUT unitId;
	
	private short[] weapons = new short[10];
	
	private short[] unkFlags = new short[36];
	
	private short healthModAdjust;
	
	private short terminalFlag;
	
	
	public UnitInfo() {}


	public short getIndexId() {
		return indexId;
	}

	public short[] getHeaderFlags() {
		return headerFlags;
	}

	public HercLUT getUnitId() {
		return unitId;
	}

	public short[] getWeapons() {
		return weapons;
	}

	public short[] getUnkFlags() {
		return unkFlags;
	}

	public short getHealthModAdjust() {
		return healthModAdjust;
	}

	public short getTerminalFlag() {
		return terminalFlag;
	}

	public void setIndexId(short indexId) {
		this.indexId = indexId;
	}

	public void setHeaderFlags(short[] headerFlags) {
		this.headerFlags = headerFlags;
	}

	public void setUnitId(HercLUT unitId) {
		this.unitId = unitId;
	}

	public void setWeapons(short[] weapons) {
		this.weapons = weapons;
	}

	public void setUnkFlags(short[] unkFlags) {
		this.unkFlags = unkFlags;
	}

	public void setHealthModAdjust(short healthModAdjust) {
		this.healthModAdjust = healthModAdjust;
	}

	public void setTerminalFlag(short terminalFlag) {
		this.terminalFlag = terminalFlag;
	}
}
