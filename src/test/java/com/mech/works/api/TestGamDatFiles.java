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
			if(entry.getFileName().equals("RAPTOR2.DAT")) {
				raptorDat = entry;
			}
			else if(entry.getFileName().equals("MECHSYS.BND")) {
				test = entry;
			}
			else if(entry.getFileName().equals("TOMAHAWK.DAT")) {
				tomaDat = entry;
			}
			else if(entry.getFileName().equals("RAMSES.DAT")) {
				ram = entry;
			}
			
		}
		
		tomaDat.getRawBytes()[4] = (byte)0x58;	//fwd speed for debug
		tomaDat.getRawBytes()[5] = (byte)0x02;
		
		tomaDat.getRawBytes()[10] = (byte)0x0A;	//camera bone dbg
		tomaDat.getRawBytes()[11] = (byte)0x00;

//		tomaDat.getRawBytes()[i] = (byte)0xF0;
//		tomaDat.getRawBytes()[i+1] = (byte)0xD8;
		
//		for(int i = 124; i < 148; i+=2) {
//			tomaDat.getRawBytes()[i] = (byte)0xF0;
//			tomaDat.getRawBytes()[i+1] = (byte)0xD8;
//			
//			ram.getRawBytes()[i] = (byte)0xF0;
//			ram.getRawBytes()[i+1] = (byte)0xD8;
//		}
		
		
		tomaDat.getRawBytes()[150] = (byte)0x00;
		tomaDat.getRawBytes()[151] = (byte)0x00;
		tomaDat.getRawBytes()[152] = (byte)0x00;
		tomaDat.getRawBytes()[153] = (byte)0x00;
		tomaDat.getRawBytes()[154] = (byte)0x00;
		tomaDat.getRawBytes()[155] = (byte)0x00;
		tomaDat.getRawBytes()[156] = (byte)0x00;
		tomaDat.getRawBytes()[157] = (byte)0x00;
		
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

