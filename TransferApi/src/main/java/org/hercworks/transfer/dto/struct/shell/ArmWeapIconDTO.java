package org.hercworks.transfer.dto.struct.shell;

import org.hercworks.core.data.struct.WeaponLUT;
import org.hercworks.core.data.struct.vshell.hercs.UiImageDBA.RFlag;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("")
public class ArmWeapIconDTO {

	@JsonProperty(value = "weapon", index = 1)
	private WeaponLUT id;
	
	@JsonProperty(value = "x", index = 2)
	private int originX;
	
	@JsonProperty(value = "y", index = 3)
	private int originY;

	@JsonProperty(value = "frame", index = 6)
	private short frameId;
	
	@JsonProperty(value = "draw_flag", index = 7)
	private RFlag flag;

	public ArmWeapIconDTO() {}
	
	public WeaponLUT getId() {
		return id;
	}

	public void setId(WeaponLUT id) {
		this.id = id;
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
