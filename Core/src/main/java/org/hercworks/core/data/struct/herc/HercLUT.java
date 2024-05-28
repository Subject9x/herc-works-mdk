package org.hercworks.core.data.struct.herc;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/***
 * 	Hardcoded LUT for sake of development speed.
 * 	TODO - eventually let's abstract this out to a configurable list,
 */
public enum HercLUT {

	OUTLAW((short)0, "Outlaw", (short)3, "OUTLAW"),
	RAPTOR_II((short)1, "Raptor_II", (short)5, "RAPTOR2"),
	TOMAHAWK((short)2, "Tomahawk", (short)5, "TOMAHAWK"),
	SAMSON((short)3, "Samson", (short)8, "SAMSON"),
	COLOSSUS((short)4, "Colossus",  (short)9, "COLOSSUS"),
	APOCALYPSE((short)5, "Apocalypse",  (short)10, "APOCA"),
	OGRE((short)6, "Ogre",  (short)10, "OGRE"),
	MAVERICK((short)7, "Maverick", (short)4, "MAVERICK"),
	RAZOR((short)8, "Razor", (short)7, "RAZOR"),
	//WARN - I don't know if Cybrid hercs are on the same num-id sequence as player hercs, so the following is not accurate.
	//I have cybrids here for convenience.
	ACHILLES((short)9, "ACHILLES", (short)6, "ACHILLES"),
	CERBERUS((short)10, "CERBERUS", (short)9, "CERBERUS"),
	DIABLO((short)11, "DIABLO", (short)8, "DIABLO"),
	HEADHUNTER((short)12, "HEADHUNTER", (short)9, "HEADHUNT"),
	HYPERION((short)13, "HYPERION", (short)9, "HYPERION"),
	MIRIMAC((short)14, "MIRIMAC", (short)5, "MIRIMAC"),
	MONGOOSE((short)15, "MONGOOSE", (short)3, "MONGOOSE"),
	PITBULL((short)16, "PITBULL", (short)1, "PITBULL"),
	RAMSES((short)17, "RAMSES", (short)4, "RAMSES"),
	SCARAB((short)18, "SCARAB", (short)4, "SCARAB"),
	STINGRAY((short)19, "STINGRAY", (short)3, "STINGRAY"),
	SKIMMER((short)20, "SKIMMER", (short)3, "SKIMMER"),
	SPIDER((short)21, "SPIDER", (short)1, "SPIDER");

	private static final Map<Short,HercLUT> ENUM_MAP;
	private String name;
	private short id;
	private short hardpointMax;
	private String abbrevDat;
	
	private HercLUT(short id, String name, short hardpointMax, String abbrevDat) {
		this.name = name;
		this.id = id;
		this.hardpointMax = hardpointMax;
		this.abbrevDat = abbrevDat;
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
	
	public short getHardpointMax() {
		return hardpointMax;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(short id) {
		this.id = id;
	}

	public String getAbbrevDat() {
		return abbrevDat;
	}

	public void setAbbrevDat(String abbrevDat) {
		this.abbrevDat = abbrevDat;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
