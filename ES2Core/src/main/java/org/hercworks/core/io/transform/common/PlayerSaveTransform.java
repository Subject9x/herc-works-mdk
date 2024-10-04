package org.hercworks.core.io.transform.common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import org.hercworks.core.data.file.sav.PlayerSave;
import org.hercworks.core.data.struct.MissileType;
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
import org.hercworks.core.io.transform.ThreeSpaceByteTransformer;
import org.hercworks.voln.DataFile;
import org.hercworks.voln.FileType;

import at.favre.lib.bytes.Bytes;

public class PlayerSaveTransform extends ThreeSpaceByteTransformer{

	private int dbgBuffer = 0;
	
	public PlayerSaveTransform() {}
	
	@Override
	public DataFile bytesToObject(byte[] inputArray) throws ClassCastException {
		
		if(inputArray == null || inputArray.length <= 0) {
			return null;
		}
		
		setBytes(inputArray);
		
		PlayerSave save = new PlayerSave();
		save.setExt(FileType.SAV);
		save.setDir(FileType.SAV);
		
		//INVENTORY SEGMENT - 33 entries, matches total weapons in game, ERROR/cut weapon id's ARE included here, but zeroed out.
		System.out.println("Parsing Inventory Segment----------------" + index);
		Inventory inventory = new Inventory();
		inventory.setItems(new Inventory.InventoryItem[WeaponLUT.values().length]);
		for(int i=0; i < WeaponLUT.values().length; i++) {
			System.out.println("	weapon at @" + index);
			byte flag = indexByte();
			short quant = indexShortLE();
			Inventory.InventoryItem entry = inventory.newEntry();
			entry.setId(WeaponLUT.getById((short)(i)));
			entry.setUnlockFlag(flag);
			entry.setQuantity(quant);
			System.out.println("		["+entry.getId().getName()+"]=" + entry.getQuantity());

			ShellWeaponEntry[] items = new ShellWeaponEntry[quant];
			for(int q=0; q < (int)quant; q++) {
				ShellWeaponEntry weapon = new ShellWeaponEntry();
				weapon.setId(WeaponLUT.getById(indexShortLE()));
				System.out.println("			id@" + index + " = " + weapon.getId());
				weapon.setNameId(indexShortLE());
				weapon.setHealthArmor(indexShortLE());
				weapon.setHealthInternal(indexShortLE());
				weapon.setMissileType(MissileType.getById(indexShortLE()));
				System.out.println("					missileType@" + index);
				items[q] = weapon;
			}
			entry.setData(items);
			
			System.out.println("");
			inventory.getItems()[i] = entry;
		}
		save.setInventory(inventory);
		System.out.println("->Invetory loaded, byte index:=" + index);
		
		//Workshop state
 		System.out.println("Parsing Workshop Segment----------------" + index);
		save.setWorkshopSpace(indexShortLE());
		System.out.println("			workspaces@" + index + " = " + save.getWorkshopSpace());
		for(int w=0; w < save.getWorkshopSlots().length; w++) {
			indexShortLE();	//why would these ever be out of order?
			save.getWorkshopSlots()[w] = WeaponLUT.getById((int)indexShortLE());
		}
		System.out.println("->Workshop loaded, byte index:=" + index);
		
		//Campaign flags, series of IN16's
		System.out.println("Parsing Campaign Flags Segment----------------" + index);
		int marker = index;
		for(int f=0; f < save.getUnk4_stateFlags().length; f++) {
			save.getUnk4_stateFlags()[f] = indexShortLE();
		}
		System.out.println("->Campaign flags loaded, byte index:=" + index +", size = " + (index  - marker) + " bytes");
		
		//Squadmate segment
		System.out.println("Parsing Squad Segment----------------" + index);
		PilotEntry[] squad = new PilotEntry[36];	//36 squadmates
		for(int s=0; s < squad.length; s++) {
			squad[s] = indexSquadmate();
		}
		save.setSquadmates(squad);
		System.out.println("->Squadmates loaded, byte index:=" + index);

		//Unknown post-pilot, pre-player 9 short range.
		System.out.println("Parsing Unknown pre-player Segment----------------" + index);
		for(int r=0; r < save.getUnkRange_prePlayer().length; r++) {
			save.getUnkRange_prePlayer()[r] = indexShortLE();
			System.out.println("	" + save.getUnkRange_prePlayer()[r]);
		}
		System.out.println("->Parsed unknown segment, byte index:=" + index);
		
		//Pilot segment
		System.out.println("Parsing Player Pilot Segment----------------" + index);
		save.setPlayerPilot(indexPlayerPilot());
//		System.out.println("->Pilot Entry loaded, byte index:=" + index);
		
		//Herc bay Data
		System.out.println("Parsing Herc Bay entries Segment----------------" + index);
		short baySlots = indexShortLE();
		for(int b=0; b < (int)baySlots; b++) {
			short bayId = indexShortLE();
			save.getHercBay().put(bayId, indexHercEntry());
		}
		System.out.println("->Parsed Herc Bay Segment----------------" + index);
		
		//Herc Unlock Flags 9 bytes
		System.out.println("Parsing Herc Unlocks Segment----------------" + index);
		int l = 0;
		while(l < HercLUT.ACHILLES.getId()) {
			short val = indexShortLE();
			save.getUnlockedHercs().put(HercLUT.getById((short)l), val);
			System.out.println("		"+ HercLUT.getById((short)l).getName() +"=" +val);
			l += 1;
		}
		System.out.println("->Parsed Herc unlock Segment----------------" + index);
		
		//Total available salvage
		System.out.println("Parsing Total available salvage   ----------------" + index);
		save.setSalvageTotal(indexIntLE());
		System.out.println("		Salvage= " + save.getSalvageTotal());
		System.out.println("->Parsed available salvage----------------" + index);

		//Total available salvage
		System.out.println("Parsing unknown bytes   ----------------" + index);
		ByteArrayOutputStream fragmentFlags = new ByteArrayOutputStream();
		while(index < getBytes().length) {
			fragmentFlags.write(indexByte());
		}
		save.setUnknownSaveValues(fragmentFlags.toByteArray());
		System.out.println("->parsed unknown bytes "+ save.getUnknownSaveValues().length);
		System.out.println("->parsed unknown bytes   ----------------" + index);
		
		return save;
	}
	
