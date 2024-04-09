package org.hercworks.transfer.dto.file.shell;

import java.util.List;

import org.hercworks.transfer.dto.shell.struct.StartHercsEntryDTO;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("")
public class StartHercsDTO {

	private List<StartHercsEntryDTO> hercs;
	
	public StartHercsDTO() {}
	
	public List<StartHercsEntryDTO> getHercs() {
		return hercs;
	}

	public void setHercs(List<StartHercsEntryDTO> hercs) {
		this.hercs = hercs;
	}
}
