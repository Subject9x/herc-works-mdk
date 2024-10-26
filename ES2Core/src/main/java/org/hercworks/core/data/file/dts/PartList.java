package org.hercworks.core.data.file.dts;

import java.util.LinkedList;

public class PartList extends BasePart implements DTSSegment{

	private LinkedList<DTSObject> parts = new LinkedList<DTSObject>();

	public PartList() {}
	
	public LinkedList<DTSObject> getParts() {
		return parts;
	}

	public void setParts(LinkedList<DTSObject> parts) {
		this.parts = parts;
	}

	@Override
	public DTSChunkTypes getSegType() {
		return DTSChunkTypes.PART_LIST;
	}
}
