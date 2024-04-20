package org.hercworks.extract.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Used to match incoming .JSON files against known file types for conversion to ES2 DAT binary formats.
 */
public enum FileMatch {

	ARM_WEAP("ARM_WEAP"),
	WEAPONS("WEAPONS"),
	ARM_HERC("ARM_"),
	RPR_HERC("RPR_"),
	INI_HERC("INI_"),
	HERCS("HERCS"),
	HERC_INF("HERC_INF"),
	CAREER("CAREER"),
	TRAINING_HERCS("TRN_HERC"),
	SAVE_FILE("GAME_");
	
	private String namePattern;
	private static final Map<String,FileMatch> ENUM_MAP;
	
	
	private FileMatch(String pattern) {
		this.namePattern = pattern;
	}
	
	public String getPattern() {
		return this.namePattern;
	}
	
	static {
	    Map<String,FileMatch> map = new HashMap<String, FileMatch>();
	    for (FileMatch instance : FileMatch.values()) {
	        map.put(instance.getPattern(),instance);
	    }
	    ENUM_MAP = Collections.unmodifiableMap(map);
	}
	
    public static FileMatch getByPattern(String file) {
    	for(String pattern : ENUM_MAP.keySet()) {
    		if(file.toUpperCase().contains(pattern)) {
    			return ENUM_MAP.get(pattern);
    		}
    	}
    	return null;
    }
	
}
