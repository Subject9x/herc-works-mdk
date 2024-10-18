package org.hercworks.core.data.struct;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum ProjectileType {

	MISSILE("MISSILE", (short)0),
	BULLET("BULLET", (short)2),
	ROCKET("ROCKET", (short)3),
	BEAM("BEAM", (short)4);
	
	private String type;
	private short bit;
	
	private static final Map<Short,ProjectileType> ENUM_MAP;
	
	private ProjectileType(String type, short bit) {
		this.type = type;
		this.bit = bit;
	}
	
	static {
	    Map<Short,ProjectileType> map = new HashMap<Short, ProjectileType>();
	    for (ProjectileType instance : ProjectileType.values()) {
	        map.put(instance.val(),instance);
	    }
	    ENUM_MAP = Collections.unmodifiableMap(map);
	}
	
	@Override
	public String toString() {
		return this.type;
	}
	
	public short val() {
		return this.bit;
	}
	
	public static ProjectileType forId(short id) {
		return ProjectileType.ENUM_MAP.get(id);
	}
}
