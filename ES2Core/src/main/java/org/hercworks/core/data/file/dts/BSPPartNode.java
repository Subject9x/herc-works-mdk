package org.hercworks.core.data.file.dts;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

public class BSPPartNode {

	private Vector3D normal = new Vector3D(0,0,0);
	private int coeff = 0;
	private short front = 0;
	private short back = 0;
	
	public BSPPartNode() {}

	public Vector3D getNormal() {
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

	public void setNormal(Vector3D normal) {
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
