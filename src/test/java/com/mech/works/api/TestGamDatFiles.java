package com.mech.works.api;

import static org.junit.Assert.assertNotNull;

import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

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
	public void testEditShellDat() {
	
		Voln shell = null;
		try {
//			shell = getVol("E:\\ES2_OS\\dev\\earthsiege2\\VOL\\BACKUP\\SIMVOL0.VOL");
			shell = getVol("E:\\ES2_OS\\dev\\earthsiege2\\VOL\\BACKUP\\SHELL0.VOL");
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}

		assertNotNull(shell);
		
		shell.setFileName("SHELL0.VOL");
		shell.setDestPath("E:\\ES2_OS\\dev\\earthsiege2\\VOL");
		
		VolEntry test = null;
		
		for(VolEntry entry : shell.getFilesSet()) {
			if(entry.getFileName().equals("ARM_RAPT.DAT")) {
				test = entry;
			}
			
		}
		
		test.getRawBytes()[2] = (byte)0x01;
		
		test.getRawBytes()[10] = (byte)0x32;
		
//		test.getRawBytes()[48] = (byte)0x05;	//0x05
//		test.getRawBytes()[50] = (byte)0x02;	//0x02
		
//		test.getRawBytes()[164] = (byte)0x00;	//0x02
		
		
//		test.getRawBytes()[182] = (byte)0x0A;	//ATC100 frame
//		test.getRawBytes()[184] = (byte)0x00;	//no X-flip op
//		test.getRawBytes()[186] = (byte)0x01;
		if(shell != null) {
			try {
				VolFileWriter.packVolToFileStrict(shell, "E:\\ES2_OS\\dev\\earthsiege2\\VOL");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
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
		
		VolEntry test = null;
		
		for(VolEntry entry : shell.getFilesSet()) {
			if(entry.getFileName().equals("BULLETS.DAT")) {
				test = entry;
			}
		}
		
//		Object[][] table = new Object[28][19];
		
		
		
		//PROJ.DAT
//		Object[][] table = new Object[28][36];
//		table[0] = new String[]{"row","0-1","2-3","4-5","6-7","8-9","10-11","12-13","14-15","16-17","18-19","20-21","22-23","24-25","26-27","28-29","30-31","32-33","34-35","36-37"};
//		
//		byte[][] entries = new byte[27][];
//		int r = 0;
//		for(int i=2; i < test.getRawBytes().length; i += 36) {
//			byte[] row = Bytes.from(test.getRawBytes(), i, 36).array();
//			
//			List<String> str = new ArrayList<String>();
//			str.add(r+"|"+(i-2)+":");
//			for(int b=0; b<row.length; b+=2) {
//				str.add(String.valueOf(Bytes.from(row, b, 2).byteOrder(ByteOrder.LITTLE_ENDIAN).toShort()));
//			}
//			table[r+1] = str.toArray();
//			
//			if(r>8 && r<13) {
//				test.getRawBytes()[i+4] = (byte)0x01;
//				test.getRawBytes()[i+5] = (byte)0x00;
//			}
//			
//		
////			test.getRawBytes()[i+6] = (byte)0x01;
////			test.getRawBytes()[i+7] = (byte)0x00;
//			
//			entries[r] = row;
//			r++;
//		}
//		
//		for (final Object[] row : table) {
//		    System.out.format("%10s%10s%10s%10s%10s%10s%10s%10s%10s%10s%10s%10s%10s%10s%10s%10s%10s%10s%10s%n", row);
//		}

		//BULLETS.DAT
//		Object[][] table = new Object[13][14];
//		table[0] = new String[]{"row","0-1","2-3","4-5","6-7","8-9","10-11","12-13","14-15","16-17","18-19","20-21","22-23","24-25","26-27","28-29","30-31","32-33","34-35","36-37"};
//		
//		byte[][] entries = new byte[13][];
//		int r = 0;
//		for(int i=2; i < test.getRawBytes().length; i += 14) {
//			byte[] row = Bytes.from(test.getRawBytes(), i, 14).array();
//			
//			List<String> str = new ArrayList<String>();
//			str.add(r+"|"+(i-2)+":");
//			for(int b=0; b<row.length; b+=2) {
//				str.add(String.valueOf(Bytes.from(row, b, 2).byteOrder(ByteOrder.LITTLE_ENDIAN).toShort()));
//			}
//			table[r+1] = str.toArray();
//			
////			test.getRawBytes()[i] = (byte)0x02;
//			
//			System.out.println("i+4=" + Bytes.from(test.getRawBytes()[i+4]).encodeHex());
//			System.out.println("i+5=" + Bytes.from(test.getRawBytes()[i+5]).encodeHex());
//			test.getRawBytes()[i+12] = (byte)0x01;
//			test.getRawBytes()[i+13] = (byte)0x00;
//			
//			entries[r] = row;
//			r++;
//		}
//		
//		for (final Object[] row : table) {
//		    System.out.format("%10s%10s%10s%10s%10s%10s%10s%10s%n", row);
//		}
		
		
		if(shell != null) {
			try {
				VolFileWriter.packVolToFileStrict(shell, "E:\\ES2_OS\\dev\\earthsiege2\\VOL");
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
	}
}

