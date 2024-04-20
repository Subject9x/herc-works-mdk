package org.hercworks.core.io.transform.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hercworks.core.data.file.sav.PlayerSave;
import org.hercworks.core.data.struct.WeaponLUT;
import org.hercworks.core.data.struct.herc.HercExternals;
import org.hercworks.core.data.struct.herc.HercInternals;
import org.hercworks.core.data.struct.herc.HercLUT;
import org.hercworks.core.data.struct.vshell.hercs.ShellHercPart;
import org.hercworks.core.data.struct.vshell.sav.HercBayEntry;
import org.hercworks.core.data.struct.vshell.sav.Inventory;
import org.hercworks.core.data.struct.vshell.sav.PilotEntry;
import org.hercworks.core.data.struct.vshell.sav.PilotRank;
import org.hercworks.core.data.struct.vshell.sav.ShellWeaponEntry;
import org.hercworks.core.io.transform.ThreeSpaceByteTransformer;
import org.hercworks.voln.DataFile;
import org.hercworks.voln.FileType;

import at.favre.lib.bytes.Bytes;

public class PlayerSaveTransform extends ThreeSpaceByteTransformer{

	@Override
	public DataFile bytesToObject(byte[] inputArray) throws ClassCastException {
		
		if(inputArray == null || inputArray.length <= 0) {
			return null;
		}
		
		setBytes(inputArray);
		
		PlayerSave save = new PlayerSave();
		save.setExt(FileType.SAV);
		
		save.setUnk1_uint16(indexShortLE());
		save.setSpacer1(indexByte());
		
		//INVENTORY SEGMENT - 33 entries, matches total weapons in game, ERROR/cut weapon id's ARE included here, but zeroed out.
		System.out.println("Parsing Inventory Segment----------------");
		Inventory inventory = new Inventory();
		inventory.setItems(new Inventory.Entry[32]);
		for(int i=0; i < 32; i++) {
			byte flag = indexByte();
			short quant = indexShortLE();
			Inventory.Entry entry = inventory.newEntry();
			entry.setId(WeaponLUT.getById((short)(i+1)));
			entry.setUnlockFlag(flag);
			entry.setQuantity(quant);
			System.out.println("Weapon["+entry.getId().getName()+"]=" + entry.getQuantity());
			if(quant > 0) {
				ShellWeaponEntry[] items = new ShellWeaponEntry[quant];
				for(int q=0; q < (int)quant; q++) {
					ShellWeaponEntry weapon = new ShellWeaponEntry();
					weapon.setId(WeaponLUT.getById(indexShortLE()));
					weapon.setNameId(indexShortLE());
					short[] hp = new short[2];
					hp[0] = indexShortLE();
					hp[1] = indexShortLE();
					weapon.setHealth(hp);
					weapon.setMissileNum(indexShortLE());
					items[q] = weapon;
				}
				entry.setData(items);
			}
			System.out.println("");
			inventory.getItems()[i] = entry;
		}
		save.setInventory(inventory);
		System.out.println("->Invetory loaded, byte index:=" + index);
		
		//Workshop state
		System.out.println("Parsing Workshop Segment----------------");
		save.setWorkshopSpace(indexShortLE());
		for(int w=0; w<5; w++) {
			indexShortLE();	//why would these ever be out of order?
			save.getWorkshopSlots()[w] = WeaponLUT.getById((int)indexShortLE());
		}
		System.out.println("->Workshop loaded, byte index:=" + index);
		
		//Campaign flags, series of IN16's
		System.out.println("Parsing Campaign Flags Segment----------------");
		for(int f=0; f<78; f++) {
			short flag = indexShortLE();
			save.getUnk2_flags()[f] = flag;
		}
		System.out.println("->Campaign flags loaded, byte index:=" + index);
		
		//Squadmate segment
		System.out.println("Parsing Squad Segment----------------");
		PilotEntry[] squad = new PilotEntry[36];	//36 squadmates
		for(int s=0; s < squad.length; s++) {
			squad[s] = indexSquadmate();
		}
		save.setSquadmates(squad);
		System.out.println("->Squadmates loaded, byte index:=" + index);
	
		//Pilot segment
		System.out.println("Parsing Player Pilot Segment----------------");
		short[] unk_short8 = new short[8];
		for(int u=0; u < 8; u++) {
			unk_short8[u] = indexShortLE();
		}

		save.setPlayerPilot(indexPlayerPilot());
		System.out.println("->Pilot Entry loaded, byte index:=" + index);
		
		//Herc bay Data
		System.out.println("Parsing Herc Bay entries Segment----------------");
		short baySlots = indexShortLE();
		Map<Short, HercBayEntry> hercBay = new HashMap<Short, HercBayEntry>();
		for(int b=0; b < (int)baySlots; b++) {
			short bayId = indexShortLE();
			hercBay.put(bayId, indexHercEntry());
		}
		save.setHercBay(hercBay);
		
		//Herc Unlock Flags 9 bytes
		Map<HercLUT, Short> unlockedHercs = new HashMap<HercLUT, Short>();
		for(HercLUT herc : HercLUT.values()) {
			unlockedHercs.put(herc, indexShortLE());
		}
		save.setUnlockedHercs(unlockedHercs);
		
		//Total available salvage
		save.setSalvageTotal(indexIntLE());
		
		
		
		return save;
	}

