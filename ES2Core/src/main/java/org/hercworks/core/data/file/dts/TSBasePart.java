package org.hercworks.core.data.file.dts;

import java.util.Vector;

import at.favre.lib.bytes.Bytes;

public class TSBasePart implements DTSSegment{

	private short transform;
	
	private short IDNumber;
	
	private short radius;
	
	private Vector<Short> origin = new Vector<Short>(3);
	
	public TSBasePart() {}
	
	public TSBasePart(short transform, short id, short radius, Vector<Short> origin) {
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

	public Vector<Short> getOrigin() {
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

	public void setOrigin(Vector<Short> origin) {
		this.origin = origin;
	}

	@Override
	public Bytes getSegmentType() {
		return Bytes.from("0x140005".toCharArray());
	}
}
