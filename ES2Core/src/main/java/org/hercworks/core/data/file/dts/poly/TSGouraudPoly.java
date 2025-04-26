package org.hercworks.core.data.file.dts.poly;

import org.hercworks.core.data.file.dts.TSChunkHeader;

public class TSGouraudPoly extends TSSolidPoly {

	private short normalList;
	
	public TSGouraudPoly() {
		super(TSChunkHeader.TS_GOURAUD_POLY);
	}
	
	public TSGouraudPoly(TSChunkHeader hdr) {
		super(hdr);
	}

	public short getNormalList() {
		return normalList;
	}

	public void setNormalList(short normalList) {
		this.normalList = normalList;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		str.append(metaInfoString(getClass().getSimpleName()));
		str.append("\"normal\" : ").append(getNormal()).append(",\n");
		str.append("\"center\" : ").append(getCenter()).append(",\n");
		str.append("\"vertexCount\" : ").append(getVertexCount()).append(",\n");
		str.append("\"vertexList\" : ").append(getVertexList()).append(",\n");
		str.append("\"color\" : ").append(getColor()).append(",\n");
		str.append("\"normalList\" : ").append(getNormalList()).append("\n");
		str.append("}");
		
		return str.toString();
	}
	
}
