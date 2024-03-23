package org.hercworks.core.data.file.dat.shell;

import java.util.LinkedHashMap;

import org.hercworks.core.data.struct.vshell.hercs.UiWeaponEntry;
import org.hercworks.voln.DataFile;


/**
 * 	FILE
 * 		/SHELL/GAM/HERCS.DAT
 * 	
 * 	most likely sets player's starting herc list when a new campaign is started.
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

	private short total;
	private Entry[] data;
	
	public Hercs(short total) {
		this.total = total;
		this.data = new Entry[total];
	}
	
	public class Entry{
		private short bayId;
		private short hercId;
		private short healthRatio;
		private short buildCompleteLevel;
		private short hardpointCount;
		private LinkedHashMap<Short, UiWeaponEntry> data;
		
		public Entry() {}

		public short getBayId() {
			return bayId;
		}

		public short getHercId() {
			return hercId;
		}

		public short getHealthRatio() {
			return healthRatio;
		}

		public short getBuildCompleteLevel() {
			return buildCompleteLevel;
		}

		public short getHardpointCount() {
			return hardpointCount;
		}

		public LinkedHashMap<Short, UiWeaponEntry> getData() {
			return data;
		}

		public void setBayId(short bayId) {
			this.bayId = bayId;
		}

		public void setHercId(short hercId) {
			this.hercId = hercId;
		}

		public void setHealthRatio(short healthRatio) {
			this.healthRatio = healthRatio;
		}

		public void setBuildCompleteLevel(short buildCompleteLevel) {
			this.buildCompleteLevel = buildCompleteLevel;
		}

		public void setHardpointCount(short hardpointCount) {
			this.hardpointCount = hardpointCount;
		}

		public void setData(LinkedHashMap<Short, UiWeaponEntry> data) {
			this.data = data;
		}
	}

	public short getTotal() {
		return total;
	}

	public Entry[] getData() {
		return data;
	}

	public void setTotal(short total) {
		this.total = total;
	}

	public void setData(Entry[] data) {
		this.data = data;
	}
}
