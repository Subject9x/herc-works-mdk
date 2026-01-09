package org.hercworks.core.io.transform.common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.hercworks.core.data.file.msn.MapCoord;
import org.hercworks.core.data.file.msn.MapObject;
import org.hercworks.core.data.file.msn.MiscEntityInfo;
import org.hercworks.core.data.file.msn.MissionFile;
import org.hercworks.core.data.file.msn.UnitInfo;
import org.hercworks.core.data.file.msn.UnkEntity102Bytes;
import org.hercworks.core.data.file.msn.UnkEntity10Byte;
import org.hercworks.core.data.file.msn.UnkEntity164Bytes;
import org.hercworks.core.data.file.msn.UnkEntity16Byte;
import org.hercworks.core.data.file.msn.UnkEntity22Byte;
import org.hercworks.core.data.file.msn.UnkEntity58Byte;
import org.hercworks.core.data.file.msn.UnkHeaderEntry;
import org.hercworks.core.data.struct.herc.HercLUT;
import org.hercworks.core.data.struct.herc.MiscEntityLUT;
import org.hercworks.core.io.transform.ThreeSpaceByteTransformer;
import org.hercworks.voln.DataFile;
import org.hercworks.voln.FileType;

public class MissionFileTransformer extends ThreeSpaceByteTransformer {

