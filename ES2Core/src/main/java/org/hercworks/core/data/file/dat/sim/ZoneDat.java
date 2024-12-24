package org.hercworks.core.data.file.dat.sim;

import org.hercworks.voln.DataFile;


/**
 * 	FILE
 * 		/ZONE/DAT/ZONEXXXX.DAT
 * 	0- UINT32 - must either be 7 or 8
 *  4- UINT32 - must either be 7 or 8
 *  8- UINT32 - 13,14,15 - 
 *  12- UINT32 - height scalar value cannot be 0!
 * 
 */

public class ZoneDat extends DataFile {

	private int unkInt1_7or8;
	
	
	private int unkInt2_7or8;
	
	//13, 14, 15
	private int unkInt3_varies;
	
	
	private int heightScalar;
	
	
	public ZoneDat() {}
	
	
	
}
