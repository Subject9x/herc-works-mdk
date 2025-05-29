package org.hercworks.core.data.file.dts.anim;

import org.hercworks.core.data.struct.Vec3Short;

public class ANAnimListTransform {

	private Vec3Short rotation;
	private Vec3Short translation;
	private int index;
	
	
	public ANAnimListTransform() {}

	public Vec3Short getRotation() {
		return rotation;
	}

	public void setRotation(Vec3Short r) {
		this.rotation = r;
	}

	public Vec3Short getTranslation() {
		return translation;
	}

	public void setTranslation(Vec3Short t) {
		this.translation = t;
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
		str.append("\"rotation\" : ").append(getRotation().toString()).append(",\n");
		str.append("\"translation\" : ").append(getTranslation().toString()).append("\n");
		str.append("}\n");
		
		return str.toString();
	}
}
