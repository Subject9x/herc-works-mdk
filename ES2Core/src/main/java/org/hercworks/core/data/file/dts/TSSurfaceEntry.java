package org.hercworks.core.data.file.dts;

/**
 * UTILITY
 * 
 * used in {@linkplain TSGroup}, 'colors' here really are 4 config options expressed as flags.
 * first int is 'surface' color/shade, but for TSTexture4Poly, this is read as DBA frame num.
 * second int is 'outline' or 'edge' color, but only for TSShadedPoly.
 * 
 * the 'flags' for each entry are also unknown, TSTexture4Poly cannot have any flags, then
 * TSShadedPoly have 1024 for front, and 5120 for back.
 * interesting.
 */
public class TSSurfaceEntry {

	private short frontColor;
	private short frontFlag;
	
	private short frontLineColor;
	private short frontLineFlag;
	
	private short backColor;
	private short backColorFlag;

	private short backLineColor;
	private short backLineFlag;
	
	public TSSurfaceEntry() {}
	
	

	public short getFrontColor() {
		return frontColor;
	}



	public void setFrontColor(short frontColor) {
		this.frontColor = frontColor;
	}



	public short getFrontFlag() {
		return frontFlag;
	}



	public void setFrontFlag(short frontFlag) {
		this.frontFlag = frontFlag;
	}



	public short getFrontLineColor() {
		return frontLineColor;
	}



	public void setFrontLineColor(short frontLineColor) {
		this.frontLineColor = frontLineColor;
	}

	public short getFrontLineFlag() {
		return frontLineFlag;
	}

	public void setFrontLineFlag(short frontLineFlag) {
		this.frontLineFlag = frontLineFlag;
	}

	public short getBackColor() {
		return backColor;
	}

	public void setBackColor(short backColor) {
		this.backColor = backColor;
	}

	public short getBackColorFlag() {
		return backColorFlag;
	}

	public void setBackColorFlag(short backColorFlag) {
		this.backColorFlag = backColorFlag;
	}

	public short getBackLineColor() {
		return backLineColor;
	}

	public void setBackLineColor(short backLineColor) {
		this.backLineColor = backLineColor;
	}

	public short getBackLineFlag() {
		return backLineFlag;
	}

	public void setBackLineFlag(short backLineFlag) {
		this.backLineFlag = backLineFlag;
	}


	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		str.append("{");
		
		str.append("\"frontColor\" : ").append(getFrontColor()).append(",\n");
		str.append("\"frontFlag\" : ").append(getFrontFlag()).append(",\n");
		str.append("\"frontEdgeColor\" : ").append(getFrontLineColor()).append(",\n");
		str.append("\"frontEdgeFlag\" : ").append(getFrontLineFlag()).append(",\n");
		str.append("\"backColor\" : ").append(getBackColor()).append(",\n");
		str.append("\"backFlag\" : ").append(getBackColorFlag()).append(",\n");
		str.append("\"backEdgeColor\" : ").append(getBackLineColor()).append(",\n");
		str.append("\"backEdgeFlag\" : ").append(getBackLineFlag()).append("\n");
		
		str.append("}\n");
		
		return str.toString();
	}
}
