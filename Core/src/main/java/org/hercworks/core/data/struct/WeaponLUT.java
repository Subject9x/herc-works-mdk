package org.hercworks.core.data.struct;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/***
 * 	Hardcoded LUT for sake of development speed.
 * 	TODO - eventually let's abstract this out to a configurable list,
 * 		or check for WEAPONS.DAT in the shell (or /SIMVOL/WEAPONS.DAT)
 */
public enum WeaponLUT {

	NONE(0, "NONE", 0),
	ATC20(1, "ATC20", 1),
	ATC35(2, "ATC35", 2), 
	ATC50(3, "ATC50", 3), 
	ATC75(4, "ATC75", 4), 
	ATC100(5, "ATC100", 5),
	ELFW(6, "ELFW", 6),   
	EMPC(7, "EMPC", 7),   
	LAS100(8, "L100", 8),
	LAS200(9, "L200", 9),
	LAS300(10, "L300", 10),
	LAS400(11, "L400", 11),
	LAS500(12, "L500", 12),
	MSL6(13, "MSL6", 13), 
	MSL8(14, "MSL8", 14), 
	MSL10(15, "MSL10", 15),
	MSLR(16, "MSLR", 16), 
	PBW(17, "PBW", 17),   
	ECM(18, "ECM", 18),   
	BEMP(19, "BEMP", 0), 
	BPBW(20, "BPBW", 0), 
	BMSL(21, "BMSL", 0), 
	ELF2(22, "ELF2", 19), 
	EMP2(23, "EMP2", 20), 
	PBW2(24, "PBW2", 21), 
	PLAS(25, "PLAS", 22),  
	LAEW(26, "LAEW", 0), 
	MINE(27, "MINE", 0), 
	MFAC(28, "MFAC", 0), 
	TARG(29, "TARG", 26),  
	SHLD(30, "SHLD", 27),  
	TURB(31, "TURB", 28),  
	ENRG(32, "ENRG", 29);
	
	private int id;
	private String name;
	private int secondId;
	private static final Map<Integer,WeaponLUT> ENUM_MAP;
	
	private WeaponLUT(int id, String name, int secondId) {
		this.id = id;
		this.name = name;
		this.secondId = secondId;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getSecondId() {
		return secondId;
	}

	static {
	    Map<Integer,WeaponLUT> map = new HashMap<Integer, WeaponLUT>();
	    for (WeaponLUT instance : WeaponLUT.values()) {
	        map.put(instance.getId(),instance);
	    }
	    ENUM_MAP = Collections.unmodifiableMap(map);
	}
	
    public static WeaponLUT getById(int id) {
        return ENUM_MAP.get(id);
    }
    
    public static WeaponLUT getByName(String name) {
    	for(WeaponLUT weapon : ENUM_MAP.values()) {
    		if(name.equals(weapon.getName())) {
    			return weapon;
    		}
    	}
    	return null;
    }
}
