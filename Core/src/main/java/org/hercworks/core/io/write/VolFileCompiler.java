package org.hercworks.core.io.write;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteOrder;
import java.util.Map;

import org.hercworks.voln.VolDir;
import org.hercworks.voln.VolEntry;
import org.hercworks.voln.Voln;

import at.favre.lib.bytes.Bytes;

/**
 * 	Attempts to write non-strct {@linkplain Voln} objects out to a proper
 * ThreeSpace 2.0 .VOL file.
 */
public final class VolFileCompiler {
	
	public VolFileCompiler() {}
	
	/**
	 * Compiles a brand new vol file, calculating offsets and sizes.
	 * @param vol
	 */
	public static void compile(Voln vol) {
		
		VolFileCompiler.calculateDirHeader(vol);
		VolFileCompiler.generateFileSet(vol);
		VolFileCompiler.compileFileList(vol);
		
		byte[] rawBytes = null;
		try {
			rawBytes = VolFileCompiler.packCompiledVol(vol);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		vol.setRawBytes(rawBytes);

		try {
			VolFileWriter.packVolToFileStrict(vol, "E:\\ES2_OS\\dev\\earthsiege2\\VOL");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
	}
	
	private static void calculateDirHeader(Voln vol) {
		vol.setDirCount((byte)vol.getFolders().size());;
		
		short dirLen = (short)0;
		
		for(int i = 0; i < vol.getFolders().size(); i++) {
			
			VolDir dir = vol.getFolders().get((byte)i);
			if(dir == null) {
				continue;
			}
			int size = dir.getLabel().length()+ 2; // \\ + spacer byte 
			
			dirLen += (short)size;
		}
		
		vol.setDirSize((short)dirLen);
	}
	
	private static void generateFileSet(Voln vol) {
		
		int size = 0;
		
		for(int i = 0; i < vol.getFolders().size(); i++) {
			if(vol.getFolders().get((byte)i) == null) {
				continue;
			}
			size += vol.getFolders().get((byte)i).getFiles().size();
		}
		
		vol.setFilesSet(new VolEntry[size]);
		
		int idx = 0;
		for(int i = 0; i < vol.getFolders().size(); i++) {
			VolDir dir = vol.getFolders().get((byte)i);
			if(dir == null) {
				continue;
			}
			for(VolEntry entry : dir.getFiles()) {
				vol.getFilesSet()[idx] = entry;
				idx++;
			}
		}
		vol.setListCount((short)idx);
		vol.setListSize((short)idx*18);
	}
	
	
	private static void compileFileList(Voln vol) {
		//1) start at correct offset - header len + dir list len
		//2) calculate file list size: 18 * file total
		//	+ store offset: startOfs + fileList size
		
		int offset = Voln.ByteHeader.VOLN_HEADER_LEN.bytes().length() + 1 + 2 + vol.getDirSize();
		
		int fileListSize = vol.getFilesSet().length * 18;
		int fileOfs = offset + fileListSize + 6; //2 for [# of files], 4 for [file list size]
		for(int i=0; i < vol.getFilesSet().length; i++) {
			VolEntry entry = vol.getFilesSet()[i];
			int entrySize = VolFileCompiler.calcFileChunkSize(entry) - i;
			
			System.out.println(entry.getFileName() + ": entrySize[" + entrySize +"]");
			
			Bytes item = Bytes.from(entry.getFileName());
			
			int rem = 13 - item.array().length;
			for(int r=0; r<rem; r++) {
				item = item.append((byte)0x00);	//possible empty space in file name
			}
			
			entry.setVolListBytes(Bytes.from(item));
			entry.setVolOffset(Bytes.from(fileOfs).byteOrder(ByteOrder.BIG_ENDIAN).reverse());
			
			fileOfs += entrySize;
		}
	}
	

	private static byte[] packCompiledVol(Voln vol) throws IOException {
		
		ByteArrayOutputStream volStream = new ByteArrayOutputStream();
		
		System.out.println("-Dir List="+vol.getDirCount());	//DEBUG
		System.out.println("-Dir Byte Size="+vol.getDirSize());	//DEBUG
		System.out.println("-File List="+vol.getListCount());	//DEBUG
		System.out.println("-File Byte Size="+vol.getListSize());	//DEBUG
		
		//HEADER with UNKNOWN
		volStream.write(Voln.ByteHeader.VOLN.bytes().array());
		
		//Engine use flags
		volStream.write(vol.isDbsimFlag() == true ? 0x01 : 0x00);
		volStream.write(vol.isVshellFlag() == true ? 0x01 : 0x00);
		
		//Unknown flags
		volStream.write(0x00);
		volStream.write(0x00);
		
		//VOL load-order precedence flag, 05 (first), 0A (observed in SHELL1.vol)
		volStream.write(0x0A); // TODO
		
		//Directory Count
		volStream.write(Bytes.from(vol.getDirCount()).byteOrder(ByteOrder.LITTLE_ENDIAN).reverse().array()[0]);
		
		//Directory List Byte Size
		volStream.write(Bytes.from(vol.getDirSize()).reverse().array());
		
		//Write folder list
		VolFileCompiler.compileVolDirList(vol.isDbsimFlag(), vol.getDirCount(), vol.getFolders(), volStream);
		
		//Header File List Total
		volStream.write(Bytes.from(vol.getListCount()).byteOrder(ByteOrder.LITTLE_ENDIAN).reverse().array());
		
		//Header File List Size in Bytes
		volStream.write(Bytes.from(vol.getListSize()).byteOrder(ByteOrder.LITTLE_ENDIAN).reverse().array());
		volStream.flush();
		
		//Write Files list
		VolFileCompiler.compileVolFileList(vol, volStream);
			
		//Write Files
		VolFileCompiler.packVolFiles(vol, volStream);
		
		
		return volStream.toByteArray();
	}
	
	
	private static void compileVolDirList(boolean isSim, int totalDirs, Map<Byte, VolDir> directory, ByteArrayOutputStream bass) throws IOException {
		
		for(int i = 0; i < totalDirs; i++) {
			VolDir dir = directory.get((byte)i);
			if(dir == null) {
				continue;
			}
			String dirName = "";
			
			if(isSim) {
				dirName = dir.getLabel().toLowerCase();
			}
			else {
				dirName = dir.getLabel().toUpperCase();
			}
			dirName += "\\";
			
			bass.write(Bytes.from(dirName).array());
			bass.write(0x00);
			bass.flush();
		}
	}
	
	private static void compileVolFileList(Voln vol, ByteArrayOutputStream bass) throws IOException{

		for(VolEntry entry : vol.getFilesSet()) {
			bass.write(entry.getVolListBytes().array());
			
			bass.write(entry.getDirIdx());	//write directory index
			
			bass.write(entry.getVolOffset().array());
			bass.flush();
		}
	}
	
	private static void packVolFiles(Voln vol, ByteArrayOutputStream bass) throws IOException{
		System.out.println("COMPILE FILE LIST=====================================");
		
		
		for(VolEntry entry : vol.getFilesSet()) {
			String tailByte = entry.getUnknownEoFByte() != null ? entry.getUnknownEoFByte().encodeHex() : "";
			int tailByteVal = entry.getUnknownEoFByte() != null ? entry.getUnknownEoFByte().toUnsignedByte() : 0;
			
			System.out.println(entry.getFileName()
			+ "	| ofs[" + entry.getVolOffset().byteOrder(ByteOrder.LITTLE_ENDIAN).toInt() + "]"
			+ "	| magic[" + entry.printMagicPrefix() + "]" 
			+ "	| rawByteSize[" + entry.getRawBytes().length +"]"
			+ "	| tail byte[" + tailByte + "]("+tailByteVal+")");
	
			
			Bytes write = Bytes.from(entry.getFileCompressionType().byteAt(0));
			write = write.append(entry.getFileSize().byteOrder(ByteOrder.BIG_ENDIAN).array());
			write = write.append(entry.getMagicPrefix().array());
			write = write.append(entry.getRawBytes());
			if(tailByte.length() > 0){
				for(byte b : entry.getUnknownEoFByte().array()) {
					write = write.append((byte)0x00);
				}
			}
			bass.write(write.array());
		}
	}
	
	private static int calcFileChunkSize(VolEntry entry) {
		String tailByte = entry.getUnknownEoFByte() != null ? entry.getUnknownEoFByte().encodeHex() : "";
		int seg = 1;
		seg += 4; //file size UINT32
		seg += 4; //magic UINT32
		seg += entry.getRawBytes().length;
		seg += Bytes.from(tailByte).array().length / 2;
		
		return seg;
	}
}
