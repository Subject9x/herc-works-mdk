package org.hercworks.core.data.file.msn.script;

import org.hercworks.core.data.file.msn.UnitInfo;
import org.hercworks.core.data.struct.WeaponLUT;
import org.hercworks.core.data.struct.herc.HercLUT;

/**
 * Truncated version of {@linkplain UnitInfo} objects seen in .MSN files
 */
public class ScriptUnitEntry {

	private short mapCoordId;	//raw value
	private ScriptCoord mapCoord;	//
	
	private short[] flags; //len 19, unknown usage
	
	private short unitId;
	private HercLUT unit;
	
	private short[] itemId; //len 10 
	private WeaponLUT items;
	
	private short[] unkFlags = new short[36];
	
	private short healthModAdjust;
	
	public ScriptUnitEntry() {
		
	}

	public short getMapCoordId() {
		return mapCoordId;
	}

	public void setMapCoordId(short mapCoordId) {
		this.mapCoordId = mapCoordId;
	}

	public ScriptCoord getMapCoord() {
		return mapCoord;
	}

	public void setMapCoord(ScriptCoord mapCoord) {
		this.mapCoord = mapCoord;
	}

	public short[] getFlags() {
		return flags;
	}

	public void setFlags(short[] flags) {
		this.flags = flags;
	}

	public short getUnitId() {
		return unitId;
	}

	public void setUnitId(short unitId) {
		this.unitId = unitId;
	}

	public HercLUT getUnit() {
		return unit;
	}

	public void setUnit(HercLUT unit) {
		this.unit = unit;
	}

	public short[] getItemId() {
		return itemId;
	}

	public void setItemId(short[] itemId) {
		this.itemId = itemId;
	}

	public WeaponLUT getItems() {
		return items;
	}

	public void setItems(WeaponLUT items) {
		this.items = items;
	}

	public short[] getUnkFlags() {
		return unkFlags;
	}

	public void setUnkFlags(short[] unkFlags) {
		this.unkFlags = unkFlags;
	}

	public short getHealthModAdjust() {
		return healthModAdjust;
	}

	public void setHealthModAdjust(short healthModAdjust) {
		this.healthModAdjust = healthModAdjust;
	}
}
