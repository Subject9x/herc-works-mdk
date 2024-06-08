package org.hercworks.core.data.struct;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum MissileType {

	SARH(0, "Semi-Active Radar", "SARH"),
	ARH(1, "Active-Radar Homing", "ARH"),
	ARM(2, "Anti-Rad", "ARM"),
	EO(3, "Electro-Optical", "EO"),
	BMSL(4, "Big Missile", "BMSL"),
	NONE(5, "NONE", "NONE");
	

	private static final Map<Integer, MissileType> ENUM_MAP;
	
	private int id;
	private String abbrev;
	private String name;
	
	
	private MissileType(int id, String name, String abbrev) {
		this.id = id;
		this.name = name;
		this.abbrev = abbrev;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getAbbrev() {
		return abbrev;
	}
	
	static {
	    Map<Integer,MissileType> map = new HashMap<Integer, MissileType>();
	    for (MissileType instance : MissileType.values()) {
	        map.put(instance.getId(),instance);
	    }
	    ENUM_MAP = Collections.unmodifiableMap(map);
	}
	
    public static MissileType getById(int id) {
        return ENUM_MAP.get(id);
    }
	
	
}
