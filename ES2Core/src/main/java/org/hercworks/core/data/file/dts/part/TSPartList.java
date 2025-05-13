package org.hercworks.core.data.file.dts.part;

import org.hercworks.core.data.file.dts.TSBasePart;
import org.hercworks.core.data.file.dts.TSObject;
import org.hercworks.core.data.file.dts.TSObjectHeader;
import org.hercworks.core.data.file.dts.TSRootObject;

public class TSPartList extends TSBasePart implements TSRootObject{

	private TSObject[] parts;
	
	public TSPartList() {
		super(TSObjectHeader.TS_PART_LIST);
	}
	
	public TSPartList(TSObjectHeader hdr) {
		super(hdr);
	}

	public TSObject[] getParts() {
		return parts;
	}

	public void setParts(TSObject[] parts) {
		this.parts = parts;
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
		str.append("\"parts\" : [\n");
		for(int s=0; s < getParts().length; s++) {
			str.append(getParts()[s].toString());
			if(s < getParts().length - 1) {
				str.append(",");
			}
			str.append("\n");
		}
		str.append("]");
		
		return str;
	}
	
}
