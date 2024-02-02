package com.mech.works.io.read;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.mech.works.data.file.VolEntry;
import com.mech.works.data.ref.files.DataFile;
import com.mech.works.vol.data.Voln;
import com.mech.works.vol.data.Voln.FileType;

import at.favre.lib.bytes.Bytes;

public final class VolFileReader {
	
//	private static int offsetVolHeader = 0;
	
	private static int offsetVolExe1 = 4;
	
	private static int offsetVolExe2 = 5;
	
	private static int offsetUNKValue = 8;	//its set to 05 in every file
	
	private static int offsetDirCount = 9;
	
	private static int offsetDirSize = 10;
	
	private static int offsetDirListStart = 12;
	
	private static int dirBytesLength = 5;
	
	//file List comes after dir data
	
	//file size is following bytes
	
	public VolFileReader() {}
	
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(volFile == null) {
			throw new FileNotFoundException();
		}
		
		Bytes volData = Bytes.from(volFile.getRawBytes());
		
		if(volData.byteAt(offsetVolExe1) == 1 && volData.byteAt(offsetVolExe2) == 0) {
			volFile.setExeUse1(Voln.ExeUse.DBSIM);
		}
		else {
			volFile.setExeUse1(Voln.ExeUse.VSHELL);
		}
		
		volFile.setDirCount(volData.byteAt(offsetDirCount));
		System.out.println("-Dir List="+volFile.getDirCount());	//DEBUG
		
		volFile.setDirSize(volData.byteAt(offsetDirSize));
		System.out.println("-Dir Byte Size="+volFile.getDirSize());	//DEBUG
		
		HashMap<FileType, Set<DataFile>> directory = new HashMap<Voln.FileType, Set<DataFile>>();
		
		int cursor = offsetDirListStart;
		for(int i = 0; i < volFile.getDirCount(); i++) {
			Bytes dirName = Bytes.from(volData.array(), cursor, dirBytesLength);
			cursor += dirBytesLength;
			
			//debug
			System.out.println(new String(dirName.toCharArray()).substring(0, 3));//DEBUG
			
			directory.put(Voln.FileType.typeFromVal(new String(dirName.toCharArray()).substring(0, 3)), new HashSet<DataFile>());
		}
		volFile.setDirectory(directory);

		
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
		
		/*
		 * TODO! - sort files into directories AFTER pulling their raw bytes
		 * 	why? the file list header is contiguous, it ignores folder structure when determining the offsets
		 */
		VolFileReader.generateFileList(volFile, fileListBytes);
		
		VolEntry[] fileEntries = new VolEntry[volFile.getFilesSet().size()];
		volFile.getFilesSet().toArray(fileEntries);
		
		for(int lf = 0; lf < fileEntries.length; lf++) {
			if(lf < fileEntries.length - 1) {
				
				Bytes raw = VolFileReader.fetchFileBytes(volFile.getRawBytes(), fileEntries[lf].getVolOffset(), fileEntries[lf+1].getVolOffset());
				fileEntries[lf].setRawBytes(raw.array());
			}
		}
		
		VolFileReader.sortHeaderFileListDirs(volFile);
	
		return volFile;
	}
	
	/**
	 * Given an isolated Bytes array of just the file list header, generate the list of files.
	 * @param vol {@link Voln}
	 * @param fileListBytes {@link Bytes}
	 * @throws Exception
	 */
	private static void generateFileList(Voln vol, Bytes fileListBytes) throws Exception{

		//we'll have to march the old fashioned way, using '.' as the only real concrete delimiter and guidepost.
		for(int i = 0; i < vol.getListSize(); i++) {
			
			if(fileListBytes.byteAt(i) == '.') {
				Bytes extCheck = Bytes.from(fileListBytes.array(), i+1, 3);	//all extensions are 3 chars after '.'
				String extStr = new String(extCheck.array(), Charset.forName("UTF-8"));
				
				//find if extension is a valid pull of 3 chars.
				Voln.FileType isType = Voln.FileType.typeFromVal(extStr);
				if(isType != null) {
					//we know that file names 
					int fileNameStart = i;
					for(int c = -1; c > -9; c--) {
						byte findChar = fileListBytes.byteAt(i + c);
						if(findChar == 0x00) {
							break;
						}
						fileNameStart--;
					}
					
					int fileNameEnd = (i - fileNameStart) + 4;
					
					String fileName = new String(Bytes.from(fileListBytes.array(), fileNameStart, fileNameEnd).array(),
							Charset.forName("UTF-8"));
					
					int memOffsetStart = (fileNameStart + fileNameEnd) + (14 - fileName.length());
					Bytes fileMemOffset = Bytes.from(fileListBytes.array(), memOffsetStart, 4).byteOrder(ByteOrder.LITTLE_ENDIAN);
					
					System.out.println("--" + fileName +"(" + new String(fileMemOffset.array(), Charset.defaultCharset()) +")"); //DEBUG
					
					i = memOffsetStart + 4;
					
					if(extStr.toLowerCase().equals(Voln.FileType.STR.val())) {
						extStr = Voln.FileType.GAM.val();
					}
					
					
					VolFileReader.insertNewFileToHeaderList(vol.getFilesSet(), isType, fileName, fileMemOffset);
					
				}
				if(i > vol.getListSize() - 18) {
					break;
				}
			}
		}
	}
	
	/**
	 * Specifically populates the header list, which ignores actual folder structure when determining file starting offset vs next file.
	 * @param dir
	 * @param type
	 * @param fileName
	 * @param memOfs
	 */
	private static void insertNewFileToHeaderList(Set<VolEntry> dir, Voln.FileType type, String fileName, Bytes memOfs) {
		if(dir == null) {
			return;
		}
		VolEntry newRecord = new VolEntry();
		newRecord.setExt(type);
		newRecord.setFileName(fileName);
		newRecord.setVolOffset(Bytes.from(memOfs));
		
		dir.add(newRecord);
	}
	
	private static void sortHeaderFileListDirs(Voln vol) {
		
		Iterator<VolEntry> iterate = vol.getFilesSet().iterator();
		
		while(iterate.hasNext()) {
			VolEntry entry = iterate.next();
			vol.getDirectory().get(entry.getExt()).add(entry);
		}
	}
	
	/**
	 * 
	 * @param dir
	 * @param type
	 * @param fileName
	 * @param memOfs
	 */
	private static void insertNewFileToDir(Set<DataFile> dir, Voln.FileType type, String fileName, Bytes memOfs) {
		if(dir == null) {
			return;
		}
		VolEntry newRecord = new VolEntry();
		newRecord.setExt(type);
		newRecord.setFileName(fileName);
		newRecord.setVolOffset(Bytes.from(memOfs));
		
		//TODO
		newRecord.setRawBytes(null);
		
		dir.add(newRecord);
	}
	
	public static Bytes fetchFileBytes(byte[] volData, Bytes fileMemOfs, Bytes nextFileMemOfs) {
		
		int startOfs = fileMemOfs.byteOrder(ByteOrder.LITTLE_ENDIAN).toInt();
		int endOfs = nextFileMemOfs.byteOrder(ByteOrder.LITTLE_ENDIAN).toInt();
		
		Bytes file = Bytes.from(volData, startOfs, (endOfs - startOfs));
		
		return file;
	}
}
