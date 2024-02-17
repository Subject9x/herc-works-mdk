package com.mech.works.io.write;

import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;
import java.awt.image.IndexColorModel;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.mech.works.data.file.dyn.DynamixBitmap;
import com.mech.works.data.file.dyn.DynamixPalette;
import com.mech.works.data.struct.ColorBytes;

import at.favre.lib.bytes.Bytes;

public final class DynFileWriter {

	public DynFileWriter() {}
	
	
	public static void writeDBMToFile(DynamixBitmap dbm, DynamixPalette palette, String filePath) {
		
		IndexColorModel colorIndex = new IndexColorModel(8,
															256,
															palette.toColorMap(),
															0,
															false,
															-1,
															DataBuffer.TYPE_BYTE
														);
		///-------------------------
		
		File file = new File(filePath + dbm.getFileName() + ".bmp");
		BufferedImage imageOut = null;
		//TYPE_USHORT_555_RGB almost has it
		//XXX: TYPE_INT_ARGB - DOES NOT WORK WITH IMAGEIO on .BMP!
		
		try {
			imageOut = new BufferedImage(dbm.getCols(), dbm.getRows(), BufferedImage.TYPE_INT_RGB, colorIndex);
//			imageOut = new BufferedImage(dbm.getCols(), dbm.getRows(), BufferedImage.TYPE_);
//			imageOut = new BufferedImage(dbm.getCols(), dbm.getRows(), BufferedImage.TYPE_BYTE_INDEXED, colorIndex);	
		}
		catch(Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		//write image data directly.
//		byte[] data = ((DataBufferByte)imageOut.getRaster().getDataBuffer()).getData();	//TYPE_BYTE
		int[] data = ((DataBufferInt)imageOut.getRaster().getDataBuffer()).getData(); //ANY TYPE_INT
		
		for(int i=0; i < dbm.getImageData().array().length; i++) {
			data[i] = dbm.getImageData().array()[i];
		}
		
		for(int r=0; r < dbm.getRows(); r++) {
			for(int c=0; c < dbm.getCols(); c++) {
				int cell = (r * dbm.getCols()) + c;
				String id = Bytes.from(dbm.getImageData().array()[cell]).encodeHex();
				
				if(palette.getColors().get(id) != null) {
					ColorBytes shade = palette.getColors().get(id);
					
					imageOut.setRGB(c, r, shade.getColor().getRGB());
				}
			}
		}
		
		try {
			ImageIO.write(imageOut, "bmp", file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

	}
	
	public static void writeDBMToFileNoPalette(DynamixBitmap dbm, String filePath) {
		
		
//		IndexColorModel colorIndex = new IndexColorModel(16, 
//						4, 
//						palette.toByteArray(), 
//						0,
//						false);
		
		///-------------------------
		
		File file = new File(filePath + dbm.getFileName().substring(0, dbm.getFileName().lastIndexOf('.')) + "_RAW.bmp");
		BufferedImage imageOut = null;
		//TYPE_USHORT_555_RGB almost has it
		//XXX: TYPE_INT_ARGB - DOES NOT WORK WITH IMAGEIO on .BMP!
		
		try {
			imageOut = new BufferedImage(dbm.getCols(), dbm.getRows(), BufferedImage.TYPE_3BYTE_BGR);
		}
		catch(Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		byte[] data = ((DataBufferByte)imageOut.getRaster().getDataBuffer()).getData();
		
		for(int i=0; i < dbm.getImageData().array().length; i++) {
			data[i] = dbm.getImageData().array()[i];
		}
		
		
		try {
			ImageIO.write(imageOut, "bmp", file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

	}
}