	@Override
	public DataFile bytesToObject(byte[] inputArray) throws ClassCastException {
		
		if(inputArray == null || inputArray.length <= 0) {
			//TODO - null input
			return null;
		}
		
		MissionFile data = new MissionFile();
		data.setRawBytes(inputArray);
		data.setExt(FileType.MSN);
		data.setDir(FileType.MSN);
		
		setBytes(inputArray);
		
		data.setMarkedObjects(new ArrayList<MapObject>());
		
		data.setUnknownFileId(indexShortLE());
//		System.out.println("Unk short 1 - " + data.getUnknownFileId() + " @" + (index-2) );
		
		UnkHeaderEntry[] headerEntries = new UnkHeaderEntry[(int)indexShortLE()];
//		System.out.println("header entry count: " + headerEntries.length + " @" + (index-2) );
		
		for(int i=0; i < headerEntries.length; i++) {
			headerEntries[i] = new UnkHeaderEntry(indexShortLE(), indexShortLE(), indexShortLE(), indexShortLE(), indexShortLE(), indexShortLE(), indexShortLE());
			System.out.println(headerEntries[i].toString());
		}
		data.setHeaderEntries(headerEntries);
		
		data.setUnkVal_is2(indexShortLE());
//		System.out.println("is 2 or not: " + data.getUnkVal_is2() + " @" + (index-2) );
		
		data.setUnkFlag_isFFFF(indexShortLE());
//		System.out.println("-1 or 1?  " + data.getUnkFlag_isFFFF() + " @" + (index-2) );
		
		data.setWorldId(indexShortLE());
//		System.out.println("world Id? " + data.getWorldId() + " @" + (index-2) );
		
		data.setZoneNumber(indexShortLE());
//		System.out.println("zone file id= " + data.getZoneNumber() + " @" + (index-2) );
		
		
		
		//XXX - hard coded test for TRAIN5.MSN, this block is variable length in scripts, and I don't know yet where the
		// the size is accounted for.
		//	tried: header entry count - no divisible match
//		short[] unkSeg1 = new short[80];
//		for(int i=0; i < 80; i++) {
//			unkSeg1[i] = indexShortLE();
//		}
//		data.setUnkSeg1(indexShortLEArray(80));	//TRAIN5
//		System.out.println("	unk chunk shorts: @" + (index - 160) + ", " + Arrays.toString(data.getUnkSeg1()));
		
		data.setUnkSeg1(indexShortLEArray(189));	//C1_01
		System.out.println("	unk chunk shorts: @" + (index - 378) + ", " + Arrays.toString(data.getUnkSeg1()));
		
		data.setUnkSTRFileLinks(indexShortLEArray(13));
		
		
//		System.out.println("	HUD - objective txt start val : @" + index + ", " + indexShortLE() );
//		System.out.println("		-1 spacer? : @" + index + ", " + indexShortLE() );
//		System.out.println("		STR entry : @" + index + ", " + indexShortLE() );
//		System.out.println("		STR entry : @" + index + ", " + indexShortLE() );
//		System.out.println("		STR entry : @" + index + ", " + indexShortLE() );
//		System.out.println("		STR entry : @" + index + ", " + indexShortLE() );
//		System.out.println("		STR entry : @" + index + ", " + indexShortLE() );
//		System.out.println("		STR entry : @" + index + ", " + indexShortLE() );
//		System.out.println("		STR entry : @" + index + ", " + indexShortLE() );
//		System.out.println("		STR entry : @" + index + ", " + indexShortLE() );
//		System.out.println("		STR entry : @" + index + ", " + indexShortLE() );
//		System.out.println("		STR entry : @" + index + ", " + indexShortLE() );
//		System.out.println("		STR entry : @" + index + ", " + indexShortLE() );
		
		
		
//		for(int i=0; i < 61; i++) {
//			System.out.println("	unk chunk shorts: @" + index + ", " + indexShortLE() );
//		}
//		data.setUnknown4ByteOr2ByteVals(indexShortLEArray(61));	//TRAIN5
		data.setUnknown4ByteOr2ByteVals(indexShortLEArray(20));
		
		//map coords
		//total count of 'static map coords' - these seem pre-compiled in the script, probably for fast reference
		MapCoord[] coords = new MapCoord[(int)indexShortLE()];
//		System.out.println("map coords count: " + coords.length + " @" + (index-2) + ", using fixed int * 1000 to generate floats" );
		for(int m=0; m < coords.length; m++) {
//			int midx = index + 2;
			coords[m] = new MapCoord(indexShortLE(), indexShortLE(), indexShortLE(), indexShortLE(), indexShortLE(), indexIntLE(), indexIntLE(), indexIntLE());
//			System.out.println("	coord @ "+ midx + ": " + coords[m].toString());
		}
		data.setMapCoordHeader(coords);
		
		UnkEntity10Byte[] unk10s = new UnkEntity10Byte[indexShortLE()];
		for(int u=0; u < unk10s.length; u++) {
			UnkEntity10Byte ent = new UnkEntity10Byte();
			
			ent.setGUID(indexShortLE());
			ent.setValues(indexShortLEArray(4));
			
			unk10s[u] = ent;
			data.getMarkedObjects().add(unk10s[u]);
			
		}
		data.setUnk10ByteEnts(unk10s);
		
		
		UnkEntity16Byte[] unk16s = new UnkEntity16Byte[indexShortLE()];
		for(int u=0; u < unk16s.length; u++) {
			UnkEntity16Byte ent = new UnkEntity16Byte();
			
			ent.setGUID(indexShortLE());
			ent.setValues(indexShortLEArray(7));
			
			unk16s[u] = ent;
			data.getMarkedObjects().add(unk16s[u]);
		}
		data.setUnk16ByteEnts(unk16s);
		
		
//		System.out.println("skipping unknown segment @" + index +" 306 bytes");	//TRAIN5
//		data.setUnkVariableLengthSeg(indexShortLEArray(153));

		System.out.println("skipping unknown segment @" + index +" 306 bytes");	//TRAIN5
		data.setUnkVariableLengthSeg(indexShortLEArray(393));
		//-------------------------------------------------------------------
		UnitInfo[] units = new UnitInfo[(int)indexShortLE()];
//		System.out.println("possible unit entry count " + units.length + " @ " + (index-2));		
		
		for(int u=0; u < units.length; u++) {
			int udx = index + 2;
			units[u] = parseUnitInfo();
			if(units[u].getGUID() != (short)-1) {
				data.getMarkedObjects().add(units[u]);
			}
//			System.out.println(u + " @ " + udx + " = UnitInfo" + units[u].toString());
		}
		data.setMapUnits(units);
		
		//-------------------------------------------------------------------
		UnkEntity102Bytes[] unkEntity102s = new UnkEntity102Bytes[(int)indexShortLE()];
//		System.out.println("UnknownEntity102Bytes " +  unkEntity102s.length + " @ " + (index-2));
		
		for(int o=0; o < unkEntity102s.length; o++) {
			unkEntity102s[o] = parseUnk102();
//			System.out.println(o + " @ " + index + " = UnkEntity102Bytes" + unkEntity102s[o].toString());

			if(unkEntity102s[o].getGUID() != (short)-1) {
				data.getMarkedObjects().add(unkEntity102s[o]);
			}
		}
		data.setUnk102ByteEnts(unkEntity102s);
		
		
		//-------------------------------------------------------------------
		MiscEntityInfo[] miscEnts = new MiscEntityInfo[(int)indexShortLE()];
//		System.out.println("Misc Building / Objects = " +  miscEnts.length + " @ " + (index-2));
		for(int m=0; m < miscEnts.length; m++) {
			int mark = index;
			miscEnts[m] = parseMiscEntity();
//			System.out.println(m + " @ " + mark + " = MiscEntityInfo" + miscEnts[m].toString());

			if(miscEnts[m].getGUID() != (short)-1) {
				data.getMarkedObjects().add(miscEnts[m]);
			}
		}
		data.setMapMiscEntities(miscEnts);
		
		
		//-------------------------------------------------------------------
		UnkEntity22Byte[] unkEntity22s = new UnkEntity22Byte[(int)indexShortLE()];
//		System.out.println("unkEnt22 count = " +  unkEntity22s.length + " @ " + (index-2));
		for(int e=0; e < unkEntity22s.length; e++) {
			int eidx = index;
			unkEntity22s[e] = parseUnkEntity22();
//			System.out.println(e + " @ " + eidx + " = UnkEntity22Byte" + unkEntity22s[e].toString());

			if(unkEntity22s[e].getGUID() != (short)-1) {
				data.getMarkedObjects().add(unkEntity22s[e]);
			}
		}
		data.setUnk22ByteEnts(unkEntity22s);
		
		//-------------------------------------------------------------------
		UnkEntity164Bytes[] unkEntity164s = new UnkEntity164Bytes[(int)indexShortLE()];
//		System.out.println("UnkEntity164Bytes count = " + unkEntity164s.length + " @ " + (index-2));
		
		for(int e=0; e < unkEntity164s.length; e++) {
			int edx = index;
			unkEntity164s[e] = parseUnkEntity164Bytes(data);
//			System.out.println(e + " @ " + edx + " = UnkEntity164Bytes" + unkEntity164s[e].toString());
			if(unkEntity164s[e].getGUID() != (short)-1) {
				data.getMarkedObjects().add(unkEntity164s[e]);
			}
		}
		data.setUnk164ByteEnts(unkEntity164s);
		
		
		//-------------------------------------------------------------------
		UnkEntity58Byte[] unkEntity58s = new UnkEntity58Byte[(int)indexShortLE()];
//		System.out.println("UnkEntity58Byte count = " +  unkEntity58s.length + " @ " + (index-2));
		for(int e=0; e < unkEntity58s.length; e++) {
			int eindex = index;
			unkEntity58s[e] = parseUnkEntity58Byte();
//			System.out.println(e + " @ " + eindex + " = UnkEntity58Byte" + unkEntity58s[e].toString());
		}
		data.setUnk58ByteEnts(unkEntity58s);
		
		return data;
	}
	
	
	private UnitInfo parseUnitInfo() {

		UnitInfo unit = new UnitInfo();
		
		unit.setGUID(indexShortLE());
		unit.setMapCoordId(indexShortLE());
		for(int i=0; i < 22; i++) {
			unit.getHeaderFlags()[i] = indexShortLE();
		}
		
		short unitId = indexShortLE();
		
		unit.setUnitId(unitId == -1 ? null : HercLUT.getById(unitId));
		
		for(int i=0; i < 10; i++) {
			
			unit.getWeapons()[i] = indexShortLE();
		}
		
		for(int i=0; i < 36; i++) {
			unit.getUnkFlags()[i] = indexShortLE();
		}
		
		unit.setHealthModAdjust(indexShortLE());
		
		return unit;
	}
	
