package org.hercworks.transfer.dto.file.shell;

import java.util.Map;

import org.hercworks.core.data.file.dat.shell.ArmHerc;
import org.hercworks.core.data.struct.WeaponLUT;
import org.hercworks.transfer.dto.file.TransferObject;
import org.hercworks.transfer.dto.struct.shell.UiHardpointDTO;
import org.hercworks.transfer.dto.struct.shell.UiImageDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/***
 * Wraps {@linkplain ArmHerc} SHELL0/GAM/
 */
@JsonRootName("Arm_Herc_dat")
public class ArmHercDTO extends TransferObject  {

	@JsonProperty(value = "img_bay_top_frame", index = 1)
	private short topImgArrId;
	
	@JsonProperty(value = "img_herc_body_top", index = 2)
	private UiImageDTO hercTopImg;
	
	@JsonProperty(value = "img_bay_bottom_frame", index = 3)
	private short bottomImgArrId;
	
	@JsonProperty(value = "img_herc_body_bottom",index = 4)
	private UiImageDTO hercBotImg;
	
	@JsonProperty(value = "max_weapons", defaultValue = "26", index = 5)
	private short totalWeapons;
	
	@JsonProperty(value = "weapons", index = 6)
	private Map<WeaponLUT, UiHardpointDTO[]> weaponsMap;
	
	public ArmHercDTO() {}

	public UiImageDTO getHercTopImg() {
		return hercTopImg;
	}

	public short getTopImgArrId() {
		return topImgArrId;
	}

	public short getBottomImgArrId() {
		return bottomImgArrId;
	}

	public UiImageDTO getHercBotImg() {
		return hercBotImg;
	}

	public short getTotalWeapons() {
		return totalWeapons;
	}

	public Map<WeaponLUT, UiHardpointDTO[]> getWeaponsMap() {
		return weaponsMap;
	}

	public void setHercTopImg(UiImageDTO hercTopImg) {
		this.hercTopImg = hercTopImg;
	}

	public void setHercBotImg(UiImageDTO hercBotImg) {
		this.hercBotImg = hercBotImg;
	}

	public void setTotalWeapons(short totalWeapons) {
		this.totalWeapons = totalWeapons;
	}
	
	public void setWeaponsMap(Map<WeaponLUT, UiHardpointDTO[]> weaponHardpoints) {
		this.weaponsMap = weaponHardpoints;
	}

	public void setTopImgArrId(short topImgArrId) {
		this.topImgArrId = topImgArrId;
	}

	public void setBottomImgArrId(short bottomImgArrId) {
		this.bottomImgArrId = bottomImgArrId;
	}

}
