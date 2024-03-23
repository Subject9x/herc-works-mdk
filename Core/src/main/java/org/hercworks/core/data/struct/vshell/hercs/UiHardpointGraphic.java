package org.hercworks.core.data.struct.vshell.hercs;

import org.hercworks.core.data.file.dyn.DynamixBitmapArray;

/**
 * Class based on observed data patterns in /SHELL/GAM/ARM_[herc].DAT files.
 * Wraps a {@linkplain UiImageDBA} class with extra details
 */
public class UiHardpointGraphic extends UiImageDBA{

	private short id;
	
	private DynamixBitmapArray outlineImg;	//linked via shared frameId.
	
	private int outlineX;
	private int outlineY;
	
	public UiHardpointGraphic() {}

	public short getId() {
		return id;
	}

	public DynamixBitmapArray getOutlineImg() {
		return outlineImg;
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

	public void setOutlineImg(DynamixBitmapArray outlineImg) {
		this.outlineImg = outlineImg;
	}

	public void setOutlineX(int outlineX) {
		this.outlineX = outlineX;
	}

	public void setOutlineY(int outlineY) {
		this.outlineY = outlineY;
	}
}