	private PilotEntry indexSquadmate() {
		PilotEntry entry = new PilotEntry();

		entry.setSquadmateId(indexShortLE());
		System.out.println("			Pilot@" + index + " = " + entry.getSquadmateId());
		System.out.println("			id: " + entry.getSquadmateId());
		
		short nameLen = indexShortLE();
		System.out.println("			name@" + index);
		byte[] name = indexSegment(nameLen);
		
		entry.setName(new String(Bytes.from(name).toCharArray()).substring(0, nameLen - 1));
		System.out.println("				Pilot: " + entry.getName());
		
		entry.setBayId(indexShortLE());
		System.out.println("			bayid @" + (index - 2) + " = " + entry.getBayId());
		
		entry.setActive(indexByte());
		System.out.println("			setActive @" + (index - 1) + " = " + entry.getActive());
		
		entry.setRank(PilotRank.getById(indexShortLE()));
		System.out.println("			Rank @" + (index - 2) + " = " + entry.getRank());
		
		entry.setCrewRowNum(indexShortLE());
		System.out.println("			setCrewRowNum @" + (index - 2) + " = " + entry.getCrewRowNum());
		
		entry.setUnk2_uint16(indexShortLE());
		System.out.println("			setUnk2_uint16 @" + (index - 2) + " = " + entry.getUnk2_uint16());
		
		entry.setProbablyHealth(indexShortLE());
		System.out.println("			setProbablyHealth @" + (index - 2) + " = " + entry.getProbablyHealth());

		entry.setKillsHercs(indexShortLE());
		System.out.println("			setKillsHercs @" + (index - 2) + " = " + entry.getKillsHercs());
		
		entry.setKillsFlyers(indexShortLE());
		System.out.println("			setKillsFlyers @" + (index - 2) + " = " + entry.getKillsFlyers());
		
		entry.setKillsBuilding(indexShortLE());
		System.out.println("			setKillsBuilding @" + (index - 2) + " = " + entry.getKillsBuilding());
		
		entry.setTotalKillHerc(indexShortLE());
		System.out.println("			setTotalKillHerc @" + (index - 2) + " = " + entry.getTotalKillHerc());
		
		entry.setTotalKillFlyer(indexShortLE());
		System.out.println("			setTotalKillFlyer @" + (index - 2) + " = " + entry.getTotalKillFlyer());
		
		entry.setTotalKillBldng(indexShortLE());
		System.out.println("			setTotalKillBldng @" + (index - 2) + " = " + entry.getTotalKillBldng());
		
		entry.setMissionCount(indexShortLE());
		System.out.println("			setMissionCount @" + (index - 2) + " = " + entry.getMissionCount());
		
		entry.setUnk5_uint16(indexShortLE());
		System.out.println("			setUnk5_uint16 @" + (index - 2) + " = " + entry.getUnk5_uint16());
		System.out.println("=======================================================================================");
		
		
		return entry;
	}
	
