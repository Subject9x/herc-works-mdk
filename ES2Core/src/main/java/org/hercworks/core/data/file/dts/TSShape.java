package org.hercworks.core.data.file.dts;

import java.util.Arrays;

import org.hercworks.core.data.file.dts.part.TSPartList;

public class TSShape extends TSPartList {

	//FIXME - transformLIst !?
	private short[] transformList;
	
	private short[] sequenceList;

	private TSChunk[] extraParts;
	
	public TSShape() {
		super(TSChunkHeader.TS_SHAPE);
	}
	
	public TSShape(TSChunkHeader hdr) {
		super(hdr);
	}

	public short[] getSequenceList() {
		return sequenceList;
	}

	public void setSequenceList(short[] seqList1) {
		this.sequenceList = seqList1;
	}

	public short[] getTransformList() {
		return transformList;
	}

	public void setTransformList(short[] transformList) {
		this.transformList = transformList;
	}
	
	public TSChunk[] getExtraParts() {
		return extraParts;
	}

	public void setExtraParts(TSChunk[] extraParts) {
		this.extraParts = extraParts;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		str.append(metaInfoString(getClass().getSimpleName()));
		str.append("\"transform\" : ").append(getTransform()).append(",\n");
		str.append("\"IDNumber\" : ").append(getIDNumber()).append(",\n");
		str.append("\"radius\" : ").append(getRadius()).append(",\n");
		str.append("\"center\" : ").append((getCenter().toString())).append(",\n");
		str.append("\"parts\" : [\n");
		for(int s=0; s < getParts().length; s++) {
			str.append(getParts()[s].toString());
			if(s < getParts().length - 1) {
				str.append(",");
			}
			str.append("\n");
		}
		str.append("],\n");
		
		str.append("\"sequenceList\" : ").append(Arrays.toString(getSequenceList())).append("\n");
		
//		str.append("\"extra_parts\" : [");
//		for(int s=0; s < getExtraParts().length; s++) {
//			str.append(getExtraParts()[s].toString());
//			if(s < getExtraParts().length - 1) {
//				str.append(",");
//			}
//			str.append("\n");
//		}
//		str.append("]\n");
		
		
		str.append("}\n");
		
		return str.toString();
	}
}
