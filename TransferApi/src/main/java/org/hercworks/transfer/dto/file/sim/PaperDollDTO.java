package org.hercworks.transfer.dto.file.sim;

import java.util.LinkedHashMap;

import org.hercworks.transfer.dto.file.TransferObject;
import org.hercworks.transfer.dto.struct.dbsim.PaperDollHardpointDTO;
import org.hercworks.transfer.dto.struct.dbsim.PaperDollViewDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("")
public class PaperDollDTO extends TransferObject {

	@JsonProperty(value = "total", index = 0)
	private int totalViews;
	
	@JsonProperty(value = "structure", index = 0)
	private PaperDollViewDTO structure;
	
	@JsonProperty(value = "internals", index = 1)
	private PaperDollViewDTO internals;
	
	@JsonProperty(value = "hudTarget", index = 2)
	private PaperDollViewDTO hudTarget;
	
	@JsonProperty(value = "hardpoints", index = 3)
	private LinkedHashMap<Integer , PaperDollHardpointDTO> hardpoints = new LinkedHashMap<Integer, PaperDollHardpointDTO>();
	
	public PaperDollDTO() {}

	public int getTotalViews() {
		return totalViews;
	}

	public PaperDollViewDTO getStructure() {
		return structure;
	}

	public PaperDollViewDTO getInternals() {
		return internals;
	}
	
	public PaperDollViewDTO getHudTarget() {
		return hudTarget;
	}

	public void setTotalViews(int totalViews) {
		this.totalViews = totalViews;
	}
	
	public void setStructure(PaperDollViewDTO structure) {
		this.structure = structure;
	}

	public void setInternals(PaperDollViewDTO internals) {
		this.internals = internals;
	}
	
	public void setHudTarget(PaperDollViewDTO hudTarget) {
		this.hudTarget = hudTarget;
	}

	public LinkedHashMap<Integer, PaperDollHardpointDTO> getHardpoints() {
		return hardpoints;
	}

	public void setHardpoints(LinkedHashMap<Integer , PaperDollHardpointDTO> hardpoints) {
		this.hardpoints = hardpoints;
	}
}
