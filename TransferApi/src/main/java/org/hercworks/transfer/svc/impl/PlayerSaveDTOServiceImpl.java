package org.hercworks.transfer.svc.impl;

import java.util.HashMap;

import org.hercworks.core.data.file.sav.PlayerSave;
import org.hercworks.core.data.struct.WeaponLUT;
import org.hercworks.core.data.struct.herc.HercExternals;
import org.hercworks.core.data.struct.herc.HercInternals;
import org.hercworks.core.data.struct.herc.HercLUT;
import org.hercworks.core.data.struct.vshell.hercs.ShellHercPart;
import org.hercworks.core.data.struct.vshell.sav.HercBayEntry;
import org.hercworks.core.data.struct.vshell.sav.Inventory;
import org.hercworks.core.data.struct.vshell.sav.Inventory.InventoryItem;
import org.hercworks.core.data.struct.vshell.sav.PilotEntry;
import org.hercworks.core.data.struct.vshell.sav.PilotRank;
import org.hercworks.core.data.struct.vshell.sav.ShellWeaponEntry;
import org.hercworks.transfer.dto.file.TransferObject;
import org.hercworks.transfer.dto.file.sys.sav.HercBayEntryDTO;
import org.hercworks.transfer.dto.file.sys.sav.PlayerSaveFileDTO;
import org.hercworks.transfer.dto.file.sys.sav.PlayerSavePilotDataDTO;
import org.hercworks.transfer.dto.struct.shell.HercHardpointDTO;
import org.hercworks.transfer.dto.struct.sys.SaveInventoryEntryDTO;
import org.hercworks.transfer.dto.struct.sys.WeaponEntryDTO;
import org.hercworks.transfer.svc.GeneralDTOService;
import org.hercworks.voln.DataFile;
import org.hercworks.voln.FileType;

public class PlayerSaveDTOServiceImpl implements GeneralDTOService {

	@Override
	public TransferObject convertToDTO(DataFile source) {
		
		PlayerSave save = (PlayerSave)source;
		
		PlayerSaveFileDTO dto = new PlayerSaveFileDTO();
		
		//	PLAYER WEAPON INVENTORY BLOCK
		//we skip 0, which is the empty weapon.
		dto.setInventory(new HashMap<String, SaveInventoryEntryDTO>());
		for(int i=1; i < save.getInventory().getItems().length; i++){
			InventoryItem item = save.getInventory().getItems()[i];
			SaveInventoryEntryDTO itemDTO = new SaveInventoryEntryDTO();
			itemDTO.setData(new WeaponEntryDTO[item.getData().length]);
			
			itemDTO.setUnlockFlag(item.getUnlockFlag());
			
			for(int q=0; q < itemDTO.getData().length; q++) {
				ShellWeaponEntry entry = item.getData()[q];
				WeaponEntryDTO weapon = new WeaponEntryDTO();

				weapon.setArmor(entry.getHealthArmor());
				weapon.setStructure(entry.getHealthInteral());
				weapon.setMissileType(entry.getMissileType());
				
				itemDTO.getData()[q] = weapon;
			}
			
			dto.getInventory().put(item.getId().getName(), itemDTO);
		}
		
		// WORKSHOP DATA BLOCK
		dto.setWorkshop(new HashMap<Integer, String>());
		for(int w=0; w < save.getWorkshopSlots().length; w++) {
			dto.getWorkshop().put(w, save.getWorkshopSlots()[w].getName());
		}
		
		//  UNKNOWN MISSION FLAGS?
		dto.setUnkFlags(new int[save.getUnk4_stateFlags().length]);
		for(int f=0; f < save.getUnk4_stateFlags().length; f++) {
			dto.getUnkFlags()[f] = save.getUnk4_stateFlags()[f];
		}
		
		//	SQUADMATE PILOT DATA
		dto.setPilots(new PlayerSavePilotDataDTO[save.getSquadmates().length]);
		for(int p=0; p < save.getSquadmates().length; p++) {
			dto.getPilots()[p] = exportPilotData(new PlayerSavePilotDataDTO(), save.getSquadmates()[p]);
		}
				
		//	UNKNOWN RANGE 
		for(int u=0; u < 9; u++) {
			dto.getUnknownDataPilots()[u] = save.getUnkRange_prePlayer()[u];
		}
		
		//	PLAYER PILOT DATA
		dto.setPlayerPilot(exportPilotData(new PlayerSavePilotDataDTO(), save.getPlayerPilot()));
		
		//	HERC BAY SEGMENT
		dto.setHercBay(new HercBayEntryDTO[save.getHercBay().size()]);
		for(int b=0; b < dto.getHercBay().length; b++) {
			dto.getHercBay()[b] = exportHercBayEntry(new HercBayEntryDTO(), save.getHercBay().get((short)b));
		}
		
		//	UNLOCKED HERCS
		dto.setHercUnlocks(new HashMap<String, Integer>());
		for(HercLUT herc : save.getUnlockedHercs().keySet()) {
			dto.getHercUnlocks().put(herc.getAbbrevDat(), (int)save.getUnlockedHercs().get(herc));
		}
		
		//	SALVAGE
		dto.setSalvage(save.getSalvageTotal());
		
		dto.setUnknownSaveValues(new int[save.getUnknownSaveValues().length]);
		for(int v=0; v < save.getUnknownSaveValues().length; v++) {
			dto.getUnknownSaveValues()[v] = (int)save.getUnknownSaveValues()[v];
		}
		
		return dto;
	}

	
	private PlayerSavePilotDataDTO exportPilotData(PlayerSavePilotDataDTO pilotDTO, PilotEntry data) {
		
		pilotDTO.setSquadmateId(data.getSquadmateId());
		pilotDTO.setName(data.getName());
		pilotDTO.setBayId(data.getBayId());
		pilotDTO.setActiveFlag(data.getActive()==(byte)1 ? true : false);
		pilotDTO.setRank(data.getRank().getLabel());
		pilotDTO.setCrewRowNum(data.getCrewRowNum());
		pilotDTO.setUnk2_uint16(data.getUnk2_uint16());
		pilotDTO.setProbablyHealth(data.getProbablyHealth());
		
		pilotDTO.getKills().put("hercs", (int)data.getKillsHercs());
		pilotDTO.getKills().put("flyers", (int)data.getKillsFlyers());
		pilotDTO.getKills().put("building", (int)data.getKillsBuilding());
		
		pilotDTO.getTotalKills().put("hercs", (int)data.getTotalKillHerc());
		pilotDTO.getTotalKills().put("flyers", (int)data.getTotalKillFlyer());
		pilotDTO.getTotalKills().put("building", (int)data.getTotalKillBldng());
		
		pilotDTO.setMissionCount(data.getMissionCount());
		pilotDTO.setUnk5_uint16(data.getUnk5_uint16());
		
		return pilotDTO;
	}
	
