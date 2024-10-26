package org.hercworks.core.data.file.dts;

import java.util.LinkedList;

public class Shape extends PartList implements DTSSegment{
	
	private LinkedList<Short> sequenceList = new LinkedList<Short>();
	private LinkedList<Short> transformList = new LinkedList<Short>();
	private LinkedList<DTSObject> extraParts = new LinkedList<DTSObject>();
	//transformList ??
	
	public Shape() {}
	
	public LinkedList<Short> getSequenceList() {
		return sequenceList;
	}

	public LinkedList<Short> getTransformList() {
		return transformList;
	}

	public void setSequenceList(LinkedList<Short> sequenceList) {
		this.sequenceList = sequenceList;
	}

	public void setTransformList(LinkedList<Short> transformList) {
		this.transformList = transformList;
	}
	
	public LinkedList<DTSObject> getExtraParts() {
		return extraParts;
	}

	public void setExtraParts(LinkedList<DTSObject> extraParts) {
		this.extraParts = extraParts;
	}

	@Override
	public DTSChunkTypes getSegType() {
		return DTSChunkTypes.TS_SHAPE;
	}
}
