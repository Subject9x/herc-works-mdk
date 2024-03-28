package org.hercworks.voln.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteOrder;

import org.hercworks.voln.DataFile;
import org.hercworks.voln.VolDir;
import org.hercworks.voln.VolEntry;
import org.hercworks.voln.Voln;
import org.hercworks.voln.Voln.FileType;

import at.favre.lib.bytes.Bytes;

public final class VolFileReader {
	
//	private static int offsetVolHeader = 0;
	
	private static int offsetDBSimFlag = 4;
	
	private static int offsetVShellFlag = 5;
	
	private static int offsetVolOrderNum = 8;	//its set to 05 in every file, UPDATE: this might actually be VOL 'type' or 'load precedence'
		//I noted in the SHELL1.vol and SIMPATCH.vol that this value is actually 0A, which might say to load this vol 'second', much like how
		//Quake loads .pak files in numbered order PAK001, PAK002, PAK003, etc
	
	private static int offsetDirCount = 9;
	
	private static int offsetDirSize = 10;
	
	private static int offsetDirListStart = 12;
	
	//file List comes after dir data
	
	//file size is following bytes
	
	public VolFileReader() {}
	
	public static Voln parseVolFile(String volPath) throws Exception{
		
		System.setProperty("Endianess", "LITTLE_ENDIAN");
		
		File f = new File(volPath);
		Voln volFile = null;
		
		try(FileInputStream fizz = new FileInputStream(f);){
			
			byte[] data = new byte[(int)f.length()];
			fizz.read(data);
			volFile = new Voln.VolnBuilder()
					.setFileName(DataFile.makeFileName(f.getName()))
					.setRawBytes(data)
					.build();
			
		} catch (IOException e) {
			throw new IOException(e);
		}
		if(volFile == null) {
			throw new FileNotFoundException("volFile not built, check path.("+f.getAbsolutePath() +")");
		}
		
		volFile.setFilePath(volPath);
		
		Bytes volData = Bytes.from(volFile.getRawBytes());
		
		volFile.setDbsimFlag(volData.byteAt(offsetDBSimFlag) == 1 ? true : false);
		volFile.setVshellFlag(volData.byteAt(offsetVShellFlag) == 1 ? true : false);
		
		volFile.setVolOrderNum(volData.byteAt(offsetVolOrderNum));
//		System.out.println("-Load Order="+volFile.getVolOrderNum());	//DEBUG
		
		volFile.setDirCount(volData.byteAt(offsetDirCount));
//		System.out.println("-Dir List="+volFile.getDirCount());	//DEBUG
		
		volFile.setDirSize(Bytes.from(volData.array(), offsetDirSize, 2).byteOrder(ByteOrder.LITTLE_ENDIAN).toShort());
//		System.out.println("-Dir Byte Size="+volFile.getDirSize());	//DEBUG
		
		//BUILD FOLDER LIST
		int cursor = offsetDirListStart;
		cursor = VolFileReader.generateFolderList(volFile, volData, cursor);
		
		//DOES sorting directory in-take really matter if writing a new vol won't conform to the read-in vol's format at all?
		volFile.setListCount(Bytes.from(volData.array(), cursor, 2).byteOrder(ByteOrder.LITTLE_ENDIAN).toShort());
		cursor += 2;
//		System.out.println("-File List="+volFile.getListCount());	//DEBUG
		
		volFile.setListSize(Bytes.from(volData.array(), cursor, 4).byteOrder(ByteOrder.LITTLE_ENDIAN).toInt());
		cursor += 4;
//		System.out.println("-File Byte Size="+volFile.getListSize());	//DEBUG
		
		//FIXME - turns  out Vol files can be 2-chars long...so I had to rewire the directory list builder to march bytes, this has borked line 98 :(
//		System.out.println("Cursor byte check:" + cursor);
		Bytes fileListBytes = Bytes.from(volFile.getRawBytes(), cursor, volFile.getListSize());
		
		if(fileListBytes.array().length == 0) {
			System.out.println("WARN: no files! ending process");
			return volFile;
		}

		VolFileReader.generateFileList(volFile, fileListBytes);
		VolFileReader.sortHeaderFileListDirs(volFile);
		
		cursor += volFile.getListSize();
//		System.out.println("Cursor byte check:" + cursor);
		
		
		//DEBUG - it appears every file gets a 9byte prefix of unknown(at this time) use.
//		VolFileReader.scanVoidBytes(cursor, volFile);
//		VolFileReader.debugSortPrefix(volFile);
//		VolFileReader.debugUnsortedPrefix(volFile.getDirCount(), volFile.getFolders());
//		VolFileReader.debugFileByteJoins(volFile);
		
		return volFile;
	}
	
	
	private static int generateFolderList(Voln vol, Bytes volData, int cursor) throws Exception{
		
		Bytes dirList = Bytes.from(volData.array(), cursor, vol.getDirSize());
		Bytes dirNameBytes = Bytes.allocate(0);
		byte dirCount = 0;
		for(byte b : dirList.array()) {
			if( b >= 0x30 && b <= 0x7A) {
				if( b == 0x5C) {
					//debug
					System.out.println(new String(dirNameBytes.toCharArray()));//DEBUG
					
					VolDir dir = new VolDir(new String(dirNameBytes.toCharArray()), dirCount);
					
					vol.getFolders().put(dir.getDirIdx(), dir);

					cursor += dirNameBytes.array().length + 2;
							
					dirCount = (byte)(dirCount + 1);
					dirNameBytes = Bytes.allocate(0);
				}
				else{
					dirNameBytes = dirNameBytes.append(b);
				}
				
			}	
		}
		return cursor;
	}
	
	
	private static void generateFileList(Voln vol, Bytes fileListBytes) throws Exception{

		vol.setFilesSet(new VolEntry[vol.getListCount()]);
		
		int fileCount = 0;
		int cursor = 0;
		
		for(int i = 0; i < vol.getListCount(); i++) {
			VolEntry entry = new VolEntry();
			cursor = i * 18;
			
			Bytes listing = Bytes.from(fileListBytes.array(), cursor, 18).byteOrder(ByteOrder.LITTLE_ENDIAN);
			
			Bytes listName = Bytes.from(listing.array(), 0, 13);
			entry.setVolListBytes(listName);
			
			entry.setDirIdx(Bytes.from(listing.array()).byteAt(13));

			entry.setVolOffset(Bytes.from(listing.array(), 14, 4).byteOrder(ByteOrder.LITTLE_ENDIAN));
			
			entry.setFileName(VolEntry.nameFromListByte(listName.array()));
			
			entry.setExt(FileType.typeFromVal(entry.getFileName().substring(entry.getFileName().lastIndexOf('.') + 1)));
			
			int startOfs = entry.getVolOffset().toInt();
			
			entry.setFileCompressionType(Bytes.from(vol.getRawBytes(), startOfs, 1));
			startOfs += 1;
			
			entry.setFileSize(Bytes.from(vol.getRawBytes(), startOfs, 4).byteOrder(ByteOrder.LITTLE_ENDIAN));
			startOfs += 4;
			
			entry.setMagicPrefix(Bytes.from(vol.getRawBytes(), startOfs, 4));
			startOfs += 4;
			
			int endOfs = startOfs + entry.getFileSize().toInt();
			
			try {
				entry.setRawBytes(VolFileReader.fetchFileBytes(vol.getRawBytes(), startOfs, endOfs).array());
			}catch(Exception e){
				System.out.println("ERROR[" + entry.getFileName() +"](setRawBytes): " + e.getMessage());
			}
				
			if(Voln.filesNoHeader().contains(entry.getExt().val())) {
				entry.setHeader(new byte[0]);
			}
			else {
				try {
					entry.setHeader(Bytes.from(entry.getRawBytes(), 0, 4).byteOrder(ByteOrder.BIG_ENDIAN).array());
				}
				catch(Exception dbgE) {
					System.out.println("ERROR["+entry.getFileName()+"](setHeader):"+dbgE.getMessage());
				}
			}
			
			if(endOfs < vol.getRawBytes().length) {
				entry.setUnknownEoFByte(Bytes.from(vol.getRawBytes(), endOfs, 1));
			}
			else {
				entry.setUnknownEoFByte(null);
			}
			
			vol.getFilesSet()[fileCount] = entry;
			fileCount += 1;
			
		}
	}
	
