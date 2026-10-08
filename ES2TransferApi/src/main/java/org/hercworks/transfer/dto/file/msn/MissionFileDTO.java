package org.hercworks.transfer.dto.file.msn;

import java.util.HashMap;

import org.hercworks.transfer.dto.file.TransferObject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * JSON formatted version of .MSN script files.
 */

@JsonRootName("MissionFile")
public class MissionFileDTO extends TransferObject {

	private short unknownFileId;
	
	private UnkHeaderEntryDTO[] headerEntries;
	
	private short unkVal_is2;
	
	private short unkFlag_isFFFF;
	
	private short worldId;
	
	private short zoneNumber;
	
	private short[] unknown4ByteOr2ByteVals;
	
	private MapCoordDTO[] mapCoordHeader;

	@JsonIgnore
	private HashMap<Short, MapObjectDTO> markedObjects;
	
	//some unknown, variable-length block of data.
	
	private UnitInfoDTO[] mapUnits;
	
	private UnkEntity102BytesDTO[] unk102ByteEnts;
	
	private MiscEntityInfoDTO[] mapMiscEntities;
	
	private UnkEntity22ByteDTO[] unk22ByteEnts;
	
	private UnkEntity164BytesDTO[] unk164ByteEnts;
	
	private UnkEntity58ByteDTO[] unk58ByteEnts;
	
	
	public MissionFileDTO() {}


	public short getUnknownFileId() {
		return unknownFileId;
	}


	public void setUnknownFileId(short unknownFileId) {
		this.unknownFileId = unknownFileId;
	}


	public UnkHeaderEntryDTO[] getHeaderEntries() {
		return headerEntries;
	}


	public void setHeaderEntries(UnkHeaderEntryDTO[] headerEntries) {
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


	public MapCoordDTO[] getMapCoordHeader() {
		return mapCoordHeader;
	}


	public void setMapCoordHeader(MapCoordDTO[] mapCoordHeader) {
		this.mapCoordHeader = mapCoordHeader;
	}


	public UnitInfoDTO[] getMapUnits() {
		return mapUnits;
	}


	public void setMapUnits(UnitInfoDTO[] mapUnits) {
		this.mapUnits = mapUnits;
	}


	public UnkEntity102BytesDTO[] getUnk102ByteEnts() {
		return unk102ByteEnts;
	}


	public void setUnk102ByteEnts(UnkEntity102BytesDTO[] unk102ByteEnts) {
		this.unk102ByteEnts = unk102ByteEnts;
	}


	public MiscEntityInfoDTO[] getMapMiscEntities() {
		return mapMiscEntities;
	}


	public void setMapMiscEntities(MiscEntityInfoDTO[] mapMiscEntities) {
		this.mapMiscEntities = mapMiscEntities;
	}


	public UnkEntity22ByteDTO[] getUnk22ByteEnts() {
		return unk22ByteEnts;
	}


	public void setUnk22ByteEnts(UnkEntity22ByteDTO[] unk22ByteEnts) {
		this.unk22ByteEnts = unk22ByteEnts;
	}


	public UnkEntity164BytesDTO[] getUnk164ByteEnts() {
		return unk164ByteEnts;
	}


	public void setUnk164ByteEnts(UnkEntity164BytesDTO[] unk164ByteEnts) {
		this.unk164ByteEnts = unk164ByteEnts;
	}


	public UnkEntity58ByteDTO[] getUnk58ByteEnts() {
		return unk58ByteEnts;
	}


	public void setUnk58ByteEnts(UnkEntity58ByteDTO[] unk58ByteEnts) {
		this.unk58ByteEnts = unk58ByteEnts;
	}


	public HashMap<Short, MapObjectDTO> getMarkedObjects() {
		return markedObjects;
	}


	public void setMarkedObjects(HashMap<Short, MapObjectDTO> markedObjects) {
		this.markedObjects = markedObjects;
	}
}
