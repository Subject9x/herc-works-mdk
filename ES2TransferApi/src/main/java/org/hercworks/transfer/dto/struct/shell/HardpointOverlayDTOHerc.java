package org.hercworks.transfer.dto.struct.shell;

import java.util.LinkedHashMap;

import org.hercworks.core.data.struct.herc.HercLUT;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName(value = "entry")
public class HardpointOverlayDTOHerc {
		
	@JsonProperty(index = 0, value = "herc")
	private HercLUT herc;
	
	@JsonProperty(index = 1, value = "parts", defaultValue = "[]")
	private LinkedHashMap<String, HardpointOverlayDTOSegment> parts;
	
	public HardpointOverlayDTOHerc() {}

	public HercLUT getHerc() {
		return herc;
	}

	public LinkedHashMap<String, HardpointOverlayDTOSegment> getPartAreas() {
		return parts;
	}

	public void setHerc(HercLUT herc) {
		this.herc = herc;
	}

	public void setPartAreas(LinkedHashMap<String, HardpointOverlayDTOSegment> parts) {
		this.parts = parts;
	}
}
