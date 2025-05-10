package org.hercworks.core.data.file.dts.anim;

import java.util.Arrays;

import org.hercworks.core.data.file.dts.TSObject;
import org.hercworks.core.data.file.dts.TSObjectHeader;

public class ANSequence extends TSObject {

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
		super(TSObjectHeader.AN_SEQUENCE);
	}
	
	public ANSequence(TSObjectHeader hdr) {
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
		
		str = jsonString(str);
		
		str.append("\n");
		str.append("}\n");
		
		return str.toString();
	}	
	
	@Override
	public StringBuilder jsonString(StringBuilder str) {
		
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
		str.append("\"transformIndices\" : ").append(Arrays.toString(getTransformIndices()));
		
		return str;
	}
}
