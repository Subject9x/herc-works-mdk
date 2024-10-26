package org.hercworks.core.data.file.dts;

public class BMPPart extends BasePart implements DTSSegment {

	private short bmpTag;
	private byte ofs_x;
	private byte ofs_y;
	
	public BMPPart() {}
	
	public short getBmpTag() {
		return bmpTag;
	}

	public byte getOfs_x() {
		return ofs_x;
	}

	public byte getOfs_y() {
		return ofs_y;
	}

	public void setBmpTag(short bmpTag) {
		this.bmpTag = bmpTag;
	}

	public void setOfs_x(byte ofs_x) {
		this.ofs_x = ofs_x;
	}

	public void setOfs_y(byte ofs_y) {
		this.ofs_y = ofs_y;
	}

	@Override
	public DTSChunkTypes getSegType() {
		return DTSChunkTypes.BMP_PART;
	}
}
