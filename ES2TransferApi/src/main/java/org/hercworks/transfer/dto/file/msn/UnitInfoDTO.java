package org.hercworks.transfer.dto.file.msn;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * 144 Byte segment
 * 
 * defines Herc unit spawn info.
 */
@JsonRootName("UnitInfoDTO")
public class UnitInfoDTO extends MapObjectDTO{
	
	private MapCoordRefDTO mapCoord;
	
	private short headerFlags[] = new short[22];
	
	private String unitId;
	
	private String[] weapons = new String[10];
	
	private short[] unkFlags = new short[36];
	
	private short healthModAdjust;
	
	
	public UnitInfoDTO() {}


	public MapCoordRefDTO getMapCoord() {
		return mapCoord;
	}

	public short[] getHeaderFlags() {
		return headerFlags;
	}

	public String getUnitId() {
		return unitId;
	}

	public String[] getWeapons() {
		return weapons;
	}

	public short[] getUnkFlags() {
		return unkFlags;
	}

	public short getHealthModAdjust() {
		return healthModAdjust;
	}

	public void setMapCoord(MapCoordRefDTO mapCoord) {
		this.mapCoord = mapCoord;
	}

	public void setHeaderFlags(short[] headerFlags) {
		this.headerFlags = headerFlags;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public void setWeapons(String[] weapons) {
		this.weapons = weapons;
	}

	public void setUnkFlags(short[] unkFlags) {
		this.unkFlags = unkFlags;
	}

	public void setHealthModAdjust(short healthModAdjust) {
		this.healthModAdjust = healthModAdjust;
	}
}
