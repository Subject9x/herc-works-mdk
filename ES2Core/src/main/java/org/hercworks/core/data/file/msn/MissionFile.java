package org.hercworks.core.data.file.msn;

import org.hercworks.voln.DataFile;

/**
 * 	FILE
 * 		/ZONES.VOL/MSN/ZONE.DAT
 * 		
 */


public class MissionFile extends DataFile {

	private short unknownFileId;
	
	private UnkHeaderEntry[] headerEntries;
	
	private short unkVal_is2;
	
	private short unkFlag_isFFFF;
	
	private short worldId;
	
	private short zoneNumber;
	
	private MapCoord[] mapCoordHeader;
	
	private UnitInfo[] mapUnits;
	
	private UnkEntity102Bytes[] unk102ByteEnts;
	
	private MiscEntityInfo[] mapMiscEntities;
	
	private UnkEntity22Byte[] unk22ByteEnts;
	
	private UnkEntity164Bytes[] unk164ByteEnts;
	
}
