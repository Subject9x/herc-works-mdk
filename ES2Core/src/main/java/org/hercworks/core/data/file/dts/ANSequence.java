package org.hercworks.core.data.file.dts;

import at.favre.lib.bytes.Bytes;

public class ANSequence implements DTSSegment {

	private short tic = 0;
	private short priority = 0;
	private short gm = 0;
	private ANSequenceFrame[] frames;
	private short[] parts;
	private short[] transformIndices;
	
	public ANSequence() {}
	
	public short getTic() {
		return tic;
	}

	public short getPriority() {
		return priority;
	}

	public short getGm() {
		return gm;
	}

	public ANSequenceFrame[] getFrames() {
		return frames;
	}

	public short[] getParts() {
		return parts;
	}

	public short[] getTransformIndices() {
		return transformIndices;
	}

	public void setTic(short tic) {
		this.tic = tic;
	}

	public void setPriority(short priority) {
		this.priority = priority;
	}

	public void setGm(short gm) {
		this.gm = gm;
	}

	public void setFrames(ANSequenceFrame[] frames) {
		this.frames = frames;
	}

	public void setParts(short[] parts) {
		this.parts = parts;
	}

	public void setTransformIndices(short[] transformIndices) {
		this.transformIndices = transformIndices;
	}
	
	@Override
	public Bytes getSegmentType() {
		return Bytes.from("0x1e0001".toCharArray());
	}
	
}
