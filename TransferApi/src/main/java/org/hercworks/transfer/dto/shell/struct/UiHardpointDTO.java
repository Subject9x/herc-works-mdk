package org.hercworks.transfer.dto.shell.struct;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("")
public class UiHardpointDTO extends UiImageDTO{
	
	@JsonProperty(value = "hardpoint", index = 1)
	private short id;
	
	@JsonProperty(value = "outline_x", index = 4)
	private int outlineX;
	
	@JsonProperty(value = "outline_y", index = 5)
	private int outlineY;
	
	public UiHardpointDTO() {}

	public short getId() {
		return id;
	}

	public int getOutlineX() {
		return outlineX;
	}

	public int getOutlineY() {
		return outlineY;
	}

	public void setId(short id) {
		this.id = id;
	}

	public void setOutlineX(int outlineX) {
		this.outlineX = outlineX;
	}

	public void setOutlineY(int outlineY) {
		this.outlineY = outlineY;
	}
}
