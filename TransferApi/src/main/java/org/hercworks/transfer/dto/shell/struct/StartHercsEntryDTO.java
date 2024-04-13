package org.hercworks.transfer.dto.shell.struct;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("")
public class StartHercsEntryDTO {
	
	@JsonProperty(index = 1)
	private short bayId;
	
	@JsonProperty(value = "", index = 2)
	private ShellHercDTO herc;
	
	public StartHercsEntryDTO() {}

	public short getBayId() {
		return bayId;
	}

	public ShellHercDTO getHerc() {
		return herc;
	}

	public void setBayId(short bayId) {
		this.bayId = bayId;
	}

	public void setHerc(ShellHercDTO herc) {
		this.herc = herc;
	}
}
