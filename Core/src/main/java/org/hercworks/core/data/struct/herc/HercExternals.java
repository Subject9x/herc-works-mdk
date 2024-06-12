package org.hercworks.core.data.struct.herc;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum HercExternals {
	
	COCKPIT_FRONT((short)0, "Cockpit Front"),
	COCKPIT_REAR((short)1, "Cockpit Rear"),
	TORSO_LEFT_FRONT((short)2, "Left Torso"),
	TORSO_RIGHT_FRONT((short)3, "Right Torso"),
	TORSO_LEFT_REAR((short)4, "Left Torso"),
	TORSO_RIGHT_REAR((short)5, "Right Torso"),
	CHASSIS((short)6, "Chassis"),
	LEG_LEFT_TOP((short)7, "Leg Left Thigh"),
	LEG_RIGHT_TOP((short)8, "Leg Right Thigh"),
	LEG_LEFT_MID((short)9, "Leg Left Calf"),
	LEG_RIGHT_MID((short)10, "Leg Right Calf"),
	LEG_LEFT_FOOT((short)11, "Leg Left Foot"),
	LEG_RIGHT_FOOT((short)12, "Leg Right Foot");
	
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
    
    public static HercExternals getByName(String name) {
    	for(HercExternals external : HercExternals.values()) {
    		if(name.toLowerCase().equals(external.getLabel().toLowerCase())) {
    			return external;
    		}
    	}
    	return null;
    }
    
	
	public String getLabel() {
		return label;
	}

	public short getId() {
		return id;
	}
	
	
}
