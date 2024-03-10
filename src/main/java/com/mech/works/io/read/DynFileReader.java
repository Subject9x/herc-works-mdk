package com.mech.works.io.read;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.StringTokenizer;

import com.mech.works.data.file.dyn.DynamixBitmap;
import com.mech.works.data.file.dyn.DynamixBitmapArray;
import com.mech.works.data.file.dyn.DynamixPalette;
import com.mech.works.data.ref.files.DataFile;
import com.mech.works.io.transform.common.DynamixBitmapArrayTransformer;
import com.mech.works.io.transform.common.DynamixBitmapTransformer;
import com.mech.works.io.transform.common.DynamixPaletteTransformer;
import com.mech.works.vol.data.Voln;

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

//				
//				int cursor = 4;	//skip header
//				
//				int paletteBytesSize = Bytes.from(fileBytes.array(), cursor, 4).byteOrder(ByteOrder.LITTLE_ENDIAN).toInt();
//				cursor += 4;
//				
//				int colorCount = Bytes.from(fileBytes.array(), cursor, 4).byteOrder(ByteOrder.LITTLE_ENDIAN).toInt();
//				newDPal.setColorCount(colorCount);
//				
//				cursor += 4;
//				
//				int colorBytes = colorCount * 4;	//assuming 4 bytes per color?
//				
//				newDPal.setRawIndexBytes(Bytes.from(fileBytes.array(), cursor, fileBytes.array().length - cursor));
//			
//				int scalar = 4;
//				int colorIdx = 0;
//				
//				//DO NOT CHANGE - original "mostly working" 4-byte RGBA value read
//				for(int b=cursor; b < colorBytes; b += 4) {
//					
//					Bytes shadeBytes = Bytes.from(fileBytes.array(), b, 4).byteOrder(ByteOrder.BIG_ENDIAN);	
//					byte[] bytes = new byte[4];
//					
//					System.out.println("["+ colorIdx +"] Palette Entry RAW:" + shadeBytes.encodeHex());
//					System.out.println("["+ colorIdx +"] Palette Entry LENDIAN RAW:" + shadeBytes.byteOrder(ByteOrder.LITTLE_ENDIAN).encodeHex());
//					
//					int ir = Byte.toUnsignedInt(shadeBytes.byteAt(0));
//					ir = (ir * scalar) > 255 ? 255 : ir * scalar;
//					bytes[0] = (byte) ir;
//					
//					
//					int ig = Byte.toUnsignedInt(shadeBytes.byteAt(1));
//					ig = (ig * scalar) > 255 ? 255 : ig * scalar;
//					bytes[1] = (byte) ig;
//					
//					int ib =  Byte.toUnsignedInt(shadeBytes.byteAt(2));
//					ib = (ib * scalar) > 255 ? 255 : ib * scalar;
//					bytes[2] = (byte) ib;
//					
//					
//					int ia = shadeBytes.byteAt(3);
//					ia = ia == 1 ? 255 : 0;
//					bytes[3] = (byte) ia;
//					
//					System.out.println("		Palette Entry:" + shadeBytes.encodeHex());
//
//					if(ir == 0 && ig == 0 && ib == 0) {
//						//I think this is their alpha color;
//						bytes[3] = (byte) 1;
//						ia = 128;
//					}
//					
//					ColorBytes rawColor = new ColorBytes(bytes);
//					
//					rawColor.setColor(new Color(ir, ig, ib, ia));
//					
//					newDPal.getColors().put(colorIdx, rawColor);
//					colorIdx++;	
//					
//					
//					
//					System.out.println("		index:" + colorIdx);
//					System.out.println("		getIntBGR:" + Bytes.from(rawColor.getIntBGR()).encodeHex());
//					System.out.println("		getIntRGB:" + Bytes.from(rawColor.getIntRGB()).encodeHex());
//					
//				}
			}
		}

