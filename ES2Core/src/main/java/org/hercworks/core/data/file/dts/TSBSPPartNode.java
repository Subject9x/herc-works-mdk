package org.hercworks.core.data.file.dts;

import java.util.Vector;

public class TSBSPPartNode {

	private Vector<Short> normal = new Vector<Short>(3);
	private int coeff = 0;
	private short front = 0;
	private short back = 0;
	
	public TSBSPPartNode() {}

	public Vector<Short> getNormal() {
		return normal;
	}

	public int getCoeff() {
		return coeff;
	}

	public short getFront() {
		return front;
	}

	public short getBack() {
		return back;
	}

	public void setNormal(Vector<Short> normal) {
		this.normal = normal;
	}

	public void setCoeff(int coeff) {
		this.coeff = coeff;
	}

	public void setFront(short front) {
		this.front = front;
	}

	public void setBack(short back) {
		this.back = back;
	}
}
