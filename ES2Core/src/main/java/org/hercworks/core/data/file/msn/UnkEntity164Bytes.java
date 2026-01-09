package org.hercworks.core.data.file.msn;

import java.util.Arrays;

/**
 * Seen towards end of map script
 * TRAIN5 has 13 of these.
 * 
 */
public class UnkEntity164Bytes extends MapObject{

	/**
	 * 0 = -1
	 * 1 = -1
	 * 2 = 1	// seems to toggle base ground texture?
	 * 3 =
	 * 4 =
	 * 5 =
	 * 6 = 
	 * 7 = 
	 * 8 = 
	 */
	private short[] flags;	//len 22

	//last val of '2' seen for bases but not unit spawns,
	// 2 is the only val for bases, crashes otherwise
	private short layoutType;
		//2 = bases
		//0 = units?
	
	/*	
	 *  0 - 4 : off-map, unknown config
	 *  5 - exists
	 *  6 : off-map, unknown config
	 *  7 - exists
	 *  8 - exists
	 *  9 - exists
	 *  10 - exists
	 *  11 - exists
	 *  12 - exists
	 *  13 - exists
	 *  14 - exists
	 *  15 - exists
	 *  16 - exists
	 *  17 - exists
	 *  18 - crash
	 *  19 - crash
	 */
	private short layoutId;	//
	
	private MapCoord mapCoord = null;
	
	private UnkEntity10Byte unkEntity10Byte = null;
	private short unk10ByteId;
	
	private UnkEntity16Byte unkEntity16Byte = null;
	private short unk16ByteId;
	
	private MapObject[] mapEntities;	//len 20
	private short[] mapEntIds;	//the raw array of shorts.
	
	private UnkEntity22Byte unkEntity22Byte = null;
	private short unk22ByteId;
	
	private short[] values;	//len 33
	
	public UnkEntity164Bytes() {}


	
	public short[] getFlags() {
		return flags;
	}

	public void setFlags(short[] flags) {
		this.flags = flags;
	}
	

	public MapCoord getMapCoord() {
		return mapCoord;
	}

	public void setMapCoord(MapCoord mapCoord) {
		this.mapCoord = mapCoord;
	}

	public UnkEntity10Byte getUnkEntity10Byte() {
		return unkEntity10Byte;
	}

	public void setUnkEntity10Byte(UnkEntity10Byte unkEntity10Byte) {
		this.unkEntity10Byte = unkEntity10Byte;
	}

	public UnkEntity16Byte getUnkEntity16Byte() {
		return unkEntity16Byte;
	}


	public void setUnkEntity16Byte(UnkEntity16Byte unkEntity16Byte) {
		this.unkEntity16Byte = unkEntity16Byte;
	}


	public MapObject[] getMapEntities() {
		return mapEntities;
	}

	public void setMapEntities(MapObject[] mapEntities) {
		this.mapEntities = mapEntities;
	}

	public UnkEntity22Byte getUnkEntity22Byte() {
		return unkEntity22Byte;
	}

	public void setUnkEntity22Byte(UnkEntity22Byte unkEntity22Byte) {
		this.unkEntity22Byte = unkEntity22Byte;
	}

	public short[] getValues() {
		return values;
	}

	public void setValues(short[] values) {
		this.values = values;
	}

	public short getLayoutId() {
		return layoutId;
	}

	public void setLayoutId(short layoutId) {
		this.layoutId = layoutId;
	}

	public short getLayoutType() {
		return layoutType;
	}

	public void setLayoutType(short layoutType) {
		this.layoutType = layoutType;
	}	
	
	public short[] getMapEntIds() {
		return mapEntIds;
	}

	public void setMapEntIds(short[] mapEntIds) {
		this.mapEntIds = mapEntIds;
	}

	public short getUnk10ByteId() {
		return unk10ByteId;
	}

	public void setUnk10ByteId(short unk10ByteId) {
		this.unk10ByteId = unk10ByteId;
	}

	public short getUnk16ByteId() {
		return unk16ByteId;
	}

	public void setUnk16ByteId(short unk16ByteId) {
		this.unk16ByteId = unk16ByteId;
	}

	public short getUnk22ByteId() {
		return unk22ByteId;
	}

	public void setUnk22ByteId(short unk22ByteId) {
		this.unk22ByteId = unk22ByteId;
	}



	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("\n{\n")
		.append("	guid = ").append(getGUID()).append("\n")
		.append(" 	flags = ").append(Arrays.toString(getFlags())).append("\n")
		.append("	layoutType = ").append(getLayoutType()).append("\n")
		.append("	layoutId = ").append(getLayoutId()).append("\n")
		.append(" 	mapCoord = ").append(getMapCoord() == null ? "-1" : getMapCoord().toString()).append("\n")
//		.append(" 	unk10 = ").append(getUnkEntity10Byte() == null ? "-1" : getUnkEntity10Byte().toString()).append("\n")
//		.append(" 	unk16 = ").append(getUnkEntity16Byte() == null ? "-1" : (getUnkEntity16Byte().toString())).append("\n")
		.append(" 	unk10? = ").append(getUnk10ByteId()).append("\n")
		.append(" 	unk16? = ").append(getUnk16ByteId()).append("\n")
		.append(" 	entities = ").append(Arrays.toString(getMapEntities())).append("\n")
//		.append(" 	unk22 = ").append(getUnkEntity22Byte() == null ? "-1" :getUnkEntity22Byte().toString() ).append("\n")
		.append(" 	unk22? = ").append(getUnk22ByteId()).append("\n")
		.append("	vals = ").append(Arrays.toString(getValues()))
//		for(int v=0; v < getValues().length; v++) {
//			sb.append("    ").append(v).append(" = ").append(getValues()[v]).append("\n");
//		}
		
		.append("\n")
		.append("}\n");
		
		return sb.toString();
	}
	
	
}
