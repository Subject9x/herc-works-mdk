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

import at.favre.lib.bytes.Bytes;

public final class VolFileReader {
	
//	private static int offsetVolHeader = 0;
	
	private static int offsetVolExe1 = 4;
	
	private static int offsetVolExe2 = 5;
	
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
		
		if(volData.byteAt(offsetVolExe1) == 1 && volData.byteAt(offsetVolExe2) == 0) {
			volFile.setExeUse1(Voln.ExeUse.DBSIM);
		}
		else {
			volFile.setExeUse1(Voln.ExeUse.VSHELL);
		}
		
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
			
			VolDir dir = new VolDir(new String(dirName.toCharArray()).substring(0, 3), Bytes.from(i).byteOrder(ByteOrder.LITTLE_ENDIAN).array()[3]);
			
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
	
		return volFile;
	}
	
	
	private static void generateFileList(Voln vol, Bytes fileListBytes) throws Exception{

		vol.setFilesSet(new VolEntry[vol.getListCount()]);
		int fileCount = 0;
		
		VolEntry link = null;
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
					
					int cursorFileOfs = (fileNameStart + fileNameEnd) + (12 - fileName.length());
					cursorFileOfs += 1;	//unknown byte, but imo its Dir index as UINT8
					
					byte fileDirIdx = fileListBytes.byteAt(cursorFileOfs);
					cursorFileOfs += 1;
					
					Bytes fileMemOffset = Bytes.from(fileListBytes.array(), cursorFileOfs, 4).byteOrder(ByteOrder.LITTLE_ENDIAN).reverse();
					
					System.out.println("--" + fileName +"(" + new String(fileMemOffset.array(), Charset.defaultCharset()) +")"); //DEBUG
					
					i = cursorFileOfs + 4;
					
					if(extStr.toLowerCase().equals(Voln.FileType.STR.val())) {
						extStr = Voln.FileType.GAM.val();
					}
					
					VolEntry nextEntry = VolFileReader.newFileEntry(vol, isType, fileDirIdx, fileName, fileMemOffset);
					vol.getFilesSet()[fileCount] = nextEntry;
					fileCount++;
					if(link != null) {
						link.setNextEntry(nextEntry);
					}
					link = nextEntry;
					
					
				}
				if(i > vol.getListSize() - 18) {
					break;
				}
			}
		}
		//File header list is fixed order, populating an array is the best choice.
		//	WARN: file offset in file header list is from the dir-count byte, ignoring the first 8 bytes.
		
		Bytes bytesNoHeader = Bytes.from(vol.getRawBytes(), 9, vol.getRawBytes().length-10);
		for(int i = 0; i < vol.getFilesSet().length; i++) {
			VolEntry entryFile = vol.getFilesSet()[i];

			int startOfs = entryFile.getVolOffset().toInt();
			int endOfs = 0;
			
			Bytes fileEndOfs = null;
			if(i < vol.getFilesSet().length - 1) {
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
	
	private static VolEntry newFileEntry(Voln vol, Voln.FileType type, byte dirIndex, String fileName, Bytes memOfs) {
		
		VolEntry newRecord = new VolEntry();
		newRecord.setExt(type);
		newRecord.setFileName(fileName);
		newRecord.setDirIdx(dirIndex);
		newRecord.setVolOffset(Bytes.from(memOfs));
		
		return newRecord;		
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
}
