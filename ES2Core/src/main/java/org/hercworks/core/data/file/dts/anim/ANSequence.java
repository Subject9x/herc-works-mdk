package org.hercworks.core.data.file.dts.anim;

import java.util.Arrays;

import org.hercworks.core.data.file.dts.TSChunk;
import org.hercworks.core.data.file.dts.TSChunkHeader;

public class ANSequence extends TSChunk {

	//unsigned
	private short tick;
	
	//unsigned
	private short priority;
	
	//unsigned
	private short groundMovement;
	
	private ANSequenceFrame[] frames;
	
	private short[] partIds;
	
	private short[] transformIndices;
	
	
	public ANSequence() {
		super(TSChunkHeader.AN_SEQUENCE);
	}
	
	public ANSequence(TSChunkHeader hdr) {
		super(hdr);
	}


	public short getTick() {
		return tick;
	}
	
	public void setTick(short tick) {
		this.tick = tick;
	}

	public short getPriority() {
		return priority;
	}

	public void setPriority(short priority) {
		this.priority = priority;
	}

	public short getGroundMovement() {
		return groundMovement;
	}

	public void setGroundMovement(short gm) {
		this.groundMovement = gm;
	}

	public ANSequenceFrame[] getFrames() {
		return frames;
	}

	public void setFrames(ANSequenceFrame[] frames) {
		this.frames = frames;
	}

	public short[] getPartIds() {
		return partIds;
	}

	public void setPartIds(short[] partIds) {
		this.partIds = partIds;
	}

	public short[] getTransformIndices() {
		return transformIndices;
	}

	public void setTransformIndices(short[] transformIndices) {
		this.transformIndices = transformIndices;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		str.append(metaInfoString(getClass().getSimpleName()));
		
		str.append("\"tick\" : ").append(getTick()).append(",\n");
		str.append("\"priority\" : ").append(getPriority()).append(",\n");
		str.append("\"groundMove\" : ").append(getGroundMovement()).append(",\n");
		
		str.append("\"frames\" : [\n");
		for(int s=0; s < getFrames().length; s++) {
			str.append(getFrames()[s].toString());
			if(s < getFrames().length - 1) {
				str.append(",");
			}
			str.append("\n");
		}
		str.append("],\n");
		
		str.append("\"partIds\" : ").append(Arrays.toString(getPartIds())).append(",\n");
		str.append("\"transformIndices\" : ").append(Arrays.toString(getTransformIndices())).append("\n");
		str.append("}\n");
		
		return str.toString();
	}
}
