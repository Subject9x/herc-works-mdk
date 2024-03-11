package com.mech.works.data.file.dat.shell;

import com.mech.works.data.ref.files.DataFile;

/**
 * 	FILE
 * 		/SHELL/GAM/WEAPONS.DAT
 * 
 *  0- UINT16 - total weapon list.
 *  SEQ 0
 *  	S0 - UINT16 - weapon id.
 *  	S2 - UINT16 - len of weapon name, null-terminated.
 *  	S4+ - STR - null terminated string.
 *      S4+Len - UINT16 - Salvage cost, game * 100 to convert from tons to Kgs.
 *      S4+Len+UINT16 - Byte - ? - unknown flag.
 *      S4+Len+UINT16+Byte - UINT16 - ? - unknown value
 *  
 *  0 + SEQ(len) - UINT16 - total campaign-start weapon inventory 
 *  SEQ 1
 *  	S0- UINT16 - weapon id.
 *      S2- UINT16 - weapon health as % in 100.
 *      S4- UINT16 - ? - unknown , always 0x05
 */
public class Weapons extends DataFile{

	private short totalCount;
	
	private Entry[] data;
	
	private WeaponEntry[] startingWeapons;
	
	public Weapons(int total) {
		data = new Entry[total];
	}
	
	public short getTotalCount() {
		return totalCount;
	}


	public Entry[] getData() {
		return data;
	}


	public void setTotalCount(short totalCount) {
		this.totalCount = totalCount;
	}


	public void setData(Entry[] data) {
		this.data = data;
	}

	public WeaponEntry[] getStartingWeapons() {
		return startingWeapons;
	}

	public void setStartingWeapons(WeaponEntry[] startingWeapons) {
		this.startingWeapons = startingWeapons;
	}

	/**
	 * 
	 */
	public class Entry{
		
		private short id;
		private byte nameLen;
		private String name;
		private short salvageCost;	//* 100 to convert to tons
		private byte unk1;
		private short unk2;
		
		public Entry() {}

		public short getId() {
			return id;
		}

		public byte getNameLen() {
			return nameLen;
		}

		public String getName() {
			return name;
		}

		public short getSalvageCost() {
			return salvageCost;
		}

		public byte getUnk1() {
			return unk1;
		}

		public short getUnk2() {
			return unk2;
		}

		public void setId(short id) {
			this.id = id;
		}

		public void setNameLen(byte nameLen) {
			this.nameLen = nameLen;
		}

		public void setName(String name) {
			this.name = name;
		}

		public void setSalvageCost(short salvageCost) {
			this.salvageCost = salvageCost;
		}

		public void setUnk1(byte unk1) {
			this.unk1 = unk1;
		}

		public void setUnk2(short unk2) {
			this.unk2 = unk2;
		}
	}
	
	/**
	 * These might be universal to the entire engine, I've seen this pattern
	 * all over the save file, herc arming data, etc.
	 * TODO - maybe this becomes a universal class sometime.
	 */
	public class WeaponEntry{
		private short id;
		private short healthPercent;
		private short unk1;
		
		public WeaponEntry(short id, short hp, short unk1){
			this.id = id;
			this.healthPercent = hp;
			this.unk1 = unk1;
		}

		public short getId() {
			return id;
		}

		public short getHealthPercent() {
			return healthPercent;
		}

		public short getUnk1() {
			return unk1;
		}

		public void setId(short id) {
			this.id = id;
		}

		public void setHealthPercent(short healthPercent) {
			this.healthPercent = healthPercent;
		}

		public void setUnk1(short unk1) {
			this.unk1 = unk1;
		}
	}
}
