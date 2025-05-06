package org.hercworks.core.data.file.dts;

import java.util.Arrays;

import org.hercworks.core.data.file.dts.part.TSPartList;

public class TSShape extends TSPartList {
	
	
	private short transformTotal;
	private short sequenceTotal;
	
	//FIXME - transformLIst !?	
	private short[] transformList;
	
	private short[] sequenceList;

	private TSObject[] extraParts;
	
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
	
	public TSObject[] getExtraParts() {
		return extraParts;
	}

	public void setExtraParts(TSObject[] extraParts) {
		this.extraParts = extraParts;
	}

	
	
	
	public short getTransformTotal() {
		return transformTotal;
	}

	public void setTransformTotal(short transformTotal) {
		this.transformTotal = transformTotal;
	}

	public short getSequenceTotal() {
		return sequenceTotal;
	}

	public void setSequenceTotal(short sequenceTotal) {
		this.sequenceTotal = sequenceTotal;
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
		

		str.append("\"seq_list\" : ").append(Arrays.toString(getSequenceList())).append(",\n");
		str.append("\"trns_list\" : ").append(Arrays.toString(getTransformList())).append("\n");
		
//		str.append("\"sequenceList\" : ").append(Arrays.toString(getSequenceList())).append("\n");
		
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