	private HercBayEntryDTO exportHercBayEntry(HercBayEntryDTO hercDTO, HercBayEntry entry) {
		
		hercDTO.setId(entry.getId().getName());
		
		for(HercExternals extern : HercExternals.values()) {
			
			ShellHercPart part = entry.getHealthExternals().get(extern);
			
			if(part != null) {
				hercDTO.getHealthExternals().put(extern.getLabel(), (int)part.getHealth());
			}
		}
		
		for(HercInternals intern : entry.getHealthInternals().keySet()) {
			ShellHercPart part = entry.getHealthInternals().get(intern);
			hercDTO.getHealthInternals().put(intern.getLabel(), (int)part.getHealth());
		}
	
		for(ShellHercPart hardpoint : entry.getHealthHardpoints()) {
			if(hardpoint.getId() < entry.getId().getHardpointMax()) {
				hercDTO.getHardpoints().put(((int)hardpoint.getId())+1, (int)hardpoint.getHealth());
			}
			//the save file actually tracks all 9 hardpoints for some reason, so the
			//DTO service will fill in the unused ones as default 100 (0064)
		}
		
		hercDTO.setBuildPercent(entry.getBuildPercent());
		hercDTO.setBuildStepNum(entry.getBuildStepNum());
		
		//Map<Integer, HercHardpointDTO> weapons
		hercDTO.setWeapons(new HercHardpointDTO[entry.getWeapons().values().size()]);
		int idx = 0;	//harpdoint list actually can be any order.
		for(Short wepSlot : entry.getWeapons().keySet()) {
			ShellWeaponEntry weapon = entry.getWeapons().get(wepSlot);
			HercHardpointDTO wpnDTO = new HercHardpointDTO();
			
			wpnDTO.setHardpoint(wepSlot);
			wpnDTO.setItem(weapon.getId());
			wpnDTO.setArmor(weapon.getHealthArmor());
			wpnDTO.setStructure(weapon.getHealthInteral());
			wpnDTO.setMissileType(weapon.getMissileType());
			
			hercDTO.getWeapons()[idx] = wpnDTO;
			idx += 1;
		}
		
		return hercDTO;
	}
	
	
	@Override
	public DataFile fromDTO(TransferObject source) {

		PlayerSaveFileDTO dto = (PlayerSaveFileDTO)source;
		PlayerSave save = new PlayerSave();
		save.setExt(FileType.SAV);
		save.setDir(FileType.SAV);
		
		//	PLAYER WEAPON INVENTORY BLOCK
		//we skip 0, which is the empty weapon.
		Inventory saveInv = new Inventory();
		saveInv.setItems(new InventoryItem[ WeaponLUT.values().length]);
		for(WeaponLUT weapon : WeaponLUT.values()) {
			InventoryItem entry = saveInv.newEntry();
			if(weapon.equals(WeaponLUT.NONE)) {
				entry.setId(weapon);
				entry.setQuantity((short)0);
				entry.setData(new ShellWeaponEntry[0]);
			}
			else {
				SaveInventoryEntryDTO dtoItem = dto.getInventory().get(weapon.getName());
				
				entry.setId(weapon);
				entry.setQuantity((short)dtoItem.getData().length);
				entry.setUnlockFlag((short)dtoItem.getUnlockFlag());
				
				entry.setData(new ShellWeaponEntry[dtoItem.getData().length]);
				for(int q=0; q < dtoItem.getData().length; q++) {
					ShellWeaponEntry entryStock = new ShellWeaponEntry();
					WeaponEntryDTO dtoStock = dtoItem.getData()[q];
					
					entryStock.setId(weapon);
					entryStock.setMissileType(dtoStock.getMissileType());
					entryStock.setNameId((short)weapon.getId());
					entryStock.setHealthArmor((short)dtoStock.getArmor());
					entryStock.setHealthInternal((short)dtoStock.getStructure());
					
					entry.getData()[q] = entryStock;
				}
			}
			saveInv.getItems()[weapon.getId()] = entry;
		}
		save.setInventory(saveInv);
		
		// WORKSHOP DATA BLOCK
		int cnt = 0;
		for(int w=0; w < 5; w++) {
			String label = dto.getWorkshop().get(w);
			WeaponLUT wep = WeaponLUT.NONE;
			if(label != null && !label.equals("")) {
				wep = WeaponLUT.getByName(label);
				if(wep == null) {
					wep = WeaponLUT.NONE;
				}
			}
			if(!wep.equals(WeaponLUT.NONE)) {
				cnt += 1;
			}
			save.getWorkshopSlots()[w] = wep;
		}
		save.setWorkshopSpace((short)(5 - cnt));
		
		//  UNKNOWN MISSION FLAGS?
		save.setUnk4_stateFlags(new short[dto.getUnkFlags().length]);
		for(int f=0; f < dto.getUnkFlags().length; f++) {
			save.getUnk4_stateFlags()[f] = (short)dto.getUnkFlags()[f];
		}
		
		//	SQUADMATE PILOT DATA
		save.setSquadmates(new PilotEntry[dto.getPilots().length]);
		for(int p=0; p < dto.getPilots().length; p++) {
			save.getSquadmates()[p] = importPilotData(new PilotEntry(), dto.getPilots()[p]);	
		}
		
		//	UNKNOWN RANGE 
		for(int u=0; u < 9; u++) {
			save.getUnkRange_prePlayer()[u] = (short)dto.getUnknownDataPilots()[u];
		}
		
		//	PLAYER PILOT DATA
		save.setPlayerPilot(importPilotData(new PilotEntry(), dto.getPlayerPilot()));
		
		//	HERC BAY SEGMENT
		save.setHercBay(new HashMap<Short, HercBayEntry>());
		for(int b=0; b < dto.getHercBay().length; b++) {
			save.getHercBay().put((short)b, importHercBayEntry(new HercBayEntry(), dto.getHercBay()[b]));
		}
		
		//	UNLOCKED HERCS
		for(HercLUT herc : HercLUT.values()) {
			Integer unlock = dto.getHercUnlocks().get(herc.getName());
			if(unlock != null) {
				save.getUnlockedHercs().put(herc, unlock.shortValue());
			}
		}
	
		//	SALVAGE
		save.setSalvageTotal(dto.getSalvage());
		
		// BIG UNKNOWN TAIL RANGE
		save.setUnknownSaveValues(new byte[dto.getUnknownSaveValues().length]);
		for(int v=0; v < save.getUnknownSaveValues().length; v++) {
			save.getUnknownSaveValues()[v] = (byte)dto.getUnknownSaveValues()[v];
		}
		
		
		return save;
	}
	
