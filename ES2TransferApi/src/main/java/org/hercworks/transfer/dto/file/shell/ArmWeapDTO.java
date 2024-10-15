package org.hercworks.transfer.dto.file.shell;

import org.hercworks.transfer.dto.file.TransferObject;
import org.hercworks.transfer.dto.struct.shell.ArmWeapIconDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("ArmWeap")
public class ArmWeapDTO extends TransferObject  {

	@JsonProperty(value="total_weapons", index = 1)
	private short totalWeapons;
	
	@JsonProperty(value="weapon_icons", index = 2)
	private ArmWeapIconDTO[] icons;
	
	@JsonProperty(value="unknown_list", index = 3)
	private short secondList;
	
	@JsonProperty(value="unknown_second_list", index = 4)
	private ArmWeapIconDTO[] secondary;
	
	public ArmWeapDTO() {}

	public ArmWeapDTO(short totalWeapons) {
		this.totalWeapons = totalWeapons;
		this.icons = new ArmWeapIconDTO[totalWeapons];
	}

	public short getTotalWeapons() {
		return totalWeapons;
	}

	public ArmWeapIconDTO[] getIcons() {
		return icons;
	}

	public short getSecondList() {
		return secondList;
	}

	public ArmWeapIconDTO[] getSecondary() {
		return secondary;
	}

	public void setTotalWeapons(short totalWeapons) {
		this.totalWeapons = totalWeapons;
	}

	public void setIcons(ArmWeapIconDTO[] icons) {
		this.icons = icons;
	}

	public void setSecondList(short secondList) {
		this.secondList = secondList;
	}

	public void setSecondary(ArmWeapIconDTO[] secondary) {
		this.secondary = secondary;
	}
	
}
