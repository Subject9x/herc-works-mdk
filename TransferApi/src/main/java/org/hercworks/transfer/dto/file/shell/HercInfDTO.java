package org.hercworks.transfer.dto.file.shell;

import org.hercworks.core.data.file.dat.shell.HercInf;
import org.hercworks.transfer.dto.file.TransferObject;
import org.hercworks.transfer.dto.shell.struct.HercInfoDTOEntry;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * 	Wraps {@linkplain HercInf}  SHELL0/GAM/ file
 */
@JsonRootName("")
public class HercInfDTO extends TransferObject  {

	@JsonProperty(value = "total_hercs", index = 1)
	private short totalHercs;
	
	@JsonProperty(value = "", index = 2)
	private HercInfoDTOEntry[] data;
	
	public HercInfDTO() {}
	
	public HercInfDTO(short totalHercs) {
		this.totalHercs = totalHercs;
		data = new HercInfoDTOEntry[totalHercs];
	}
	

	public HercInfoDTOEntry newEntry() {
		return new HercInfoDTOEntry();
	}
	
	public short getTotalHercs() {
		return totalHercs;
	}

	public HercInfoDTOEntry[] getData() {
		return data;
	}

	public void setTotalHercs(short totalHercs) {
		this.totalHercs = totalHercs;
	}

	public void setData(HercInfoDTOEntry[] data) {
		this.data = data;
	}
	
}
