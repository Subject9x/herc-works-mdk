package org.hercworks.transfer.dto.file.shell;

import org.hercworks.transfer.dto.file.TransferObject;
import org.hercworks.transfer.dto.shell.struct.ArmWeapIconDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("")
public class ArmWeapDTO extends TransferObject  {

	@JsonProperty(value="total active items", index = 1)
	private short totalWeapons;
	
	@JsonProperty(index = 2)
	private ArmWeapIconDTO[] icons;
	
	@JsonProperty(value="unknown second total list", index = 3)
	private short secondList;
	
	@JsonProperty(index = 4)
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
