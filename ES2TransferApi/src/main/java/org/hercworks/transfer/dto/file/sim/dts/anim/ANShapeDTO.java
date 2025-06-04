package org.hercworks.transfer.dto.file.sim.dts.anim;

import org.hercworks.transfer.dto.file.sim.dts.TSShapeDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("")
public class ANShapeDTO extends TSShapeDTO {
	
	@JsonProperty(value = "animations")
	private ANAnimListDTO animationList;
	
	public ANShapeDTO() {}

	public ANAnimListDTO getAnimationList() {
		return animationList;
	}

	public void setAnimationList(ANAnimListDTO animationList) {
		this.animationList = animationList;
	}	
}
