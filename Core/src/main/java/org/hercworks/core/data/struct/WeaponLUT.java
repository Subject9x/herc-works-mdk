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

	EMPTY(0, "Empty"),
	ATC20(1, "ATC20"),
	ATC35(2, "ATC35"), 
	ATC50(3, "ATC50"), 
	ATC75(4, "ATC75"), 
	ATC100(5, "ATC100"),
	ELF(6, "ELF"),   
	EMP(7, "EMP"),   
	LAS100(8, "LAS100"),
	LAS200(9, "LAS200"),
	LAS300(10, "LAS300"),
	LAS400(11, "LAS400"),
	LAS500(12, "LAS500"),
	MSL6(13, "MSL6"), 
	MSL8(14, "MSL8"), 
	MSL10(15, "MSL10"),
	MSLR(16, "MSLR"), 
	PBW(17, "PBW"),   
	ECM(18, "ECM"),   
	UNKNOWN_1(19, "UNKNOWN_1"), 
	UNKNOWN_2(20, "UNKNOWN_2"), 
	UNKNOWN_3(21, "UNKNOWN_3"), 
	ELF2(22, "ELF2"), 
	EMP2(23, "EMP2"), 
	PBW2(24, "PBW2"), 
	PLAS(25, "PLAS"),  
	UNKNOWN_4(26, "UNKNOWN_4"), 
	UNKNOWN_5(27, "UNKNOWN_5"), 
	UNKNOWN_6(28, "UNKNOWN_6"), 
	TARG(29, "TARG"),  
	SHLD(30, "SHLD"),  
	TURB(31, "TURB"),  
	ENRG(32, "ENRG");  
	
	private int id;
	private String name;
	private static final Map<Integer,WeaponLUT> ENUM_MAP;
	
	private WeaponLUT(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
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
}
