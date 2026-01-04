package org.hercworks.core.data.file.msn;

import java.util.List;
import java.util.Optional;

import org.hercworks.voln.DataFile;

/**
 * 	FILE
 * 		/ZONES.VOL/MSN/MSN_FILE.MSN
 * 		
 *	Mission files are the root file for all data for a mission in earthsiege 2.
 *	The script.dat file in /DATA/ seems to be a parsed or excised snippet of the MSN file.
 *	
 *	Discovered so far:
 *		+ Zone heightmap file id, world id (Sector, then day/night)
 *		+ Global table of map coords, unclear how they're mapped to entities just yet.
 *		+ Spawn data for hercs, buildings (including misc stuff like ruins).
 *		
 *		
 *	What's expected:
 *		+ probably a file-global object / entry UUID for ents linking to each other.
 *		+ nav point entities, my guess is they're more than special map coords.
 *		+ entity definitions for objectives.
 *		+ pointer ID's for strings within .STR files.
 *			+ intel section
 *			+ objectives section
 *		+ possible campaign flags or some flag system to track campaign events.
 *			+ check / compare blocks 
 *			+ example: mission that unlocks the Raptor II
 *				+ there's 10 playable vehicles, I assume script spec then has 10 unlock slots somewhere
 *				+ same with weapon unlocks - 33 total iirc
 *		+ trigger logic and callbacks.
 *			+ probably not a concise as DooM or Quake's callbacks, but I assume something close to it.
 */


public class MissionFile extends DataFile {

	private short unknownFileId;
	
	private UnkHeaderEntry[] headerEntries;
	
	private short unkVal_is2;
	
	private short unkFlag_isFFFF;
	
	private short worldId;
	
	private short zoneNumber;
	
	private short[] unknown4ByteOr2ByteVals;
	
	private MapCoord[] mapCoordHeader;
	
	private UnkEntity10Byte[] unk10ByteEnts;
	
	private UnkEntity16Byte[] unk16ByteEnts;

	private List<MapObject> markedObjects;
	
	//some unknown, variable-length block of data.
	
	private UnitInfo[] mapUnits;
	
	private UnkEntity102Bytes[] unk102ByteEnts;
	
	private MiscEntityInfo[] mapMiscEntities;
	
	private UnkEntity22Byte[] unk22ByteEnts;
	
	private UnkEntity164Bytes[] unk164ByteEnts;
	
	private UnkEntity58Byte[] unk58ByteEnts;
	
	
	public MissionFile() {}


	public short getUnknownFileId() {
		return unknownFileId;
	}


	public void setUnknownFileId(short unknownFileId) {
		this.unknownFileId = unknownFileId;
	}


	public UnkHeaderEntry[] getHeaderEntries() {
		return headerEntries;
	}


	public void setHeaderEntries(UnkHeaderEntry[] headerEntries) {
		this.headerEntries = headerEntries;
	}


	public short getUnkVal_is2() {
		return unkVal_is2;
	}


	public void setUnkVal_is2(short unkVal_is2) {
		this.unkVal_is2 = unkVal_is2;
	}


	public short getUnkFlag_isFFFF() {
		return unkFlag_isFFFF;
	}


	public void setUnkFlag_isFFFF(short unkFlag_isFFFF) {
		this.unkFlag_isFFFF = unkFlag_isFFFF;
	}


	public short getWorldId() {
		return worldId;
	}


	public void setWorldId(short worldId) {
		this.worldId = worldId;
	}


	public short getZoneNumber() {
		return zoneNumber;
	}


	public void setZoneNumber(short zoneNumber) {
		this.zoneNumber = zoneNumber;
	}


	public short[] getUnknown4ByteOr2ByteVals() {
		return unknown4ByteOr2ByteVals;
	}


	public void setUnknown4ByteOr2ByteVals(short[] unknown4ByteOr2ByteVals) {
		this.unknown4ByteOr2ByteVals = unknown4ByteOr2ByteVals;
	}


	public MapCoord[] getMapCoordHeader() {
		return mapCoordHeader;
	}


	public void setMapCoordHeader(MapCoord[] mapCoordHeader) {
		this.mapCoordHeader = mapCoordHeader;
	}


	public UnitInfo[] getMapUnits() {
		return mapUnits;
	}


	public void setMapUnits(UnitInfo[] mapUnits) {
		this.mapUnits = mapUnits;
	}


	public UnkEntity102Bytes[] getUnk102ByteEnts() {
		return unk102ByteEnts;
	}


	public void setUnk102ByteEnts(UnkEntity102Bytes[] unk102ByteEnts) {
		this.unk102ByteEnts = unk102ByteEnts;
	}


	public MiscEntityInfo[] getMapMiscEntities() {
		return mapMiscEntities;
	}


	public void setMapMiscEntities(MiscEntityInfo[] mapMiscEntities) {
		this.mapMiscEntities = mapMiscEntities;
	}


	public UnkEntity22Byte[] getUnk22ByteEnts() {
		return unk22ByteEnts;
	}


	public void setUnk22ByteEnts(UnkEntity22Byte[] unk22ByteEnts) {
		this.unk22ByteEnts = unk22ByteEnts;
	}


	public UnkEntity164Bytes[] getUnk164ByteEnts() {
		return unk164ByteEnts;
	}


	public void setUnk164ByteEnts(UnkEntity164Bytes[] unk164ByteEnts) {
		this.unk164ByteEnts = unk164ByteEnts;
	}


	public UnkEntity58Byte[] getUnk58ByteEnts() {
		return unk58ByteEnts;
	}


	public void setUnk58ByteEnts(UnkEntity58Byte[] unk58ByteEnts) {
		this.unk58ByteEnts = unk58ByteEnts;
	}


	public List<MapObject> getMarkedObjects() {
		return markedObjects;
	}


	public void setMarkedObjects(List<MapObject> markedObjects) {
		this.markedObjects = markedObjects;
	}


	public UnkEntity10Byte[] getUnk10ByteEnts() {
		return unk10ByteEnts;
	}


	public void setUnk10ByteEnts(UnkEntity10Byte[] unk10ByteEnts) {
		this.unk10ByteEnts = unk10ByteEnts;
	}
	

	
	public UnkEntity16Byte[] getUnk16ByteEnts() {
		return unk16ByteEnts;
	}


	public void setUnk16ByteEnts(UnkEntity16Byte[] unk16ByteEnts) {
		this.unk16ByteEnts = unk16ByteEnts;
	}
	
	public MapObject findMarkedObjectById(short guid) {
		
		Optional<MapObject> o = getMarkedObjects().stream().filter(t -> t.getGUID() == guid).findFirst();
		
		return o.orElse(null);
	}
	
	public MapCoord getMapCoordById(short id) {
		if(id == -1) {
			return null;
		}
		return getMapCoordHeader()[id];
	}
}
