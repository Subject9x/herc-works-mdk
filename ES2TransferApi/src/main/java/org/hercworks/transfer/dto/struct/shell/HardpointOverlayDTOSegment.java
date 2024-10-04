package org.hercworks.transfer.dto.struct.shell;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName(value = "part")
public class HardpointOverlayDTOSegment {
	
	@JsonProperty(index = 0)
	private int x;
	
	@JsonProperty(index = 1)
	private int y;
	
	@JsonProperty(index = 2)
	private int width;
	
	@JsonProperty(index = 3)
	private int height;
	
	public HardpointOverlayDTOSegment() {}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
