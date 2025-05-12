package org.hercworks.core.data.file.dts.poly;

import org.hercworks.core.data.file.dts.TSObjectHeader;

public class TSTexture4Poly extends TSSolidPoly {

	
	public TSTexture4Poly() {
		super(TSObjectHeader.TS_TEXTURE4_POLY);
	}
	
	public TSTexture4Poly(TSObjectHeader hdr) {
		super(hdr);
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
		return str;
	}
}
