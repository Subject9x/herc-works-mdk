package com.mech.works.io.read;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteOrder;
import java.util.ArrayList;

import com.mech.works.data.file.dyn.DynamixBitmap;
import com.mech.works.data.file.dyn.DynamixBitmapArray;
import com.mech.works.data.file.dyn.DynamixPalette;

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
				newDPal = new DynamixPalette();
				newDPal.setFileName(filePath);
				newDPal.setRawBytes(fileBytes.array());
				
				int cursor = 4;	//skip header
				
				int paletteBytesSize = Bytes.from(fileBytes.array(), cursor, 4).byteOrder(ByteOrder.LITTLE_ENDIAN).toInt();
				cursor += 4;
				
				int colorCount = Bytes.from(fileBytes.array(), cursor, 4).byteOrder(ByteOrder.LITTLE_ENDIAN).toInt();
				newDPal.setColorCount(colorCount);
				
				cursor += 4;
				
				int colorBytes = colorCount * 4;	//assuming 6 bytes per color?
//				cursor += 4;	//there's 4 unaccounted for bytes here? probably?
				
				for(int b=cursor; b < colorBytes; b += 4) {
					
					Bytes shadeBytes = Bytes.from(fileBytes.array(), b, 4);
					
					int ir = shadeBytes.byteAt(0);
					
					int ig = shadeBytes.byteAt(1);
					int ib = shadeBytes.byteAt(2);
					
					int ia = shadeBytes.byteAt(3);
					ia = ia == 1 ? 255 : 0;
					
					System.out.println("b="+b + "(" + ir + ", " + ig +", " + ib +", "+ia+")");
//					shadeBytes = shadeBytes.append(Bytes.from(fileBytes.array(), b+3, 1));
					
//					Color shade = new Color(shadeBytes.array()[0], shadeBytes.array()[1], shadeBytes.array()[2], shadeBytes.array()[3]);
					Color shade = new Color(ir, ig, ib);
					shade = shade.brighter().brighter();
					
					newDPal.getColors().add(shade);
//					b+=7;
				}
			}
		}
		
		return newDPal;
	}
	
	public static DynamixBitmap loadDBM(String filePath) throws Exception{
	
		File targDBM = new File(filePath);
	
		if(!targDBM.exists()) {
			throw new IOException("File not found:" + filePath);
		}
		DynamixBitmap newDBM = null;

		Bytes imgBytes = null;
		
		try(FileInputStream fizz = new FileInputStream(targDBM);){
			
			Bytes fileBytes = Bytes.from(fizz.readAllBytes());
			if(fileBytes != null && fileBytes.array().length > 0) {
				newDBM = new DynamixBitmap();
				newDBM.setFileName(filePath);
				newDBM.setRawBytes(fileBytes.array());
				
				int cursor = 4;	//skip header
				
				newDBM.setFileSize(Bytes.from(fileBytes.array(), cursor, 4).byteOrder(ByteOrder.LITTLE_ENDIAN));
				cursor += 4;
				
				//height loaded first
				newDBM.setRows(Bytes.from(fileBytes.array(), cursor, 2).byteOrder(ByteOrder.LITTLE_ENDIAN).toChar());
				cursor += 2;
				System.out.println("Height:" + newDBM.getRows());
				
				//width second
				newDBM.setCols(Bytes.from(fileBytes.array(), cursor, 2).byteOrder(ByteOrder.LITTLE_ENDIAN).toChar());
				cursor += 2;
				System.out.println("Width:" + newDBM.getCols());

				newDBM.setBlockSize(Bytes.from(fileBytes.array(), cursor, 4).byteOrder(ByteOrder.LITTLE_ENDIAN).toInt());
				cursor += 4;
				
				int row = 0;
				
				imgBytes = Bytes.from(fileBytes.array(), cursor, fileBytes.array().length - 16).byteOrder(ByteOrder.LITTLE_ENDIAN);
				
			}
		}
		
		//debug write out
		if(imgBytes != null) {
			
			File file = new File(targDBM.getPath().substring(0, targDBM.getPath().lastIndexOf('\\')) + "\\" + "out.bmp");
			
			try(FileOutputStream fizz = new FileOutputStream(file);){
				fizz.write(imgBytes.array());
				
			} catch (IOException e) {
				e.printStackTrace();
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

		Bytes imgBytes = null;
		
		try(FileInputStream fizz = new FileInputStream(targDBA);){
			
			Bytes fileBytes = Bytes.from(fizz.readAllBytes());
			if(fileBytes != null && fileBytes.array().length > 0) {
				newDBA = new DynamixBitmapArray();
				newDBA.setFileName(filePath);
				newDBA.setRawBytes(fileBytes.array());
				newDBA.setImages(new ArrayList<DynamixBitmap>());
				
				
				int cursor = 4;	//skip header
				
				newDBA.setFileSize(Bytes.from(fileBytes.array(), cursor, 4).byteOrder(ByteOrder.LITTLE_ENDIAN));
				cursor += 4;
				
				cursor += 4;	//FIXME: 4-byte UINT of unknown value.
				
				int imgCount = 0;
				for(int i=cursor; i < fileBytes.array().length-12; i+=4) {
					Bytes chunk = Bytes.from(fileBytes.array(), i, 4);
					if(chunk.encodeHex().equals(DynamixBitmap.header.encodeHex())) {
						
					}
					
				}
				
				int row = 0;
				
				imgBytes = Bytes.from(fileBytes.array(), cursor, fileBytes.array().length - 16).byteOrder(ByteOrder.LITTLE_ENDIAN);
				
			}
		}
		
		//debug write out
//		if(imgBytes != null) {
//			
//			File file = new File(targDBA.getPath().substring(0, targDBA.getPath().lastIndexOf('\\')) + "\\" + "dba.bmp");
//			
//			try(FileOutputStream fizz = new FileOutputStream(file);){
//				fizz.write(imgBytes.array());
//				
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
		return newDBA;
	}
	
	
}
