package org.hercworks.transfer.dto.file.sim.dts.anim;

import org.hercworks.transfer.dto.file.sim.dts.TSObjectDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;


@JsonRootName("")
@JsonTypeInfo(
		  use = JsonTypeInfo.Id.NAME, 
		  include = JsonTypeInfo.As.PROPERTY, 
		  property = "type")
@JsonSubTypes({ 
  @Type(value = ANCyclicSequenceDTO.class, name = "ANCyclicSequence")
})

public class ANSequenceDTO extends TSObjectDTO {

	@JsonProperty(required = false, index = 0)
	private int index;
	
	private int tick;
	
	private int priority;
	
	private int groundMoveFlag;
	
	private SequenceFrameDTO[] frames;
	
	private int[] partIds;
	
	private int[] transformIndexes;
	
	public ANSequenceDTO() {}

	public int getTick() {
		return tick;
	}

	public void setTick(int tick) {
		this.tick = tick;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getGroundMoveFlag() {
		return groundMoveFlag;
	}

	public void setGroundMoveFlag(int groundMoveFlag) {
		this.groundMoveFlag = groundMoveFlag;
	}

	public SequenceFrameDTO[] getFrames() {
		return frames;
	}

	public void setFrames(SequenceFrameDTO[] frames) {
		this.frames = frames;
	}

	public int[] getPartIds() {
		return partIds;
	}

	public void setPartIds(int[] partIds) {
		this.partIds = partIds;
	}

	public int[] getTransformIndexes() {
		return transformIndexes;
	}

	public void setTransformIndexes(int[] transformIndexes) {
		this.transformIndexes = transformIndexes;
	}
	
}
