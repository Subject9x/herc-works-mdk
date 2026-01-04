package org.hercworks.core.data.file.msn;

import java.util.Arrays;

/**
 * Seen towards end of map script
 * TRAIN5 has 13 of these.
 * 
 */
public class UnkEntity164Bytes extends MapObject{

	private short[] flags;	//len 24
	
	private MapCoord mapCoord = null;
	
	private UnkEntity10Byte unkEntity10Byte = null;
	
	private UnkEntity16Byte unkEntity16Byte = null;
	
	private MapObject[] mapEntities;	//len 20
	
	private UnkEntity22Byte unkEntity22Byte = null;
	
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

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("\n{\n")
		.append("	guid = ").append(getGUID()).append("\n")
		.append(" 	flags = ").append(Arrays.toString(getFlags())).append("\n")
		.append(" 	mapCoord = ").append(getMapCoord() == null ? "-1" : getMapCoord().toString()).append("\n")
		.append(" 	unk10 = ").append(getUnkEntity10Byte() == null ? "-1" : getUnkEntity10Byte().toString()).append("\n")
		.append(" 	unk16 = ").append(getUnkEntity16Byte() == null ? "-1" : (getUnkEntity16Byte().toString())).append("\n")
		.append(" 	entities = ").append(Arrays.toString(getMapEntities())).append("\n")
		.append(" 	unk22 = ").append(getUnkEntity22Byte() == null ? "-1" :getUnkEntity22Byte().toString() ).append("\n")
		.append("	vals = ").append(Arrays.toString(getValues()))
//		for(int v=0; v < getValues().length; v++) {
//			sb.append("    ").append(v).append(" = ").append(getValues()[v]).append("\n");
//		}
		
		.append("\n")
		.append("}\n");
		
		return sb.toString();
	}
	
}
