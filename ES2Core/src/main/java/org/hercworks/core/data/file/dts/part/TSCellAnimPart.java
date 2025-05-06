package org.hercworks.core.data.file.dts.part;

import org.hercworks.core.data.file.dts.TSObjectHeader;

/**
 * FIXME - testing in /BULLETS.DTS, the tail bytes look very much like a TSShape segment as the direct inherit vs a TSPartList,
 *	TSShape inherits directly from TSPartList, so other sources aren't too far off.
 *
 *	will test other files.
 */
public class TSCellAnimPart extends TSPartList {

	//unsigned
	private short animSequence;
	
	public TSCellAnimPart() {
		super(TSObjectHeader.TS_CELL_ANIM_PART);
	}
	
	public TSCellAnimPart(TSObjectHeader hdr) {
		super(hdr);
	}

	public short getAnimSequence() {
		return animSequence;
	}

	public void setAnimSequence(short animSequence) {
		this.animSequence = animSequence;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		str.append(metaInfoString(getClass().getSimpleName()));
		str.append("\"transform\" : ").append(String.valueOf(getTransform())).append(",\n");
		str.append("\"IDNumber\" : ").append(String.valueOf(getIDNumber())).append(",\n");
		str.append("\"radius\" : ").append(String.valueOf(getRadius())).append(",\n");
		str.append("\"center\" : ").append(String.valueOf(getCenter().toString())).append(",\n");
		str.append("\"parts\" : [\n");
		for(int s=0; s < getParts().length; s++) {
			str.append(getParts()[s].toString());
			if(s < getParts().length - 1) {
				str.append(",");
			}
			str.append("\n");
		}
		str.append("],\n");
		
		str.append("\"animSequence\" : ").append(getAnimSequence()).append("\n");
		
		str.append("}\n");
		
		return str.toString();
	}
}
