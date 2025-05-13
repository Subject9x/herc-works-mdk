package org.hercworks.core.data.file.dts.part;

import org.hercworks.core.data.file.dts.TSBasePart;
import org.hercworks.core.data.file.dts.TSObjectHeader;
import org.hercworks.core.data.file.dts.TSRootObject;

/**
 * NOTES:
 * 	+ ThreeSpace2 engine somehow already knows the target DBA to bind these too.
 *  + ThreeSpace2 engine knows to generate a basic textured quad which is the destination.
 *  + TODO - make sure this is exported to a basic quad but not sure how to find matching material.
 */
public class TSBitmapPart extends TSBasePart implements TSRootObject{
	
	private short bmpTag;
	
	private byte ofs_x;
	
	private byte ofs_y;
	
	
	public TSBitmapPart() {
		super(TSObjectHeader.TS_BITMAP_PART);
	}
	
	public TSBitmapPart(TSObjectHeader hdr) {
		super(hdr);
	}

	public short getBmpTag() {
		return bmpTag;
	}

	public void setBmpTag(short bmpTag) {
		this.bmpTag = bmpTag;
	}

	public short getOfsX() {
		return ofs_x;
	}

	public void setOfsX(byte rotation) {
		this.ofs_x = rotation;
	}

	public short getOfsY() {
		return ofs_y;
	}

	public void setOfsY(byte compress) {
		this.ofs_y = compress;
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
		str.append("\"bmp_tag\" : ").append(getBmpTag()).append(",\n");
		str.append("\"ofs_x\" : ").append(getOfsX()).append(",\n");
		str.append("\"ofs_y\" : ").append(getOfsY());
		
		return str;
	}
}
