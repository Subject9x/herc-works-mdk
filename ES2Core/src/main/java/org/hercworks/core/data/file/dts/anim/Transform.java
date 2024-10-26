package org.hercworks.core.data.file.dts.anim;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

public class Transform {

	private Vector3D r;
	private Vector3D t; //unsigned short
	
	public Transform() {}

	public Vector3D getR() {
		return r;
	}

	public Vector3D getT() {
		return t;
	}

	public void setR(Vector3D r) {
		this.r = r;
	}

	public void setT(Vector3D t) {
		this.t = t;
	}
}
