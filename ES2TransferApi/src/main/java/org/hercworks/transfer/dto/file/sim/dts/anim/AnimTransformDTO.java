package org.hercworks.transfer.dto.file.sim.dts.anim;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("")
public class AnimTransformDTO {

	@JsonProperty(required = false, index = 0)
	private int index;
	
	@JsonProperty(index = 1)
	private float[] rotation;

	@JsonProperty(index = 2)
	private float[] translation;
	
	public AnimTransformDTO() {}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public float[] getRotation() {
		return rotation;
	}

	public void setRotation(float[] rotation) {
		this.rotation = rotation;
	}

	public float[] getTranslation() {
		return translation;
	}

	public void setTranslation(float[] translation) {
		this.translation = translation;
	}
}
