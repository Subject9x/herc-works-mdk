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
 *  
 *  
 *  150- UINT16 - ammo count for ATC20
 *  
 *  SEQ
 *     
 *      x-6 - UINT16 - unknown
 *  	x-4 - UINT16 - unknown
 *  	x-2 - UINT16 ammo consumed per shot
 *  	x - UINT16 - ammo count
 *      X+2 - UINT16 - unknown
 *      X+4 - UINT16 - projectile ID
 *      X+6 - INT16 - PROJ\OFS\X
 *      X+8 - INT16 - PROJ\OFS\Y
 *      X+8 - INT16 - PROJ\OFS\Z
 *      X+12 - INT16- ORG\OFS\X
 *      X+14 - INT16- ORG\OFS\Y
 *      X+16 - INT16- ORG\OFS\Z
 *      x+18 - INT16 - fire rate
 *      
 *      
 *  
 */
public class Weapons extends DataFile{

	private short total;
	
	public Weapons() {}
	
	public Weapons(short total) {
		this.total = total;
	}
}
