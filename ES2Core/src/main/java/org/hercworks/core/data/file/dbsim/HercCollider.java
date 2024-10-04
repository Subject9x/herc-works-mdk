package org.hercworks.core.data.file.dbsim;

import org.hercworks.voln.DataFile;

/**
 * 	FILE
 * 		/SIMVOL0/COL/<herc>.COL
 * 
 * 0- UINT16 - always 6, seems to be number of main collider components
 * 2- UINT16 - always 3 for hercs, FFFF for skimmer
 * 4- UINT16 - possible collider type - 0x00 means herc registers hit but 0 damage to anything, 0x01 seems default, >0x01 causes crash issues
 * 6- UINT16 - unknown val, hercs 0x07
 * 8- UNIT16 - unknown val, hercs 0x02, skimmer 0x6, razor 0x03
 */
public class HercCollider extends DataFile{

	private short primaryBBoxesTotal;
	private short unk2_flag;	
	private short collideType = (short)0x01;	//usually 1
	private short unk4_val = (short)0x07;	//hercs have 7, spider/skimmer 0
	private short unk8_val = (short)0x02;
	
	public HercCollider() {}
}
