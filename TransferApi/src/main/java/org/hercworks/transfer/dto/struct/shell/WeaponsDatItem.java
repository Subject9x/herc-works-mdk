package org.hercworks.transfer.dto.struct.shell;

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
	
	@JsonProperty(value = "auto-build_priority", index = 5)
	private short autobuildPriority;
	
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

	public short getAutobuildPriority() {
		return autobuildPriority;
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

	public void setAutobuildPriority(short autobuildPriority) {
		this.autobuildPriority = autobuildPriority;
	}
}
