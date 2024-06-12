package org.hercworks.transfer.dto.struct.shell;

import org.hercworks.core.data.struct.WeaponLUT;
import org.hercworks.transfer.dto.struct.sys.WeaponEntryDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("")
public class HercHardpointDTO extends WeaponEntryDTO {
	
	@JsonProperty(index = 0)
	private short hardpoint;
	
	@JsonProperty(index = 1)
	private WeaponLUT item;
	
	public HercHardpointDTO() {}

	public short getHardpoint() {
		return hardpoint;
	}

	public WeaponLUT getItem() {
		return item;
	}

	public void setHardpoint(short hardpoint) {
		this.hardpoint = hardpoint;
	}

	public void setItem(WeaponLUT item) {
		this.item = item;
	}
}
