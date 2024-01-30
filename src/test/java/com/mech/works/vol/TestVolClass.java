package com.mech.works.vol;

import static org.junit.Assert.assertNotNull;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import com.mech.works.io.read.VolFileReader;
import com.mech.works.vol.data.Voln;

import at.favre.lib.bytes.Bytes;

public class TestVolClass {

	
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
//		Voln v = new Voln.VolnBuilder().setDestPath("E:\\jdk14\\workspace\\src\\test\\resources\\")
//		.setFileName("SHELL0.VOL")
//		.setGameDirPath("E:\\ES2_OS\\dev\\earthsiege2\\")
//		.build();
//	
//		File volFile = new File(v.getGameDirPath()+"VOL\\"+v.getFileName());
//		
//		try(FileInputStream fizz = new FileInputStream(volFile);
//				ByteArrayInputStream bizz = new ByteArrayInputStream(fizz.readAllBytes());){
//			v.setRawBytes(bizz.readAllBytes());
//		
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		assertNotNull(v.getRawBytes());
//		
//		byte[] raw = v.getRawBytes();
//		
//		Bytes b = Bytes.from(raw, 0, 4);
//		
//		System.out.println(b);
//		System.out.println(b.compareTo(Voln.ByteHeader.VOLN.bytes()));
//		
		
		Voln vol = null;
		try {
			vol = VolFileReader.parseVolFile("E:\\ES2_OS\\dev\\earthsiege2\\VOL\\SHELL0.vol");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertNotNull(vol);
	}
	
	public void testVolWrite() {
		
		
		
	}
		
}