	@Override
	public byte[] objectToBytes(DataFile source) throws ClassCastException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	private PilotEntry indexSquadmate() {
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
		entry.setUnk5_uint16(indexShortLE());
		entry.setSquadmateId(indexShortLE());
		
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
		Map<HercExternals, ShellHercPart> externals = new HashMap<HercExternals, ShellHercPart>();
		for(HercExternals e : HercExternals.values()) {
			externals.put(e, new ShellHercPart(e.getId(), e.getLabel()));
		}
		externals.get(HercExternals.COCKPIT).getValues()[0] = indexShortLE();
		externals.get(HercExternals.COCKPIT).getValues()[1] = indexShortLE();
		externals.get(HercExternals.TORSO_LEFT).getValues()[0] = indexShortLE();
		externals.get(HercExternals.TORSO_RIGHT).getValues()[0] = indexShortLE();
		externals.get(HercExternals.TORSO_LEFT).getValues()[1] = indexShortLE();
		externals.get(HercExternals.TORSO_RIGHT).getValues()[1] = indexShortLE();
		externals.get(HercExternals.CHASSIS).getValues()[0] = indexShortLE();
		externals.get(HercExternals.LEG_LEFT).getValues()[0] = indexShortLE();
		externals.get(HercExternals.LEG_RIGHT).getValues()[0] = indexShortLE();
		externals.get(HercExternals.LEG_LEFT).getValues()[1] = indexShortLE();
		externals.get(HercExternals.LEG_RIGHT).getValues()[1] = indexShortLE();
		externals.get(HercExternals.LEG_LEFT).getValues()[2] = indexShortLE();
		externals.get(HercExternals.LEG_RIGHT).getValues()[2] = indexShortLE();
		herc.setHealthExternals(externals);
		
		//Set Herc Internals health values.
		Map<HercInternals, ShellHercPart> internals = new HashMap<HercInternals, ShellHercPart>();
		for(HercInternals e : HercInternals.values()) {
			internals.put(e, new ShellHercPart(e.getId(), e.getLabel()));
		}
		internals.get(HercInternals.SERVOS_LEG_LEFT).getValues()[0] = indexShortLE();
		internals.get(HercInternals.SERVOS_LEG_RIGHT).getValues()[0] = indexShortLE();
		internals.get(HercInternals.SENSOR_ARRAY).getValues()[0] = indexShortLE();
		internals.get(HercInternals.TARG_COMP).getValues()[0] = indexShortLE();
		internals.get(HercInternals.SHIELD_GEN).getValues()[0] = indexShortLE();
		internals.get(HercInternals.ENGINE).getValues()[0] = indexShortLE();
		internals.get(HercInternals.HYDRAULICS).getValues()[0] = indexShortLE();
		internals.get(HercInternals.STABILIZERS).getValues()[0] = indexShortLE();
		internals.get(HercInternals.LIFE_SUPPRT).getValues()[0] = indexShortLE();
		herc.setHealthInternals(internals);
		
		ShellHercPart unk1_afterServos = new ShellHercPart((short)99, "unk1_afterServos");
		unk1_afterServos.getValues()[0] = indexShortLE();
		herc.setUnk1_afterServos(unk1_afterServos);
		
		List<ShellHercPart> healthHardpoints = new ArrayList<ShellHercPart>();
		for(int h=0; h < 9; h++) {
			ShellHercPart hardpointHp = new ShellHercPart((short)h, "hardpoint_" + h);
			hardpointHp.getValues()[0] = indexShortLE();
			healthHardpoints.add(hardpointHp);
		}
		herc.setHealthHardpoints(healthHardpoints);
		
		ShellHercPart unk2_afterHardpoints = new ShellHercPart((short)99, "unk2_afterHardpoints");
		unk2_afterHardpoints.getValues()[0] = indexShortLE();
		herc.setUnk2_afterHardpoints(unk2_afterHardpoints);
		
		herc.setBuildPercent(indexShortLE());
		if(herc.getBuildPercent() != 0) {
			herc.setBuildStepNum(indexShortLE());	
		}
		herc.setHardpointMax(indexShortLE());
		herc.setActiveSockets(indexShortLE());
		herc.setWeapons(new ShellWeaponEntry[herc.getActiveSockets()]);
		if(herc.getActiveSockets() > 0) {
			for(int h=0; h < herc.getWeapons().length; h++) {
				ShellWeaponEntry weapon = new ShellWeaponEntry();
				short socketId = indexShortLE();
				weapon.setId(WeaponLUT.getById(indexShortLE()));
				weapon.setNameId(indexShortLE());
				weapon.getHealth()[0] = indexShortLE();
				weapon.getHealth()[1] = indexShortLE();
				weapon.setMissileNum(indexShortLE());
				herc.getWeapons()[socketId] = weapon;
			}
		}
		
		return herc;
	}
}
