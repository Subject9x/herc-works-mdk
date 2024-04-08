package org.hercworks.transfer.dto.shell.struct;

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
	private short missileNum;
	
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

	public short getMissileNum() {
		return missileNum;
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

	public void setMissileNum(short missileNum) {
		this.missileNum = missileNum;
	}
}
