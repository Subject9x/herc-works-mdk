package org.hercworks.core.data.file.dts.anim;

import org.hercworks.core.data.file.dts.DTSChunkTypes;
import org.hercworks.core.data.file.dts.DTSObject;
import org.hercworks.core.data.file.dts.DTSSegment;
import org.hercworks.core.data.file.dts.Shape;

public class AnimShape extends Shape implements DTSSegment {

	private DTSObject part = null;
	
	public AnimShape() {}
	
	public DTSObject getPart() {
		return part;
	}
	
	public void setPart(DTSObject part) {
		this.part = part;
	}
	
	@Override
	public DTSChunkTypes getSegType() {
		return DTSChunkTypes.ANIM_SHAPE;
	}
}
