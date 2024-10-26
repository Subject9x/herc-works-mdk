package org.hercworks.core.data.file.dts;

public class BSPGroupNode {

	private int coeff = 0;
	private short poly = 0;
	private short front = 0;
	private short back = 0;
	
	public BSPGroupNode() {}

	public int getCoeff() {
		return coeff;
	}

	public short getPoly() {
		return poly;
	}

	public short getFront() {
		return front;
	}

	public short getBack() {
		return back;
	}

	public void setCoeff(int coeff) {
		this.coeff = coeff;
	}

	public void setPoly(short poly) {
		this.poly = poly;
	}

	public void setFront(short front) {
		this.front = front;
	}

	public void setBack(short back) {
		this.back = back;
	}
}
