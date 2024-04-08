package org.hercworks.core.data.struct;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/***
 * 	Hardcoded LUT for sake of development speed.
 * 	TODO - eventually let's abstract this out to a configurable list,
 */
public enum HercLUT {

	OUTLAW((short)0, "Outlaw", (short)3),
	RAPTOR_II((short)1, "Raptor_II", (short)5),
	TOMAHAWK((short)2, "Tomahawk", (short)5),
	SAMSON((short)3, "Samson", (short)8),
	COLOSSUS((short)4, "Colossus",  (short)9),
	APOCALYPSE((short)5, "Apocalypse",  (short)10),
	OGRE((short)6, "Ogre",  (short)10),
	MAVERICK((short)7, "Maverick", (short)4),
	RAZOR((short)8, "Razor", (short)7);

	private static final Map<Short,HercLUT> ENUM_MAP;
	private String name;
	private short id;
	private short hardpointMax;
	
	private HercLUT(short id, String name, short hardpointMax) {
		this.name = name;
		this.id = id;
		this.hardpointMax = hardpointMax;
	}

	static {
	    Map<Short,HercLUT> map = new HashMap<Short, HercLUT>();
	    for (HercLUT instance : HercLUT.values()) {
	        map.put(instance.getId(),instance);
	    }
	    ENUM_MAP = Collections.unmodifiableMap(map);
	}
	
    public static HercLUT getById(short id) {
        return ENUM_MAP.get(id);
    }
	
	public String getName() {
		return name;
	}

	public short getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(short id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
}
