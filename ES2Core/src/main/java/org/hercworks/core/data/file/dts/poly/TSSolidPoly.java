package org.hercworks.core.data.file.dts.poly;

import org.hercworks.core.data.file.dts.TSObjectHeader;
import org.hercworks.core.data.file.dts.TSPoly;

public class TSSolidPoly extends TSPoly {

	//unsigned
	private short colorIndexId;
	
	public TSSolidPoly() {
		super(TSObjectHeader.TS_SOLID_POLY);
	}
	
	public TSSolidPoly(TSObjectHeader hdr) {
		super(hdr);
	}

	public short getColorIndexId() {
		return colorIndexId;
	}

	public void setColorIndexId(short colorIndexId) {
		this.colorIndexId = colorIndexId;
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
		str.append("\"colorIndexId\" : ").append(getColorIndexId());
		
		return str;
	}
}
