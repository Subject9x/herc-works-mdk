package com.mech.works.api;

import static org.junit.Assert.assertNotNull;

import java.util.Iterator;

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
		
		for(VolEntry entry : shell.getFilesSet()) {
			if(entry.getFileName().equals("RAPTOR2.DAT")) {
				raptorDat = entry;
			}
		}
		
//		raptorDat.getRawBytes()[0] = (byte) 0xB0;	//turning speed
//		raptorDat.getRawBytes()[1] = (byte) 0x04;	//
		
		raptorDat.getRawBytes()[4] = (byte) 0x2C;	//forward speed.
		raptorDat.getRawBytes()[5] = (byte) 0x01;
		
		raptorDat.getRawBytes()[6] = (byte) 0x5A;
		
//		raptorDat.getRawBytes()[8] = (byte) 0x19;	//turning decel
//		raptorDat.getRawBytes()[9] = (byte) 0x00;
		
//		raptorDat.getRawBytes()[10] = (byte) 0x0A;	//camera bone num
		
//		raptorDat.getRawBytes()[12] = (byte) 0x01;	//something to do with movement
//		raptorDat.getRawBytes()[16] = (byte) 0x01;
//		raptorDat.getRawBytes()[18] = (byte) 0x08;
		
		raptorDat.getRawBytes()[22] = (byte) 0xFA;
		raptorDat.getRawBytes()[23] = (byte) 0x00;
		
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
