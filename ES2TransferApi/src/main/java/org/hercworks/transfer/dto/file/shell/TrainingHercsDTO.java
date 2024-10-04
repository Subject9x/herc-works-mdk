package org.hercworks.transfer.dto.file.shell;

import org.hercworks.transfer.dto.file.TransferObject;
import org.hercworks.transfer.dto.struct.shell.ShellHercDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("")
public class TrainingHercsDTO extends TransferObject  {

	@JsonProperty(value = "", index = 1)
	private ShellHercDTO[] data;
	
	public TrainingHercsDTO() {}

	public ShellHercDTO[] getData() {
		return data;
	}

	public void setData(ShellHercDTO[] data) {
		this.data = data;
	}
	
}
