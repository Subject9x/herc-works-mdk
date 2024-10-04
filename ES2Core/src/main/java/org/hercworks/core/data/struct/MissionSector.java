package org.hercworks.core.data.struct;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum MissionSector {
	RAZR("RAZR", (short)5),
	ALPHA("ALPHA", (short)0),
	DELTA("DELTA", (short)1),
	OMICR("OMICRON", (short)2),
	BRAVO("BRAVO", (short)3),
	LUNA("LUNA", (short)4);
	
	private String val;
	private short id;
	
	private static final Map<Integer,MissionSector> ENUM_MAP;
	
	private MissionSector(String val, short id) {
		this.val = val;
		this.id = id;
	}
	
	
	static {
	    Map<Integer,MissionSector> map = new HashMap<Integer, MissionSector>();
	    for (MissionSector instance : MissionSector.values()) {
	        map.put((int)instance.id(),instance);
	    }
	    ENUM_MAP = Collections.unmodifiableMap(map);
	}
	
	public static MissionSector getById(int id) {
		return ENUM_MAP.get(id);
	}
	
	public short id() {
		return this.id;
	}
	
	public String val() {
		return this.val;
	}
	
}
