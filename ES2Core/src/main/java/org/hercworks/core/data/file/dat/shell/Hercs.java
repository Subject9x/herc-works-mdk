package org.hercworks.core.data.file.dat.shell;

import org.hercworks.core.data.struct.vshell.hercs.ShellHercData;
import org.hercworks.voln.DataFile;


/**
 * 	FILE
 * 		/SHELL/GAM/HERCS.DAT
 * 	
 * 	most likely sets player's starting herc list when a new campaign is started.
 * 
 * 	It's a collection of {@linkplain ShellHercData}
 * 
 *  0- UINT16 - total hercs
 *  SEQ0 - herc count
 *  	S0_0- UINT16 - bayId
 *  	S0_2- UINT16 - Herc Id
 *  	S0_4- UINT16 - health ratio?
 *  	S0_6- UINT16 - build completeness, 00 = complete, > 0 = remaining missions to build.
 *  	S0_8- UINT16 - hardpoint count
 *  		SEQ1 - hardpoint count
 *  			S1_0- UINT - hardpoint ID
 *  			S1_2- UINT - item ID
 *  			S1_4- UINT - health percentage
 *  			S1_6- UINT - missile enum, 05 = no missile type.
 */
public class Hercs extends DataFile{

	private Entry[] data;
	
	public Hercs() {}
	
	public Hercs(short total) {
		this.data = new Entry[total];
	}
	
	public class Entry{
		private short bayId;
		private ShellHercData herc;
		
		public Entry() {}

		public short getBayId() {
			return bayId;
		}

		public void setBayId(short bayId) {
			this.bayId = bayId;
		}

		public ShellHercData getHerc() {
			return herc;
		}

		public void setHerc(ShellHercData herc) {
			this.herc = herc;
		}
	}
	
	public Entry addEntry() {
		return new Entry();
	}

	public Entry[] getData() {
		return data;
	}

	public void setData(Entry[] data) {
		this.data = data;
	}
}
