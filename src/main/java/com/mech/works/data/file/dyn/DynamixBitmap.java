package com.mech.works.data.file.dyn;

import java.nio.charset.StandardCharsets;

import com.mech.works.data.ref.files.DataFile;

import at.favre.lib.bytes.Bytes;

/**
 * FILE
 * 		VOL file, .DBM
 * 		Dynamix Bitmap
 * 		its a bitmap file, needs a matching .DPL file to really be viewed.
 * 
 * 	      UINT32 uint header tag
 *     
 *        UINT32 file size value
 * 
 *        UINT16 uint row count (height)
 *
 *        UINT16 uint col count (width)
 *
 *        UINT16 uint bitdepth length
 *
 *        nullbyte
 *
 *        UINT32 payload (raw image data) length
 *
 *       2 nullbytes
 *       
 *       <begin data>
 *       
 */
public class DynamixBitmap extends DataFile{

	public static Bytes header = Bytes.from("0E002800", StandardCharsets.UTF_8);
	
	private int rows;
	private int cols;
	private int bitDepth;
	
	public DynamixBitmap() {}

	private Bytes imageData;
	
	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getCols() {
		return cols;
	}

	public void setCols(int cols) {
		this.cols = cols;
	}

	public int getBitDepth() {
		return bitDepth;
	}

	public void setBitDepth(int blockSize) {
		this.bitDepth = blockSize;
	}

	public Bytes getImageData() {
		return imageData;
	}

	public void setImageData(Bytes imageData) {
		this.imageData = imageData;
	}
}
