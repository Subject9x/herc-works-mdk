package org.hercworks.core.data.file.msn;

import java.util.Arrays;

import org.hercworks.core.data.struct.WeaponLUT;
import org.hercworks.core.data.struct.herc.HercLUT;

/**
 * 144 Byte segment
 * 
 * defines Herc unit spawn info.
 */
public class UnitInfo {

	//I think this is terminal, instead its some leading flag Most entries have this, and the
	//bytes dont fully line up to the following counter after this segment IF this is terminal
	private short terminalFlag;
	
	private short indexId;
	
	private short headerFlags[] = new short[22];
	
	private HercLUT unitId;
	
	private short[] weapons = new short[10];
	
	private short[] unkFlags = new short[36];
	
	private short healthModAdjust;
	
	
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
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		
		str.append("{\n");
		str.append("	map uuid? = ").append(getIndexId()).append("\n");
		str.append("	flags = \n");
		str.append("		").append(Arrays.toString(getHeaderFlags())).append("\n");
		if(getUnitId() == null) {
			str.append("	No unit id!?\n");
		}
		else {
			str.append("	").append(getUnitId().getName()).append("\n");
		}
		str.append(" 	weapons = [");
		for(short s : getWeapons()) {
			if(s == -1) {
				str.append(WeaponLUT.NONE.getName()).append(", ");
			}
			else {
				str.append(WeaponLUT.getById((int)s).getName()).append(", ");
			}
		}
		str.append("]\n		");
		str.append(Arrays.toString(getUnkFlags())).append("\n");
		str.append("	hp mod = ").append(getHealthModAdjust()).append("\n");
		str.append("	terminal = ").append(getTerminalFlag()).append("\n");
		str.append("}\n");
		
		return str.toString();
	}
}
