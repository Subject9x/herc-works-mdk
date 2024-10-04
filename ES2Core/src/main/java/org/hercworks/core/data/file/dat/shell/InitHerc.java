package org.hercworks.core.data.file.dat.shell;

import java.nio.charset.StandardCharsets;

import org.hercworks.core.data.struct.vshell.hercs.ShellHercData;
import org.hercworks.voln.DataFile;

import at.favre.lib.bytes.Bytes;

/**
 * 	TODO - I don't think these files are directly used in /SHELL/?
 * 		I zeroed out INIT_OUTL hardpoints and no changes observed.
 *  Wrapper for {@linkplain ShellHercData}
 * 	FILE
 * 		/SHELL/GAM/INI_[herc].DAT	
 * 		presume it deals with initializing herc stats for...runtime?
 * 
 * 	0- UINT16 - herc id
 *  2- UINT16 - '100' - purpose unknown atm.
 *  4- UINT16 - 'completeness' number, linked to [herc]_bod.DBA frames.
 *  6- UINT16 - Hardpoint count
 *  SEQ - hardpoints
 *  	S0 - UINT16 - hardpoint id
 *  	S2 - UINT16 - weapon id
 *      S4 - UINT16 - health percent
 *      S6 - UINT16 - missile_num, 5 for non-Missile
 */
public class InitHerc extends DataFile{

	public static Bytes header = Bytes.from("661FAF55", StandardCharsets.UTF_8);
	
	private ShellHercData data;
	
	public InitHerc() {}
	
	public InitHerc(String fileName, String dirPath) {
		super(fileName, dirPath);
	}

	public ShellHercData getData() {
		return data;
	}

	public void setData(ShellHercData data) {
		this.data = data;
	}
}