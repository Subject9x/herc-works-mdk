package org.hercworks.transfer.dto.file.shell;

import java.util.List;

import org.hercworks.transfer.dto.file.TransferObject;
import org.hercworks.transfer.dto.struct.shell.StartHercsEntryDTO;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("")
public class StartHercsDTO extends TransferObject  {

	private List<StartHercsEntryDTO> hercs;
	
	public StartHercsDTO() {}
	
	public List<StartHercsEntryDTO> getHercs() {
		return hercs;
	}

	public void setHercs(List<StartHercsEntryDTO> hercs) {
		this.hercs = hercs;
	}
}
