package org.hercworks.core.data.struct.vshell.hercs;

import org.hercworks.core.vol.util.ByteOps;

/**
 * 	STRUCT
 * 		as observed at various parts of the code, herc hardpoint structs are seen mostly in VSHELL.EXE
 * 		WARN: hardpoint Ids themselves are controlled by the parent of this class, this is just the
 * 			byte data for weapons-in-hardpoint.
 *  0- UINT16 - weapon id
 *  2- UINT16 - health percentage as 0-100, usually 0x64 (100%) - this ties into player inventory weapon health tracking.
 *  4- UINT16 - missile enum - OBSERVED - 'none' = 0x05, then 0x01-0x03 for the actual missile types.
 */
public class UiWeaponEntry {

	private short itemId;
	private short healthPercent;
	private short missileEnum;
	
	public UiWeaponEntry() {}
	
	public UiWeaponEntry(short itemId, short healthPercent, short missileEnum) {
		this.itemId = itemId;
		this.healthPercent = healthPercent;
		this.missileEnum = missileEnum;
	}

	public byte[] toByte() {
		byte[] data = new byte[6];
		
		ByteOps.shortLEToByteArr(data, 0, getItemId());
		ByteOps.shortLEToByteArr(data, 2, getHealthPercent());
		ByteOps.shortLEToByteArr(data, 4, getMissileEnum());
		
		return data;
	}
	
	public short getItemId() {
		return itemId;
	}

	public short getHealthPercent() {
		return healthPercent;
	}

	public short getMissileEnum() {
		return missileEnum;
	}
	
	public void setItemId(short itemId) {
		this.itemId = itemId;
	}

	public void setHealthPercent(short healthPercent) {
		this.healthPercent = healthPercent;
	}

	public void setMissileEnum(short missileEnum) {
		this.missileEnum = missileEnum;
	}
}