//		Map<Integer, ColorBytes> reverse = new HashMap<Integer, ColorBytes>();
//		int idx = 0;
//		for(int i =0; i < newDPal.getColors().keySet().size(); i++) {
//			
//			ColorBytes old = newDPal.getColors().get(i);
//			
//			ColorBytes replaceBytes = new ColorBytes(Bytes.from(old.getArray()).copy().array());
//			replaceBytes.setColor(new Color(old.getColor().getRed(), old.getColor().getGreen(), old.getColor().getBlue(), old.getColor().getAlpha()));
//			
//			reverse.put(idx, replaceBytes);
//			idx++;
//		}
//		newDPal.setColors(reverse);
		
		
		return newDPal;
	}
	
	public static DynamixBitmap parseBytesToDBM(byte[] data) throws Exception{
		
		if(data.length == 0) {
			//TODO: log empty file warning.
			return null;
		}
		DynamixBitmapTransformer convert = new DynamixBitmapTransformer();
		
		DynamixBitmap newDBM = (DynamixBitmap)convert.bytesToObject(data);
		
		
//		DynamixBitmap newDBM =  new DynamixBitmap();
//		Bytes imgBytes = null;
//		
//		newDBM.setRawBytes(data);
//		
//		int cursor = 4;	//skip header
//		
//		newDBM.setFileSize(Bytes.from(data, cursor, 4).byteOrder(ByteOrder.LITTLE_ENDIAN));
//		cursor += 4;
//		
//		//height loaded first
//		newDBM.setRows(Bytes.from(data, cursor, 2).byteOrder(ByteOrder.LITTLE_ENDIAN).toChar());
//		cursor += 2;
//		System.out.println("Height:" + newDBM.getRows());
//		
//		//width second
//		newDBM.setCols(Bytes.from(data, cursor, 2).byteOrder(ByteOrder.LITTLE_ENDIAN).toChar());
//		cursor += 2;
//		System.out.println("Width:" + newDBM.getCols());
//
//		
//		newDBM.setBitDepth(Bytes.from(data, cursor, 2).byteOrder(ByteOrder.LITTLE_ENDIAN).toChar());
//		cursor += 2;
//		
//		cursor += 1; //null byte spacer?
//		
//		int imgByteLen = Bytes.from(data, cursor, 4).byteOrder(ByteOrder.LITTLE_ENDIAN).toInt();
//		cursor += 4;	//size in UINT32 of image data.
//		
//		cursor += 2;	//null spacer bytes
//		
//		imgBytes = Bytes.from(data, cursor, imgByteLen);
//	
//		newDBM.setImageData(imgBytes);
		
		return newDBM;
	}
	
	public static DynamixBitmapArray parseBytesToDBA(byte[] data) throws Exception{
		
		if(data.length == 0) {
			//TODO: log warning about empty file
			return null;
		}
		
		DynamixBitmapArrayTransformer transform = new DynamixBitmapArrayTransformer();
		
		
		DynamixBitmapArray newDBA = (DynamixBitmapArray)transform.bytesToObject(data);
	
//		newDBA.setRawBytes(data);
//		newDBA.setImages(new LinkedHashSet<DynamixBitmap>());
//		
//		int cursor = 4;	//skip header
//		
//		newDBA.setFileSize(Bytes.from(data, cursor, 4).byteOrder(ByteOrder.LITTLE_ENDIAN));
//		cursor += 4;
//		
//		newDBA.setArrayRow(Bytes.from(data, cursor, 2).byteOrder(ByteOrder.LITTLE_ENDIAN).toChar());
//		cursor += 2;
//		
//		newDBA.setArrayCols(Bytes.from(data, cursor, 2).byteOrder(ByteOrder.LITTLE_ENDIAN).toChar());
//		cursor += 2;
//		
//		int imgCount = 0;	//for debug
//		for(int i=cursor; i < data.length-12; i++) {
//			//lil bit of read-ahead.
//			int imgByteLen = Bytes.from(data, i+15, 4).byteOrder(ByteOrder.LITTLE_ENDIAN).toInt();
//			
//			int fileChunk = 21 + imgByteLen;
//			Bytes dbaItem = Bytes.from(data, i, fileChunk).byteOrder(ByteOrder.LITTLE_ENDIAN);
//			
//			DynamixBitmap dbm = DynFileReader.parseBytesToDBM(dbaItem.array());
//			
//			i += dbm.getRawBytes().length;	//accounts for the 7 bytes of meta data AFTER file size in bytes.
//			
//			System.out.println("IMAGE:"+imgCount+"-------------");
//			System.out.println("Width:" + dbm.getCols());
//			System.out.println("Height:" + dbm.getRows());
//			System.out.println("imgByteLen:" + imgByteLen);
//			
//			dbm.setFileName(imgCount+"_");
//			
//			newDBA.getImages().add(dbm);
//			imgCount++;
//		}
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
			if(Voln.FileType.typeFromVal(t) != null){
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
