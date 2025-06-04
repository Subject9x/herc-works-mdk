package org.hercworks.transfer.dto.file.sim.dts.anim;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("frame")
public class SequenceFrameDTO {

	@JsonProperty(required = false, index = 0)
	private int id;
	
	@JsonProperty(index = 1)
	private int tick;
	
	@JsonProperty(index = 2)
	private int numberTransitions;

	@JsonProperty(index = 3)
	private int firstTransition;
	
	public SequenceFrameDTO() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTick() {
		return tick;
	}

	public void setTick(int tick) {
		this.tick = tick;
	}

	public int getNumberTransitions() {
		return numberTransitions;
	}

	public void setNumberTransitions(int numberTransitions) {
		this.numberTransitions = numberTransitions;
	}

	public int getFirstTransition() {
		return firstTransition;
	}

	public void setFirstTransition(int firstTransition) {
		this.firstTransition = firstTransition;
	}
}
