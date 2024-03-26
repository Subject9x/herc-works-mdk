package org.hercworks.app.api;

/**
 * Dead-simple Vector2d with ints for gui.
 */
public class Vector2DInt {

	public int x = 0;
	public int y = 0;
	
	public Vector2DInt() {}
	
	public Vector2DInt(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
