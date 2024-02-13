package com.mech.works.io.write;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteOrder;
import java.util.Map;
import java.util.Set;

import com.mech.works.vol.data.VolDir;
import com.mech.works.vol.data.VolEntry;
import com.mech.works.vol.data.Voln;
import com.mech.works.vol.util.ByteOps;

import at.favre.lib.bytes.Bytes;

public final class VolFileWriter {

	private VolFileWriter() {}

	
	public static void packVolToFile(Voln vol, String destPath) throws Exception{
		
		File destDir = new File(destPath);
		if(!destDir.exists()) {
			if(!destDir.mkdir()) {
				throw new Exception("ERROR: failed to make dir["+ destPath +"]");
			}
		}
		
		File volOutput = new File(destPath + "\\" + vol.getFileName());
		
		try (ByteArrayOutputStream bass = new ByteArrayOutputStream(vol.getRawBytes().length); FileOutputStream fout = new FileOutputStream(volOutput)){
		
				//HEADER with UNKNOWN
				bass.write(Voln.ByteHeader.VOLN.bytes().array());
				
				//Engine use flags
				bass.write(vol.isDbsimFlag() == true ? 0x01 : 0x00);
				bass.write(vol.isVshellFlag() == true ? 0x01 : 0x00);
				
				//Unknown flags
				bass.write(0x00);
				bass.write(0x00);
				
				//VOL load-order precedence flag, 05 (first), 0A (observed in SHELL1.vol)
				bass.write(0x05);
				
				//Directory Count
				bass.write(Bytes.from(vol.getDirCount()).byteOrder(ByteOrder.LITTLE_ENDIAN).reverse().array()[0]);
				
				//Directory List Byte Size
				bass.write(Bytes.from(vol.getDirSize()).byteOrder(ByteOrder.LITTLE_ENDIAN).array()[1]);
				bass.write(Bytes.from(vol.getDirSize()).byteOrder(ByteOrder.LITTLE_ENDIAN).array()[0]);
				
				//Write folder list
				VolFileWriter.writeVolDirList(vol.getDirCount(), vol.getFolders(), bass);
				
				//Header File List Total
				bass.write(Bytes.from(vol.getListCount()).byteOrder(ByteOrder.LITTLE_ENDIAN).reverse().array()[0]);
				bass.write(Bytes.from(vol.getListCount()).byteOrder(ByteOrder.LITTLE_ENDIAN).reverse().array()[1]);
				
				//Header File List Size in Bytes
				bass.write(Bytes.from(vol.getListSize()).byteOrder(ByteOrder.LITTLE_ENDIAN).reverse().array());
				
				//Write Files list
				VolFileWriter.writeVolFileList(vol.getDirCount(), vol.getFolders(), bass);
					
				//Write Files
				VolFileWriter.packFilesToVol(vol.getDirCount(), vol.getFolders(), bass);
				
				
				fout.write(bass.toByteArray());
		}
	}
	
	private static void writeVolDirList(int totalDirs, Map<Byte, VolDir> directory, ByteArrayOutputStream bass) throws IOException {
		
		for(int i = 0; i < totalDirs; i++) {
			VolDir dir = directory.get(Bytes.from(i).reverse().array()[0]);
			bass.write(dir.getLabel().toUpperCase().getBytes());
			bass.write("\\".getBytes());
			bass.write(0x00);
			
		}
	}
	
	private static void writeVolFileList(int totalDirs, Map<Byte, VolDir> directory, ByteArrayOutputStream bass) throws IOException{
		for(int i = 0; i < totalDirs; i++) {
			Set<VolEntry> folder = directory.get(Bytes.from(i).reverse().array()[0]).getFiles();
			
			for(VolEntry entry : folder) {
				
				bass.write(entry.getVolListBytes().array());
				
				bass.write(ByteOps.int4ToByteLittleEndian(i));	//write directory index
				
				bass.write(entry.getVolOffset().byteOrder(ByteOrder.LITTLE_ENDIAN).array());
			}
		}
	}
	
	private static void packFilesToVol(int totalDirs, Map<Byte, VolDir> directory, ByteArrayOutputStream bass) throws IOException{

		for(int i = 0; i < totalDirs; i++) {
			Set<VolEntry> folder = directory.get(Bytes.from(i).reverse().array()[0]).getFiles();
			
			for(VolEntry entry : folder) {
				bass.write(entry.getFileCompressionType().reverse().array());
				bass.write(entry.getFileSize().byteOrder(ByteOrder.BIG_ENDIAN).array());
				bass.write(0x00000000);
				bass.write(entry.getRawBytes());
			}
		}
	}
	
	
	public static void unpackVol(Voln vol, String destPath){
		
		File volDir = new File(destPath);
		
		if(!volDir.exists()) {
			volDir.mkdir();
		}
		
		String volDirRoot = vol.getFileName().substring(0, vol.getFileName().lastIndexOf('.'));
		
		volDir = new File(destPath + "\\" + volDirRoot);
		volDirRoot = destPath + "\\" + volDirRoot;
		
		if(!volDir.exists()) {
			volDir.mkdir();
		}
		
		for(Byte idx : vol.getFolders().keySet()) {
			VolDir folder = vol.getFolders().get(idx);
			String dirPath = volDirRoot + "\\" + folder.getLabel();
			File dirPathAct = new File(dirPath);
			if(!dirPathAct.exists()) {
				dirPathAct.mkdir();	
			}
			//double check writes auth?
			if(dirPathAct.exists()) {
				for(VolEntry entry : folder.getFiles()) {
					if(entry.getRawBytes() == null || entry.getRawBytes().length == 0) {
						continue;
					}
					VolFileWriter.writeVolAssetFile(dirPath,entry);
				}
			}
		}
		
	}
	
	private static void writeVolAssetFile(String dirPath, VolEntry entry){
		File file = new File(dirPath + "\\" + entry.getFileName());
		
		try(FileOutputStream fizz = new FileOutputStream(file);){
			fizz.write(entry.getRawBytes());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
