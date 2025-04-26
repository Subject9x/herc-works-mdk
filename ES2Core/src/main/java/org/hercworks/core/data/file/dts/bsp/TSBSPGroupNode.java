package org.hercworks.core.data.file.dts.bsp;

public class TSBSPGroupNode {

	private int index;
	private int len;
	
	private short coeff;
	
	private short poly;
	
	private short front;
	
	private short back;
	
	public TSBSPGroupNode() {}
	
	public TSBSPGroupNode(short coeff, short poly, short front, short back) {
		this.coeff = coeff;
		this.poly = poly;
		this.front = front;
		this.back = back;
	}

	public int getCoeff() {
		return coeff;
	}

	public void setCoeff(short coeff) {
		this.coeff = coeff;
	}

	public short getPoly() {
		return poly;
	}

	public void setPoly(short poly) {
		this.poly = poly;
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

	public int getLen() {
		return len;
	}

	public void setLen(int len) {
		this.len = len;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		str.append("{\n");
		str.append("\"class\" : ").append("TSBSPGroupNode").append(",\n");
		str.append("\"index\" : ").append(getIndex()).append(",\n");
		str.append("\"coeff\" : ").append(getCoeff()).append(",\n");
		str.append("\"poly\" : ").append(getPoly()).append(",\n");
		str.append("\"front\" : ").append(getFront()).append(",\n");
		str.append("\"back\" : ").append(getBack()).append("\n");
		str.append("}\n");
		
		return str.toString();
	}
}
