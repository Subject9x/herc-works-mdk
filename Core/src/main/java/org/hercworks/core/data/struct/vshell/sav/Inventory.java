package org.hercworks.core.data.struct.vshell.sav;

import org.hercworks.core.data.file.sav.PlayerSave;
import org.hercworks.core.data.struct.WeaponLUT;

/**
 * Specifically bound to {@linkplain PlayerSave},
 * 	there's a chunk of save data dealing just with inventory state.
 * 
 * 	Inventory is:
 *  starting at0x04
 *  01 - UINT8 - flag - weapon is buildable in armory
 *  00 00 - UINT16 - existing quantity of weapon.
 * 
 * 	1) total items in the inventory.
 *  2) an array of {@linkplain ShellWeaponEntry} structs
 * 
 */
public class Inventory {

	private InventoryItem[] items;
	
	public Inventory() {}

	public InventoryItem newEntry() {
		return new InventoryItem();
	}
	
	public InventoryItem[] getItems() {
		return items;
	}

	public void setItems(InventoryItem[] items) {
		this.items = items;
	}
	
	public class InventoryItem{
		private WeaponLUT id;
		private short unlockFlag;
		private short quantity;
		private ShellWeaponEntry[] data;
		
		public InventoryItem() {}
		
		public InventoryItem(int total) {
			this.quantity = (short)total;
			this.data = new ShellWeaponEntry[total];
		}
		
		public WeaponLUT getId() {
			return id;
		}

		public void setId(WeaponLUT id) {
			this.id = id;
		}

		public short getUnlockFlag() {
			return unlockFlag;
		}

		public short getQuantity() {
			return quantity;
		}

		public ShellWeaponEntry[] getData() {
			return data;
		}

		public void setUnlockFlag(short unlockFlag) {
			this.unlockFlag = unlockFlag;
		}

		public void setQuantity(short quantity) {
			this.quantity = quantity;
		}

		public void setData(ShellWeaponEntry[] data) {
			this.data = data;
		}
	}
}
