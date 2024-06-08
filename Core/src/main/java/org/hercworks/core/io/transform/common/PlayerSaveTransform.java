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
			System.out.println("	weapon at index=" + index);
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
				weapon.setNameId(indexShortLE());
				short[] hp = new short[2];
				hp[0] = indexShortLE();
				hp[1] = indexShortLE();
				weapon.setHealth(hp);
				weapon.setMissileType(MissileType.getById(indexShortLE()));
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
		for(int w=0; w<5; w++) {
			indexShortLE();	//why would these ever be out of order?
			save.getWorkshopSlots()[w] = WeaponLUT.getById((int)indexShortLE());
		}
		System.out.println("->Workshop loaded, byte index:=" + index);
		
		//Campaign flags, series of IN16's
		System.out.println("Parsing Campaign Flags Segment----------------" + index);
		for(int f=0; f<77; f++) {
			short flag = indexShortLE();
			save.getUnk4_stateFlags()[f] = flag;
		}
		System.out.println("->Campaign flags loaded, byte index:=" + index);
		
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
		System.out.println("->Pilot Entry loaded, byte index:=" + index);
		
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
		
		short nameLen = indexShortLE();
		byte[] name = indexSegment(nameLen);
		
		entry.setName(new String(Bytes.from(name).toCharArray()).substring(0, nameLen - 1));
		entry.setBayId(indexShortLE());
		entry.setUnk1_uint8(indexByte());
		entry.setRank(PilotRank.getById(indexShortLE()));
		entry.setCrewRowNum(indexShortLE());
		entry.setUnk2_uint16(indexShortLE());
		entry.setProbablyHealth(indexShortLE());

		entry.setKillsHercs(indexShortLE());
		entry.setKillsFlyers(indexShortLE());
		entry.setKillsBuilding(indexShortLE());
		entry.setTotalKillHerc(indexShortLE());
		entry.setTotalKillFlyer(indexShortLE());
		entry.setTotalKillBldng(indexShortLE());
		
		entry.setMissionCount(indexShortLE());
		entry.setUnk5_uint16(indexShortLE());
		
		System.out.println("	id: " + entry.getSquadmateId());
		System.out.println("	Pilot: " + entry.getName());
		
		return entry;
	}
	
	//Player data drops the last 2 shorts vs an AI squadmate's data, not sure why ATM.
	private PilotEntry indexPlayerPilot() {
		PilotEntry entry = new PilotEntry();

		short nameLen = indexShortLE();
		byte[] name = indexSegment(nameLen);
		
		entry.setName(new String(Bytes.from(name).toCharArray()).substring(0, nameLen - 1));
		entry.setBayId(indexShortLE());
		entry.setUnk1_uint8(indexByte());
		entry.setRank(PilotRank.getById(indexShortLE()));
		entry.setCrewRowNum(indexShortLE());
		entry.setUnk2_uint16(indexShortLE());
		entry.setProbablyHealth(indexShortLE());
		entry.setKillsHercs(indexShortLE());
		entry.setKillsFlyers(indexShortLE());
		entry.setKillsBuilding(indexShortLE());
		entry.setTotalKillHerc(indexShortLE());
		entry.setTotalKillFlyer(indexShortLE());
		entry.setTotalKillBldng(indexShortLE());
		entry.setMissionCount(indexShortLE());
		
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
			weapon.getHealth()[0] = indexShortLE();
			weapon.getHealth()[1] = indexShortLE();
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
		System.out.println("Importing Inventory Segment----------------");
		for(int i=0; i < save.getInventory().getItems().length; i++) {
			InventoryItem item = save.getInventory().getItems()[i];
			
			out.write((byte)item.getUnlockFlag());
			out.write(writeShortLE(item.getQuantity()));
			for(int s=0; s < item.getData().length; s++) {
				ShellWeaponEntry entry = item.getData()[s];
				out.write(writeShortLE((short)entry.getId().getId()));
				out.write(writeShortLE((short)entry.getNameId()));
				out.write(writeShortLE(entry.getHealth()[0]));
				out.write(writeShortLE(entry.getHealth()[0]));
				out.write(writeShortLE((short)entry.getMissileType().getId()));
			}
		}
		
		//	WORKSHOP SLOTS
		System.out.println("Importing Workshop Segment----------------");
		out.write(writeShortLE(save.getWorkshopSpace()));
		for(int w=0; w < 5; w++) {
			out.write(writeShortLE((short)w));
			out.write(writeShortLE((short)save.getWorkshopSlots()[w].getId()));
		}
		
		//	CAMPAIGN FLAGS	
		System.out.println("Importing Campaign Flags Segment----------------");
		for(short f : save.getUnk4_stateFlags()) {
			out.write(writeShortLE(f));
		}
		
		//	PILOT DATA
		System.out.println("Importing Squadmate Data Segment----------------");
		for(PilotEntry pilot : save.getSquadmates()) {
			writePilotData(pilot, out, false);
		}
		
		//	UNKNOWN PILOT DATA
		System.out.println("Importing Unknown Pilot Data Segment----------------");
		for(short unk : save.getUnkRange_prePlayer()) {
			out.write(writeShortLE(unk));
		}
		
		//	PLAYER PILOT
		System.out.println("Importing Player Pilot Segment----------------");
		writePilotData(save.getPlayerPilot(), out, true);

		//	HERC DATA
		System.out.println("Importing Herc Bay Entries Segment----------------");
		out.write(writeShortLE((short)save.getHercBay().size()));
		for(int h=0; h < save.getHercBay().size(); h++) {
			writeHercEntry((short)h, save.getHercBay().get((short)h), out);
		}
		
		//	HERC UNLOCKS
		System.out.println("Importing Herc Unlocks Segment----------------");
		for(HercLUT herc : HercLUT.values()) {
			if(herc.getId() < HercLUT.ACHILLES.getId()) {
				out.write(writeShortLE((short)1));
			}
		}
		
		//	SALVAGE
		System.out.println("Importing Salvage Segment----------------");
		out.write(writeIntLE(save.getSalvageTotal()));
		
		//	UNKNOWN TAIL SEGMENT
		System.out.println("Importing Unknown Bytes Segment----------------");
		for(byte b : save.getUnknownSaveValues()) {
			out.write(b);
		}
		
		return out.toByteArray();
	}

	private void writePilotData(PilotEntry pilot, ByteArrayOutputStream outArr, boolean isPlayer) throws IOException {
		
		outArr.write(writeShortLE(pilot.getSquadmateId()));
		
		byte[] arr = Bytes.from(pilot.getName().toCharArray(), StandardCharsets.UTF_8).append((byte)0x00).array();
		
		outArr.write(writeShortLE((short)arr.length));
		outArr.write(arr);
		
		outArr.write(writeShortLE(pilot.getBayId()));
		outArr.write(pilot.getUnk1_uint8());
		outArr.write(writeShortLE(pilot.getRank().getId()));
		outArr.write(writeShortLE(pilot.getCrewRowNum()));
		outArr.write(writeShortLE(pilot.getUnk2_uint16()));
		outArr.write(writeShortLE(pilot.getProbablyHealth()));
		outArr.write(writeShortLE(pilot.getKillsHercs()));
		outArr.write(writeShortLE(pilot.getKillsFlyers()));
		outArr.write(writeShortLE(pilot.getKillsBuilding()));
		outArr.write(writeShortLE(pilot.getTotalKillHerc()));
		outArr.write(writeShortLE(pilot.getTotalKillFlyer()));
		outArr.write(writeShortLE(pilot.getTotalKillBldng()));
		outArr.write(writeShortLE(pilot.getMissionCount()));
		
		if(!isPlayer) {
			outArr.write(writeShortLE(pilot.getUnk5_uint16()));	
		}
		
	}

	private void writeHercEntry(short bayId, HercBayEntry herc, ByteArrayOutputStream outArr) throws IOException{
		
		outArr.write(writeShortLE(bayId));
		outArr.write(writeShortLE(herc.getId().getId()));
		outArr.write(writeShortLE(herc.getNameId()));
		
		for(HercExternals external : HercExternals.values()) {
			outArr.write(writeShortLE(herc.getHealthExternals().get(external).getHealth()));
		}
		
		for(HercInternals internal : HercInternals.values()) {
			if(internal.getId() < HercInternals.SERVOS_LEG_LEFT_REAR.getId()) {
				outArr.write(writeShortLE(herc.getHealthInternals().get(internal).getHealth()));	
			}	
		}
		
		for(ShellHercPart part : herc.getHealthHardpoints()) {
			if(part == null) {
				outArr.write(writeShortLE((short)100));
			}
			else {
				outArr.write(writeShortLE(part.getHealth()));	
			}
			
		}
		
		outArr.write(writeShortLE(herc.getBuildPercent()));
		outArr.write(writeShortLE(herc.getBuildStepNum()));
		
		outArr.write(writeShortLE(herc.getHardpointMax()));
		outArr.write(writeShortLE(herc.getActiveSockets()));
		
		for(int w=0; w < herc.getActiveSockets(); w++) {
			ShellWeaponEntry weapon = herc.getWeapons().get((short)w);
			outArr.write(writeShortLE((short)weapon.getId().getId()));
			outArr.write(writeShortLE((short)weapon.getNameId()));
			outArr.write(writeShortLE(weapon.getHealth()[0]));
			outArr.write(writeShortLE(weapon.getHealth()[1]));
			outArr.write(writeShortLE((short)weapon.getMissileType().getId()));
			
		}
	}
	
}
