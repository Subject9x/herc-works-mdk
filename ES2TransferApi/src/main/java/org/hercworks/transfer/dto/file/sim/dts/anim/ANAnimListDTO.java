package org.hercworks.transfer.dto.file.sim.dts.anim;

import org.hercworks.transfer.dto.file.sim.dts.TSObjectDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("ANAnimList")
public class ANAnimListDTO extends TSObjectDTO {

	
	private TSObjectDTO[] sequences;
	
	private AnimTransitionDTO[] transitions;
	
	private AnimTransformDTO[] transforms;
	
	@JsonProperty(value = "default_transforms")
	private int[] defaultTransforms;
	
	@JsonProperty(value = "transform_links")
	private int[][] transformIdLinks;
	
	public ANAnimListDTO() {}

	public TSObjectDTO[] getSequences() {
		return sequences;
	}

	public void setSequences(TSObjectDTO[] sequences) {
		this.sequences = sequences;
	}

	public AnimTransitionDTO[] getTransitions() {
		return transitions;
	}

	public void setTransitions(AnimTransitionDTO[] transitions) {
		this.transitions = transitions;
	}

	public AnimTransformDTO[] getTransforms() {
		return transforms;
	}

	public void setTransforms(AnimTransformDTO[] transforms) {
		this.transforms = transforms;
	}

	public int[] getDefaultTransforms() {
		return defaultTransforms;
	}

	public void setDefaultTransforms(int[] defaultTransforms) {
		this.defaultTransforms = defaultTransforms;
	}

	public int[][] getTransformIdLinks() {
		return transformIdLinks;
	}

	public void setTransformIdLinks(int[][] transformIdLinks) {
		this.transformIdLinks = transformIdLinks;
	}
}
