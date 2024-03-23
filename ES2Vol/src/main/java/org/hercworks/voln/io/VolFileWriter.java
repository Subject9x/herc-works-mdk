package org.hercworks.voln.io;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteOrder;
import java.util.Map;

import org.hercworks.voln.VolDir;
import org.hercworks.voln.VolEntry;
import org.hercworks.voln.Voln;

import at.favre.lib.bytes.Bytes;

public final class VolFileWriter {

	private VolFileWriter() {}

	/**
	 * 'Strict' here means DO NOT calculate new dynamic sizes, instead write vol directly to a file with
	 * all data already assembled and counted. Best case is modifying an existing vol and doing simple byte-edit
	 * tasks.
	 * @param vol
	 * @param destPath
	 * @throws Exception
	 */
	public static void packVolToFileStrict(Voln vol, String destPath) throws Exception{
		
		File destDir = new File(destPath);
		if(!destDir.exists()) {
			if(!destDir.mkdir()) {
				throw new Exception("ERROR: failed to make dir["+ destPath +"]");
			}
		}
		
		File volOutput = new File(destPath + "\\" + vol.getFileName());
		
		try (ByteArrayOutputStream bass = new ByteArrayOutputStream(vol.getRawBytes().length);  FileOutputStream fout = new FileOutputStream(volOutput)){
					
				System.out.println("-Dir List="+vol.getDirCount());	//DEBUG
				System.out.println("-Dir Byte Size="+vol.getDirSize());	//DEBUG
				System.out.println("-File List="+vol.getListCount());	//DEBUG
				System.out.println("-File Byte Size="+vol.getListSize());	//DEBUG
				
			
				//HEADER with UNKNOWN
				fout.write(Voln.ByteHeader.VOLN.bytes().array());
				
				//Engine use flags
				fout.write(vol.isDbsimFlag() == true ? 0x01 : 0x00);
				fout.write(vol.isVshellFlag() == true ? 0x01 : 0x00);
				
				//Unknown flags
				fout.write(0x00);
				fout.write(0x00);
				
				//VOL load-order precedence flag, 05 (first), 0A (observed in SHELL1.vol)
				fout.write(vol.getVolOrderNum()); // TODO
				
				//Directory Count
				fout.write(Bytes.from(vol.getDirCount()).byteOrder(ByteOrder.LITTLE_ENDIAN).reverse().array()[0]);
				
				//Directory List Byte Size
				fout.write(Bytes.from(vol.getDirSize()).reverse().array());
				
				//Write folder list
				VolFileWriter.writeVolDirList(vol.isDbsimFlag(), vol.getDirCount(), vol.getFolders(), fout);
				
				//Header File List Total
				fout.write(Bytes.from(vol.getListCount()).byteOrder(ByteOrder.LITTLE_ENDIAN).reverse().array());
				
				//Header File List Size in Bytes
				fout.write(Bytes.from(vol.getListSize()).byteOrder(ByteOrder.LITTLE_ENDIAN).reverse().array());
				fout.flush();
				
				//Write Files list
				VolFileWriter.writeKnownVolFileList(vol, fout);
					
				//Write Files
				VolFileWriter.packKnownVolFiles(vol, fout);
		}
	}
	
	private static void writeVolDirList(boolean isSim, int totalDirs, Map<Byte, VolDir> directory, FileOutputStream bass) throws IOException {
		
		for(int i = 0; i < totalDirs; i++) {
			VolDir dir = directory.get((byte)i);

			if(dir == null) {
				continue;
			}
			if(isSim) {
				bass.write(dir.getLabel().toLowerCase().getBytes());
			}
			else {
				bass.write(dir.getLabel().toUpperCase().getBytes());
			}
			
			bass.write(Bytes.from("\\").array()[0]);
			bass.write(0x00);
			bass.flush();
		}
	}
	
	/**
	 * "KNOWN" here means we've loaded a compiled/existing vol to memory and what to write it back out
	 * EXACTLY as it was loaded. This is best used for debugging unmodified original ES2 vol files.
	 * @param vol
	 * @param bass
	 * @throws IOException
	 */
	private static void writeKnownVolFileList(Voln vol, FileOutputStream bass) throws IOException{

		for(VolEntry entry : vol.getFilesSet()) {
			bass.write(entry.getVolListBytes().array());
			
			bass.write(entry.getDirIdx());	//write directory index
			
			bass.write(entry.getVolOffset().byteOrder(ByteOrder.BIG_ENDIAN).array());
			bass.flush();
		}
	}
	
