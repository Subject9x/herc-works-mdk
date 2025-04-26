package org.hercworks.core.data.file.dts.anim;

import org.hercworks.core.data.struct.Vec3Short;

public class ANAnimListTransform {

	private Vec3Short r;
	private Vec3Short t;
	private int index;
	
	
	public ANAnimListTransform() {}

	public Vec3Short getR() {
		return r;
	}

	public void setR(Vec3Short r) {
		this.r = r;
	}

	public Vec3Short getT() {
		return t;
	}

	public void setT(Vec3Short t) {
		this.t = t;
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
		
		str.append("{ \"class\" : \"").append(getClass().getSimpleName()).append("\",\n");
		str.append("\"index\" : ").append(getIndex()).append(",\n");
		str.append("\"r\" : ").append(getR().toString()).append(",\n");
		str.append("\"t\" : ").append(getT().toString()).append("\n");
		str.append("}\n");
		
		return str.toString();
	}
}
