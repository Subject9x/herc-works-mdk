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
 *         16-bit uint row count (height)

        16-bit uint col count (width)

        16-bit uint blocksize length

        nullbyte

        32-bit payload (raw image data) length

        two nullbytes
 */
public class DynamixBitmap extends DataFile{

	public static Bytes header = Bytes.from("0E002800", StandardCharsets.UTF_8);
	
	private int rows;
	private int cols;
	private int blockSize;
	
	public DynamixBitmap() {}

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

	public int getBlockSize() {
		return blockSize;
	}

	public void setBlockSize(int blockSize) {
		this.blockSize = blockSize;
	}
}
