package org.hercworks.core.data.file.dts;

public class TSPoly extends TSObject {

	//unsigned
	private short normal;
	
	//unsigned
	private short center;
	
	//unsigned
	private short vertexCount;
	
	//unsigned
	private short vertexList;
	
	public TSPoly() {
		super(TSObjectHeader.TS_POLY);
	}
	
	public TSPoly(TSObjectHeader hdr) {
		super(hdr);
	}

	public short getNormal() {
		return normal;
	}

	public void setNormal(short normal) {
		this.normal = normal;
	}

	public short getCenter() {
		return center;
	}

	public void setCenter(short center) {
		this.center = center;
	}

	public short getVertexCount() {
		return vertexCount;
	}

	public void setVertexCount(short vertexCount) {
		this.vertexCount = vertexCount;
	}

	public short getVertexList() {
		return vertexList;
	}

	public void setVertexList(short vertexList) {
		this.vertexList = vertexList;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		str.append(metaInfoString(getClass().getSimpleName()));
		str.append("\"normal\" : ").append(getNormal()).append(",\n");
		str.append("\"center\" : ").append(getCenter()).append(",\n");
		str.append("\"vertexCount\" : ").append(getVertexCount()).append(",\n");
		str.append("\"vertexList\" : ").append(getVertexList()).append("\n");
		str.append("}");
		
		return str.toString();
	}
}
