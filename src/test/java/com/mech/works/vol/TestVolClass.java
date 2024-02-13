package com.mech.works.vol;

import static org.junit.Assert.assertNotNull;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import com.mech.works.io.read.VolFileReader;
import com.mech.works.io.write.VolFileWriter;
import com.mech.works.vol.data.Voln;

public class TestVolClass {

	private Voln testVol;
	
	@Test
	public void testVolLoad() {
		Voln testVol = null;
		try {
			//testVol = VolFileReader.parseVolFile("E:\\ES2_OS\\dev\\earthsiege2\\VOL\\SIMVOL0.vol");
			testVol = VolFileReader.parseVolFile("E:\\ES2_OS\\dev\\earthsiege2\\VOL\\ORIG_SHELL0_ORIG.vol");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertNotNull(testVol.getRawBytes());
	}
	
	@Test
	public void testVolParse(){		
		
		try {
			testVol = VolFileReader.parseVolFile("E:\\ES2_OS\\dev\\earthsiege2\\VOL\\BACKUP\\SHELL0.vol");
			testVol.setFileName("DBG_SHELL01.VOL");
//			VolFileWriter.unpackVol(testVol, "E:\\ES2_OS\\dev\\earthsiege2\\VOL\\DBG\\SHELL");
			
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (Exception e2) {
			e2.printStackTrace();
		}

		
		assertNotNull(testVol);
	}
	
	@Test
	public void testVolWrite() {
		testVolParse();
		
		if(testVol != null) {
			try {
				VolFileWriter.packVolToFile(testVol, "E:\\ES2_OS\\dev\\earthsiege2\\VOL\\DBG\\SHELL");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
