package org.hercworks.core.data.file.dat.sim;

import org.hercworks.voln.DataFile;


/**
 * 	FILE
 * 		/DBSIM/DAT/WEAPONS.DAT
 * 
 * 	0- UINT16 - Total weapons (33 in stock game, 6 cut/deleted)
 * 	2- UINT16 - ?
 *  4- UINT16 - ?  
 *  6- UINT16 - ?
 *  8- UINT16 - ? - > 0 = crash engine
 *  10- UINT16 - ? - 13 (i19) = but different values work
 *  12- UINT16 - ? - > 0 = crash engine
 *  14- UINT16 - ?
 *  16- UINT16 - ? - > 0 = crash engine
 *  18- UINT16 - ?
 *  20- UINT16 - ?
 *  22- UINT16 - ?
 *  24- UINT16 - ?
 *  26- UINT16 - ?
 */
public class Weapons extends DataFile{

	private short total;
	
	public Weapons() {}
	
	public Weapons(short total) {
		this.total = total;
	}
}
