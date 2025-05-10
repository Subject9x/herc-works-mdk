package org.hercworks.core.data.struct;

/**
 * Utility class, proper Vec3 implmentation, kept internal here for some
 * portability.
 */
public class Vec3Dbl {

	private double x = 0;
	private double y = 0;
	private double z = 0;
	
	
	public Vec3Dbl() {}
	
	public Vec3Dbl(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}


	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}
	
	public double[] toDouble() {
		double[] val = new double[3];
	
		val[0] = getX() / 10;
		val[1] = getY() / 10;
		val[2] = getZ() / 10;
		
		return val;
	}

	@Override
	public String toString(){
		StringBuilder str = new StringBuilder();
		
		str.append("[")
			.append(getX())
			.append(", ")
			.append(getY())
			.append(", ")
			.append(getZ())
			.append("]");
		
		return str.toString();
	}

}