	private static void sortHeaderFileListDirs(Voln vol) {
		System.out.println("sorting file--------------------------------");
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
	
	
//	@Deprecated
//	private static void debugUnsortedPrefix(int totalDirs, Map<Byte, VolDir> directory){
//		System.out.println("WRITING FILE LIST=====================================");
//		for(int i = 0; i < totalDirs; i++) {
//			Set<VolEntry> folder = directory.get((byte)i).getFiles();
//			
//			for(VolEntry entry : folder) {
////				System.out.println(directory.get((byte)i).getLabel() +"\\" + entry.getFileName() + "|" + entry.printMagicPrefix());
////				System.out.println(entry.getFileName() + "|" + entry.printMagicPrefix() + "| rawByteSize[" + entry.getRawBytes().length +"] | ofs[" + entry.getVolOffset().toInt()+"]");
//			}
//		}
//	}
	
//	@Deprecated
//	private static void debugSortPrefix(Voln vol) {
//		
//		Map<String, List<VolEntry>> uniquePrefix = new HashMap<String, List<VolEntry>>();
//		
//		
//		File log = new File(vol.getFilePath().substring(0, vol.getFilePath().lastIndexOf('\\')+1) + "write.log");
//		
//		
//		for(VolEntry file : vol.getFilesSet()) {
//			String prefix = file.getMagicPrefix().encodeHex();
//			prefix = prefix.substring(prefix.length()-3, prefix.length());
//			if(!uniquePrefix.keySet().contains(prefix)) {
//				List<VolEntry> newList = new ArrayList<VolEntry>();
//				uniquePrefix.put(prefix, newList);
//			}
//			uniquePrefix.get(prefix).add(file);
//		}
//		
//		try(FileOutputStream fozz = new FileOutputStream(log);){
//			for(String k : uniquePrefix.keySet()) {			
////				uniquePrefix.get(k).stream().forEach(v -> System.out.println(v.getFileName() + "|" + v.printMagicPrefix()));
//				
//				uniquePrefix.get(k).stream().forEach(v -> {
//					try {
//						fozz.write(new String(v.getFileName() + "|" + v.printMagicPrefix()+"\n").getBytes());
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				});
//			}
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
	
	
	/**
	 * Scans the 10? bytes between a file's end offset and before the NEXT file's starting offset.
	 * Trying to figure out if the  byte before the 02 relates TO this 02 bytes.
	 * @param vol
	 */
