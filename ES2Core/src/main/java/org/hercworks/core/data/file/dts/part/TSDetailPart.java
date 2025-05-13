package org.hercworks.core.data.file.dts.part;

import java.util.Arrays;

import org.hercworks.core.data.file.dts.TSObjectHeader;
import org.hercworks.core.data.file.dts.TSRootObject;

public class TSDetailPart extends TSPartList implements TSRootObject{

	private short[] details;
	
	
	public TSDetailPart() {
		super(TSObjectHeader.TS_DETAIL_PART);
	}
	
	
	public TSDetailPart(TSObjectHeader hdr) {
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
		
		str = jsonString(str);
		
		str.append("\n");
		str.append("}\n");
		
		return str.toString();
	}

	@Override
	public StringBuilder jsonString(StringBuilder str) {
		
		str = super.jsonString(str);
		
		str.append(",\n");
		str.append("\"details\" : ").append(Arrays.toString(getDetails()));
		
		return str;
	}
}
