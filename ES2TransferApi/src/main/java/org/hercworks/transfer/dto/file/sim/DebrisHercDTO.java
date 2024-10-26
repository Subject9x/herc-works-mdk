package org.hercworks.transfer.dto.file.sim;

import org.hercworks.transfer.dto.file.TransferObject;
import org.hercworks.transfer.svc.impl.dbsim.DebrisHercEntryDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("DebrisHerc")
public class DebrisHercDTO extends TransferObject{

	@JsonProperty(value = "pieces")
	private DebrisHercEntryDTO[] pieces;
	
	public DebrisHercDTO() {}

	public DebrisHercEntryDTO[] getPieces() {
		return pieces;
	}

	public void setPieces(DebrisHercEntryDTO[] pieces) {
		this.pieces = pieces;
	}
}
