package org.hercworks.transfer.dto.file.shell;

import java.util.LinkedHashMap;

import org.hercworks.core.data.struct.MissionSector;
import org.hercworks.transfer.dto.file.TransferObject;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;


@JsonRootName("Career")
public class CareerMissionsDTO extends TransferObject {

	@JsonProperty(value = "sectors")
	private LinkedHashMap<MissionSector, int[]> sectors;
	
	public CareerMissionsDTO() {}

	public LinkedHashMap<MissionSector, int[]> getSectors() {
		return sectors;
	}

	public void setSectors(LinkedHashMap<MissionSector, int[]> sectors) {
		this.sectors = sectors;
	}
}