	private UnkEntity102Bytes parseUnk102() {
		UnkEntity102Bytes unk = new UnkEntity102Bytes();
		
		unk.setGUID(indexShortLE());

		unk.setFlags(indexShortLEArray(49));
		
		unk.setUnkVal_100(indexShortLE());
		
		return unk;
	}
	
	private MiscEntityInfo parseMiscEntity() {
		MiscEntityInfo misc = new MiscEntityInfo();
		
		misc.setGUID(indexShortLE());
		
		misc.setHeaderFlags(indexShortLEArray(3));
		
		short miscId = indexShortLE();
		misc.setMiscEntityId(miscId == -1 ? null : MiscEntityLUT.getById(miscId));
		
		misc.setSpawnflags(indexShortLEArray(25));
		
		misc.setHealthModAdjust(indexShortLE());
		
		return misc;
	}
	
	private UnkEntity22Byte parseUnkEntity22() {
		UnkEntity22Byte ent = new UnkEntity22Byte();
		
		ent.setGUID(indexShortLE());
		ent.setFlags(indexShortLEArray(10));
		
		return ent;
		
	}

	private UnkEntity164Bytes parseUnkEntity164Bytes(MissionFile msn) {
		UnkEntity164Bytes unk = new UnkEntity164Bytes();
		
		unk.setGUID(indexShortLE());
		unk.setFlags(indexShortLEArray(22));
		unk.setLayoutType(indexShortLE());
		unk.setLayoutId(indexShortLE());
		unk.setMapCoord(msn.getMapCoordById(indexShortLE()));
		unk.setUnk10ByteId(indexShortLE());
		unk.setUnk16ByteId(indexShortLE());
//		unk.setUnkEntity10Byte((UnkEntity10Byte)msn.findMarkedObjectById(indexShortLE()));
//		unk.setUnkEntity16Byte((UnkEntity16Byte)msn.findMarkedObjectById(indexShortLE()));
		
		short[] guids = indexShortLEArray(20);
		MapObject[] obs = new MapObject[20];
		for(int i=0; i < guids.length; i++) {
			obs[i] = msn.findMarkedObjectById(guids[i]);
		}
		
		unk.setMapEntities(obs);
		unk.setMapEntIds(guids);
		
//		unk.setUnkEntity22Byte((UnkEntity22Byte)msn.findMarkedObjectById(indexShortLE()));
		unk.setUnk22ByteId(indexShortLE());
		
		unk.setValues(indexShortLEArray(33));
		
		return unk;
	}

