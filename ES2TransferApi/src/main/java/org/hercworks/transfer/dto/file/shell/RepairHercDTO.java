package org.hercworks.transfer.dto.file.shell;

import java.util.Map;

import org.hercworks.core.data.struct.WeaponLUT;
import org.hercworks.transfer.dto.file.TransferObject;
import org.hercworks.transfer.dto.struct.shell.UiImageDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("")
public class RepairHercDTO extends TransferObject {

	@JsonProperty(value = "body_frames", index = 1)
	private Map<Short, UiImageDTO> bodyImages;
	
	@JsonProperty(value = "internals_img", index = 2)
	private UiImageDTO internalImage;
	
	@JsonProperty(value = "weapons", index = 3)
	private Map<WeaponLUT, UiImageDTO[]> weaponsMap;
	
	public RepairHercDTO() {}

	public Map<Short, UiImageDTO> getBodyImages() {
		return bodyImages;
	}

	public UiImageDTO getInternalImage() {
		return internalImage;
	}

	public Map<WeaponLUT, UiImageDTO[]> getWeaponsMap() {
		return weaponsMap;
	}

	public void setBodyImages(Map<Short, UiImageDTO> bodyImages) {
		this.bodyImages = bodyImages;
	}

	public void setInternalImage(UiImageDTO internalImage) {
		this.internalImage = internalImage;
	}

	public void setWeaponsMap(Map<WeaponLUT, UiImageDTO[]> weaponsMap) {
		this.weaponsMap = weaponsMap;
	}
}
