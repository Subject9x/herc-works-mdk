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

		str = jsonString(str);
		
		str.append("\n");
		str.append("}\n");
		
		return str.toString();
	}
	
	@Override
	public StringBuilder jsonString(StringBuilder str) {
		
		str = super.jsonString(str);
		str.append(",\n");
		str.append("\"animSequence\" : ").append(getAnimSequence());
		
		return str;
	}
}
