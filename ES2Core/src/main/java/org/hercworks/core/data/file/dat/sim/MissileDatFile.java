package org.hercworks.core.data.file.dat.sim;

import org.hercworks.core.data.struct.dbsim.ProjMissileDatEntry;
import org.hercworks.voln.DataFile;


/**
 * 	FILE
 * 		/SIMVOL0/DAT/BULLETS.DAT
 *		/SIMVOL0/DAT/ROCKETS.DAT
 *	
 * This struct seems shared by both
 *  {@linkplain BulletsData} and {@linkplain RocketsData}
 * 0- UINT16 - total count
 * 	
 * 	SEQ0 - 14-byte segments
 * 		S0_0- UINT16 - model ID - 0x01 is the ES1 ATC model!?
 * 		S1_2- UINT16 - projectile timer - in milliseconds
 * 		S2-4- UINT16 - projectile speed
 *  	S3_6- UINT16 - unknown flag value - EMP proj ID's have 256, all else 0
 *  					tried 256 on non-EMP Id crashes sim.
 *  	S4_8- UINT16 - SFX\BULLET\FIRE
 *  	S5_10- UINT16 - ?
 *  	S6_12- UINT16 - SFX\ROCKET\FIRE
*/
public class MissileDatFile extends DataFile {

	private short total;
	private ProjMissileDatEntry[] entries;
	
	public MissileDatFile() {}
	
	public MissileDatFile(short total) {
		this.total = total;
		this.entries = new ProjMissileDatEntry[total];
	}

	public short getTotal() {
		return total;
	}

	public ProjMissileDatEntry[] getEntries() {
		return entries;
	}

	public void setTotal(short total) {
		this.total = total;
	}

	public void setEntries(ProjMissileDatEntry[] entries) {
		this.entries = entries;
	}
}
