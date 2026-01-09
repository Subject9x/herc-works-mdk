package org.hercworks.core.api;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

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
import org.hercworks.core.io.transform.common.MissionStringFileTransformer;
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
	public void decompSTRFile() {
		
		decompSTRFile_exe( new File("e://es2_os/dev/earthsiege2/unpack/zones/msn/TRAIN1.ENG"));
		decompSTRFile_exe( new File("e://es2_os/dev/earthsiege2/unpack/zones/msn/TRAIN2.ENG"));
		decompSTRFile_exe( new File("e://es2_os/dev/earthsiege2/unpack/zones/msn/TRAIN3.ENG"));
		decompSTRFile_exe( new File("e://es2_os/dev/earthsiege2/unpack/zones/msn/TRAIN4.ENG"));
		decompSTRFile_exe( new File("e://es2_os/dev/earthsiege2/unpack/zones/msn/TRAIN5.ENG"));
		decompSTRFile_exe( new File("e://es2_os/dev/earthsiege2/unpack/zones/msn/TRAIN6.ENG"));
		decompSTRFile_exe( new File("e://es2_os/dev/earthsiege2/unpack/zones/msn/TRAIN7.ENG"));
		decompSTRFile_exe( new File("e://es2_os/dev/earthsiege2/unpack/zones/msn/C1_01.ENG"));
		
	}
	
	
	private void decompSTRFile_exe(File file) {
		
		assertTrue(file.isFile());
		FileInputStream fizz;
		
		try {
			System.out.println(file.getName() + "-------------------------------------------------------\n");
			fizz = new FileInputStream(file);
			byte[] inputArr = fizz.readAllBytes();
			
			fizz.close();
			
			MissionStringFileTransformer strf = new MissionStringFileTransformer();
			
			strf.bytesToObject(inputArr);
			
			
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
			
//		File file = new File("e://es2_os/dev/earthsiege2/MSN/TRAIN5.MSN");
//		File file = new File("e://es2_os/dev/earthsiege2/MSN/TRAIN6.MSN");
//		File file = new File("e://es2_os/dev/earthsiege2/MSN/TRAIN7.MSN");
//		File file = new File("e://es2_os/dev/earthsiege2/MSN/TRAIN8.MSN");
		File file = new File("e://es2_os/dev/earthsiege2/MSN/C1_01.MSN");
		
		
		assertTrue(file.isFile());
		
		FileInputStream fizz;
		try {
			fizz = new FileInputStream(file);
			byte[] inputArr = fizz.readAllBytes();
			
			fizz.close();
			
			MissionFileTransformer msntf = new MissionFileTransformer();
			
			MissionFile msn = (MissionFile)msntf.bytesToObject(inputArr);
			
			assertNotNull(msn);
			
			//tests
			
			
			System.out.println("header count = " + msn.getHeaderEntries().length);
			//data probes
			for(UnkHeaderEntry hdr : msn.getHeaderEntries()) {
				System.out.println(hdr.toString());
			}
			
			msn.getMarkedObjects().stream()
					.sorted((o1, o2) -> ((Short)o1.getGUID()).compareTo(((short)o2.getGUID())))
					.forEach(t -> System.out.println(t.getGUID() + " = " + t.getClass().getSimpleName()));
			
			
			
			for(MapObject o : msn.getMarkedObjects()) {
				
//					System.out.println(o.getGUID() + " = " + o.getClass().getSimpleName());
				
			}
			
			System.out.println("map coord count = " + msn.getMapCoordHeader().length);
			for(MapCoord c : msn.getMapCoordHeader()) {
//				System.out.println(c.getId() + " = " + c.getClass().getSimpleName());
			}
//			
			System.out.println("UnkEntity10Byte count = " + msn.getUnk10ByteEnts().length);
			for(UnkEntity10Byte u : msn.getUnk10ByteEnts()) {
//				System.out.println(u.getGUID() + " = " + u.getClass().getSimpleName());
			}
//			
			System.out.println("UnkEntity16Byte count = " + msn.getUnk16ByteEnts().length);
			for(UnkEntity16Byte u : msn.getUnk16ByteEnts()) {
//				System.out.println(u.getGUID() + " = " + u.getClass().getSimpleName());
			}
//			
			System.out.println("UnitInfo count = " + msn.getMapUnits().length);
			for(UnitInfo u : msn.getMapUnits()) {
//				System.out.println(u.getGUID() + " = " + u.getClass().getSimpleName());
			}
//			
			System.out.println("UnkEntity102Bytes count = " + msn.getUnk102ByteEnts().length);
			for(UnkEntity102Bytes u : msn.getUnk102ByteEnts()) {
//				System.out.println(u.getGUID() + " = " + u.getClass().getSimpleName());
			}
//
			System.out.println("MiscEntityInfo count = " + msn.getMapMiscEntities().length);
			for(MiscEntityInfo u : msn.getMapMiscEntities()) {
//				System.out.println(u.getGUID() + " = " + u.getClass().getSimpleName());
			}
//			
			System.out.println("UnkEntity22Byte count = " + msn.getUnk22ByteEnts().length);
			for(UnkEntity22Byte u : msn.getUnk22ByteEnts()) {
//				System.out.println(u.getGUID() + " = " + u.getClass().getSimpleName());
//				for(short v : u.getFlags()) {
//					MapObject o = msn.findMarkedObjectById(v);
//					if(o != null) {
//						System.out.println("    linked: " + v + "=" + o.getClass().getSimpleName());
//					}
//				}
			}
//
			System.out.println("UnkEntity164Bytes count = " + msn.getUnk164ByteEnts().length);
			for(UnkEntity164Bytes u : msn.getUnk164ByteEnts()) {
//				System.out.println(u.getGUID() + " = " + u.getClass().getSimpleName());
//				for(short v : u.getValues()) {
//					MapObject o = msn.findMarkedObjectById(v);
//					if(o != null) {
//						String ln = "    linked: " + v + " = ";
//						if(o instanceof MiscEntityInfo && ((MiscEntityInfo)o).getMiscEntityId() != null) {
//							ln += ((MiscEntityInfo)o).getMiscEntityId().getName();
//						}
//						else if(o instanceof UnitInfo && ((UnitInfo)o).getUnitId() != null) {
//							ln += ((UnitInfo)o).getUnitId().getName();
//						}
//						else{
//							ln +=  o.getClass().getSimpleName();
//						}
//						System.out.println(ln);
//					}
//				}
//				System.out.println(u.toString());
			}
//			
			System.out.println("UnkEntity58Byte count = " + msn.getUnk58ByteEnts().length);
			for(UnkEntity58Byte u : msn.getUnk58ByteEnts()) {
//				System.out.println(u.getGUID() + " = " + u.getClass().getSimpleName());
//				for(short v : u.getFlags()) {
//					MapObject o = msn.findMarkedObjectById(v);
//					if(o != null) {
//						System.out.println("    linked:" + v + "=" + o.getClass().getSimpleName());
//					}
//				}
			}
			
			
//			FileOutputStream fos = new FileOutputStream(new File("e://es2_os/dev/earthsiege2/MSN/TRAIN5_x.MSN"));
//			fos.write(msntf.objectToBytes(msn));
//			fos.close();
//			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}


