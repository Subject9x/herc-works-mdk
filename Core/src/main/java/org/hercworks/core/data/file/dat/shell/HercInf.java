package org.hercworks.core.data.file.dat.shell;

import org.hercworks.core.data.struct.vshell.hercs.HercInfEntry;
import org.hercworks.voln.DataFile;

/**
 * 	FILE
 * 		/SHELL/GAM/HERC_INF.DAT
 * 
 * 	0- UINT16 - ? - value is 9, most likely total hercs.
 * 	
 * 	SEQ - hercs in id order
 * 		S0- UINT16 - herc id
 * 		S2- UINT16 - weight in 'tons' as Integer-only.
 * 		S4- UINT16 - speed as KPH, Integer-only
 * 		S6- UINT16 - hardpoint total
 * 		S8- UINT16 - Salvage requirement in 'tons', Integer-only.
 * 		S10- UINT - ? - possibly unlock flag
 * 		S12- UINT16 - Mission count to finish build.
 * 		S14- UINT16 - boolean flag - start campaign available
 */
public class HercInf extends DataFile{
 
	private short totalHercs;
	private HercInfEntry[] data;
	
	public HercInf(int totalHercs) {
		this.totalHercs= (short) totalHercs;
		data = new HercInfEntry[totalHercs];
	}

	public short getTotalHercs() {
		return totalHercs;
	}

	public HercInfEntry[] getData() {
		return data;
	}

	public void setTotalHercs(short totalHercs) {
		this.totalHercs = totalHercs;
	}

	public void setData(HercInfEntry[] data) {
		this.data = data;
	}
}