	private UnkEntity58Byte parseUnkEntity58Byte() {
		UnkEntity58Byte unk = new UnkEntity58Byte();
		
		unk.setGUID(indexShortLE());
		unk.setFlags(indexShortLEArray(28));
		
		return unk;
	}
	
	@Override
	public byte[] objectToBytes(DataFile source) throws ClassCastException, IOException {
	
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		MissionFile data = (MissionFile)source;
		
		out.write(writeShortLE((short)5));
		
		out.write(writeShortLE((short)data.getHeaderEntries().length));
		for(int i=0; i < data.getHeaderEntries().length; i++) {
			UnkHeaderEntry hdr = data.getHeaderEntries()[i];
			out.write(writeShortLE(hdr.getIndexId()));
			out.write(writeShortLE(hdr.getStartFrameIndexId()));
			out.write(writeShortLE(hdr.getUnkValue1()));
			out.write(writeShortLE(hdr.getFrameStartTime()));
			out.write(writeShortLE(hdr.getFrameEndTime()));
			out.write(writeShortLE(hdr.getTotalTime()));
			out.write(writeShortLE(hdr.getUnkValue2()));
		}
		
		out.write(writeShortLE(data.getUnkVal_is2()));
		out.write(writeShortLE(data.getUnkFlag_isFFFF()));
		out.write(writeShortLE(data.getWorldId()));
		out.write(writeShortLE(data.getZoneNumber()));
		
		
		out.write(writeShortLESegment(data.getUnkSeg1()));
		
		out.write(writeShortLESegment(data.getUnkSTRFileLinks()));
		
		out.write(writeShortLESegment(data.getUnknown4ByteOr2ByteVals()));
		
		out.write(writeShortLE((short)data.getMapCoordHeader().length));
		for(int m=0; m < data.getMapCoordHeader().length; m++) {
			MapCoord mp = data.getMapCoordHeader()[m];
			out.write(writeShortLE(mp.getId()));
			out.write(writeShortLE(mp.getUnkFlag1()));
			out.write(writeShortLE(mp.getUnkFlag2()));
			out.write(writeShortLE(mp.getUnkFlag3()));
			out.write(writeShortLE(mp.getUnkFlag4()));
			out.write(writeIntLE(mp.getX()));
			out.write(writeIntLE(mp.getY()));
			out.write(writeIntLE(mp.getZ()));	
		}
		
		out.write(writeShortLE((short)data.getUnk10ByteEnts().length));
		for(int u=0; u < data.getUnk10ByteEnts().length; u++) {
			UnkEntity10Byte ent = data.getUnk10ByteEnts()[u];
			out.write(writeShortLE(ent.getGUID()));
			out.write(writeShortLESegment(ent.getValues()));	
		}
		
		out.write(writeShortLE((short)data.getUnk16ByteEnts().length));
		for(int u=0; u < data.getUnk16ByteEnts().length; u++) {
			UnkEntity16Byte ent = data.getUnk16ByteEnts()[u];
			out.write(writeShortLE(ent.getGUID()));
			out.write(writeShortLESegment(ent.getValues()));	
		}
		
		out.write(writeShortLESegment(data.getUnkVariableLengthSeg()));
		
		out.write(writeShortLE((short)data.getMapUnits().length));
		for(int u=0; u < data.getMapUnits().length; u++) {
			UnitInfo unit = data.getMapUnits()[u];
			out.write(writeShortLE(unit.getGUID()));
			out.write(writeShortLE(unit.getMapCoordId()));
			out.write(writeShortLESegment(unit.getHeaderFlags()));
			out.write(writeShortLE(unit.getUnitId() == null ? (short)-1 : unit.getUnitId().getId()));
			out.write(writeShortLESegment(unit.getWeapons()));
			out.write(writeShortLESegment(unit.getUnkFlags()));
			out.write(writeShortLE(unit.getHealthModAdjust()));
		}
		
		out.write(writeShortLE((short)data.getUnk102ByteEnts().length));
		for(int u=0; u < data.getUnk102ByteEnts().length; u++) {
			UnkEntity102Bytes ent = data.getUnk102ByteEnts()[u];
			out.write(writeShortLE(ent.getGUID()));
			out.write(writeShortLESegment(ent.getFlags()));
			out.write(writeShortLE(ent.getUnkVal_100()));
		}
		
		out.write(writeShortLE((short)data.getMapMiscEntities().length));
		for(int u=0; u < data.getMapMiscEntities().length; u++) {
			MiscEntityInfo ent = data.getMapMiscEntities()[u];
			out.write(writeShortLE(ent.getGUID()));
			out.write(writeShortLESegment(ent.getHeaderFlags()));
			out.write(writeShortLE(ent.getMiscEntityId() == null ? (short)-1 : ent.getMiscEntityId().getId()));
			out.write(writeShortLESegment(ent.getSpawnflags()));
			out.write(writeShortLE(ent.getHealthModAdjust()));
		}
		

//		UnkEntity22Byte
		out.write(writeShortLE((short)data.getUnk22ByteEnts().length));
		for(int u=0; u < data.getUnk22ByteEnts().length; u++) {
			UnkEntity22Byte ent = data.getUnk22ByteEnts()[u];
			out.write(writeShortLE(ent.getGUID()));
			out.write(writeShortLESegment(ent.getFlags()));	
		}
		
//		UnkEntity164Bytes
		out.write(writeShortLE((short)data.getUnk164ByteEnts().length));
		for(int u=0; u < data.getUnk164ByteEnts().length; u++) {
			UnkEntity164Bytes ent = data.getUnk164ByteEnts()[u];
			out.write(writeShortLE(ent.getGUID()));
			out.write(writeShortLESegment(ent.getFlags()));
			out.write(writeShortLE(ent.getLayoutType()));
			out.write(writeShortLE(ent.getLayoutId()));
			out.write(writeShortLE(ent.getMapCoord() == null ? (short)-1 : ent.getMapCoord().getId()));
			out.write(writeShortLE(ent.getUnkEntity10Byte() == null ? (short)-1 : ent.getUnkEntity10Byte().getGUID()));
			out.write(writeShortLE(ent.getUnkEntity16Byte() == null ? (short)-1 : ent.getUnkEntity16Byte().getGUID()));
			
			out.write(writeShortLESegment(ent.getMapEntIds()));
			
			out.write(writeShortLE(ent.getUnkEntity22Byte() == null ? (short)-1 : ent.getUnkEntity22Byte().getGUID()));
			
			out.write(writeShortLESegment(ent.getValues()));
		}
		
//		UnkEntity58Byte
		out.write(writeShortLE((short)data.getUnk58ByteEnts().length));
		for(int u=0; u < data.getUnk58ByteEnts().length; u++) {
			UnkEntity58Byte ent = data.getUnk58ByteEnts()[u];
			out.write(writeShortLE(ent.getGUID()));
			out.write(writeShortLESegment(ent.getFlags()));
		}
		
		
		return out.toByteArray();
	}
	
	
}
