package org.hercworks.transfer.dto.file.msn;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * in-line reference without the root name required.
 * 
 * useful for debugging atm but not required.
 */
@JsonRootName("")
public class MapCoordRefDTO {
	private short id;
//	private short unkFlag1;
//	private short unkFlag2;
//	private short unkFlag3;
//	private short unkFlag4; //or possible spacer
	
	private double x;
	private double y;
	private double z;
	
	
	public MapCoordRefDTO() {}

	public short getId() {
		return id;
	}

//	public short getUnkFlag1() {
//		return unkFlag1;
//	}
//
//	public short getUnkFlag2() {
//		return unkFlag2;
//	}
//
//	public short getUnkFlag3() {
//		return unkFlag3;
//	}
//
//	public short getUnkFlag4() {
//		return unkFlag4;
//	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getZ() {
		return z;
	}

	public void setId(short id) {
		this.id = id;
	}

//	public void setUnkFlag1(short unkFlag1) {
//		this.unkFlag1 = unkFlag1;
//	}
//
//	public void setUnkFlag2(short unkFlag2) {
//		this.unkFlag2 = unkFlag2;
//	}
//
//	public void setUnkFlag3(short unkFlag3) {
//		this.unkFlag3 = unkFlag3;
//	}
//
//	public void setUnkFlag4(short unkFlag4) {
//		this.unkFlag4 = unkFlag4;
//	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setZ(double z) {
		this.z = z;
	}
	
	public static double fixedInt(int f) {
		return f/1000;
	}
	
	public static MapCoordRefDTO init(MapCoordDTO coordDto) {
		MapCoordRefDTO ref = new MapCoordRefDTO();
		
		ref.setId(coordDto.getId());
//		ref.setUnkFlag1(coordDto.getUnkFlag1());
//		ref.setUnkFlag2(coordDto.getUnkFlag2());
//		ref.setUnkFlag3(coordDto.getUnkFlag3());
//		ref.setUnkFlag4(coordDto.getUnkFlag4());
		
		ref.setX(coordDto.getX());
		ref.setY(coordDto.getY());
		ref.setZ(coordDto.getZ());

		return ref;
	}
	
}
