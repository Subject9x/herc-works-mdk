package org.hercworks.core.data.file.dts;

import java.util.Arrays;

public enum TSObjectHeader {

	TS_POLY(new byte[]{0x01,0x00,0x14,0x00}, "TSPoly"),
	
	TS_SOLID_POLY(new byte[]{0x02,0x00,0x14,0x00}, "TSSolidPoly"),
	
	TS_SHADED_POLY(new byte[]{0x03,0x00,0x14,0x00}, "TSShadedPoly"),
	
	TS_BASE_PART(new byte[]{0x05,0x00,0x14,0x00}, "TSBasePart"),
	
	TS_PART_LIST(new byte[]{0x07,0x00,0x14,0x00}, "TSPartList"),
	
	TS_SHAPE(new byte[]{0x08,0x00,0x14,0x00}, "TSShape"),

	TS_GOURAUD_POLY(new byte[] {0x09,0x00,0x14,0x00}, "TSGouraudPoly"),
	
	TS_BSP_GROUP(new byte[] {0x0a,0x00,0x14,0x00}, "TSBSPGroup"),
	
	TS_CELL_ANIM_PART(new byte[]{0x0b,0x00,0x14,0x00}, "TSCellAnimPart"),
	
	TS_DETAIL_PART(new byte[] {0x0c,0x00,0x14,0x00}, "TSDetailPart"),
	
	TS_TEXTURE4_POLY(new byte[]{0x0f,0x00,0x14,0x00}, "TSTexture4Poly"),

	TS_GROUP(new byte[]{0x14,0x00,0x14,0x00}, "TSGroup"),
	
	ALIAS_SOLID_POLY(new byte[] {0x10,0x00,0x14,0x00}, "TSAliasSolidPoly"),
	
	ALIAS_SHADED_POLY(new byte[] {0x11,0x00,0x14,0x00}, "TSAliasShadedPoly"),
	
	ALIAS_GOURAUD_POLY(new byte[] {0x12,0x00,0x14,0x00}, "TSAliasGouraudPoly"),
	
	TS_BITMAP_PART(new byte[] {0x13,0x00,0x14,0x00}, "TSBitmapPart"),
	
	BSP_PART(new byte[]{0x15,0x00,0x14,0x00}, "TSBSPPart"),
	
	AN_SEQUENCE(new byte[]{0x01,0x00,0x1e,0x00}, "ANSequence"),
	
	AN_ANIM_LIST(new byte[]{0x02,0x00,0x1e,0x00}, "ANAnimList"),
	
	AN_SHAPE(new byte[]{0x03,0x00,0x1e,0x00}, "ANShape"),
	
	AN_CYCLIC_SEQUENCE(new byte[]{0x04,0x00,0x1e,0x00}, "ANCyclicSequence")
	;	
	
	private final byte[] val;
	private final String id;
	
	private TSObjectHeader(byte[] val, String id) {
		this.val = val;
		this.id = id;
	}
	
	public byte[] val() {
		return this.val;
	}
	
	public String id() {
		return this.id;
	}
	
	public static TSObjectHeader findVal(byte[] marker) {
		for(TSObjectHeader hdr : TSObjectHeader.values()) {
			if(Arrays.equals(hdr.val(), marker)) {
				return hdr;
			}
		}
		return null;
	}
}
