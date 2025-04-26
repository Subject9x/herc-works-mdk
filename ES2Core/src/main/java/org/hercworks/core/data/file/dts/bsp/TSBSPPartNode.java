package org.hercworks.core.data.file.dts.bsp;

import org.hercworks.core.data.struct.Vec3Short;

public class TSBSPPartNode {
	
	private int index;
	
	private Vec3Short normal;
	
	//signed long
	private byte coeff;
	
	//signed
	private byte front;
	
	//signed
	private byte back;
	
	public TSBSPPartNode() {}

	public Vec3Short getNormal() {
		return normal;
	}

	public void setNormal(Vec3Short normal) {
		this.normal = normal;
	}

	public long getCoeff() {
		return coeff;
	}

	public void setCoeff(byte coeff) {
		this.coeff = coeff;
	}

	public byte getFront() {
		return front;
	}

	public void setFront(byte front) {
		this.front = front;
	}

	public byte getBack() {
		return back;
	}

	public void setBack(byte back) {
		this.back = back;
	}	
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		str.append("{ \n\"class\" : \"TSBSPPartNode\",\n");
		str.append("\"index\" : ").append(getIndex()).append(",\n");
		str.append("\"normal\" : ").append(getNormal().toString()).append(",\n");
		str.append("\"coeff\" : ").append(getCoeff()).append(",\n");
		str.append("\"front\" : ").append(getFront()).append(",\n");
		str.append("\"back\" : ").append(getBack()).append("\n");
		str.append("}\n");
		
		return str.toString();
	}
}
