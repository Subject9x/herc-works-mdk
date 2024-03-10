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
		
		test.getRawBytes()[134] = (byte)0x01;
		test.getRawBytes()[135] = (byte)0x00;
		
		test.getRawBytes()[136] = (byte)0x01;
		test.getRawBytes()[137] = (byte)0x00;
//		
//		test.getRawBytes()[56] = (byte)0x00;
//		test.getRawBytes()[57] = (byte)0x00;
//		
//		test.getRawBytes()[58] = (byte)0x00;
//		test.getRawBytes()[59] = (byte)0x00;
//		
//		test.getRawBytes()[60] = (byte)0x00;
//		test.getRawBytes()[61] = (byte)0x00;
//		
//		test.getRawBytes()[62] = (byte)0x00;
//		test.getRawBytes()[63] = (byte)0x00;
//		
//		test.getRawBytes()[64] = (byte)0x00;
//		test.getRawBytes()[65] = (byte)0x00;
//		
//		test.getRawBytes()[66] = (byte)0x00;
//		test.getRawBytes()[67] = (byte)0x00;
//		
//		test.getRawBytes()[68] = (byte)0x00;
//		test.getRawBytes()[69] = (byte)0x00;
		
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

