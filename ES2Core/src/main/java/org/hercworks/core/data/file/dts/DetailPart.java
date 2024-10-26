package org.hercworks.core.data.file.dts;

import java.util.LinkedList;

public class DetailPart extends PartList implements DTSSegment {

	private LinkedList<Short> details = new LinkedList<Short>();
	
	public DetailPart() {}
	
	public LinkedList<Short> getDetails() {
		return details;
	}

	public void setDetails(LinkedList<Short> details) {
		this.details = details;
	}

	@Override
	public DTSChunkTypes getSegType() {
		// TODO Auto-generated method stub
		return null;
	}
}
