package org.hercworks.core.data.struct.herc;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum HercExternals {

	/**
	 * Byte order in herc data
	 * cockpit 1
	 * cockpit 2
	 * 
	 * torso-left 1
	 * torso-right 1
	 * 
	 * torso-left 2
	 * torso-right 2
	 * 
	 * chassis 1
	 * 
	 * leg-left-1
	 * leg-right-1
	 * 
	 * leg-left-2
	 * leg-right-2
	 * 
	 * leg-left-3
	 * leg-right-3
	 */

	COCKPIT((short)0, "Cockpit"),
	TORSO_LEFT((short)1, "Left Torso"),
	TORSO_RIGHT((short)2, "Right Torso"),
	CHASSIS((short)3, "Chassis"),
	LEG_LEFT((short)4, "Leg Left"),
	LEG_RIGHT((short)5, "Leg Right");
	
	private short id;
	private String label;
	private static final Map<Short,HercExternals> ENUM_MAP;
	
	private HercExternals(short id, String label) {
		this.id = id;
		this.label = label;
	}
	static {
	    Map<Short,HercExternals> map = new HashMap<Short, HercExternals>();
	    for (HercExternals instance : HercExternals.values()) {
	        map.put(instance.getId(),instance);
	    }
	    ENUM_MAP = Collections.unmodifiableMap(map);
	}
	
    public static HercExternals getById(short id) {
        return ENUM_MAP.get(id);
    }
	
	public String getLabel() {
		return label;
	}

	public short getId() {
		return id;
	}
	
	
}
