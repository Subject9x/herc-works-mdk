package org.hercworks.core.data.file.dat.shell;

import java.util.LinkedHashMap;

import org.hercworks.core.data.struct.MissionSector;
import org.hercworks.voln.DataFile;

/**
 * 	FILE
 * 		/SHELL/GAM/CAREER.DAT
 * 
 *  0- UINT16 - ? - related to MSN_GEN.CPP, always 6, 0/1 cause issues.
 *  SEQ_0
 *  	0_0 - UINT16 - sector ID
 *  	0_2 - UINT16 - total missions in sector
 *  	SEQ_1
 *  		1_0 - UINT16 - mission id.
 *  	
 */
public class CareerMissions extends DataFile{
	
	private LinkedHashMap<MissionSector, int[]> sectors;
	
	public CareerMissions() {}

	public LinkedHashMap<MissionSector, int[]> getSectors() {
		return sectors;
	}

	public void setSectors(LinkedHashMap<MissionSector, int[]> sectors) {
		this.sectors = sectors;
	}
}
