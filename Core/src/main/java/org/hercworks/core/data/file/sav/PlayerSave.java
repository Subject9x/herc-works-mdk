package org.hercworks.core.data.file.sav;

import java.util.Map;

import org.hercworks.core.data.struct.WeaponLUT;
import org.hercworks.core.data.struct.herc.HercLUT;
import org.hercworks.core.data.struct.vshell.sav.HercBayEntry;
import org.hercworks.core.data.struct.vshell.sav.Inventory;
import org.hercworks.core.data.struct.vshell.sav.PilotEntry;
import org.hercworks.core.data.struct.vshell.sav.Workshop;
import org.hercworks.voln.DataFile;


/**
 * 	FILE
 * 		[root]/SAV/<player name>.sav
 * 	0- UINT16 - unknown flag
 *  2- UINT8 - spacer, possibly unknown value
 *  3- UINT8 - begin {@linkplain Inventory} segment
 *  
 *  3+X - UINT16 - begin {@linkplain Workshop} segment
 *  
 *  
 * 
 */
public class PlayerSave extends DataFile {

	private short unk1_uint16;
	private byte spacer1;
	private Inventory inventory;
	private short workshopSpace;
	private WeaponLUT[] workshopSlots = new WeaponLUT[5];
	private short[] unk2_flags = new short[78];
	private PilotEntry[] squadmates;
	private PilotEntry playerPilot;
	private Map<Short, HercBayEntry> hercBay;
	private Map<HercLUT, Short> unlockedHercs;
	private int salvageTotal;
	
	public PlayerSave() {}
	
	public short getUnk1_uint16() {
		return unk1_uint16;
	}

	public byte getSpacer1() {
		return spacer1;
	}
	public void setUnk1_uint16(short unk1_uint16) {
		this.unk1_uint16 = unk1_uint16;
	}
	
	public short[] getUnk2_flags() {
		return unk2_flags;
	}

	public void setUnk2_flags(short[] unk2_flags) {
		this.unk2_flags = unk2_flags;
	}

	public void setSpacer1(byte spacer1) {
		this.spacer1 = spacer1;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public PilotEntry[] getSquadmates() {
		return squadmates;
	}

	public Map<Short, HercBayEntry> getHercBay() {
		return hercBay;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public void setSquadmates(PilotEntry[] squadmates) {
		this.squadmates = squadmates;
	}

	public void setHercBay(Map<Short, HercBayEntry> hercBay) {
		this.hercBay = hercBay;
	}

	public short getWorkshopSpace() {
		return workshopSpace;
	}

	public WeaponLUT[] getWorkshopSlots() {
		return workshopSlots;
	}

	public void setWorkshopSpace(short workshopSpace) {
		this.workshopSpace = workshopSpace;
	}

	public void setWorkshopSlots(WeaponLUT[] workshopSlots) {
		this.workshopSlots = workshopSlots;
	}

	public PilotEntry getPlayerPilot() {
		return playerPilot;
	}

	public void setPlayerPilot(PilotEntry playerPilot) {
		this.playerPilot = playerPilot;
	}

	public Map<HercLUT, Short> getUnlockedHercs() {
		return unlockedHercs;
	}

	public void setUnlockedHercs(Map<HercLUT, Short> unlockedHercs) {
		this.unlockedHercs = unlockedHercs;
	}

	public int getSalvageTotal() {
		return salvageTotal;
	}

	public void setSalvageTotal(int salvageTotal) {
		this.salvageTotal = salvageTotal;
	}
}
