package org.hercworks.transfer.dto.file.sys.sav;

import java.util.Map;

import org.hercworks.transfer.dto.file.TransferObject;
import org.hercworks.transfer.dto.struct.sys.SaveInventoryEntryDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("")
public class PlayerSaveFileDTO extends TransferObject {

	@JsonProperty(value = "inventory", index = 0)
	private Map<String, SaveInventoryEntryDTO> inventory;
	
	@JsonProperty(value = "workshop", index = 1)
	private Map<Integer, String> workshop;
	
	@JsonProperty(value = "unknown_mission_flags", index = 2)
	private int[] unkFlags;
	
	@JsonProperty(value = "pilots", index = 3)
	private PlayerSavePilotDataDTO[] pilots;
	
	@JsonProperty(value = "unk_range_pilots", index = 4)
	private int[] unknownDataPilots = new int[9];
	
	@JsonProperty(value = "player_pilot", index = 5)
	private PlayerSavePilotDataDTO playerPilot;
	
	@JsonProperty(value = "herc_bay", index = 6)
	private HercBayEntryDTO[] hercBay;
	
	@JsonProperty(value = "unlocked_hercs", index = 7)
	private Map<String, Integer> hercUnlocks;
	
	@JsonProperty(value = "current_salvage", index = 8)
	private int salvage = 0;
	
	@JsonProperty(value = "unknown_save_values", index = 9)
	private int[] unknownSaveValues;
	
	public PlayerSaveFileDTO() {}

	public Map<String, SaveInventoryEntryDTO> getInventory() {
		return inventory;
	}

	public Map<Integer, String> getWorkshop() {
		return workshop;
	}

	public int[] getUnkFlags() {
		return unkFlags;
	}

	public PlayerSavePilotDataDTO[] getPilots() {
		return pilots;
	}

	public int[] getUnknownDataPilots() {
		return unknownDataPilots;
	}

	public PlayerSavePilotDataDTO getPlayerPilot() {
		return playerPilot;
	}

	public HercBayEntryDTO[] getHercBay() {
		return hercBay;
	}

	public Map<String, Integer> getHercUnlocks() {
		return hercUnlocks;
	}

	public int getSalvage() {
		return salvage;
	}

	public int[] getUnknownSaveValues() {
		return unknownSaveValues;
	}

	public void setInventory(Map<String, SaveInventoryEntryDTO> inventory) {
		this.inventory = inventory;
	}

	public void setWorkshop(Map<Integer, String> workshop) {
		this.workshop = workshop;
	}

	public void setUnkFlags(int[] unkFlags) {
		this.unkFlags = unkFlags;
	}

	public void setPilots(PlayerSavePilotDataDTO[] pilots) {
		this.pilots = pilots;
	}

	public void setUnknownDataPilots(int[] unknownDataPilots) {
		this.unknownDataPilots = unknownDataPilots;
	}

	public void setPlayerPilot(PlayerSavePilotDataDTO playerPilot) {
		this.playerPilot = playerPilot;
	}

	public void setHercBay(HercBayEntryDTO[] hercBay) {
		this.hercBay = hercBay;
	}

	public void setHercUnlocks(Map<String, Integer> hercUnlocks) {
		this.hercUnlocks = hercUnlocks;
	}

	public void setSalvage(int salvage) {
		this.salvage = salvage;
	}

	public void setUnknownSaveValues(int[] unknownSaveValues) {
		this.unknownSaveValues = unknownSaveValues;
	}
}
