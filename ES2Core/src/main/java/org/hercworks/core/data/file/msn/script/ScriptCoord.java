package org.hercworks.core.data.file.msn.script;


/**
 * Truncated version of {@linkplain MapCoord} objects seen in .MSN files
 */
public class ScriptCoord {
	
	private int x;
	
	private int y;
	
	private int z;
	
	public ScriptCoord() {}
	
	public ScriptCoord(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
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

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}
	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("{\n")
			.append(" 'class' : '").append(getClass().getSimpleName()).append(",\n")
			.append(" 'x' : '").append(getX()).append("',\n")
			.append(" 'y' : '").append(getY()).append("',\n")
			.append(" 'z' : '").append(getZ()).append("'\n}")
		;
		
		
		return sb.toString();
	}

}
