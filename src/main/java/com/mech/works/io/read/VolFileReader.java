package com.mech.works.io.read;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

import com.mech.works.vol.data.VolDir;
import com.mech.works.vol.data.VolEntry;
import com.mech.works.vol.data.Voln;
import com.mech.works.vol.data.Voln.FileType;
import com.mech.works.vol.util.ByteOps;

import at.favre.lib.bytes.Bytes;

public final class VolFileReader {
	
//	private static int offsetVolHeader = 0;
	
	private static int offsetDBSimFlag = 4;
	
	private static int offsetVShellFlag = 5;
	
	private static int offsetUNKValue = 8;	//its set to 05 in every file, UPDATE: this might actually be VOL 'type' or 'load precedence'
		//I noted in the SHELL1.vol and SIMPATCH.vol that this value is actually 0A, which might say to load this vol 'second', much like how
		//Quake loads .pak files in numbered order PAK001, PAK002, PAK003, etc
	
	private static int offsetDirCount = 9;
	
	private static int offsetDirSize = 10;
	
	private static int offsetDirListStart = 12;
	
	private static int dirBytesLength = 5;
	
	//file List comes after dir data
	
	//file size is following bytes
	
	private VolFileReader() {}
	
	public static Voln parseVolFile(String volPath) throws Exception{
		
		System.setProperty("Endianess", "LITTLE_ENDIAN");
		
		File f = new File(volPath);
		Voln volFile = null;
		
		try(FileInputStream fizz = new FileInputStream(f); 
				ByteArrayInputStream bizz = new ByteArrayInputStream(fizz.readAllBytes())){
		
			volFile = new Voln.VolnBuilder()
					.setFileName(f.getName())
					.setRawBytes(bizz.readAllBytes())
					.build();
			
		} catch (IOException e) {
			throw new IOException(e);
		}
		if(volFile == null) {
			throw new FileNotFoundException("volFile not built, check path.("+f.getAbsolutePath() +")");
		}
		
		Bytes volData = Bytes.from(volFile.getRawBytes());
		
		volFile.setDbsimFlag(volData.byteAt(offsetDBSimFlag) == 1 ? true : false);
		volFile.setVshellFlag(volData.byteAt(offsetVShellFlag) == 1 ? true : false);
		
		volFile.setDirCount(volData.byteAt(offsetDirCount));
		System.out.println("-Dir List="+volFile.getDirCount());	//DEBUG
		
		volFile.setDirSize(Bytes.from(volData.array(), offsetDirSize, 2).byteOrder(ByteOrder.LITTLE_ENDIAN).toShort());
		System.out.println("-Dir Byte Size="+volFile.getDirSize());	//DEBUG
		
		int cursor = offsetDirListStart;
		for(int i = 0; i < volFile.getDirCount(); i++) {
			Bytes dirName = Bytes.from(volData.array(), cursor, dirBytesLength);
			cursor += dirBytesLength;
			
			//debug
			System.out.println(new String(dirName.toCharArray()).substring(0, 3));//DEBUG
			
			VolDir dir = new VolDir(new String(dirName.toCharArray()).substring(0, 3), ByteOps.int4ToByteLittleEndian(i));
			
			volFile.getFolders().put(dir.getDirIdx(), dir);
		}
		//DOES sorting directory in-take really matter if writing a new vol won't conform to the read-in vol's format at all?
		
		volFile.setListCount(Bytes.from(volData.array(), cursor, 2).byteOrder(ByteOrder.LITTLE_ENDIAN).toShort());
		System.out.println("-File List="+volFile.getListCount());	//DEBUG
		
		cursor += 2;
		volFile.setListSize(Bytes.from(volData.array(), cursor, 4).byteOrder(ByteOrder.LITTLE_ENDIAN).toInt());
		System.out.println("-File Byte Size="+volFile.getListSize());	//DEBUG
		
		cursor += 4;
		System.out.println("Cursor byte check:" + cursor);
		Bytes fileListBytes = Bytes.from(volFile.getRawBytes(), cursor, volFile.getListSize());
		
		if(fileListBytes.array().length == 0) {
			System.out.println("WARN: no files! ending process");
			return volFile;
		}

		VolFileReader.generateFileList(volFile, fileListBytes);
		VolFileReader.sortHeaderFileListDirs(volFile);
	
		Bytes magic9 = Bytes.from(volFile.getRawBytes(), cursor + volFile.getListSize(), 9).byteOrder(ByteOrder.LITTLE_ENDIAN);

		String s = "";
		for(byte b : magic9.toList()) {
			s += Integer.toHexString(b);
			s += " ";
		}
		
		
		System.out.println("---Magic 9 bytes after file list:" + s);
		
		return volFile;
	}
	
	
	private static void generateFileList(Voln vol, Bytes fileListBytes) throws Exception{

		vol.setFilesSet(new VolEntry[vol.getListCount()]);
		int fileCount = 0;
		
		VolEntry link = null;
		
		//update: file list names are always 12 chars, there's some unknown bytes trailing some of the names, but they do fit.
		for(int i = 0; i <= vol.getListSize() - 18; i+=18) {
			
			VolEntry entry = new VolEntry();
			
			Bytes listName = Bytes.from(fileListBytes.array(), i, 12);
			entry.setVolListBytes(listName);
			
			
			entry.setDirIdx(Bytes.from(fileListBytes.array()).byteAt(i+13));
			
			if(entry.getDirIdx() == 0x01) {
				System.out.println("honk");
			}
			
			entry.setVolOffset(Bytes.from(fileListBytes.array(), i+14, 4).byteOrder(ByteOrder.LITTLE_ENDIAN));
			

			String fileName = new String(listName.array(), Charset.forName("UTF-8"));
			fileName = fileName.substring(0, (fileName.lastIndexOf('.')+4));
			entry.setFileName(fileName);
			
			entry.setExt(FileType.typeFromVal(fileName.substring(fileName.lastIndexOf('.')+1)));
			
			vol.getFilesSet()[fileCount] = entry;
			fileCount++;
			if(link != null) {
				link.setNextEntry(entry);
			}
			link = entry;
			
		}
		//File header list is fixed order, populating an array is the best choice.
		//	WARN: file offset in file header list is from the dir-count byte, ignoring the first 8 bytes.
		
		Bytes bytesNoHeader = Bytes.from(vol.getRawBytes(), 9, vol.getRawBytes().length-10);
		for(int i = 0; i < vol.getFilesSet().length; i++) {
			VolEntry entryFile = vol.getFilesSet()[i];

			int startOfs = entryFile.getVolOffset().toInt();
			int endOfs = 0;
			
			Bytes fileEndOfs = null;
			if(entryFile.getNextEntry() != null) {
				fileEndOfs = vol.getFilesSet()[i+1].getVolOffset();
				endOfs = fileEndOfs.toInt() - 10;	//acounts for 9 header byte at top of VOL file that got snipped.
			}
			else {
				
				fileEndOfs = Bytes.from(bytesNoHeader.array().length);
				endOfs = fileEndOfs.toInt();
			}
			
			Bytes raw = VolFileReader.fetchFileBytes(bytesNoHeader.array(), startOfs, endOfs);
			entryFile.setRawBytes(raw.array());
		}
	}
	
//	private static VolEntry newFileEntry(Voln vol, Voln.FileType type, byte dirIndex, String fileName, Bytes memOfs) {
//		
//		VolEntry newRecord = new VolEntry();
//		newRecord.setExt(type);
//		newRecord.setFileName(fileName);
//		newRecord.setDirIdx(dirIndex);
//		newRecord.setVolOffset(Bytes.from(memOfs));
//		
//		return newRecord;		
//	}
	
	
	private static void sortHeaderFileListDirs(Voln vol) {
		for(VolEntry file : vol.getFilesSet()) {
			vol.getFolders().get(file.getDirIdx()).getFiles().add(file);
		}
	}
	
	
	public static Bytes fetchFileBytes(byte[] volData, int fileMemOfs, int nextFileMemOfs) {
		
		
		Bytes fileBytes = null;
		try {
			
			fileBytes = Bytes.from(volData, fileMemOfs, (nextFileMemOfs - fileMemOfs));
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
			
		return fileBytes;
	}
}
