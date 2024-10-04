package org.hercworks.transfer.dto.struct.sys;

import org.hercworks.core.data.struct.MissileType;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("")
public class WeaponEntryDTO {
	
	@JsonProperty(value = "armor", index = 2)
	private int armor;

	@JsonProperty(value = "structure", index = 3)
	private int structure;
	
	@JsonProperty(value = "missile_type", index = 4)
	private MissileType missileType;
	
	public WeaponEntryDTO() {}

	public int getArmor() {
		return armor;
	}

	public int getStructure() {
		return structure;
	}

	public void setArmor(int armor) {
		this.armor = armor;
	}

	public void setStructure(int structure) {
		this.structure = structure;
	}

	public MissileType getMissileType() {
		return missileType;
	}

	public void setMissileType(MissileType missileType) {
		this.missileType = missileType;
	}
}