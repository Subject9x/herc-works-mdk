package org.hercworks.transfer.dto.file.sim.dts.part;

import com.fasterxml.jackson.annotation.JsonRootName;


@JsonRootName("")
public class TSBitmapPartDTO extends TSBasePartDTO{

	private int bmpFrameNum;
	
	private int offsetX;
	
	private int offsetY;
	
	public TSBitmapPartDTO() {}

	public int getBmpFrameNum() {
		return bmpFrameNum;
	}

	public void setBmpFrameNum(int bmpFrameNum) {
		this.bmpFrameNum = bmpFrameNum;
	}

	public int getOffsetX() {
		return offsetX;
	}

	public void setOffsetX(int offsetX) {
		this.offsetX = offsetX;
	}

	public int getOffsetY() {
		return offsetY;
	}

	public void setOffsetY(int offsetY) {
		this.offsetY = offsetY;
	}
}
