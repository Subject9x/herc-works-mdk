package org.hercworks.transfer.dto.struct.dbsim;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("")
public class PaperDollHardpointDTO {

	@JsonProperty(value = "origin", index = 0)
	private int[] origin = new int[2];
	
	@JsonProperty(value = "unk1", index = 1)
	private int unk1;
	
	@JsonProperty(value = "unk2", index = 2)
	private int unk2;
	
	@JsonProperty(value = "spacer", index = 3)
	private int spacer;
	
	public PaperDollHardpointDTO() {}

	public int[] getOrigin() {
		return origin;
	}

	public int getUnk1() {
		return unk1;
	}

	public int getUnk2() {
		return unk2;
	}

	public int getSpacer() {
		return spacer;
	}

	public void setOrigin(int[] origin) {
		this.origin = origin;
	}

	public void setUnk1(int unk1) {
		this.unk1 = unk1;
	}

	public void setUnk2(int unk2) {
		this.unk2 = unk2;
	}

	public void setSpacer(int spacer) {
		this.spacer = spacer;
	}
}
