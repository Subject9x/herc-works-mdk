package org.hercworks.extract.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum SimFileNames {
	
	BASE_COL("BASE_COL"),
	BASES("BASES"),
	BEAM("BEAM"),
	BFORMS("BFORMS"),
	BULLETS("BULLETS"),
	COLORS("COLORS"),
	DEBRIS("_DEB"),
	FFORMS("FFORMS"),
	FIRE("FIRE"),
	FLIGHT_MODEL(".FM"),
	MAT0("MAT0"),
	MFORMS("MFORMS"),
	PROJECTILE("PROJ"),
	ROCKETS("ROCKETS"),
	SHADES("SHADES"),
	STYLES("STYLES"),
	WEAPONS("WEAPONS");
	
	private String namePattern;
	private static final Map<String,SimFileNames> ENUM_MAP;
	
	
	private SimFileNames(String pattern) {
		this.namePattern = pattern;
	}
	
	public String getPattern() {
		return this.namePattern;
	}
	
	static {
	    Map<String,SimFileNames> map = new HashMap<String, SimFileNames>();
	    for (SimFileNames instance : SimFileNames.values()) {
	        map.put(instance.getPattern(),instance);
	    }
	    ENUM_MAP = Collections.unmodifiableMap(map);
	}
	
    public static SimFileNames getByPattern(String file) {
    	for(String pattern : ENUM_MAP.keySet()) {
    		if(file.toUpperCase().contains(pattern)) {
    			return ENUM_MAP.get(pattern);
    		}
    	}
    	return null;
    }
	
}