	private PilotEntry importPilotData(PilotEntry pilot, PlayerSavePilotDataDTO dtoPilot) {
		
		 pilot.setSquadmateId((short)dtoPilot.getSquadmateId());
		 pilot.setName(dtoPilot.getName());
		 pilot.setBayId((short)dtoPilot.getBayId());
		 
		 pilot.setActive((byte)(dtoPilot.getActiveFlag() ? 1 : 0));
		 
		 pilot.setRank(PilotRank.getByName(dtoPilot.getRank()));
		 pilot.setCrewRowNum((short)dtoPilot.getCrewRowNum());
		 pilot.setUnk2_uint16((short)dtoPilot.getUnk2_uint16());
		 pilot.setProbablyHealth((short)dtoPilot.getProbablyHealth());
		 
		 pilot.setKillsHercs(dtoPilot.getKills().get("hercs").shortValue());
		 pilot.setKillsFlyers(dtoPilot.getKills().get("flyers").shortValue());
		 pilot.setKillsBuilding(dtoPilot.getKills().get("building").shortValue());
		 
		 pilot.setTotalKillHerc(dtoPilot.getKills().get("hercs").shortValue());
		 pilot.setTotalKillFlyer(dtoPilot.getKills().get("flyers").shortValue());
		 pilot.setTotalKillHerc(dtoPilot.getKills().get("building").shortValue());
		 
		 pilot.setMissionCount((short)dtoPilot.getMissionCount());
		 pilot.setUnk5_uint16((short)dtoPilot.getUnk5_uint16());
		
		return pilot;
	}

