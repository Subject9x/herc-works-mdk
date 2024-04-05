package org.hercworks.transfer.dto.shell.struct;

import org.hercworks.core.data.struct.vshell.hercs.UiImageDBA.RFlag;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("")
public class UiImageDTO {
	
	@JsonProperty(value = "x", index = 2)
	private int originX;
	
	@JsonProperty(value = "y", index = 3)
	private int originY;

	@JsonProperty(value = "frame", index = 6)
	private short frameId;
	
	@JsonProperty(value = "draw_flag", index = 7)
	private RFlag flag;
	
	public UiImageDTO() {}
	

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
