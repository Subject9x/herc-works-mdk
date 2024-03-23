package org.hercworks.core.data.file.dat.shell;

import org.hercworks.voln.DataFile;

/**
 * 	FILE
 * 		/SHELL/GAM/CAREER.DAT
 * 
 *  0- UINT16 - ? - related to MSN_GEN.CPP, always 6, 0/1 cause issues.
 *  2- UINT16 - ? - related to MSN_GEN.CPP, always 5 - probably number of sectors: Alpha, Delta, Omicron, Bravo, Moon
 *  SEQ0 - Mission list
 *  	S0_0- UINT16 - mission id's, they have to align with sector somehow otherwise they crash.
 */
public class Career extends DataFile{

	private short uint1_0;
	private short uint2_2;
	
	private byte[] missions;
	
	public Career() {}

	public short getUint1_0() {
		return uint1_0;
	}

	public short getUint2_2() {
		return uint2_2;
	}

	public byte[] getMissions() {
		return missions;
	}

	public void setUint1_0(short uint1_0) {
		this.uint1_0 = uint1_0;
	}

	public void setUint2_2(short uint2_2) {
		this.uint2_2 = uint2_2;
	}

	public void setMissions(byte[] missions) {
		this.missions = missions;
	}
}
