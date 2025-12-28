package org.hercworks.core.data.file.msn;

import org.hercworks.voln.DataFile;

/**
 * 	FILE
 * 		/ZONES.VOL/MSN/MSN_FILE.MSN
 * 		
 *	Mission files are the root file for all data for a mission in earthsiege 2.
 *	The script.dat file in /DATA/ seems to be a parsed or excised snippet of the MSN file.
 *	
 *	Discovered so far:
 *		Spawn data for hercs, and buildings.
 *		Zone heightmap file id, world id,
 *		
 *	What's expected:
 *		+ entity definitions for objectives.
 *		+ pointer ID's for strings within .STR files.
 *			+ intel section
 *			+ objectives section
 *		+ possible campaign flags or some flag system to track campaign events.
 *			+ check / compare blocks 
 *			+ example: mission that unlocks the Raptor II
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
}
