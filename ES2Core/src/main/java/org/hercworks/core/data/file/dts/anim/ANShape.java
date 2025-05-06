package org.hercworks.core.data.file.dts.anim;

import java.util.Arrays;

import org.hercworks.core.data.file.dts.TSObjectHeader;
import org.hercworks.core.data.file.dts.TSShape;
import org.hercworks.core.data.file.dts.part.TSPartList;

public class ANShape extends TSShape {

	private TSPartList part;
	
	private ANAnimList animations;
	
	
	public ANShape() {
		super(TSObjectHeader.AN_SHAPE);
	}
	
	public ANShape(TSObjectHeader hdr) {
		super(hdr);
	}

	public TSPartList getPart() {
		return part;
	}

	public void setPart(TSPartList part) {
		this.part = part;
	}
	
	public ANAnimList getAnimations() {
		return animations;
	}

	public void setAnimations(ANAnimList animations) {
		this.animations = animations;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		str.append(metaInfoString(getClass().getSimpleName()));
		str.append("\"transform\" : ").append(getTransform()).append(",\n");
		str.append("\"IDNumber\" : ").append(getIDNumber()).append(",\n");
		str.append("\"radius\" : ").append(getRadius()).append(",\n");
		str.append("\"center\" : ").append(getCenter().toString()).append(",\n");
		str.append("\"parts\" : [");
		for(int s=0; s < getParts().length; s++) {
			str.append(getParts()[s].toString());
			if(s < getParts().length - 1) {
				str.append(",");
			}
			str.append("\n");
		}
		str.append("],\n");
		
		str.append("\"seq1\" : ").append(Arrays.toString(getSequenceList())).append(",\n");
		
		str.append("\"rootPart\" : ").append(getPart().toString()).append("\n");		
		str.append("}\n");
		
		return str.toString();
	}
}
