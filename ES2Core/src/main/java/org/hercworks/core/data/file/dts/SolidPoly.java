package org.hercworks.core.data.file.dts;

public class SolidPoly extends Poly implements DTSSegment{

	private short color = 0;
	
	public SolidPoly() {}
	
	public short getColor() {
		return color;
	}

	public void setColor(short color) {
		this.color = color;
	}

	@Override
	public DTSChunkTypes getSegType() {
		return DTSChunkTypes.SOLID_POLY;
	}
}
