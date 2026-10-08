package org.hercworks.core.data.file.msn.script;

import org.hercworks.core.data.file.msn.MiscEntityInfo;
import org.hercworks.voln.DataFile;

/**
 * 	FILE
 * 		/DATA/SCript.DAT
 * 			somehow this is a parsed version of the MSN file found in
 * 				/zone.vol/msn/
 * 	
 * 	NOTE: it seems VSHELL is doing some kind of processing / parsing of the .MSN file, and the script.dat
 * 	is not simply chunks of MSN file extracted!
 * 
 * 	ex: MSN MapCoord objects are cut down to just a list of points in script.dat!
 * 
 * 	NOTE2: script.dat is LONGER than the source MSN file, yikes.
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
 * 	
 * 
 * 		
 */

public class ScriptDat extends DataFile {
	
	private short worldId;
	
	private short zoneId;

	private ScriptCoord[]  mapCoords;
	
	private ScriptUnitEntry[] units;	// these are 10bytes SHORTER than MSN file versions, TBD which bytes are removed.
	
	private MiscEntityInfo[] miscEnts; 	//probably also truncated.
	
	
	
	public ScriptDat() {}
	
	
}
