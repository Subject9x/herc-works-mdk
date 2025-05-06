package org.hercworks.core.data.file.dts.bsp;

import java.util.Arrays;

import org.hercworks.core.data.struct.Vec3Short;

public class TSBSPPartNode {
	
	private int index;
	private int byteLen;
	private byte[] data ;
	
	private Vec3Short normal;
	
	//signed long
	private int coeff;
	
	//signed
	private short front;
	
	//signed
	private short back;
	
	public TSBSPPartNode() {}

	public Vec3Short getNormal() {
		return normal;
	}

	public void setNormal(Vec3Short normal) {
		this.normal = normal;
	}

	public int getCoeff() {
		return coeff;
	}

	public void setCoeff(int coeff) {
		this.coeff = coeff;
	}

	public short getFront() {
		return front;
	}

	public void setFront(short front) {
		this.front = front;
	}

	public short getBack() {
		return back;
	}

	public void setBack(short back) {
		this.back = back;
	}	
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	public int getByteLen() {
		return byteLen;
	}

	public void setByteLen(int byteLen) {
		this.byteLen = byteLen;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		str.append("{ \n\"class\" : \"TSBSPPartNode\",\n");
		str.append("\"index\" : ").append(getIndex()).append(",\n");
		str.append("\"byteLen\" : ").append(getByteLen()).append(",\n");
		str.append("\"data\" : ").append(Arrays.toString(getData())).append(",\n");
		str.append("\"normal\" : ").append(getNormal().toString()).append(",\n");
		str.append("\"coeff\" : ").append(getCoeff()).append(",\n");
		str.append("\"front\" : ").append(getFront()).append(",\n");
		str.append("\"back\" : ").append(getBack()).append("\n");
		str.append("}\n");
		
		return str.toString();
	}
}
