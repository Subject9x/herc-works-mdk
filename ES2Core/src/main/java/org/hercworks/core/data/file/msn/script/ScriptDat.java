package org.hercworks.core.data.file.msn.script;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.hercworks.voln.DataFile;

/**
 * 	FILE
 * 		/DATA/SCript.DAT
 * 			somehow this is a parsed version of the MSN file found in
 * 				/zone.vol/msn/
 * 	
 * 0 - UINT16 - World Id num
 * 2 - UINT16 - ZonesXXX.dat number
 * 4 - UINT16 - unknown value
 * 6 - UINT16 - unknown value
 * 8 - UINT16 - unknown value
 * 10 - UINT16 - unknown value
 * 12 - UINT16 - unknown value
 * 14 - UINT16 - unknown value
 * 16 - UINT16 - unknown value
 * 18 - UINT16 - unknown value
 * 20 - UINT16 - Counter
 * SEQ_0 - possible UINT32 coords?
 * 	SEQ_0_0 - UINT32 - Player Spawn coord X
 *  SEQ_0_4 - UINT32 - Player Spawn coord Y
 *  SEQ_0_8 - UINT32 - Player Spawn coord Z
 * 	SEQ_0_12 - UINT32 - Waypoint 1 coord X
 * 	SEQ_0_16 - UINT32 - Waypoint 1 coord Y
 * 	SEQ_0_20 - UINT32 - Waypoint 1 coord Z
 *  ----------
 *  SEQ_0_XX - UINT32 - Waypoint 0 coord X
 *  SEQ_0_XX - UINT32 - Waypoint 0 coord Y
 *  SEQ_0_XX - UINT32 - Waypoint 0 coord Z
 * 	
 * 
 * 		
 */

public class ScriptDat extends DataFile {
	
	private short worldId;
	
	private short zoneId;
	
	private short unk1;
	private short unk2;
	private short unk3;
	private short unk4;
	private short unk5;
	private short unk6;
	private short unk7;
	private short unk8;
	
	//with uint16 counter
	private Vector3D[] entityOrigins;
	
	
	
}
