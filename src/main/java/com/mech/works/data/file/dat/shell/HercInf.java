package com.mech.works.data.file.dat.shell;

import com.mech.works.data.ref.files.DataFile;

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
 * 		S10- UINT - ? -
 * 		S12- UINT16 - Mission count to finish build.
 * 		S14- UINT16 - boolean flag - start campaign available
 */
public class HercInf extends DataFile{
 
	private short totalHercs;
	private Entry[] data;
	
	public HercInf(int totalHercs) {
		data = new Entry[totalHercs];
	}
	
	public class Entry{

		private short hercId;
		private short weight;
		private	short speed;
		private short hardpointTotal;
		private short salvageReq;
		private short buildMissionCount;
		private short flagCampaignStart;
		
		public Entry() {}

		public short getHercId() {
			return hercId;
		}

		public short getWeight() {
			return weight;
		}

		public short getSpeed() {
			return speed;
		}

		public short getHardpointTotal() {
			return hardpointTotal;
		}

		public short getSalvageReq() {
			return salvageReq;
		}

		public short getBuildMissionCount() {
			return buildMissionCount;
		}

		public short getFlagCampaignStart() {
			return flagCampaignStart;
		}

		public void setHercId(short hercId) {
			this.hercId = hercId;
		}

		public void setWeight(short weight) {
			this.weight = weight;
		}

		public void setSpeed(short speed) {
			this.speed = speed;
		}

		public void setHardpointTotal(short hardpointTotal) {
			this.hardpointTotal = hardpointTotal;
		}

		public void setSalvageReq(short salvageReq) {
			this.salvageReq = salvageReq;
		}

		public void setBuildMissionCount(short buildMissionCount) {
			this.buildMissionCount = buildMissionCount;
		}

		public void setFlagCampaignStart(short flagCampaignStart) {
			this.flagCampaignStart = flagCampaignStart;
		}
	}

	public short getTotalHercs() {
		return totalHercs;
	}

	public Entry[] getData() {
		return data;
	}

	public void setTotalHercs(short totalHercs) {
		this.totalHercs = totalHercs;
	}

	public void setData(Entry[] data) {
		this.data = data;
	}
}

