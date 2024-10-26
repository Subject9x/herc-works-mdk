package org.hercworks.core.data.file.dts;

public class GouradPoly extends SolidPoly implements DTSSegment {

	private short normalList;
	
	public GouradPoly() {}
	
	public short getNormalList() {
		return normalList;
	}
	
	public void setNormalList(short normal_list) {
		this.normalList = normal_list;
	}

	@Override
	public DTSChunkTypes getSegType() {
		return DTSChunkTypes.GOURAD_POLY;
	}
}
