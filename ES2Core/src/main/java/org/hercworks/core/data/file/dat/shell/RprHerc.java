package org.hercworks.core.data.file.dat.shell;

import java.util.Map;

import org.hercworks.core.data.struct.vshell.hercs.UiHardpointGraphic;
import org.hercworks.core.data.struct.vshell.hercs.UiImageDBA;
import org.hercworks.voln.DataFile;

/**
 * 	FILE
 * 		/SHELL/GAM/RPR_[herc].DAT
 * 
 *  RPR_[herc].DBA
 * 	0- UINT16 - total images to draw
 * 	SEQ_0 - {@linkplain UiHardpointGraphic}, no outline data.
 * 		0_0- UINT16 - id
 * 		0_2- UINT32 - X coord
 * 		0_6- UINT32 - Y coord
 *  	0_10- UINT16 - DBA frame Id
 *  	0_12- UINT16 - render flag
 * 
 * [HERC]_INT.DBA
 * {@linkplain UiHardpointGraphic} - internal components image
 * SEQ_0+2- UINT16 - id - 0
 * SEQ_0+4- UINT32 - X coord.
 * SEQ_0+8- UINT32 - Y coord
 * SEQ_0+12- UINT16 - DBA frame Id
 * SEQ_0+14- UINT16 - render flag
 * 
 * SEQ_0+16- UINT16 - total entries
 * 
 * Layout seems similiar to ARM_[herc].DAT
 * SEQ_1 - Weapon data?
 * 	1_0- UINT16 - Weapon Id - starts with 01, ATC20mm
 *  1_2- UINT16 - 
 * 
 * 
 */
public class RprHerc extends DataFile{

	private short bodyImgTotal;
	private Map<Short, UiImageDBA> bodyImages;
	
	private UiHardpointGraphic internalImage;
	
	private short totalHardpoints;
	private Map<Short, UiHardpointGraphic[]> weaponHardpoints;
	
	public RprHerc() {}

	public short getBodyImgTotal() {
		return bodyImgTotal;
	}

	public Map<Short, UiImageDBA> getBodyImages() {
		return bodyImages;
	}

	public UiHardpointGraphic getInternalImage() {
		return internalImage;
	}

	public short getTotalHardpoints() {
		return totalHardpoints;
	}

	public Map<Short, UiHardpointGraphic[]> getWeaponHardpoints() {
		return weaponHardpoints;
	}

	public void setBodyImgTotal(short bodyImgTotal) {
		this.bodyImgTotal = bodyImgTotal;
	}

	public void setBodyImages(Map<Short, UiImageDBA> bodyImages) {
		this.bodyImages = bodyImages;
	}

	public void setInternalImage(UiHardpointGraphic internalImage) {
		this.internalImage = internalImage;
	}

	public void setTotalHardpoints(short totalHardpoints) {
		this.totalHardpoints = totalHardpoints;
	}

	public void setWeaponHardpoints(Map<Short, UiHardpointGraphic[]> weaponHardpoints) {
		this.weaponHardpoints = weaponHardpoints;
	}
}
