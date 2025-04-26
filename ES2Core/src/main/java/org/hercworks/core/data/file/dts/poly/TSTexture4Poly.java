package org.hercworks.core.data.file.dts.poly;

import org.hercworks.core.data.file.dts.TSChunkHeader;

public class TSTexture4Poly extends TSSolidPoly {

	
	public TSTexture4Poly() {
		super(TSChunkHeader.TS_TEXTURE4_POLY);
	}
	
	public TSTexture4Poly(TSChunkHeader hdr) {
		super(hdr);
	}

	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		str.append(metaInfoString(getClass().getSimpleName()));
		str.append("\"normal\" : ").append(getNormal()).append(",\n");
		str.append("\"center\" : ").append(getCenter()).append(",\n");
		str.append("\"vertexCount\" : ").append(getVertexCount()).append(",\n");
		str.append("\"vertexList\" : ").append(getVertexList()).append(",\n");
		str.append("\"color\" : ").append(getColor()).append("\n");
		str.append("}");
		
		return str.toString();
	}
}
