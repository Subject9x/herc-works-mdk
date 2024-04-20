package org.hercworks.core.data.struct.vshell.sav;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum PilotRank {

	ROOKIE(0, "Rookie"),
	REGULAR(1, "Regular"),
	VETERAN(2, "Veteran"),
	ELITE(3, "Elite"),
	LIEUTENANT(4, "Lieutenant"),
	CAPTAIN(5, "Captain"),
	MAJOR(6, "Major"),
	LT_COLONEL(7, "Lt Colonel");

	private static final Map<Short,PilotRank> ENUM_MAP;
	private short id;
	private String label;
	
	private PilotRank(int id, String label) {
		this.id = (short)id;
		this.label = label;
	}
	
	static {
	    Map<Short,PilotRank> map = new HashMap<Short, PilotRank>();
	    for (PilotRank instance : PilotRank.values()) {
	        map.put(instance.getId(),instance);
	    }
	    ENUM_MAP = Collections.unmodifiableMap(map);
	}
	
    public static PilotRank getById(short id) {
        return ENUM_MAP.get(id);
    }

	public short getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}

	public void setId(short id) {
		this.id = id;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
