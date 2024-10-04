package org.hercworks.transfer.dto.file.shell;

import java.util.LinkedHashMap;

import org.hercworks.transfer.dto.file.TransferObject;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;


@JsonRootName("")
public class CareerMissionsDTO extends TransferObject {

	@JsonProperty(value = "sectors")
	private LinkedHashMap<String, int[]> sectors;
	
	public CareerMissionsDTO() {}

	public LinkedHashMap<String, int[]> getSectors() {
		return sectors;
	}

	public void setSectors(LinkedHashMap<String, int[]> sectors) {
		this.sectors = sectors;
	}
}
