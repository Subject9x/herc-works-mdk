package org.hercworks.core.data.file.dts;

import java.util.Vector;

public class ANAnimListTransform {

	private Vector<Short> r;
	private Vector<Short> t; //unsigned short
	
	public ANAnimListTransform() {}

	public Vector<Short> getR() {
		return r;
	}

	public Vector<Short> getT() {
		return t;
	}

	public void setR(Vector<Short> r) {
		this.r = r;
	}

	public void setT(Vector<Short> t) {
		this.t = t;
	}
}
