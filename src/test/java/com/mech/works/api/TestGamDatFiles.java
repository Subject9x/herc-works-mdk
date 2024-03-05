package com.mech.works.api;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.mech.works.io.read.VolFileReader;
import com.mech.works.io.write.VolFileWriter;
import com.mech.works.vol.data.VolEntry;
import com.mech.works.vol.data.Voln;

public class TestGamDatFiles {

	private static Voln getVol(String volDir) throws Exception {
		
		return VolFileReader.parseVolFile(volDir);
	}
	
	
	@Test
	public void testEditHercDat() {
	
		Voln shell = null;
		try {
			shell = getVol("E:\\ES2_OS\\dev\\earthsiege2\\VOL\\BACKUP\\SIMVOL0.VOL");
//			shell = getVol("E:\\ES2_OS\\dev\\earthsiege2\\VOL\\DBG\\SHELL0v1.VOL");
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}

		assertNotNull(shell);
		
		shell.setFileName("SIMVOL0.VOL");
		shell.setDestPath("E:\\ES2_OS\\dev\\earthsiege2\\VOL");
		
		VolEntry raptorDat = null;
		VolEntry tomaDat = null;
		
		VolEntry test = null;
		VolEntry ram = null;
		
		for(VolEntry entry : shell.getFilesSet()) {
			if(entry.getFileName().equals("OGRE.DMG")) {
				ram = entry;
			}
			else 
			if(entry.getFileName().equals("TOMAHAWK.DMG")) {
				test = entry;
			}
			else if(entry.getFileName().equals("TOMAHAWK.DAT")) {
				tomaDat = entry;
			}
//			else if(entry.getFileName().equals("RAMSES.DAT")) {
//				ram = entry;
//			}
			
		}
		
		
//		test.getRawBytes()[0] = (byte)0x78;
//		test.getRawBytes()[1] = (byte)0x00;
		
		
		//CRITICALS HEALTH 
//		test.getRawBytes()[2] = (byte)0x01;		//SERVO\LEG\LEFT
//		test.getRawBytes()[3] = (byte)0x00;
		
//		test.getRawBytes()[4] = (byte)0x01;		//SERVO\LEG\RIGHT
//		test.getRawBytes()[5] = (byte)0x00;
//		
//		test.getRawBytes()[6] = (byte)0x01;		//SENSOR ARRAY
//		test.getRawBytes()[7] = (byte)0x00;
//		
//		test.getRawBytes()[8] = (byte)0x01; 	//TARGETING COMPUTER
//		test.getRawBytes()[9] = (byte)0x00;
//		
//		test.getRawBytes()[10] = (byte)0x01;	//SHIELD GENERATOR
//		test.getRawBytes()[11] = (byte)0x00;
//		
//		test.getRawBytes()[12] = (byte)0x01;	//ENGINE
//		test.getRawBytes()[13] = (byte)0x00;
//		
//		test.getRawBytes()[14] = (byte)0x01;	//HYDRAULICS
//		test.getRawBytes()[15] = (byte)0x00;
		
//		test.getRawBytes()[16] = (byte)0x01;	//STABILIZERS
//		test.getRawBytes()[17] = (byte)0x00;
//		
//		test.getRawBytes()[18] = (byte)0x01;	//LIFE SUPPORT
//		test.getRawBytes()[19] = (byte)0x00;
		
		//ARMOR Values---------------------------------------
//		test.getRawBytes()[80] = (byte)0x01;	//COCKPIT\FRONT
//		test.getRawBytes()[81] = (byte)0x00;
//		
//		test.getRawBytes()[108] = (byte)0x01;	//COCKPIT\REAR
//		test.getRawBytes()[109] = (byte)0x00;
//		
//		test.getRawBytes()[116] = (byte)0x01; 	//TORSO/BRACKET ARMOR
//		test.getRawBytes()[117] = (byte)0x00;
//		
//		test.getRawBytes()[140] = (byte)0x01;	//TORSO ARMOR
//		test.getRawBytes()[141] = (byte)0x00;
//		
//		test.getRawBytes()[156] = (byte)0x01;	//LEG\LEFT\UPPER
//		test.getRawBytes()[157] = (byte)0x00;	
		
//		test.getRawBytes()[168] = (byte)0x01;	//LEG\RIGHT\UPPER
//		test.getRawBytes()[169] = (byte)0x00;
		
//		test.getRawBytes()[180] = (byte)0x01;	//LEG\LEFT\LOWER
//		test.getRawBytes()[181] = (byte)0x00;
		
//		test.getRawBytes()[192] = (byte)0x01;	//LEG\RIGHT\LOWER
//		test.getRawBytes()[193] = (byte)0x00;
		
//		test.getRawBytes()[204] = (byte)0x01;	//LEG\LEFT\FOOT
//		test.getRawBytes()[205] = (byte)0x00;
		
//		test.getRawBytes()[216] = (byte)0x01;	//LEG\RIGHT\FOOT
//		test.getRawBytes()[217] = (byte)0x00;
		
		//speed 450
		tomaDat.getRawBytes()[4] = (byte)0x58;
		tomaDat.getRawBytes()[5] = (byte)0x02;
		
		
		
		//turn speed 25% bump
		tomaDat.getRawBytes()[0] = (byte)0x20;
		tomaDat.getRawBytes()[1] = (byte)0x03;
		
		
		tomaDat.getRawBytes()[10] = (byte)0x0A;	//camera bone dbg
		tomaDat.getRawBytes()[11] = (byte)0x00;

		
		
		if(shell != null) {
			try {
				VolFileWriter.packVolToFile(shell, "E:\\ES2_OS\\dev\\earthsiege2\\VOL");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}

