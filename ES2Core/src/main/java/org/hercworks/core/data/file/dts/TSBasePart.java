package org.hercworks.core.data.file.dts;

import org.hercworks.core.data.struct.Vec3Short;

public class TSBasePart extends TSObject {

	//unsigned
	private short transform;

	//unsigned
	private short idNumber;
	
	//unsigned
	private short radius;
	
	private Vec3Short center;
	
	public TSBasePart() {
		super(TSObjectHeader.TS_BASE_PART);
	}
	
	public TSBasePart(TSObjectHeader hdr) {
		super(hdr);
	}

	public short getTransform() {
		return transform;
	}

	public void setTransform(short transform) {
		this.transform = transform;
	}

	public short getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(short iDNumber) {
		idNumber = iDNumber;
	}

	public short getRadius() {
		return radius;
	}

	public void setRadius(short radius) {
		this.radius = radius;
	}

	public Vec3Short getCenter() {
		return center;
	}

	public void setCenter(Vec3Short center) {
		this.center = center;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		str.append(metaInfoString(getClass().getSimpleName()));
		
		str = jsonString(str);
		
		str.append("\n");
		str.append("}\n");
		
		return str.toString();
	}

	@Override
	public StringBuilder jsonString(StringBuilder str) {
		
		str.append("\"transform\" : ").append(getTransform()).append(",\n");
		str.append("\"uid\" : \"").append(getIdNumber()).append("\",\n");
		str.append("\"radius\" : ").append(getRadius()).append(",\n");
		str.append("\"center\" : ").append(getCenter().toString());
		
		return str;
	}
}
