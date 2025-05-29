package org.hercworks.core.data.file.dts.anim;

import org.hercworks.core.data.file.dts.TSObjectHeader;
import org.hercworks.core.data.file.dts.TSShape;

public class ANShape extends TSShape {
	
	private ANAnimList animationList;
	
	public ANShape() {
		super(TSObjectHeader.AN_SHAPE);
	}
	
	public ANShape(TSObjectHeader hdr) {
		super(hdr);
	}
	
	public ANAnimList getAnimationList() {
		return animationList;
	}

	public void setAnimationList(ANAnimList animationList) {
		this.animationList = animationList;
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
		str.append("\"animations\" : ").append(getAnimationList().toString());
		
		return str;
	}
}
