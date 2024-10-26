package org.hercworks.core.data.file.dts;

public class Poly extends DTSObject implements DTSSegment{

	private short normal = 0;
	private short center = 0;
	private short vertexCount = 0;
	private short vertexList = 0;
	
	public Poly() {}
	
	public short getNormal() {
		return normal;
	}

	public short getCenter() {
		return center;
	}

	public short getVertexCount() {
		return vertexCount;
	}

	public short getVertexList() {
		return vertexList;
	}

	public void setNormal(short normal) {
		this.normal = normal;
	}

	public void setCenter(short center) {
		this.center = center;
	}

	public void setVertexCount(short vertexCount) {
		this.vertexCount = vertexCount;
	}

	public void setVertexList(short vertexList) {
		this.vertexList = vertexList;
	}

	@Override
	public DTSChunkTypes getSegType() {
		return DTSChunkTypes.POLY;
	}
}
