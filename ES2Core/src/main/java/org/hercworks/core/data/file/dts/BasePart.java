package org.hercworks.core.data.file.dts;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

public class BasePart extends DTSObject implements DTSSegment{

	private short transform = 0;
	private short IDNumber = 0;
	private short radius = 0;
	private Vector3D origin = new Vector3D(0,0,0);
	
	public BasePart() {}
	
	public BasePart(short transform, short id, short radius, Vector3D origin) {
		this.transform = transform;
		this.IDNumber = id;
		this.radius = radius;
		this.origin = origin;
	}

	public short getTransform() {
		return transform;
	}

	public short getIDNumber() {
		return IDNumber;
	}

	public short getRadius() {
		return radius;
	}

	public Vector3D getOrigin() {
		return origin;
	}

	public void setTransform(short transform) {
		this.transform = transform;
	}

	public void setIDNumber(short iDNumber) {
		IDNumber = iDNumber;
	}

	public void setRadius(short radius) {
		this.radius = radius;
	}

	public void setOrigin(Vector3D origin) {
		this.origin = origin;
	}

	@Override
	public DTSChunkTypes getSegType() {
		return DTSChunkTypes.BASE_PART;
	
	}
}