	private HercBayEntry importHercBayEntry(HercBayEntry hercBay, HercBayEntryDTO hercDTO) {
		
		hercBay.setId(HercLUT.getByName(hercDTO.getId()));
		hercBay.setNameId(hercBay.getId().getId());
		hercBay.setHardpointMax(hercBay.getId().getHardpointMax());
		
		hercBay.setHealthExternals(new HashMap<HercExternals, ShellHercPart>());
		for(HercExternals external : HercExternals.values()) {
			Integer health = hercDTO.getHealthExternals().get(external.getLabel());
			
			if(health != null) {
				hercBay.getHealthExternals().put(external, new ShellHercPart(external.getId(), external.getLabel(), health.shortValue()));	
			}
		}
		
		hercBay.setHealthInternals(new HashMap<HercInternals, ShellHercPart>());
		for(HercInternals internal : HercInternals.values()) {
			Integer health = hercDTO.getHealthInternals().get(internal.getLabel());
			
			if(health != null) {
				hercBay.getHealthInternals().put(internal, new ShellHercPart(internal.getId(), internal.getLabel(), health.shortValue()));
			}
		}
		
		hercBay.setHealthHardpoints(new ShellHercPart[10]);
		for(int h=0; h < 10; h++) {
			Integer health = hercDTO.getHardpoints().get(h);
			
			if(health != null) {
				hercBay.getHealthHardpoints()[h] = new ShellHercPart((short)99, "hardpoint_" + h, health.shortValue());
			}
		}
		
		hercBay.setBuildPercent((short)hercDTO.getBuildPercent());
		hercBay.setBuildStepNum((short)hercDTO.getBuildStepNum());
		hercBay.setActiveSockets((short)hercDTO.getWeapons().length);
		hercBay.setWeapons(new HashMap<Short, ShellWeaponEntry>());
		
		for(int w=0; w < hercBay.getActiveSockets(); w++) {
			HercHardpointDTO hardpointDTO = hercDTO.getWeapons()[w];
			ShellWeaponEntry weapon = new ShellWeaponEntry();
			
			weapon.setId(hardpointDTO.getItem());
			weapon.setMissileType(hardpointDTO.getMissileType());
			weapon.setNameId((short)hardpointDTO.getItem().getId());
			weapon.setHealthArmor((short)hardpointDTO.getArmor());
			weapon.setHealthInternal((short)hardpointDTO.getStructure());
			
			hercBay.getWeapons().put((short)w, weapon);
		}
		
		return hercBay;
	}	
}
