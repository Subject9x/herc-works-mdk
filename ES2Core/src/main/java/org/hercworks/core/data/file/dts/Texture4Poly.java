package org.hercworks.core.data.file.dts;

public class Texture4Poly extends SolidPoly implements DTSSegment{
	
	public Texture4Poly() {}
	
	@Override
	public DTSChunkTypes getSegType() {
		return DTSChunkTypes.TEXTURE4_POLY;
	}
}
