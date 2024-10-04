package org.hercworks.transfer.dto.struct.shell;

import org.hercworks.core.data.struct.WeaponLUT;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("")
public class WeaponsDatDTOItem {

	@JsonProperty(value = "weapon_id", index = 1)
	private short id;
	
	@JsonProperty(value = "name", index = 2)
	private WeaponLUT weaponId;
	
	@JsonProperty(value = "cost", index = 3)
	private short salvageCost;	//* 100 to convert to tons
	
	@JsonProperty(value = "startUnlock", index = 4)
	private byte startUnlocked;
	
	@JsonProperty(value = "auto-build_priority", index = 5)
	private short autobuildPriority;
	
	public WeaponsDatDTOItem() {}

	public short getId() {
		return id;
	}

	public WeaponLUT getWeaponId() {
		return weaponId;
	}

	public short getSalvageCost() {
		return salvageCost;
	}

	public byte getStartUnlock() {
		return startUnlocked;
	}

	public short getAutobuildPriority() {
		return autobuildPriority;
	}

	public void setId(short id) {
		this.id = id;
	}

	public void setWeaponId(WeaponLUT weaponId) {
		this.weaponId = weaponId;
	}

	public void setSalvageCost(short salvageCost) {
		this.salvageCost = salvageCost;
	}

	public void setStartUnlock(byte unk1) {
		this.startUnlocked = unk1;
	}

	public void setAutobuildPriority(short autobuildPriority) {
		this.autobuildPriority = autobuildPriority;
	}
}
