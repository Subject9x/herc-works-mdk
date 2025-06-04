package org.hercworks.transfer.dto.file.sim.dts.anim;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("")
public class AnimTransitionDTO {

	@JsonProperty(required = false, index = 0)
	private int index;
	
	@JsonProperty(index = 1)
	private int tick;
	
	@JsonProperty(index = 2)
	private int destination_seq;
	
	@JsonProperty(index = 3)
	private int destination_frame;
	
	@JsonProperty(index = 4)
	private int groundMovement;
	
	public AnimTransitionDTO() {}

	public int getTick() {
		return tick;
	}

	public void setTick(int tick) {
		this.tick = tick;
	}

	public int getDestination_seq() {
		return destination_seq;
	}

	public void setDestination_seq(int destination_seq) {
		this.destination_seq = destination_seq;
	}

	public int getDestination_frame() {
		return destination_frame;
	}

	public void setDestination_frame(int destination_frame) {
		this.destination_frame = destination_frame;
	}

	public int getGroundMovement() {
		return groundMovement;
	}

	public void setGroundMovement(int groundMovement) {
		this.groundMovement = groundMovement;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
