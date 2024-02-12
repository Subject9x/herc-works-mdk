package com.mech.works.io.read;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		
		//BUILD FOLDER LIST
		int cursor = offsetDirListStart;
		VolFileReader.generateFolderList(volFile, volData, cursor);
		
		
		//DOES sorting directory in-take really matter if writing a new vol won't conform to the read-in vol's format at all?
		volFile.setListCount(Bytes.from(volData.array(), cursor, 2).byteOrder(ByteOrder.LITTLE_ENDIAN).toShort());
		cursor += 2;
		System.out.println("-File List="+volFile.getListCount());	//DEBUG
		
		volFile.setListSize(Bytes.from(volData.array(), cursor, 4).byteOrder(ByteOrder.LITTLE_ENDIAN).toInt());
		cursor += 4;
		System.out.println("-File Byte Size="+volFile.getListSize());	//DEBUG
		
		//FIXME - turns  out Vol files can be 2-chars long...so I had to rewire the directory list builder to march bytes, this has borked line 98 :(
		System.out.println("Cursor byte check:" + cursor);
		Bytes fileListBytes = Bytes.from(volFile.getRawBytes(), cursor, volFile.getListSize()).byteOrder(ByteOrder.LITTLE_ENDIAN);
		
		if(fileListBytes.array().length == 0) {
			System.out.println("WARN: no files! ending process");
			return volFile;
		}

		VolFileReader.generateFileList(volFile, fileListBytes);
		VolFileReader.sortHeaderFileListDirs(volFile);
		
		cursor += volFile.getListSize();
		System.out.println("Cursor byte check:" + cursor);
		
		
		//DEBUG - it appears every file gets a 9byte prefix of unknown(at this time) use.
