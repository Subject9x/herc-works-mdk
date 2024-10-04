package org.hercworks.core.data.file.dat.shell;

import java.util.List;

import org.hercworks.core.data.struct.vshell.hercs.ShellHercData;
import org.hercworks.voln.DataFile;

/**
 * 	FILE
 * 		SHELL/GAM/TRN_HERCS.DAT
 * 
 * 	Training herc data, appears to be an array of
 * 	{@linkplain ShellHercData} entries.
 * 
 * 	Somehow VSHELL knows which herc to load, but training skips the normal ARMING/BRIEFING workflow.
 *  
 * 
 */
public final class TrainingHercs extends DataFile{
	
	private List<ShellHercData> data;
	
	public TrainingHercs() {}

	public List<ShellHercData> getData() {
		return data;
	}

	public void setData(List<ShellHercData> data) {
		this.data = data;
	}
}
