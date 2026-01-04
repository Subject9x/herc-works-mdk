package org.hercworks.core.api;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

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
import org.hercworks.core.data.file.sav.PlayerSave;
import org.hercworks.core.io.transform.common.MissionFileTransformer;
import org.hercworks.core.io.transform.common.PlayerSaveTransform;
import org.junit.Test;

public class TestSavefile {

	
	@Test
	public void testSavefile() {
			
		File file = new File("e://es2_os/dev/earthsiege2/sav/GAME_5.sav");
		
		assertTrue(file.isFile());
		
		FileInputStream fizz;
		try {
			fizz = new FileInputStream(file);
			byte[] inputArr = fizz.readAllBytes();
			
			fizz.close();
			
			PlayerSaveTransform playerSaveTransformer = new PlayerSaveTransform();
			
			PlayerSave save = (PlayerSave)playerSaveTransformer.bytesToObject(inputArr);
			
			assertNotNull(save);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testMSNFile() {
			
		File file = new File("e://es2_os/dev/earthsiege2/MSN/TRAIN5.MSN");
//		File file = new File("e://es2_os/dev/earthsiege2/MSN/TRAIN7.MSN");
//		File file = new File("e://es2_os/dev/earthsiege2/MSN/C1_01.MSN");
		
		assertTrue(file.isFile());
		
		FileInputStream fizz;
		try {
			fizz = new FileInputStream(file);
			byte[] inputArr = fizz.readAllBytes();
			
			fizz.close();
			
			MissionFileTransformer msntf = new MissionFileTransformer();
			
			MissionFile msn = (MissionFile)msntf.bytesToObject(inputArr);
			
			assertNotNull(msn);
			
			System.out.println("header count = " + msn.getHeaderEntries().length);
			//data probes
			for(UnkHeaderEntry hdr : msn.getHeaderEntries()) {
				System.out.println(hdr.toString());
			}
			
			System.out.println("map coord count = " + msn.getMapCoordHeader().length);
			for(MapCoord c : msn.getMapCoordHeader()) {
				System.out.println(c.getClass().getSimpleName() + " = " + c.toString());
			}
			
			System.out.println("UnkEntity10Byte count = " + msn.getUnk10ByteEnts().length);
			for(UnkEntity10Byte u : msn.getUnk10ByteEnts()) {
				System.out.println(u.getClass().getSimpleName() + " = " + u.toString());
			}
			
			System.out.println("UnkEntity16Byte count = " + msn.getUnk16ByteEnts().length);
			for(UnkEntity16Byte u : msn.getUnk16ByteEnts()) {
				System.out.println(u.getClass().getSimpleName() + " = " + u.toString());
			}
			
			System.out.println("UnitInfo count = " + msn.getMapUnits().length);
			for(UnitInfo u : msn.getMapUnits()) {
				System.out.println(u.getClass().getSimpleName() + " = " + u.toString());
			}
			
			System.out.println("UnkEntity102Bytes count = " + msn.getUnk102ByteEnts().length);
			for(UnkEntity102Bytes u : msn.getUnk102ByteEnts()) {
				System.out.println(u.getClass().getSimpleName() + " = " + u.toString());
			}

			System.out.println("MiscEntityInfo count = " + msn.getMapMiscEntities().length);
			for(MiscEntityInfo u : msn.getMapMiscEntities()) {
				System.out.println(u.getClass().getSimpleName() + " = " + u.toString());
			}
			
			System.out.println("UnkEntity22Byte count = " + msn.getUnk22ByteEnts().length);
			for(UnkEntity22Byte u : msn.getUnk22ByteEnts()) {
				System.out.println(u.getClass().getSimpleName() + " " + u.toString());
				for(short v : u.getFlags()) {
					MapObject o = msn.findMarkedObjectById(v);
					if(o != null) {
						System.out.println("    linked: " + v + "=" + o.getClass().getSimpleName());
					}
				}
			}

			System.out.println("UnkEntity164Bytes count = " + msn.getUnk164ByteEnts().length);
			for(UnkEntity164Bytes u : msn.getUnk164ByteEnts()) {
				System.out.println(u.getClass().getSimpleName() + " " + u.getGUID());
				for(short v : u.getValues()) {
					MapObject o = msn.findMarkedObjectById(v);
					if(o != null) {
						String ln = "    linked: " + v + " = ";
						if(o instanceof MiscEntityInfo && ((MiscEntityInfo)o).getId() != null) {
							ln += ((MiscEntityInfo)o).getId().getName();
						}
						else if(o instanceof UnitInfo && ((UnitInfo)o).getUnitId() != null) {
							ln += ((UnitInfo)o).getUnitId().getName();
						}
						else{
							ln +=  o.getClass().getSimpleName();
						}
						System.out.println(ln);
					}
				}
				System.out.println(u.toString());
			}
			
			System.out.println("UnkEntity58Byte count = " + msn.getUnk58ByteEnts().length);
			for(UnkEntity58Byte u : msn.getUnk58ByteEnts()) {
				System.out.println(u.getClass().getSimpleName() + " " + u.toString());
				for(short v : u.getFlags()) {
					MapObject o = msn.findMarkedObjectById(v);
					if(o != null) {
						System.out.println("    linked:" + v + "=" + o.getClass().getSimpleName());
					}
				}
			}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}


