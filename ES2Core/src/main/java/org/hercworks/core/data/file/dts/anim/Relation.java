package org.hercworks.core.data.file.dts.anim;

public class Relation {

	private short parent;
	private short destination;
	
	public Relation() {}

	public short getParent() {
		return parent;
	}

	public short getDestination() {
		return destination;
	}

	public void setParent(short parent) {
		this.parent = parent;
	}

	public void setDestination(short destination) {
		this.destination = destination;
	}
}
