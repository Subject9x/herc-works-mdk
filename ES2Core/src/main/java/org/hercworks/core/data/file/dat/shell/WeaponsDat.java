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
 *      S0_4+Len+UINT16 - Byte - start campaign with this unlocked.
 *      S0_4+Len+UINT16+Byte - UINT16 - Armory Workshop build priority
 *  
 *  0 + SEQ(len) - UINT16 - total campaign-start weapon inventory 
 *  SEQ 1 - {@linkplain UiWeaponEntry}
 *  	S1_0- UINT16 - weapon id.
 *      S1_2- UINT16 - weapon health as % in 100.
 *      S1_4- UINT16 - missile enum
 */
public class WeaponsDat extends DataFile{

	private short totalCount;
	private Entry[] data;
	private short startWeaponTotal;
	private UiWeaponEntry[] startingWeapons;
	
	public WeaponsDat(int total) {
		this.totalCount = (short)total;
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
	
	public short getStartWeaponTotal() {
		return startWeaponTotal;
	}

	public void setStartWeaponTotal(short startWeaponTotal) {
		this.startWeaponTotal = startWeaponTotal;
	}

	public Entry addEntry(int idx) {
		Entry item = new Entry();
		getData()[idx] = item;
		return item;
	}
	
	/**
	 * 
	 */
	public class Entry{
		
		private short id;
		private short nameLen;
		private byte[] name;
		private short salvageCost;	//* 100 to convert to tons
		private byte startUnlock;
		private short autobuildPriority;
		
		public Entry() {}

		public short getId() {
			return id;
		}

		public short getNameLen() {
			return nameLen;
		}

		public byte[] getName() {
			return name;
		}

		public short getSalvageCost() {
			return salvageCost;
		}

		public byte getStartUnlock() {
			return startUnlock;
		}

		public short getAutobuildPriority() {
			return autobuildPriority;
		}

		public void setId(short id) {
			this.id = id;
		}

		public void setNameLen(short nameLen) {
			this.nameLen = nameLen;
		}

		public void setName(byte[] name) {
			this.name = name;
		}

		public void setSalvageCost(short salvageCost) {
			this.salvageCost = salvageCost;
		}

		public void setStartUnlock(byte unk1) {
			this.startUnlock = unk1;
		}

		public void setAutobuildPriority(short autobuildPriority) {
			this.autobuildPriority = autobuildPriority;
		}
	}

}
