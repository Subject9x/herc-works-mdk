package org.hercworks.core.io.read;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.hercworks.core.data.file.dyn.DynamixBitmap;
import org.hercworks.core.data.file.dyn.DynamixBitmapArray;
import org.hercworks.core.data.file.dyn.DynamixPalette;
import org.hercworks.core.io.transform.common.DynamixBitmapArrayTransformer;
import org.hercworks.core.io.transform.common.DynamixBitmapTransformer;
import org.hercworks.core.io.transform.common.DynamixPaletteTransformer;
import org.hercworks.voln.DataFile;
import org.hercworks.voln.FileType;

import at.favre.lib.bytes.Bytes;

public final class DynFileReader {

	public DynFileReader() {}
	
	/**
	 * Loads a {@linkplain DynamixPalette} file into memory.
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	public static DynamixPalette loadDPL(String filePath) throws Exception{
	
		File targDpl = new File(filePath);
	
		if(!targDpl.exists()) {
			throw new IOException("File not found:" + filePath);
		}
		DynamixPalette newDPal = null;
		try(FileInputStream fizz = new FileInputStream(targDpl);){
			
			Bytes fileBytes = Bytes.from(fizz.readAllBytes());
				
			if(fileBytes != null && fileBytes.array().length > 0) {
				
				DynamixPaletteTransformer convert = new DynamixPaletteTransformer();
				
				
				newDPal = (DynamixPalette)convert.bytesToObject(fileBytes.array());
				newDPal.setFileName(DataFile.makeFileName(filePath));
				newDPal.assignDir(filePath);
			}
		}
		return newDPal;
	}
	
	public static DynamixBitmap parseBytesToDBM(byte[] data) throws Exception{
		
		if(data.length == 0) {
			//TODO: log empty file warning.
			return null;
		}
		DynamixBitmapTransformer convert = new DynamixBitmapTransformer();
		
		DynamixBitmap newDBM = (DynamixBitmap)convert.bytesToObject(data);

		return newDBM;
	}
	
	public static DynamixBitmapArray parseBytesToDBA(byte[] data) throws Exception{
		
		if(data.length == 0) {
			//TODO: log warning about empty file
			return null;
		}
		
		DynamixBitmapArrayTransformer transform = new DynamixBitmapArrayTransformer();
		
		
		DynamixBitmapArray newDBA = (DynamixBitmapArray)transform.bytesToObject(data);
	
		return newDBA;
	}
	
	
	public static DynamixBitmap loadDBM(String filePath) throws Exception{
	
		File targDBM = new File(filePath);
	
		if(!targDBM.exists()) {
			throw new IOException("File not found:" + filePath);
		}
		DynamixBitmap newDBM = null;
		
		try(FileInputStream fizz = new FileInputStream(targDBM);){
			
			Bytes fileBytes = Bytes.from(fizz.readAllBytes());
			if(fileBytes != null && fileBytes.array().length > 0) {
				newDBM = DynFileReader.parseBytesToDBM(fileBytes.array());
				newDBM.setFileName(DataFile.makeFileName(filePath));
				newDBM.assignDir(filePath);
			}
		}
		return newDBM;
	}
	
	
	public static DynamixBitmapArray loadDBA(String filePath) throws Exception{
		
		File targDBA = new File(filePath);
	
		if(!targDBA.exists()) {
			throw new IOException("File not found:" + filePath);
		}
		
		DynamixBitmapArray newDBA = null;
		
		try(FileInputStream fizz = new FileInputStream(targDBA);){
			
			Bytes fileBytes = Bytes.from(fizz.readAllBytes()).byteOrder(ByteOrder.LITTLE_ENDIAN);
			
			if(fileBytes != null && fileBytes.array().length > 0) {
				newDBA = DynFileReader.parseBytesToDBA(fileBytes.array());
				newDBA.setFileName(DataFile.makeFileName(filePath));
				newDBA.assignDir(filePath);
			}
		}
		return newDBA;
	}
	
	
	public static List<Byte> getDBMUniqueColors(DynamixBitmap dbm) {
		
		List<Byte> matches = new ArrayList<Byte>();
		
		for(byte b : dbm.getImageData()) {
			byte off = (byte)(b-8);
			if(!matches.contains(off)){
				matches.add(b);
			}
		}
		return matches;
	}
	
	public static List<Byte> matchUniqueColorToPalette(List<Byte> colors, DynamixPalette dpl) {
		List<Byte> matches = new ArrayList<Byte>();
		List<Byte> unknown = new ArrayList<Byte>();
		
		for(byte b : colors) {
			if(dpl.getColors().keySet().contains((int)b)) {
				matches.add(b);
			}
			else {
				unknown.add(b);
			}
		}
		return matches;
//		System.out.println("MATCHED VALUES FOR " + dpl.getFileName() + "-----------------------------------------");
//		for(byte b : matches) {
//			
//			System.out.println("IDX	("+Byte.toUnsignedInt(b)+")	["+Bytes.from(b).encodeHex()+"]	{"+b+"}	=" + dpl.getColors().get(Byte.toUnsignedInt(b)).getColor().getRGB());
//		}
//		System.out.println("MISSING VALUES FOR " + dpl.getFileName() + "-----------------------------------------");
//		for(byte b : unknown) {
//			System.out.println("NOP	("+Byte.toUnsignedInt(b)+")	["+Bytes.from(b).encodeHex()+"]	{"+b+"}");
//		}
	}
	
	public static String getVolDirOfFile(String filePath, String fileName) {
		StringTokenizer tokenzer = new StringTokenizer(filePath, "\\");
		
		String dir = null;
		while(tokenzer.hasMoreTokens()) {
			String t = tokenzer.nextToken();
			if(FileType.typeFromVal(t) != null){
				dir = t;
				break;
			}
		}
		
		if(dir != null) {
			dir = filePath.substring(0, filePath.lastIndexOf("\\"+dir) )+ "\\";
		}
		return dir;
	}
	
}