//		VolFileReader.scanVoidBytes(cursor, volFile);
		VolFileReader.debugSortPrefix(volFile);
		
		return volFile;
	}
	
	
	private static void generateFolderList(Voln vol, Bytes volData, int cursor) throws Exception{
		
		Bytes dirList = Bytes.from(volData.array(), cursor, vol.getDirSize());
		Bytes dirNameBytes = Bytes.allocate(0);
		int dirCount = 0;
		for(byte b : dirList.array()) {
			if( b >= 0x30 && b <= 0x7A) {
				if( b == 0x5C) {
					//debug
					System.out.println(new String(dirNameBytes.toCharArray()));//DEBUG
					
					VolDir dir = new VolDir(new String(dirNameBytes.toCharArray()), ByteOps.int4ToByteLittleEndian(dirCount));
					
					vol.getFolders().put(dir.getDirIdx(), dir);
					
					dirCount++;
					dirNameBytes = Bytes.allocate(0);
				}
				else{
					dirNameBytes = dirNameBytes.append(b);
				}
				
			}
			
		}
	}
	
	
	private static void generateFileList(Voln vol, Bytes fileListBytes) throws Exception{

		vol.setFilesSet(new VolEntry[vol.getListCount()]);
		
		int fileCount = 0;
		int cursor = 0;
		VolEntry link = null;
		
		for(int i = 0; i < vol.getListCount(); i++) {
			VolEntry entry = new VolEntry();
			cursor = i * 18;
			
			Bytes listing = Bytes.from(fileListBytes.array(), cursor, 18).byteOrder(ByteOrder.LITTLE_ENDIAN);
			
			Bytes listName = Bytes.from(listing.array(), 0, 13);
			entry.setVolListBytes(listName);
			
			entry.setDirIdx(Bytes.from(listing.array()).byteAt(13));

			entry.setVolOffset(Bytes.from(listing.array(), 14, 4).byteOrder(ByteOrder.LITTLE_ENDIAN));
			
			String fileName = new String(listName.array(), Charset.forName("UTF-8"));
			fileName = fileName.substring(0, (fileName.lastIndexOf('.') + 4));
			entry.setFileName(fileName);
			
			entry.setExt(FileType.typeFromVal(fileName.substring(fileName.lastIndexOf('.') + 1)));
			
			vol.getFilesSet()[fileCount] = entry;
			if(link != null) {
				link.setNextEntry(entry);
			}
			link = entry;
			fileCount += 1;
			
		}
		
		//File header list is fixed order, populating an array is the best choice.		
		for(int i = 0; i < vol.getFilesSet().length; i++) {
			VolEntry entryFile = vol.getFilesSet()[i];

			entryFile.setVolOffset(Bytes.from(entryFile.getVolOffset().toInt()+9));	///debug for 9 byte prefix printout
			int startOfs = entryFile.getVolOffset().toInt();
			int endOfs = 0;
			
			Bytes fileEndOfs = null;
			if(entryFile.getNextEntry() != null) {
				fileEndOfs = vol.getFilesSet()[i+1].getVolOffset();
				endOfs = fileEndOfs.toInt();
			}
			else {
				
				fileEndOfs = Bytes.from(vol.getRawBytes().length);
				endOfs = fileEndOfs.toInt();
			}
			
			Bytes raw = VolFileReader.fetchFileBytes(vol.getRawBytes(), startOfs, endOfs);
			entryFile.setRawBytes(raw.array());
			
			
			entryFile.setMagicPrefix(Bytes.from(vol.getRawBytes(), entryFile.getVolOffset().toInt() - 10, 9));	///debug for 9 byte prefix printout);
			
			System.out.println(entryFile.toString() + ", endOfs=" + endOfs);	//DEBUG
		}
	}
	
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
	
	
	
	private static void debugSortPrefix(Voln vol) {
		
		Map<String, List<VolEntry>> uniquePrefix = new HashMap<String, List<VolEntry>>();
		
		
		for(VolEntry file : vol.getFilesSet()) {
			String prefix = file.getMagicPrefix().encodeHex();
			prefix = prefix.substring(prefix.length()-3, prefix.length());
			if(!uniquePrefix.keySet().contains(prefix)) {
				List<VolEntry> newList = new ArrayList<VolEntry>();
				uniquePrefix.put(prefix, newList);
			}
			uniquePrefix.get(prefix).add(file);
		}
		
		for(String k : uniquePrefix.keySet()) {
			uniquePrefix.get(k).stream().forEach(v -> System.out.println(v.getFileName() + "|" + v.getMagicPrefix().encodeHex()));
		}
		
	}
	
	
	/**
	 * Deprecated - 02/11/2024 - this was used to spot that evey file entry seems to have a 9 byte prefix that DOES match the offset of the file in header list.
	 * 
	 * Debug tool, byte-marcher. Export tests create a SHELL0.vol that is short 1560 bytes, or roughly 9 bytes per file entry
	 * (170 files) * 9.
	 * This marches the entire byte array, testing if each byte falls into a defined file-bytes array range.
	 * If outside the range, report a 'true'.
	 * @param ofs
	 * @param vol
	 * @return
	 */
	@Deprecated
	private static void scanVoidBytes(int cursor, Voln vol) {
		cursor -= 1;
		int ofs = 0;
		int groupCount = 0;
		String printBytes = "";
		
		for(byte b : vol.getRawBytes()) {
			if(ofs > cursor) {
				boolean loose = true;
				for(VolEntry f : vol.getFilesSet()) {
					if(ofs >= f.getVolOffset().toInt() && ofs <= f.getVolOffset().toInt()+f.getRawBytes().length) {
						loose = false;
					}
				}
				if(loose){
					if(groupCount == 8) {
						System.out.println("file prefix byte["+printBytes+"] @ (" + (ofs - 9) +")");
						groupCount = 0;
						printBytes = "";
					}
					else {
						printBytes += Bytes.from(b).encodeHex();
					}
					groupCount += 1;
				}
			}	
			ofs += 1;
		}
	}
}
