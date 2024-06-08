package org.hercworks.transfer.dto.struct.sys;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("")
public class SaveInventoryEntryDTO {

	@JsonProperty(value = "unlocked", index = 1)
	private int unlockFlag;
	
	@JsonProperty(value = "stock", index = 2)
	private WeaponEntryDTO[] data;
	
	public SaveInventoryEntryDTO() {}
	
	public int getUnlockFlag() {
		return unlockFlag;
	}

	public WeaponEntryDTO[] getData() {
		return data;
	}

	public void setUnlockFlag(int unlockFlag) {
		this.unlockFlag = unlockFlag;
	}

	public void setData(WeaponEntryDTO[] data) {
		this.data = data;
	}
	
}
