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
		
		Voln v = new Voln.VolnBuilder().setDestPath("E:\\jdk14\\workspace\\src\\test\\resources\\")
										.setFileName("SHELL0.VOL")
										.setGameDirPath("E:\\ES2_OS\\dev\\earthsiege2\\")
										.build();
		
		File volFile = new File(v.getGameDirPath()+"VOL\\"+v.getFileName());
		
		try(FileInputStream fizz = new FileInputStream(volFile);
				ByteArrayInputStream bizz = new ByteArrayInputStream(fizz.readAllBytes());){
			v.setRawBytes(bizz.readAllBytes());
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		assertNotNull(v.getRawBytes());
	}
	
	@Test
	public void testVolParse(){		
		
		try {
			testVol = VolFileReader.parseVolFile("E:\\ES2_OS\\dev\\earthsiege2\\VOL\\SHELL0.vol");
			
			VolFileWriter.unpackVol(testVol, "E:\\ES2_OS\\dev\\earthsiege2\\VOL\\DBG\\SHELL");
			
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
			
		}
		try {
			VolFileWriter.writeVolFile(testVol, "E:\\ES2_OS\\dev\\earthsiege2\\VOL\\DBG\\SHELL");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		
}