	//Player data drops the last shorts vs an AI squadmate's data, not sure why ATM.
	private PilotEntry indexPlayerPilot() {
		PilotEntry entry = new PilotEntry();

		System.out.println("	Player Pilot @" + index);
		short nameLen = indexShortLE();
		byte[] name = indexSegment(nameLen);
		
		entry.setName(new String(Bytes.from(name).toCharArray()).substring(0, nameLen - 1));
		entry.setBayId(indexShortLE());
		System.out.println("			bayid @" + (index - 2) + " = " + entry.getBayId());
		
		entry.setActive(indexByte());
		System.out.println("			setActive @" + (index - 1) + " = " + entry.getActive());
		
		entry.setRank(PilotRank.getById(indexShortLE()));
		System.out.println("			Rank @" + (index - 2) + " = " + entry.getRank());
		
		entry.setCrewRowNum(indexShortLE());
		System.out.println("			setCrewRowNum @" + (index - 2) + " = " + entry.getCrewRowNum());
		
		entry.setUnk2_uint16(indexShortLE());
		System.out.println("			setUnk2_uint16 @" + (index - 2) + " = " + entry.getUnk2_uint16());
		
		entry.setProbablyHealth(indexShortLE());
		System.out.println("			setProbablyHealth @" + (index - 2) + " = " + entry.getProbablyHealth());
		
		entry.setKillsHercs(indexShortLE());
		System.out.println("			setKillsHercs @" + (index - 2) + " = " + entry.getKillsHercs());
		
		entry.setKillsFlyers(indexShortLE());
		System.out.println("			setKillsFlyers @" + (index - 2) + " = " + entry.getKillsFlyers());
		
		entry.setKillsBuilding(indexShortLE());
		System.out.println("			setKillsBuilding @" + (index - 2) + " = " + entry.getKillsBuilding());
		
		entry.setTotalKillHerc(indexShortLE());
		System.out.println("			setTotalKillHerc @" + (index - 2) + " = " + entry.getTotalKillHerc());
		
		entry.setTotalKillFlyer(indexShortLE());
		System.out.println("			setTotalKillFlyer @" + (index - 2) + " = " + entry.getTotalKillFlyer());
		
		entry.setTotalKillBldng(indexShortLE());
		System.out.println("			setTotalKillBldng @" + (index - 2) + " = " + entry.getTotalKillBldng());
		
		entry.setMissionCount(indexShortLE());
		System.out.println("			setMissionCount @" + (index - 2) + " = " + entry.getMissionCount());
		System.out.println("=======================================================================================");
		return entry;
	}
	
