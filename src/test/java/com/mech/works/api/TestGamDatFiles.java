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
		
		for(VolEntry entry : shell.getFilesSet()) {
			if(entry.getFileName().equals("RAPTOR2.DAT")) {
				raptorDat = entry;
			}
			else if(entry.getFileName().equals("MECHSYS.BND")) {
				test = entry;
			}
			else if(entry.getFileName().equals("TOMAHAWK.DAT")) {
				tomaDat = entry;
			}
		}
		
//		test.getRawBytes()[20] = (byte)0xF1;
		tomaDat.getRawBytes()[4] = (byte)0xF4;	//fwd speed
		tomaDat.getRawBytes()[5] = (byte)0x01;
		
		raptorDat.getRawBytes()[4] = (byte)0x90;	//fwd speed
		raptorDat.getRawBytes()[5] = (byte)0x01;
		
		tomaDat.getRawBytes()[28] = (byte)0xD0;	//TORSO TWIST SPEED
		tomaDat.getRawBytes()[29] = (byte)0x07;
		
		tomaDat.getRawBytes()[40] = (byte)0xE0;
		tomaDat.getRawBytes()[41] = (byte)0x2E;
			
		tomaDat.getRawBytes()[42] = (byte)0xD8;
		tomaDat.getRawBytes()[43] = (byte)0xDC;
		
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

