package org.hercworks.transfer.dto.struct.dbsim;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("")
public class PaperDollRegionDTO {

	@JsonProperty(value = "index", index = 0)
	private int index;

	@JsonProperty(value = "topLeft", index = 1)
	private int[] topLeft = new int[2];

	@JsonProperty(value = "botRight", index = 2)
	private int[] botRight = new int[2];

	@JsonProperty(value = "unk_val", index = 3)
	private int unk_val;

	@JsonProperty(value = "spacer", index = 4)
	private int spacer;
	
	public PaperDollRegionDTO() {}

	public int getIndex() {
		return index;
	}

	public int[] getTopLeft() {
		return topLeft;
	}

	public int[] getBotRight() {
		return botRight;
	}

	public int getUnk_val() {
		return unk_val;
	}

	public int getSpacer() {
		return spacer;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public void setTopLeft(int[] topLeft) {
		this.topLeft = topLeft;
	}

	public void setBotRight(int[] botRight) {
		this.botRight = botRight;
	}

	public void setUnk_val(int unk_val) {
		this.unk_val = unk_val;
	}

	public void setSpacer(int spacer) {
		this.spacer = spacer;
	}
}
