package org.hercworks.transfer.dto.file.sim.dts.bsp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("")
public class TSBSPPartNodeDTO {

	@JsonProperty(required = false)
	private int index;
	
	private float[] normal;
	
	private int coeff;
	
	private int front;
	
	private int back;
	
	public TSBSPPartNodeDTO() {}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public float[] getNormal() {
		return normal;
	}

	public void setNormal(float[] normal) {
		this.normal = normal;
	}

	public int getCoeff() {
		return coeff;
	}

	public void setCoeff(int coeff) {
		this.coeff = coeff;
	}

	public int getFront() {
		return front;
	}

	public void setFront(int front) {
		this.front = front;
	}

	public int getBack() {
		return back;
	}

	public void setBack(int back) {
		this.back = back;
	}
}
