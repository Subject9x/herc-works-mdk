package com.mech.works.io.write;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteOrder;
import java.util.Map;
import java.util.Set;

import com.mech.works.data.ref.files.DataFile;
import com.mech.works.vol.data.VolDir;
import com.mech.works.vol.data.VolEntry;
import com.mech.works.vol.data.Voln;

import at.favre.lib.bytes.Bytes;

public final class VolFileWriter {

	private VolFileWriter() {}

	
	public static void writeVolFile(Voln vol, String destPath) throws Exception{
		
		File destDir = new File(destPath);
		if(!destDir.exists()) {
			if(!destDir.mkdir()) {
				throw new Exception("ERROR: failed to make dir["+ destPath +"]");
			}
		}
		
		File volOutput = new File(destPath + "\\" + vol.getFileName());
		
		try (ByteArrayOutputStream bass = new ByteArrayOutputStream(vol.getRawBytes().length); FileOutputStream fout = new FileOutputStream(volOutput)){
		
				//HEADER with UNKNOWN
				bass.write(Voln.ByteHeader.SHELL0.bytes().array());
				
				//Directory Count
				bass.write(vol.getDirCount());
				
				//Directory List Byte Size
				bass.write(Bytes.from(vol.getDirSize()).byteOrder(ByteOrder.BIG_ENDIAN).reverse().array());
				
				//FIXME - vol folder structure updated
//				VolFileWriter.writeVolDirList(vol.getDirectory(), bass);
				
				
				fout.write(bass.toByteArray());
		}
	}
	
	private static void writeVolDirList(Map<VolDir, Set<DataFile>> directory, ByteArrayOutputStream bass) throws IOException {
		
		// Write the directories
		for(VolDir dir : directory.keySet()) {
			
			bass.write(dir.getLabel().toUpperCase().getBytes());
			bass.write("\\".getBytes());
			bass.write(0x00);
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
