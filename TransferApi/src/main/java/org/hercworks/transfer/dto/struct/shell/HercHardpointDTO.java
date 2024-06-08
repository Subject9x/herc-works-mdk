package org.hercworks.transfer.dto.struct.shell;

import org.hercworks.core.data.struct.MissileType;
import org.hercworks.core.data.struct.WeaponLUT;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("")
public class HercHardpointDTO {
	
	@JsonProperty(index = 1)
	private short hardpoint;
	
	@JsonProperty(index = 2)
	private WeaponLUT item;
	
	@JsonProperty(value="hp_perc", index = 3)
	private short healthPercent;
	
	@JsonProperty(value = "msl_num", index = 4)
	private MissileType missileType;
	
	public HercHardpointDTO() {}

	public short getHardpoint() {
		return hardpoint;
	}

	public WeaponLUT getItem() {
		return item;
	}

	public short getHealthPercent() {
		return healthPercent;
	}

	public MissileType getMissileType() {
		return missileType;
	}

	public void setHardpoint(short hardpoint) {
		this.hardpoint = hardpoint;
	}

	public void setItem(WeaponLUT item) {
		this.item = item;
	}

	public void setHealthPercent(short healthPercent) {
		this.healthPercent = healthPercent;
	}

	public void setMissileType(MissileType missileType) {
		this.missileType = missileType;
	}
}