	/**
	 * "KNOWN" here means we've loaded a compiled/existing vol to memory and what to write it back out
	 * EXACTLY as it was loaded. This is best used for debugging unmodified original ES2 vol files.
	 * @param vol
	 * @param bass
	 * @throws IOException
	 */
	private static void packKnownVolFiles(Voln vol, FileOutputStream bass) throws IOException{
		System.out.println("WRITING FILE LIST=====================================");
		
		
		for(VolEntry entry : vol.getFilesSet()) {
			String tailByte = entry.getUnknownEoFByte() != null ? entry.getUnknownEoFByte().encodeHex() : "";
//			int tailByteVal = entry.getUnknownEoFByte() != null ? entry.getUnknownEoFByte().toUnsignedByte() : 0;
//			System.out.println(entry.getFileName() 
//					+ "|" + entry.printMagicPrefix() 
//					+ "| rawByteSize[" + entry.getRawBytes().length +"]"
//					+ "| tail byte[" + tailByte + "]("+tailByteVal+")");
			
			Bytes write = Bytes.from(entry.getFileCompressionType().byteAt(0));
			write = write.append(entry.getFileSize().byteOrder(ByteOrder.BIG_ENDIAN).array());
			write = write.append(entry.getMagicPrefix().array());
			write = write.append(entry.getRawBytes());
			if(tailByte.length() > 0){
				for(byte b : entry.getUnknownEoFByte().array()) {
					write = write.append((byte)0x00);
				}
//				write = write.append(entry.getUnknownEoFByte());
			}
			bass.write(write.array());
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
		File file = new File(dirPath + "\\" + entry.getFileName().strip());
		
		try(FileOutputStream fizz = new FileOutputStream(file);){
			fizz.write(entry.getRawBytes());
			
//			System.out.println("Wrote ["+file.getPath()+"]");
			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage() + "\n file=" + entry.getFilePath() +"\\"+entry.getFileName());
		}
	}
	
	//XXX: this doesn't account for correctly writing a file's offset in the vol.
	//it neither respects the case of a original ES2 vol loaded (where file offset is known)
	//and it doesn't account for a files possible NEW offset, which TODO needs to be a real thing.
//	private static void writeVolFileList(int totalDirs, Map<Byte, VolDir> directory, ByteArrayOutputStream bass) throws IOException{
//
//		for(int i = 0; i < totalDirs; i++) {
//			Set<VolEntry> folder = directory.get(Bytes.from(i).reverse().array()[0]).getFiles();
//			
//			for(VolEntry entry : folder) {
//				
//				bass.write(entry.getVolListBytes().array());
//				
//				bass.write(ByteOps.int4ToByteLittleEndian(i));	//write directory index
//				
//				byte[] ofs = entry.getVolOffset().byteOrder(ByteOrder.BIG_ENDIAN).array();
//				bass.write(ofs[0]);
//				bass.write(ofs[1]);
//				bass.write(ofs[2]);
//				bass.write(ofs[3]);
//			}
//		}
//		bass.flush();
//	}
	
//	private static void packFilesToVol(int totalDirs, Map<Byte, VolDir> directory, ByteArrayOutputStream bass) throws IOException{
//	System.out.println("WRITING FILE LIST=====================================");
//	
//	for(int i = 0; i < totalDirs; i++) {
//		Set<VolEntry> folder = directory.get((byte)i).getFiles();
//		
//		for(VolEntry entry : folder) {
////			System.out.println(directory.get((byte)i).getLabel() +"\\" + entry.getFileName() + "|" + entry.printMagicPrefix());
//
//			System.out.println(entry.getFileName() + "|" + entry.printMagicPrefix() + "| rawByteSize[" + entry.getRawBytes().length +"]");
//			
//			bass.write(entry.getFileCompressionType().byteOrder(ByteOrder.BIG_ENDIAN).toShort());
//			bass.write(entry.getFileSize().byteOrder(ByteOrder.BIG_ENDIAN).array());
//			bass.write(entry.getMagicPrefix().array());
//			bass.write(entry.getRawBytes());
//			bass.write(0x00);
//		}
//	}
//}
}
