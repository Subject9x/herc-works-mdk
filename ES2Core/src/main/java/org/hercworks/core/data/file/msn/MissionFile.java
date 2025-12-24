package org.hercworks.core.data.file.msn;

import org.hercworks.voln.DataFile;

/**
 * 	FILE
 * 		/ZONES.VOL/MSN/MSN_FILE.MSN
 * 		
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
	
	
	public MissionFile() {}
	
	
	
	
}
