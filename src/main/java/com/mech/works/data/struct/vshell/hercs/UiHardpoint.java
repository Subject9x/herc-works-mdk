package com.mech.works.data.struct.vshell.hercs;

/**
 * Observed mostly in the Arming Menu, 
 * tracks data around rendering hardpoints, the base class
 * is used in several spots.
 */
public class UiHardpoint {
	private int id;

	private UiImageDBA image;
	
	public UiHardpoint() {}

	public int getId() {
		return id;
	}

	public UiImageDBA getImage() {
		return image;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setImage(UiImageDBA image) {
		this.image = image;
	}
}
