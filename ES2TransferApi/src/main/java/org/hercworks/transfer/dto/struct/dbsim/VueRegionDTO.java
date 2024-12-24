package org.hercworks.transfer.dto.struct.dbsim;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("")
public class VueRegionDTO {

	@JsonProperty(index = 0)
	private int originX;

	@JsonProperty(index = 1)
	private int originY;

	@JsonProperty(index = 2)
	private int width;

	@JsonProperty(index = 3)
	private int height;
	
	@JsonProperty(index = 4)
	private int unkOriginX;
	
	@JsonProperty(index = 5)
	private int unkOriginY;

	@JsonProperty(index = 6)
	private int unkWidth;

	@JsonProperty(index = 7)
	private int unkHeight;
	
	public VueRegionDTO() {}

	public int getOriginX() {
		return originX;
	}

	public int getOriginY() {
		return originY;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getUnkOriginX() {
		return unkOriginX;
	}

	public int getUnkOriginY() {
		return unkOriginY;
	}

	public int getUnkWidth() {
		return unkWidth;
	}

	public int getUnkHeight() {
		return unkHeight;
	}

	public void setOriginX(int originX) {
		this.originX = originX;
	}

	public void setOriginY(int originY) {
		this.originY = originY;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setUnkOriginX(int unkOriginX) {
		this.unkOriginX = unkOriginX;
	}

	public void setUnkOriginY(int unkOriginY) {
		this.unkOriginY = unkOriginY;
	}

	public void setUnkWidth(int unkWidth) {
		this.unkWidth = unkWidth;
	}

	public void setUnkHeight(int unkHeight) {
		this.unkHeight = unkHeight;
	}
}
