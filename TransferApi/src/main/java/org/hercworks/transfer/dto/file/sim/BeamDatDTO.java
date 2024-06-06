package org.hercworks.transfer.dto.file.sim;

import org.hercworks.transfer.dto.file.TransferObject;
import org.hercworks.transfer.dto.struct.dbsim.BeamDatEntryDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("")
public class BeamDatDTO extends TransferObject {

	@JsonProperty("beams")
	private BeamDatEntryDTO[] beams;
	
	public BeamDatDTO() {}
	
	public BeamDatDTO(int total) {
		this.beams = new BeamDatEntryDTO[total];
	}

	public BeamDatEntryDTO[] getBeams() {
		return beams;
	}

	public void setBeams(BeamDatEntryDTO[] beams) {
		this.beams = beams;
	}
	
}
