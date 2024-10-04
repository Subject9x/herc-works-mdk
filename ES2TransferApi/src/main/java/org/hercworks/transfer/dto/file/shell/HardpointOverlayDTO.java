package org.hercworks.transfer.dto.file.shell;

import org.hercworks.transfer.dto.file.TransferObject;
import org.hercworks.transfer.dto.struct.shell.HardpointOverlayDTOHerc;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("")
public class HardpointOverlayDTO extends TransferObject {

	public static int FILE_ARM = 0;
	public static int FILE_RPR = 1;
	
	public static String[] hercPartIds = new String[] {
			"COCKPIT",
			"TORSO_LEFT",
			"TORSO_RIGHT",
			"CHASSIS",
			"LEG_LEFT",
			"LEG_RIGHT"
	};
	
	public static String hardpointId = "HARDPOINT_";

	@JsonIgnore
	private int fileType;
	
	@JsonProperty(value="")
	private HardpointOverlayDTOHerc[] entries;
	
	public HardpointOverlayDTO() {}	
	
	public HardpointOverlayDTOHerc[] getEntries() {
		return entries;
	}

	public void setEntries(HardpointOverlayDTOHerc[] entries) {
		this.entries = entries;
	}

	public int getFileType() {
		return fileType;
	}
	
	public void setFileType(int fileType) {
		this.fileType = fileType;
	}
	
}
