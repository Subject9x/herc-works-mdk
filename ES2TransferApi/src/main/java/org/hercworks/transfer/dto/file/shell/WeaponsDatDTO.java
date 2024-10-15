package org.hercworks.transfer.dto.file.shell;

import java.util.List;

import org.hercworks.transfer.dto.file.TransferObject;
import org.hercworks.transfer.dto.struct.shell.WeaponsDatDTOItem;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("")
public class WeaponsDatDTO extends TransferObject {

	@JsonProperty(value = "total", index = 1)
	private short total;
	
	@JsonProperty(value = "weaponData", index = 2)
	private WeaponsDatDTOItem[] weapons;
	
	@JsonProperty(value = "startingList", index = 4)
	private List<String[]> startingList;
	
	public WeaponsDatDTO() {}

	public short getTotal() {
		return total;
	}

	public WeaponsDatDTOItem[] getWeapons() {
		return weapons;
	}

	public List<String[]> getStartingList() {
		return startingList;
	}

	public void setTotal(short total) {
		this.total = total;
	}

	public void setWeapons(WeaponsDatDTOItem[] weapons) {
		this.weapons = weapons;
	}

	public void setStartingList(List<String[]> startingList) {
		this.startingList = startingList;
	}
}
