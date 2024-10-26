package org.hercworks.core.data.file.dts.anim;

import java.util.LinkedList;

import org.hercworks.core.data.file.dts.DTSChunkTypes;
import org.hercworks.core.data.file.dts.DTSObject;
import org.hercworks.core.data.file.dts.DTSSegment;

public class Sequence extends DTSObject implements DTSSegment {

	private short tic = 0;
	private short priority = 0;
	private short gm = 0;
	private LinkedList<SequenceFrame> frames = new LinkedList<SequenceFrame>();
	private LinkedList<Short> parts = new LinkedList<Short>();
	private LinkedList<Short> transformIndexList = new LinkedList<Short>();
	
	public Sequence() {}
	
	public short getTic() {
		return tic;
	}

	public short getPriority() {
		return priority;
	}

	public short getGm() {
		return gm;
	}

	public LinkedList<SequenceFrame> getFrames() {
		return frames;
	}

	public LinkedList<Short>  getParts() {
		return parts;
	}

	public LinkedList<Short>  getTransformIndexList() {
		return transformIndexList;
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

	public void setFrames(LinkedList<SequenceFrame> frames) {
		this.frames = frames;
	}

	public void setParts(LinkedList<Short>  parts) {
		this.parts = parts;
	}

	public void setTransformIndexList(LinkedList<Short> transformIndexList) {
		this.transformIndexList = transformIndexList;
	}
	
	@Override
	public DTSChunkTypes getSegType() {
		return DTSChunkTypes.SEQ;
	}
}
