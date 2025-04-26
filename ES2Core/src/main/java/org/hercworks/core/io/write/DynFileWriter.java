package org.hercworks.core.io.write;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.hercworks.core.data.file.dyn.DynamixBitmap;
import org.hercworks.core.data.file.dyn.DynamixPalette;
import org.hercworks.core.data.struct.ColorBytes;
import org.hercworks.core.io.transform.common.DynamixBitmapTransformer;

public final class DynFileWriter {

	public DynFileWriter() {}
	
	
	public static void writeDBMToFile(DynamixBitmap dbm, boolean index0Alpha, DynamixPalette palette, String filePath) {
		
//		IndexColorModel colorIndex = new IndexColorModel(8,
//															256,
//															palette.toIntColorMap(),
//															0,
//															false,
//															-1,
//															DataBuffer.TYPE_BYTE
//														);
		///-------------------------
		
		File file = new File(filePath + dbm.getFileName() + ".png");
		BufferedImage imageOut = null;
		//TYPE_USHORT_555_RGB almost has it
		//XXX: TYPE_INT_ARGB - DOES NOT WORK WITH IMAGEIO on .BMP!
		
		try {
			imageOut = new BufferedImage(dbm.getCols(), dbm.getRows(), BufferedImage.TYPE_INT_RGB);
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		WritableRaster rast = (WritableRaster)imageOut.getRaster();
		int[] rasterData = new int[dbm.getCols() * dbm.getRows() * 4];
		
		int i = 0;
		for(int r=0; r < dbm.getRows(); r++) {
			for(int c=0; c < dbm.getCols(); c++) {
				if(i >= dbm.getImageData().length()) {
					break;
				}
				int cell = (r * dbm.getCols()) + c;
				int idx = Byte.toUnsignedInt(dbm.getImageData().array()[cell]);
				
				try {
					if(index0Alpha && idx == 0) {
						rasterData[i] = palette.getIndex0AlphaKey().getJavaColor().getRGB();
					}
					else{
						rasterData[i] = palette.colorAt(idx).getJavaColor().getRGB();
					}
				}
				catch(NullPointerException nope) {
					System.out.println("PIXEL("+c+","+r+")=" + (byte)idx + "~MISSING"); 
				}
				catch(Exception e) {
					System.out.println("ERROR - " + e.getMessage());
				}
				i++;
			}
		}
		rast.setDataElements(0, 0, dbm.getCols(), dbm.getRows(), rasterData);
		
		boolean wrote = false;
		try {
			wrote = ImageIO.write(imageOut, "png", file);
		} catch (Throwable t) {
			System.out.println(t.getMessage());
		}
		System.out.println("write file" + wrote);
	}
	
	
	public static void writeDBMToFile(DynamixBitmap dbm, String filePath) {
		File file = new File(filePath + dbm.getFileName() + ".DBM");
		
		try(FileOutputStream fozz = new FileOutputStream(file)){
			DynamixBitmapTransformer transform = new DynamixBitmapTransformer();
			
			fozz.write(transform.objectToBytes(dbm));
		} 
		catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} 
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
		catch(ArrayIndexOutOfBoundsException arryErr) {
			System.out.println("ERROR - " + arryErr.getMessage());
		}
		
	}
	
	public static void writeDBMToFileNoPalette(DynamixBitmap dbm, String filePath) {
		
		
//		IndexColorModel colorIndex = new IndexColorModel(16, 
//						4, 
//						palette.toByteArray(), 
//						0,
//						false);
		
		///-------------------------
		
		File file = new File(filePath + dbm.getFileName() + "_RAW.bmp");
		BufferedImage imageOut = null;
		//TYPE_USHORT_555_RGB almost has it
		//XXX: TYPE_INT_ARGB - DOES NOT WORK WITH IMAGEIO on .BMP!
		
		try {
			imageOut = new BufferedImage(dbm.getCols(), dbm.getRows(), BufferedImage.TYPE_BYTE_GRAY);
		}
		catch(Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		byte[] data = ((DataBufferByte)imageOut.getRaster().getDataBuffer()).getData();
		//int[] data = ((DataBufferInt)imageOut.getRaster().getDataBuffer()).getData();
		
		for(int i=0; i < dbm.getImageData().array().length; i++) {
			data[i] = dbm.getImageData().array()[i];
		}
		
		
		try {
			ImageIO.write(imageOut, "bmp", file);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

	}
}
