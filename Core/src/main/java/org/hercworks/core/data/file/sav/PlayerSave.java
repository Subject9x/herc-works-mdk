package org.hercworks.core.data.file.sav;

import java.util.LinkedHashMap;
import java.util.Map;

import org.hercworks.core.data.struct.WeaponLUT;
import org.hercworks.core.data.struct.herc.HercLUT;
import org.hercworks.core.data.struct.vshell.sav.HercBayEntry;
import org.hercworks.core.data.struct.vshell.sav.Inventory;
import org.hercworks.core.data.struct.vshell.sav.PilotEntry;
import org.hercworks.voln.DataFile;


/**
 * 	FILE
 * 		[root]/SAV/<player name>.sav
 * 	0- UINT16 - unknown flag
 *  2- UINT8 - spacer, possibly unknown value
 *  3- UINT8 - begin {@linkplain Inventory} segment
 *  
 *  3+X - UINT16 -
 *  
 *  
 * 
 */
public class PlayerSave extends DataFile {

	private Inventory inventory;
	private short workshopSpace;
	private WeaponLUT[] workshopSlots = new WeaponLUT[5];
	
	//0x00 - uint16 - ?
	//0x02 - uint16 - mission number
	
	private short[] unk4_stateFlags = new short[78];
	private PilotEntry[] squadmates;
	private short[] unkRange_prePlayer = new short[9];
	private PilotEntry playerPilot;
	private Map<Short, HercBayEntry> hercBay = new LinkedHashMap<Short, HercBayEntry>();
	private Map<HercLUT, Short> unlockedHercs = new LinkedHashMap<HercLUT, Short>();
	private int salvageTotal;
	private byte[] unknownSaveValues;	//massive chunk of save values after relevant data
	
	public PlayerSave() {}
	
	public short[] getUnk4_stateFlags() {
		return unk4_stateFlags;
	}

	public void setUnk4_stateFlags(short[] unk2_flags) {
		this.unk4_stateFlags = unk2_flags;
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

	public byte[] getUnknownSaveValues() {
		return unknownSaveValues;
	}

	public void setUnknownSaveValues(byte[] unknownSaveValues) {
		this.unknownSaveValues = unknownSaveValues;
	}
	
	public short[] getUnkRange_prePlayer() {
		return unkRange_prePlayer;
	}

	public void setUnkRange_prePlayer(short[] unkRange_prePlayer) {
		this.unkRange_prePlayer = unkRange_prePlayer;
	}
}
