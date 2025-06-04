package org.hercworks.transfer.dto.file.sim.dts;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("")
public class TSSurfaceEntryDTO {

	@JsonProperty(required = false, index = 0)
	private int index;
	
	@JsonProperty(index = 1)
	private Color front;
	
	@JsonProperty(index = 2)
	private Color back;
	
	public TSSurfaceEntryDTO() {}
	
	public Color getFront() {
		return front;
	}

	public void setFront(Color front) {
		this.front = front;
	}

	public Color getBack() {
		return back;
	}

	public void setBack(Color back) {
		this.back = back;
	}

	public Color newSurfaceColor(int clr, int flag, int edge, int edgeFlag) {
		return new Color(clr, flag, edge, edgeFlag);
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	
	
	@JsonRootName("")
	public class Color{
		private int color;
		private int flag;
		private int edgeColor;
		private int edgeFlag;
		
		public Color() {}
		
		public Color(int clr, int flag, int edge, int edgeFlag) {
			this.color = clr;
			this.flag = flag;
			this.edgeColor = edge;
			this.edgeFlag = edgeFlag;
		}


		public int getColor() {
			return color;
		}

		public void setColor(int color) {
			this.color = color;
		}

		public int getFlag() {
			return flag;
		}

		public void setFlag(int flag) {
			this.flag = flag;
		}

		public int getEdgeColor() {
			return edgeColor;
		}

		public void setEdgeColor(int edgeColor) {
			this.edgeColor = edgeColor;
		}

		public int getEdgeFlag() {
			return edgeFlag;
		}

		public void setEdgeFlag(int edgeFlag) {
			this.edgeFlag = edgeFlag;
		}
	}
}
