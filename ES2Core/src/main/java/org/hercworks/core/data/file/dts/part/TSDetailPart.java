package org.hercworks.core.data.file.dts.part;

import java.util.Arrays;

import org.hercworks.core.data.file.dts.TSChunkHeader;

public class TSDetailPart extends TSPartList {

	private short[] details;
	
	
	public TSDetailPart() {
		super(TSChunkHeader.TS_DETAIL_PART);
	}
	
	
	public TSDetailPart(TSChunkHeader hdr) {
		super(hdr);
	}


	public short[] getDetails() {
		return details;
	}


	public void setDetails(short[] details) {
		this.details = details;
	}	
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		str.append(metaInfoString(getClass().getSimpleName()));
		str.append("\"transform\" : ").append(getTransform()).append(",\n");
		str.append("\"IDNumber\" : ").append(getIDNumber()).append(",\n");
		str.append("\"radius\" : ").append(getRadius()).append(",\n");
		str.append("\"center\" : ").append(getCenter().toString()).append("\n");
		
		str.append("\"parts\" : [\n");
		for(int s=0; s < getParts().length; s++) {
			str.append(getParts()[s].toString());
			if(s < getParts().length - 1) {
				str.append(",");
			}
			str.append("\n");
		}
		str.append("],\n");

		str.append("\"details\" : ").append(Arrays.toString(getDetails())).append("\n");
		
		str.append("}\n");
		
		return str.toString();
	}
	
}