//	@Deprecated
//	private static void debugFileByteJoins(Voln vol) {
//		
//		VolEntry prev = null;
//		
//		for(VolEntry entry : vol.getFilesSet()) {
//
//			if(prev != null) {
//				int prevFileEnd = prev.getVolOffset().toInt() + prev.getFileSize().toInt();
//				int checkOfs = entry.getVolOffset().toInt() - 1;
//				int spaceBytes = entry.getVolOffset().toInt() - prevFileEnd;
//				
//				if(checkOfs > prevFileEnd) {
//					Bytes lookBack = Bytes.from(vol.getRawBytes(), checkOfs, 2);
//					Bytes prevFileSuffix4 = Bytes.from(vol.getRawBytes(), prevFileEnd-2, 14);
//					
////					System.out.println("JOIN:"+prev.getFileName() + "|" + entry.getFileName() + "|" + lookBack.encodeHex());
////					System.out.println("				# of bytes before file:" + spaceBytes);
////					System.out.println("					" +prevFileSuffix4.encodeHex());
////					System.out.println("-------------------------------------------------------------------------------------------------------------");
//				}
//			}
//			prev = entry;
//		}
//	}
	
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
//	@Deprecated 
//	private static void scanVoidBytes(int cursor, Voln vol) {
//		cursor -= 1;
//		int ofs = 0;
//		int groupCount = 0;
//		String printBytes = "";
//		
//		for(byte b : vol.getRawBytes()) {
//			if(ofs > cursor) {
//				boolean loose = true;
//				for(VolEntry f : vol.getFilesSet()) {
//					if(ofs >= f.getVolOffset().toInt() && ofs <= f.getVolOffset().toInt()+f.getRawBytes().length) {
//						loose = false;
//					}
//				}
//				if(loose){
//					if(groupCount == 8) {
//						System.out.println("file prefix byte["+printBytes+"] @ (" + (ofs - 9) +")");
//						groupCount = 0;
//						printBytes = "";
//					}
//					else {
//						printBytes += Bytes.from(b).encodeHex();
//					}
//					groupCount += 1;
//				}
//			}	
//			ofs += 1;
//		}
//	}
}
