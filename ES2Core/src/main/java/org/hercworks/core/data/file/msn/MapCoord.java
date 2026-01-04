package org.hercworks.core.data.file.msn;

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
public class MapCoord {

	private short id;
	private short unkFlag1;
	private short unkFlag2;
	private short unkFlag3;
	private short unkFlag4; //or possible spacer
	
	private int x;
	private int y;
	private int z;
	
	
	public MapCoord() {}
	
	public MapCoord(short id, short f1, short f2, short f3, short f4, int vx, int vy, int vz) {
		this.id = id;
		this.unkFlag1 = f1;
		this.unkFlag2 = f2;
		this.unkFlag3 = f3;
		this.unkFlag4 = f4;
		this.x = vx;
		this.y = vy;
		this.z = vz;
	}

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

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
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

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setZ(int z) {
		this.z = z;
	}
	
	public static double fixedInt(int f) {
		return f/1000;
	}
	
	@Override
	public String toString() {
		StringBuilder b =new StringBuilder();
		b.append(getId()).append(" = { ").append(getUnkFlag1()).append(", ")
						.append(getUnkFlag2()).append(", ").append(getUnkFlag3()).append(", ")
						.append(getUnkFlag4()).append(", (")
						.append(fixedInt(getX())).append(", ").append(fixedInt(getY())).append(", ").append(fixedInt(getZ()))
						.append(")}");
		
		return b.toString();
	}
}
