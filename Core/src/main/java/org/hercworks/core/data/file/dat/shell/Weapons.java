package org.hercworks.core.data.file.dat.shell;

import org.hercworks.core.data.struct.vshell.hercs.UiWeaponEntry;
import org.hercworks.voln.DataFile;

/**
 * 	FILE
 * 		/SHELL/GAM/WEAPONS.DAT
 * 
 *  0- UINT16 - total weapon list.
 *  SEQ 0
 *  	S0_0 - UINT16 - weapon id.
 *  	S0_2 - UINT16 - len of weapon name, null-terminated.
 *  	S0_4+ - STR - null terminated string.
 *      S0_4+Len - UINT16 - Salvage cost, game * 100 to convert from tons to Kgs.
 *      S0_4+Len+UINT16 - Byte - ? - unknown flag.
 *      S0_4+Len+UINT16+Byte - UINT16 - ? - unknown value
 *  
 *  0 + SEQ(len) - UINT16 - total campaign-start weapon inventory 
 *  SEQ 1 - {@linkplain UiWeaponEntry}
 *  	S1_0- UINT16 - weapon id.
 *      S1_2- UINT16 - weapon health as % in 100.
 *      S1_4- UINT16 - missile enum
 */
public class Weapons extends DataFile{

	private short totalCount;
	
	private Entry[] data;
	
	private UiWeaponEntry[] startingWeapons;
	
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

	public UiWeaponEntry[] getStartingWeapons() {
		return startingWeapons;
	}

	public void setStartingWeapons(UiWeaponEntry[] startingWeapons) {
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

}
