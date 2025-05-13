package org.hercworks.core.data.file.dts.bsp;

import org.hercworks.core.data.file.dts.TSGroup;
import org.hercworks.core.data.file.dts.TSObjectHeader;
import org.hercworks.core.data.file.dts.TSRootObject;

public class TSBSPGroup extends TSGroup implements TSRootObject{

	private TSBSPGroupNode[] groupNodes;
	
	public TSBSPGroup() {
		super(TSObjectHeader.TS_BSP_GROUP);
	}
	
	public TSBSPGroup(TSObjectHeader hdr) {
		super(hdr);
	}

	public TSBSPGroupNode[] getGroupNodes() {
		return groupNodes;
	}

	public void setGroupNodes(TSBSPGroupNode[] groupNodes) {
		this.groupNodes = groupNodes;
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
		str.append("\"bsp_nodes\" : [\n");
		for(int n=0; n < getGroupNodes().length; n++) {
			str.append(getGroupNodes()[n].toString());
			if(n < getGroupNodes().length - 1) {
				str.append(",");
			}
			str.append("\n");
		}
		str.append("]");
		
		return str;
	}
}
