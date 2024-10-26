package org.hercworks.core.data.file.dts;

public class ShadedPoly extends SolidPoly implements DTSSegment{
	
	public ShadedPoly() {}
	
	@Override
	public DTSChunkTypes getSegType() {
		return DTSChunkTypes.SHADED_POLY;
	}
}
