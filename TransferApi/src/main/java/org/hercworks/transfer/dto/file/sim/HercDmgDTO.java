package org.hercworks.transfer.dto.file.sim;

import java.util.LinkedHashMap;

import org.hercworks.transfer.dto.file.TransferObject;
import org.hercworks.transfer.dto.struct.dbsim.HercDmgPieceDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("")
public class HercDmgDTO extends TransferObject {

	@JsonProperty(value = "internals_armor", index = 0)
	private LinkedHashMap<String, Short> internals = new LinkedHashMap<String, Short>();
	
	@JsonProperty(value = "body_parts", index = 1)
	private HercDmgPieceDTO[] hercParts;
	
	public HercDmgDTO() {}

	public LinkedHashMap<String, Short> getInternals() {
		return internals;
	}

	public HercDmgPieceDTO[] getHercParts() {
		return hercParts;
	}

	public void setInternals(LinkedHashMap<String, Short> internals) {
		this.internals = internals;
	}

	public void setHercParts(HercDmgPieceDTO[] hercParts) {
		this.hercParts = hercParts;
	}
}
