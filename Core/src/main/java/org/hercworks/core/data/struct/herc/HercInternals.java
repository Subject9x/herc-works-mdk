package org.hercworks.core.data.struct.herc;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum HercInternals {

	SERVOS_LEG_LEFT((short)0, "Left Leg Servos"),
	SERVOS_LEG_RIGHT((short)1, "Right Leg Servos"),
	SENSOR_ARRAY((short)2, "Sensor Array"),
	TARG_COMP((short)3, "Targeting Computer"),
	SHIELD_GEN((short)4, "Shield Generator"),
	ENGINE((short)5, "Engine"),
	HYDRAULICS((short)6, "Hydraulics"),
	STABILIZERS((short)7, "Stabiliziers"),
	LIFE_SUPPRT((short)8, "Life Support"),
	PILOT((short)9, "Pilot");
	
	private short id;
	private String label;
	private static final Map<Short,HercInternals> ENUM_MAP;
	
	private HercInternals(short id, String label) {
		this.id = id;
		this.label = label;
	}
	static {
	    Map<Short,HercInternals> map = new HashMap<Short, HercInternals>();
	    for (HercInternals instance : HercInternals.values()) {
	        map.put(instance.getId(),instance);
	    }
	    ENUM_MAP = Collections.unmodifiableMap(map);
	}
	
    public static HercInternals getById(short id) {
        return ENUM_MAP.get(id);
    }
	
	public String getLabel() {
		return label;
	}

	public short getId() {
		return id;
	}
}