	private HercBayEntry indexHercEntry() {
		HercBayEntry herc = new HercBayEntry();
		
		herc.setId(HercLUT.getById(indexShortLE()));
		herc.setNameId(indexShortLE());
		
		//Set Herc Externals health values.
		herc.setHealthExternals(new HashMap<HercExternals, ShellHercPart>());
		for(HercExternals e : HercExternals.values()) {
			herc.getHealthExternals().put(e, new ShellHercPart(e.getId(), e.getLabel(), indexShortLE()));
		}
		
		//Set Herc Internals health values.
		//TODO - struct here caps internals to just bipedal hercs.
		herc.setHealthInternals(new HashMap<HercInternals, ShellHercPart>());
		for(HercInternals internal : HercInternals.values()) {
			if(internal.getId() < HercInternals.SERVOS_LEG_LEFT_REAR.getId()) {
				herc.getHealthInternals().put(internal, new ShellHercPart(internal.getId(), internal.getLabel(), indexShortLE()));
			}
		}
		
		//TODO - is the Pilot actually tracked here?
//		ShellHercPart unk1_afterServos = new ShellHercPart((short)99, "unk1_afterServos");
//		unk1_afterServos.getValues()[0] = indexShortLE();
//		herc.setUnk1_afterServos(unk1_afterServos);
		
		for(int h=0; h < herc.getHealthHardpoints().length; h++) {
			ShellHercPart hardpointHp = new ShellHercPart((short)h, "hardpoint_" + h, indexShortLE());
			herc.getHealthHardpoints()[h] = hardpointHp;
		}
		
		//duh, OGRE has 10 hardpoints
//		ShellHercPart unk2_afterHardpoints = new ShellHercPart((short)99, "unk2_afterHardpoints");
//		unk2_afterHardpoints.getValues()[0] = indexShortLE();
//		herc.setUnk2_afterHardpoints(unk2_afterHardpoints);
		
		herc.setBuildPercent(indexShortLE());
		herc.setBuildStepNum(indexShortLE());	
		
		herc.setHardpointMax(indexShortLE());
		herc.setActiveSockets(indexShortLE());
		for(int h=0; h < herc.getActiveSockets(); h++) {
			ShellWeaponEntry weapon = new ShellWeaponEntry();
			short socketId = indexShortLE();
			weapon.setId(WeaponLUT.getById(indexShortLE()));
			weapon.setNameId(indexShortLE());
			weapon.setHealthArmor(indexShortLE());
			weapon.setHealthInternal(indexShortLE());
			weapon.setMissileType(MissileType.getById(indexShortLE()));
			herc.getWeapons().put(socketId, weapon);
		}
		
		return herc;
	}
	@Override
	public byte[] objectToBytes(DataFile source) throws ClassCastException, IOException {
		
		PlayerSave save = (PlayerSave)source;
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		//	INVENTORY SEGMENT - 33 entries, matches total weapons in game, ERROR/cut weapon id's ARE included here, but zeroed out.
		System.out.println("Importing Inventory Segment---------------- @0x"+dbgBuffer);
		for(int i=0; i < save.getInventory().getItems().length; i++) {
			InventoryItem item = save.getInventory().getItems()[i];
			
			out.write((byte)item.getUnlockFlag()); dbgBuffer+=1;
			out.write(writeShortLE(item.getQuantity()));  dbgBuffer+=2;
			
			for(int s=0; s < item.getData().length; s++) {
				
				ShellWeaponEntry entry = item.getData()[s];
				out.write(writeShortLE((short)entry.getId().getId()));  dbgBuffer+=2;
				out.write(writeShortLE((short)entry.getNameId()));  dbgBuffer+=2;
				out.write(writeShortLE(entry.getHealthArmor()));  dbgBuffer+=2;
				out.write(writeShortLE(entry.getHealthInteral()));  dbgBuffer+=2;
				out.write(writeShortLE((short)entry.getMissileType().getId()));  dbgBuffer+=2;
			}
		}
		
		//	WORKSHOP SLOTS
		System.out.println("Importing Workshop Segment---------------- @0x"+dbgBuffer);
		out.write(writeShortLE(save.getWorkshopSpace())); dbgBuffer+=2;
		for(int w=0; w < save.getWorkshopSlots().length; w++) {
			out.write(writeShortLE((short)w)); dbgBuffer+=2;
			out.write(writeShortLE((short)save.getWorkshopSlots()[w].getId())); dbgBuffer+=2;
		}
		
		//	CAMPAIGN FLAGS	
		System.out.println("Importing Campaign Flags Segment---------------- @0x"+dbgBuffer);
		for(short f : save.getUnk4_stateFlags()) {
			out.write(writeShortLE(f));  dbgBuffer+=2;
		}
		
		//	PILOT DATA
		System.out.println("Importing Squadmate Data Segment---------------- @0x"+dbgBuffer);
		for(PilotEntry pilot : save.getSquadmates()) {
			writePilotData(pilot, out, false);
		}
		out.flush();
		
		//	UNKNOWN PILOT DATA
		System.out.println("Importing Unknown Pilot Data Segment---------------- @0x"+dbgBuffer);
		for(short unk : save.getUnkRange_prePlayer()) {
			out.write(writeShortLE(unk)); dbgBuffer+=2;
		}
		
		//	PLAYER PILOT
		System.out.println("Importing Player Pilot Segment---------------- @0x"+dbgBuffer);
		writePilotData(save.getPlayerPilot(), out, true);

		//	HERC DATA
		System.out.println("Importing Herc Bay Entries Segment----------------@0x"+dbgBuffer);
		out.write(writeShortLE((short)save.getHercBay().size()));
		for(int h=0; h < save.getHercBay().size(); h++) {
			writeHercEntry((short)h, save.getHercBay().get((short)h), out);
		}
		
		//	HERC UNLOCKS
		System.out.println("Importing Herc Unlocks Segment----------------@0x"+dbgBuffer);
		for(HercLUT herc : HercLUT.values()) {
			if(herc.getId() < HercLUT.ACHILLES.getId()) {
				out.write(writeShortLE((short)1)); dbgBuffer+=2;
			}
		}
		
		//	SALVAGE
		System.out.println("Importing Salvage Segment----------------@0x"+dbgBuffer);
		out.write(writeIntLE(save.getSalvageTotal())); dbgBuffer+=4;
		
		//	UNKNOWN TAIL SEGMENT
		System.out.println("Importing Unknown Bytes Segment----------------@0x"+dbgBuffer);
		for(byte b : save.getUnknownSaveValues()) {
			out.write(b); dbgBuffer+=1;
		}
		out.flush();
		
		return out.toByteArray();
	}

