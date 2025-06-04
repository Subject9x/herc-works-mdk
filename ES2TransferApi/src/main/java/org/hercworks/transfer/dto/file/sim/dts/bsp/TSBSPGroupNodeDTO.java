package org.hercworks.transfer.dto.file.sim.dts.bsp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("")
public class TSBSPGroupNodeDTO {

	@JsonProperty(required = false, index = 0)
	private int index;
	
	@JsonProperty(index = 1)
	private int coeff;
	
	@JsonProperty(index = 2)
	private int polyNum;
	
	@JsonProperty(index = 3)
	private int front;
	
	@JsonProperty(index = 4)
	private int back;
	
	public TSBSPGroupNodeDTO() {}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getCoeff() {
		return coeff;
	}

	public void setCoeff(int coeff) {
		this.coeff = coeff;
	}

	public int getPolyNum() {
		return polyNum;
	}

	public void setPolyNum(int polyNum) {
		this.polyNum = polyNum;
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
