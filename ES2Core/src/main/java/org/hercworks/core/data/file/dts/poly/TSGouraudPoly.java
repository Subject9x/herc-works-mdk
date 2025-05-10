package org.hercworks.core.data.file.dts.poly;

import org.hercworks.core.data.file.dts.TSObjectHeader;

public class TSGouraudPoly extends TSSolidPoly {

	private short normalList;
	
	public TSGouraudPoly() {
		super(TSObjectHeader.TS_GOURAUD_POLY);
	}
	
	public TSGouraudPoly(TSObjectHeader hdr) {
		super(hdr);
	}

	public short getNormalList() {
		return normalList;
	}

	public void setNormalList(short normalList) {
		this.normalList = normalList;
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
		str.append("\"color\" : ").append(getColor()).append(",\n");
		str.append("\"normalList\" : ").append(getNormalList());
		
		return str;
	}
}
