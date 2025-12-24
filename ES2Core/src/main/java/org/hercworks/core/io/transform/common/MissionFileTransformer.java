package org.hercworks.core.io.transform.common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.hercworks.core.data.file.msn.MapCoord;
import org.hercworks.core.data.file.msn.MissionFile;
import org.hercworks.core.data.file.msn.UnitInfo;
import org.hercworks.core.data.file.msn.UnkEntity102Bytes;
import org.hercworks.core.data.file.msn.UnkHeaderEntry;
import org.hercworks.core.data.struct.herc.HercLUT;
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
		
		
		System.out.println("Unk short 1 - " + indexShortLE() + " @" + (index-2) );
		
		int entryCount = indexShortLE();
		System.out.println("header entry count: " + entryCount + " @" + (index-2) );
		
		for(int i=0; i < entryCount; i++) {
			System.out.println("    Header Entry" + indexShortLE() + " @" + (index-2) );
//			System.out.println("        " + indexShortLE() + " @" + (index-2) );
//			System.out.println("        " + indexShortLE() + " @" + (index-2) );
//			System.out.println("        " + indexShortLE() + " @" + (index-2) );
//			System.out.println("        " + indexShortLE() + " @" + (index-2) );
//			System.out.println("        " + indexShortLE() + " @" + (index-2) );
//			System.out.println("        " + indexShortLE() + " @" + (index-2) );
			UnkHeaderEntry entry = new UnkHeaderEntry(indexShortLE(), indexShortLE(), indexShortLE(), indexShortLE(), indexShortLE(), indexShortLE());
			System.out.println("   		" + entry.toString());
			
			
		}
		
		System.out.println("is 2 or not: " + indexShortLE() + " @" + (index-2) );
		
		System.out.println("-1 or 1?  " + indexShortLE() + " @" + (index-2) );
		
		System.out.println("world Id? " + indexShortLE() + " @" + (index-2) );
		
		System.out.println("zone file id= " + indexShortLE() + " @" + (index-2) );
		
		//XXX - hard coded test for TRAIN5.MSN, this block is variable length in scripts, and I don't know yet where the
		// the size is accounted for.
		//	tried: header entry count - no divisible match
//		int count = indexShortLE();
		for(int i=0; i < (308 / 2); i++) {
			System.out.println("	unk chunk shorts by 4: @" + index + ", " + indexShortLE() );
		}
		
		//map coords
		int mapCoords = indexShortLE();	//total count of 'static map coords' - these seem pre-compiled in the script, probably for fast reference
		System.out.println("map coords count: " + mapCoords + " @" + (index-2) + ", using fixed int * 1000 to generate floats" );
		for(int m=0; m < mapCoords; m++) {
			int midx = index + 2;
			MapCoord mc = new MapCoord(indexShortLE(), indexShortLE(), indexShortLE(), indexShortLE(), indexShortLE(), indexIntLE(), indexIntLE(), indexIntLE());
			System.out.println("	coord @ "+ midx + ": " + mc.toString());
		}
		
		System.out.println("skipping unknown segment @" + index +" 394 bytes");
		index+=394;
		
		int unitCount = indexShortLE();
		System.out.println("possible unit entry count " + unitCount + " @ " + (index-2));
		
		for(int u=0; u < unitCount; u++) {
			System.out.println("count=" + u + "\n");
			parseUnitInfo();
		}
		
		int unk102Cnt = indexShortLE();
		System.out.println("unk obj counter? " +  unk102Cnt + " @ " + (index-2));
		
		for(int o=0; o < unk102Cnt; o++) {
			System.out.println("count=" + o + "\n");
			parseUnk102();
		}
		
		return data;
	}
	
	
	private UnitInfo parseUnitInfo() {

		UnitInfo unit = new UnitInfo();

		System.out.println("-----unit entry @"+index);
		unit.setTerminalFlag(indexShortLE());
		unit.setIndexId(indexShortLE());
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
		
	
		System.out.println(unit.toString());
		
		return unit;
	}
	
	private UnkEntity102Bytes parseUnk102() {
		UnkEntity102Bytes unk = new UnkEntity102Bytes();
		
		System.out.println("-----object102 entry @"+index);
		
		unk.setEntityId(indexShortLE());
		for(int i=0; i < 49; i++) {
			unk.getFlags()[i] = indexShortLE();
		}
		
		unk.setUnkVal_100(indexShortLE());
		
		
		
		System.out.println(unk.toString());
		
		return unk;
	}
	
	@Override
	public byte[] objectToBytes(DataFile source) throws ClassCastException, IOException {
	
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		MissionFile data = (MissionFile)source;
		
		
		
		
		return out.toByteArray();
	}
	
	
}
