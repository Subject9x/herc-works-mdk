package com.mech.works.vol;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.LinkedHashSet;

import org.junit.Test;

import com.mech.works.io.read.VolFileReader;
import com.mech.works.io.write.VolFileWriter;
import com.mech.works.vol.data.VolEntry;
import com.mech.works.vol.data.Voln;

import at.favre.lib.bytes.Bytes;

public class TestVolClass {

	private Voln testVol;
	
	@Test
	public void testVolLoad() {
		Voln testVol = null;
		try {
			//testVol = VolFileReader.parseVolFile("E:\\ES2_OS\\dev\\earthsiege2\\VOL\\SIMVOL0.vol");
			testVol = VolFileReader.parseVolFile("E:\\ES2_OS\\dev\\earthsiege2\\VOL\\BACKUP\\SHELL0.vol");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertNotNull(testVol.getRawBytes());
	}
	
	
	@Test
	public void testHeaderFileListBytes(){
		Voln testVol = null;
		try {
//			testVol = VolFileReader.parseVolFile("E:\\ES2_OS\\dev\\earthsiege2\\VOL\\SIMVOL0.vol");
			testVol = VolFileReader.parseVolFile("E:\\ES2_OS\\dev\\earthsiege2\\VOL\\BACKUP\\SIMVOL0.vol");
//			testVol = VolFileReader.parseVolFile("E:\\ES2_OS\\dev\\earthsiege2\\VOL\\SIMVOL0x1.vol");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertNotNull(testVol.getRawBytes());
		
		HashMap<String, LinkedHashSet<VolEntry>> map = new HashMap<String, LinkedHashSet<VolEntry>>();
		
		
		for(int i=0; i < testVol.getFilesSet().length; i++) {
			if(i + 1 < testVol.getFilesSet().length) {
				VolEntry f1 = testVol.getFilesSet()[i];
				
				
				
				int ofs = f1.getVolOffset().toInt() + f1.getRawBytes().length + 9;
				
				Bytes w = Bytes.allocate(0);
				for(int b=ofs; b<=testVol.getRawBytes().length; b++) {
					byte t = testVol.getRawBytes()[b];
					if(t == (byte)2) {
						break;
					}
					w = w.append(t);
				}
				
				
				
				int listStrEnd =  18 - (f1.getFileName().length() + 1 + 1 + 4);
				Bytes listTail = null;
				if(listStrEnd > 0 ) {
					listTail = Bytes.from(f1.getVolListBytes().array(), f1.getFileName().length() + 1, listStrEnd); 
				}
				
//				System.out.println("=================="+f1.getFileName()+"====================");
				if(listTail != null ) {
					if(listTail.length() == 1 && listTail.toByte() == 0x00) {
						//skip
					}
					else {
						LinkedHashSet<VolEntry> arr = map.get(listTail.encodeHex());
						if(arr == null) {
							arr = new LinkedHashSet<VolEntry>();
							map.put(listTail.encodeHex(), arr);
						}
						arr.add(f1);
					}
//					System.out.println("list bytes=" + listTail.encodeHex());
				}
//				System.out.println("vol offset=" + f1.getVolOffset().toInt());
//				System.out.println("file size =" + f1.getFileSize().toInt());
//				if(w.length() > 0) {
//					System.out.println("Tail Scan =" + w.encodeHex() +"|" + w.toUnsignedByte());	
//				}		
			}	
		}
		map.forEach((id, set) -> {set.forEach((f)->{
			System.out.println("===="+f.getFileName()+"====");
			int listStrEnd =  18 - (f.getFileName().length() + 1 + 1 + 4);
			Bytes listTail = null;
			if(listStrEnd > 0 ) {
				listTail = Bytes.from(f.getVolListBytes().array(), f.getFileName().length() + 1, listStrEnd); 
			}
			System.out.println("list bytes=" + listTail.encodeHex() + (listTail.length() == 1 ? "~"+listTail.toUnsignedByte(): ""));
//			System.out.println("vol offset=" + f.getVolOffset().toInt());
			System.out.println("file size =" + f.getFileSize().toInt() + "\n");
		}); });
	}
	
	@Test
	public void testVolParse(){		
		
		try {
			testVol = VolFileReader.parseVolFile("E:\\ES2_OS\\dev\\earthsiege2\\VOL\\BACKUP\\SIMVOL0.vol");
			testVol.setFileName("SIMVOL0x1.VOL");
			VolFileWriter.unpackVol(testVol, "E:\\ES2_OS\\dev\\earthsiege2\\VOL\\DBG");
			
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
				VolFileWriter.packVolToFile(testVol, "E:\\ES2_OS\\dev\\earthsiege2\\VOL");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Test
	/**
	 * Write the loaded vol byte[] directly back to a file to test for write-errors.
	 */
	public void testVolWritePlain() {
		testVolParse();

		try {
			testVol = VolFileReader.parseVolFile("E:\\ES2_OS\\dev\\earthsiege2\\VOL\\BACKUP\\SHELL0.vol");
			testVol.setFileName("SHELL0x1.VOL");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(testVol != null) {
			try {
				VolFileWriter.packVolToFile(testVol, "E:\\ES2_OS\\dev\\earthsiege2\\VOL");
				
				File write = new File("E:\\ES2_OS\\dev\\earthsiege2\\VOL\\"+testVol.getFileName());
				FileOutputStream fozz = new FileOutputStream(write);
				
				fozz.write(testVol.getRawBytes());
				fozz.flush();
				fozz.close();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Test
	public void testGranularByteCheck() {
		//load a stock vol and byte-for-byte compare vs exported vol.
		Voln stock = null;
		Voln test = null;
		try {
			stock = VolFileReader.parseVolFile("E:\\ES2_OS\\dev\\earthsiege2\\VOL\\BACKUP\\SIMVOL0.vol");
			stock.setFileName("STOCK.VOL");
			

			test = VolFileReader.parseVolFile("E:\\ES2_OS\\dev\\earthsiege2\\VOL\\SIMVOL0.vol");
			test.setFileName("TEST.VOL");
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (Exception e2) {
			e2.printStackTrace();
		}
		
		assertNotNull(stock);
		assertNotNull(test);
		
		if(test.getRawBytes().length != stock.getRawBytes().length) {
			System.out.println("COMPARE: TOTAL BYTES - MISMATCH");
			return;
		}
		

		for(int i=0; i<stock.getRawBytes().length; i++) {
			if(stock.getRawBytes()[i] != test.getRawBytes()[i]) {
				System.out.println("Byte mismatch at offset[" + i + "]	"
									+ "STOCK(" + Bytes.from(stock.getRawBytes()[i]).encodeHex() + ") vs "
									+ "TEST(" + Bytes.from(test.getRawBytes()[i]).encodeHex() +")");
				
//				for(VolEntry entry : test.getFilesSet()) {
//					int start = entry.getVolOffset().toInt() - 10;
//					int end = entry.getVolOffset().toInt() + entry.getRawBytes().length;
//					if(i>=start && i<= end) {
//						System.out.println("MISMATCH in FILE[" + entry.getFileName()+"]"
//								+ ", ofs(" + entry.getVolOffset().toInt() +"), size("+entry.getFileSize().toInt() +")"
//								+ ", tail bytes=[" + Bytes.from(entry.getRawBytes(), entry.getRawBytes().length-2, 2).encodeHex()+"]");
//					}
//				}
//				
			}
		}
	}
	
}
