package org.hercworks.core.data.struct.herc;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum MiscEntityLUT {

	SUPPLY_DEPOT((short)0, "Supply Depot"),
	HERC_FACTORY((short)1, "Herc Factory"),
	MINING_FACILITY((short)2, "Mining Facility"),
	GENERATOR((short)3, "Generator"),
	HANGAR((short)4, "Hangar"),
	LISTENING_POST((short)5, "Listening Post"),
	RADAR_TOWER((short)6, "Radar Tower"),
	REFINERY((short)7, "Refinery"),
	GUN_TOWER((short)8, "Gun Tower"),
	POWER_STATION((short)9, "Power Station"),
	//0A - 10 - CRASH
	MISSILE_TOWER((short)11, "Missile Tower"),
	BRIDGE_PYLON((short)12, "Bridge"),
	UNK_BUILDING((short)13, "Building"),
	SMOKE_STACK((short)14, "Smoke Stack"),
	OIL_TANK((short)15, "Oil Tank"),
	CONTROL_TOWER((short)16, "Control Tower"),
	LARGE_BUNKER((short)17, "Large Bunker"),
	SMALL_BUNKER((short)18, "Small Bunker"),
	FUEL_TANK_LONG((short)19, "Fuel Tank"),
	CONDUIT_CORNER((short)20, "Conduit"),
	RUINS_A((short)21, "Building"),
	RUINS_B((short)22, "Building"),
	RUINS_C((short)23, "Building"),
	SUPPLY_DEPOT_CYBRID((short)24, "Supply Depot"),
	FACTORY_CYBRID((short)25, "Factory"),
	MINING_FACILITY_CYBRID((short)26, "Mining Facility"),
	GENERATOR_CYBRID((short)27, "Generator"),
	HANGAR_CYBRID((short)28, "Hangar"),
	LISTENING_POST_CYBRID((short)29, "Listening Post"),
	RADAR_TOWER_CYBRID((short)30, "Radar Tower"),
	REFINERY_CYBRID((short)31, "Refinery"),
	GUN_TOWER_CYBRID((short)32, "Guntower"),
	POWER_STATION_CYBRID((short)33, "Power Station"),
	TRANSPORT((short)34, "Transport"),
	MISSILE_TOWER_CYBRID((short)35, "Missile Tower"),
	BASE_CORE((short)36, "Base Core"),
	BUILDING_CYBRID((short)37, "Building"),
	CONDUIT((short)38, "Conduit"),
	FUEL_TANK((short)39, "Fuel Tank"),
	CONTROL_TOWER_CYBRID((short)40, "Control Tower"),
	OIL_TANK_CYBRID((short)41, "Oil Tank"),
	UNK_BUILDING1((short)42, "Building"),
	UNK_BUILDING2((short)43, "Building"), //looks like power station, non-animated,
	UNK_BUILDING3((short)44, "Building"), //looks like power station, non-animated
	SUPPLY_TRANSPORT1((short)45, "Supply Transport"),
	SUPPLY_TRANSPORT2((short)46, "Supply Transport"),
	// 47 - CRASH
	MOBILE_CANNON((short)48, "Mobile Cannon"),
	ARMORED_ASSAULT((short)49, "Armored Assault"),
	MOBILE_MISSILE((short)50, "Mobile Missile"),
	MOBILE_MISSILE_JEEP((short)51, "Mobile Missile"),
	MOBILE_MISSILE_RAZOR((short)52, "Mobile Missile"),
	// 53 - CRASH
	// 54 - CRASH
	SUPPLY_TRANSPORT3((short)55, "Supply Transport"),
	SUPPLY_TRANSPORT4((short)56, "Supply Transport"),
	MOBILE_MISSILE_CYBRID((short)57, "Mobile Missile"),
	MOBILE_CANNON_CYBRID((short)58, "Mobile Cannon"),
	ARMORED_ASSAULT2((short)59, "Armored Assault"),
	MOBILE_MISSILE_CYBRID2((short)60, "Mobile Missile"),
	MOBILE_MISSILE_JEEP2((short)61, "Mobile Missile"),
	// 62 - CRASH
	// 63 - CRASH
	
	
	
	;
	private static final Map<Short,MiscEntityLUT> ENUM_MAP;
	private String name;
	private short id;
	
	private MiscEntityLUT(short id, String name) {
		this.name = name;
		this.id = id;
	}

	static {
	    Map<Short,MiscEntityLUT> map = new HashMap<Short, MiscEntityLUT>();
	    for (MiscEntityLUT instance : MiscEntityLUT.values()) {
	        map.put(instance.getId(),instance);
	    }
	    ENUM_MAP = Collections.unmodifiableMap(map);
	}
	
    public static MiscEntityLUT getById(short id) {
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
