package org.hercworks.transfer.dto.struct.shell;

import org.hercworks.core.data.struct.vshell.hercs.UiImageDBA.RFlag;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("")
public class UiHardpointDTO{
	
	@JsonProperty(value = "hardpoint", index = 1)
	private short id;
	
	@JsonProperty(value = "x", index = 2)
	private int originX;
	
	@JsonProperty(value = "y", index = 3)
	private int originY;
	
	@JsonProperty(value = "outline_x", index = 4)
	private int outlineX;
	
	@JsonProperty(value = "outline_y", index = 5)
	private int outlineY;
	
	@JsonProperty(value = "frame", index = 6)
	private short frameId;
	
	@JsonProperty(value = "draw_flag", index = 7)
	private RFlag flag;
	
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

	public int getOriginX() {
		return originX;
	}

	public int getOriginY() {
		return originY;
	}
	
	public short getFrameId() {
		return frameId;
	}

	public RFlag getFlag() {
		return flag;
	}
	
	public void setOriginX(int originX) {
		this.originX = originX;
	}

	public void setOriginY(int originY) {
		this.originY = originY;
	}

	public void setFrameId(short frameId) {
		this.frameId = frameId;
	}

	public void setFlag(RFlag flag) {
		this.flag = flag;
	}
}
