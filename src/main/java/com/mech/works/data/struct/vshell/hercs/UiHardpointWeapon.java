package com.mech.works.data.struct.vshell.hercs;

import com.mech.works.data.file.dyn.DynamixBitmapArray;

/**
 * Extension of {@link UiHardpoint}, provides
 *  data for weapon's outline graphic as pulled from
 *  `[herc]_OUT.DBA`
 *  
 */
public class UiHardpointWeapon extends UiHardpoint{

	private DynamixBitmapArray outlineImg;
	
	private int outlineX;
	private int outlineY;
	
	public UiHardpointWeapon() {}

	public DynamixBitmapArray getOutlineImg() {
		return outlineImg;
	}

	public int getOutlineX() {
		return outlineX;
	}

	public int getOutlineY() {
		return outlineY;
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
