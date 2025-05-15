package org.hercworks.core.data.file.dts.poly;

import org.hercworks.core.data.file.dts.TSObjectHeader;

/**
 * An untextured poly, assigned an indexed palette color based on in-game light calculations.
 * 
 */
public class TSShadedPoly extends TSSolidPoly {
	
	public TSShadedPoly() {
		super(TSObjectHeader.TS_SHADED_POLY);
	}
	
	public TSShadedPoly(TSObjectHeader hdr) {
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
