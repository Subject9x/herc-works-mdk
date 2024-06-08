package org.hercworks.transfer.dto.struct.sys;

import org.hercworks.core.data.struct.MissileType;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("")
public class WeaponEntryDTO {
	
	@JsonProperty(value = "health", index = 0)
	private int health = 100;
	
	@JsonProperty(value = "missile_type", index = 1)
	private MissileType missileType;
	
	public WeaponEntryDTO() {}

	public int getHealth() {
		return health;
	}

	public MissileType getMissileType() {
		return missileType;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public void setMissileType(MissileType missileType) {
		this.missileType = missileType;
	}
}