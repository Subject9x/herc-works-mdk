package com.mech.works.api;

import static org.junit.Assert.assertNotNull;

import java.nio.ByteOrder;
import java.util.Iterator;

import org.junit.Test;

import com.mech.works.data.file.dat.Ini_Herc_Dat;
import com.mech.works.data.ref.files.DataFile;
import com.mech.works.io.read.DatFileReader;
import com.mech.works.io.read.VolFileReader;
import com.mech.works.io.write.VolFileWriter;
import com.mech.works.vol.data.VolEntry;
import com.mech.works.vol.data.Voln;

import at.favre.lib.bytes.Bytes;

public class TestGamDatFiles {

	private static Voln getVol(String volDir) throws Exception {
		
		return VolFileReader.parseVolFile(volDir);
	}
	
	
	@Test
	public void testEditHercDat() {
	
		Voln shell = null;
		try {
			shell = getVol("E:\\ES2_OS\\dev\\earthsiege2\\VOL\\BACKUP\\SHELL0.VOL");
//			shell = getVol("E:\\ES2_OS\\dev\\earthsiege2\\VOL\\DBG\\SHELL0v1.VOL");
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}

		assertNotNull(shell);
		
		shell.setFileName("SHELL0v1.VOL");
		shell.setDestPath("E:\\ES2_OS\\dev\\earthsiege2\\VOL");
		
		DataFile datfile = null;
		
		Iterator<VolEntry> fileItr = shell.getFolders().get((byte)4).getFiles().iterator();
		while(fileItr.hasNext()) {
			VolEntry file = fileItr.next();
			if(file.getFileName().equals("INI_TOMA.DAT")) {
				datfile = file;
			}
		}
		
		Ini_Herc_Dat iniHercDat = null;
		try {
			iniHercDat = DatFileReader.parseIniHercDatStats(shell.getRawBytes(), datfile);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		assertNotNull(iniHercDat);
		
		Bytes edit = Bytes.from(datfile.header()).byteOrder(ByteOrder.BIG_ENDIAN);
		edit = edit.append(iniHercDat.getRawBytes());
		
		byte[] editData = edit.array();
		editData[42] = (byte)9;
		editData[44] = (byte)50;
		editData[46] = (byte)3;
		
		datfile.setRawBytes(edit.array());
		
		try {
			VolFileWriter.packVolToFile(shell, shell.getDestPath());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
}