	private void writePilotData(PilotEntry pilot, ByteArrayOutputStream outArr, boolean isPlayer) throws IOException {
		if(!isPlayer) {
			outArr.write(writeShortLE(pilot.getSquadmateId())); dbgBuffer+=2;
		}
		byte[] arr = Bytes.from(pilot.getName().toCharArray(), StandardCharsets.UTF_8).append((byte)0x00).array();
		
		outArr.write(writeShortLE((short)arr.length)); dbgBuffer+=2;
		outArr.write(arr);   dbgBuffer+=arr.length;
		
		outArr.write(writeShortLE(pilot.getBayId()));  dbgBuffer+=2;
		outArr.write(pilot.getActive());  dbgBuffer+=1;
		outArr.write(writeShortLE(pilot.getRank().getId()));  dbgBuffer+=2;
		outArr.write(writeShortLE(pilot.getCrewRowNum()));  dbgBuffer+=2;
		outArr.write(writeShortLE(pilot.getUnk2_uint16()));  dbgBuffer+=2;
		outArr.write(writeShortLE(pilot.getProbablyHealth()));  dbgBuffer+=2;
		outArr.write(writeShortLE(pilot.getKillsHercs()));  dbgBuffer+=2;
		outArr.write(writeShortLE(pilot.getKillsFlyers()));  dbgBuffer+=2;
		outArr.write(writeShortLE(pilot.getKillsBuilding()));  dbgBuffer+=2;
		outArr.write(writeShortLE(pilot.getTotalKillHerc()));  dbgBuffer+=2;
		outArr.write(writeShortLE(pilot.getTotalKillFlyer()));  dbgBuffer+=2;
		outArr.write(writeShortLE(pilot.getTotalKillBldng()));  dbgBuffer+=2;
		outArr.write(writeShortLE(pilot.getMissionCount()));  dbgBuffer+=2;
		
		if(!isPlayer) {
			outArr.write(writeShortLE(pilot.getUnk5_uint16()));	 dbgBuffer+=2;
		}
		
	}

	private void writeHercEntry(short bayId, HercBayEntry herc, ByteArrayOutputStream outArr) throws IOException{
		
		outArr.write(writeShortLE(bayId)); dbgBuffer+=2;
		outArr.write(writeShortLE(herc.getId().getId())); dbgBuffer+=2;
		outArr.write(writeShortLE(herc.getNameId())); dbgBuffer+=2;
		
		for(HercExternals external : HercExternals.values()) {
			outArr.write(writeShortLE(herc.getHealthExternals().get(external).getHealth())); dbgBuffer+=2;
		}
		
		for(HercInternals internal : HercInternals.values()) {
			if(internal.getId() < HercInternals.SERVOS_LEG_LEFT_REAR.getId()) {
				outArr.write(writeShortLE(herc.getHealthInternals().get(internal).getHealth()));	 dbgBuffer+=2;
			}	
		}
		
		for(ShellHercPart part : herc.getHealthHardpoints()) {
			if(part == null) {
				outArr.write(writeShortLE((short)100)); dbgBuffer+=2;
			}
			else {
				outArr.write(writeShortLE(part.getHealth()));	 dbgBuffer+=2;
			}
		}
		
		outArr.write(writeShortLE(herc.getBuildPercent())); dbgBuffer+=2;
		outArr.write(writeShortLE(herc.getBuildStepNum())); dbgBuffer+=2;
		
		outArr.write(writeShortLE(herc.getHardpointMax())); dbgBuffer+=2;
		outArr.write(writeShortLE(herc.getActiveSockets())); dbgBuffer+=2;
		
		for(int w=0; w < herc.getActiveSockets(); w++) {
			ShellWeaponEntry weapon = herc.getWeapons().get((short)w);
			outArr.write(writeShortLE((short)w)); dbgBuffer+=2;
			outArr.write(writeShortLE((short)weapon.getId().getId())); dbgBuffer+=2;
			outArr.write(writeShortLE((short)weapon.getNameId())); dbgBuffer+=2;
			outArr.write(writeShortLE(weapon.getHealthArmor())); dbgBuffer+=2;
			outArr.write(writeShortLE(weapon.getHealthInteral())); dbgBuffer+=2;
			outArr.write(writeShortLE((short)weapon.getMissileType().getId())); dbgBuffer+=2;
		}
	}
}
