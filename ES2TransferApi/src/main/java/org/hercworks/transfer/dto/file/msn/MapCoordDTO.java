package org.hercworks.transfer.dto.file.msn;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * as observed in 'script.var',
 * there's a chunk of data for map coordinates, and the pre-processed MSN file
 * has option flags and ID's before each coordinate listed.
 * 
 * size: 22 BYTES
 * 
 * XXX- map coords are INT32 but represent fixed-point integers!
 * 
 */
@JsonRootName("mapCoord")
public class MapCoordDTO {

	private short id;
	private short unkFlag1;
	private short unkFlag2;
	private short unkFlag3;
	private short unkFlag4; //or possible spacer
	
	private double x;
	private double y;
	private double z;
	
	
	public MapCoordDTO() {}

	public short getId() {
		return id;
	}

	public short getUnkFlag1() {
		return unkFlag1;
	}

	public short getUnkFlag2() {
		return unkFlag2;
	}

	public short getUnkFlag3() {
		return unkFlag3;
	}

	public short getUnkFlag4() {
		return unkFlag4;
	}

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

	public void setUnkFlag1(short unkFlag1) {
		this.unkFlag1 = unkFlag1;
	}

	public void setUnkFlag2(short unkFlag2) {
		this.unkFlag2 = unkFlag2;
	}

	public void setUnkFlag3(short unkFlag3) {
		this.unkFlag3 = unkFlag3;
	}

	public void setUnkFlag4(short unkFlag4) {
		this.unkFlag4 = unkFlag4;
	}

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
	
}
