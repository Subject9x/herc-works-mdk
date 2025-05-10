package org.hercworks.core.data.file.dts;

import java.util.Arrays;

import org.hercworks.core.data.file.dts.part.TSPartList;

public class TSShape extends TSPartList {
	
	private short[] sequenceList;

	private short[] transformList;
	
	//FIXME: not sure if this is needed
//	private TSObject[] extraParts;
	
	public TSShape() {
		super(TSObjectHeader.TS_SHAPE);
	}
	
	public TSShape(TSObjectHeader hdr) {
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
	
//	public TSObject[] getExtraParts() {
//		return extraParts;
//	}
//
//	public void setExtraParts(TSObject[] extraParts) {
//		this.extraParts = extraParts;
//	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		str.append(metaInfoString(getClass().getSimpleName()));

		str = jsonString(str);
		str.append("\n");
		str.append("}\n");
		
		return str.toString();
	}

	@Override
	public StringBuilder jsonString(StringBuilder str) {
		
		str = super.jsonString(str);
		
		str.append(",\n");
		str.append("\"sequences\" : ").append(Arrays.toString(getSequenceList())).append(",\n");
		str.append("\"transforms\" : ").append(Arrays.toString(getSequenceList()));
		
		return str;
	}
}
