package org.hercworks.transfer.dto.file.sim;

import java.util.LinkedHashMap;

import org.hercworks.transfer.dto.file.TransferObject;
import org.hercworks.transfer.dto.struct.dbsim.WeapnPDGDTOItem;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("")
public class WpnPDGDTO extends TransferObject {

	
	@JsonProperty(value ="")
	private LinkedHashMap<Integer, WeapnPDGDTOItem> entries; 
	
	
	public WpnPDGDTO() {}


	public LinkedHashMap<Integer, WeapnPDGDTOItem> getEntries() {
		return entries;
	}


	public void setEntries(LinkedHashMap<Integer, WeapnPDGDTOItem> entries) {
		this.entries = entries;
	}
	
}
