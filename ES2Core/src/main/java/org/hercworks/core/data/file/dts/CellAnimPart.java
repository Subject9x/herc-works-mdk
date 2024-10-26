package org.hercworks.core.data.file.dts;

public class CellAnimPart extends PartList implements DTSSegment {

	private short animSequence = 0;
	
	public CellAnimPart() {}	

	public int getAnimSequence() {
		return animSequence;
	}

	public void setAnimSequence(short animSequence) {
		this.animSequence = animSequence;
	}

	@Override
	public DTSChunkTypes getSegType() {
		return DTSChunkTypes.CELL_ANIM_PART;
	}
}
