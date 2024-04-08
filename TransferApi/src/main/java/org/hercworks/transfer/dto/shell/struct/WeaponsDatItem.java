package org.hercworks.transfer.dto.shell.struct;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("")
public class WeaponsDatItem {

	@JsonProperty(value = "weapon_id", index = 1)
	private short id;
	
	@JsonProperty(value = "name", index = 2)
	private String name;
	
	@JsonProperty(value = "cost", index = 3)
	private short salvageCost;	//* 100 to convert to tons
	
	@JsonProperty(value = "startUnlock", index = 4)
	private byte startUnlocked;
	
	@JsonProperty(value = "unknown2", index = 5)
	private short unk2;
	
	public WeaponsDatItem() {}

	public short getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public short getSalvageCost() {
		return salvageCost;
	}

	public byte getStartUnlock() {
		return startUnlocked;
	}

	public short getUnk2() {
		return unk2;
	}

	public void setId(short id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSalvageCost(short salvageCost) {
		this.salvageCost = salvageCost;
	}

	public void setStartUnlock(byte unk1) {
		this.startUnlocked = unk1;
	}

	public void setUnk2(short unk2) {
		this.unk2 = unk2;
	}
}
