package org.hercworks.core.data.file.dyn;

import org.hercworks.voln.DataFile;

import at.favre.lib.bytes.Bytes;

/**
 *	FILE
 * 		VOL file, .DBM
 * 	Dynamix Bitmap
 * 	its a bitmap file, needs a matching .DPL file to really be viewed.
 * 		{@linkplain DynamixPalette} field is provided, but DBM's do not explicitly bind
 * 		a palette to themselves, the game binaries seem to know which one has which.
 * 
 * 	UINT32 uint header tag
 *  UINT32 file size value
 *  UINT16 uint row count (height)
 *  UINT16 uint col count (width)
 *  UINT16 uint bitdepth length
 *  null byte
 *  UINT32 payload (raw image data) length
 *
 *  2 nullbytes
 *       
 *  <begin data>
 *       
 */
public class DynamixBitmap extends DataFile{

	public static Bytes header = Bytes.from(0x0E002800);
	
	private short rows;
	private short cols;
	private short bitDepth;
	private byte unkSpacer1;
	private int imageDataLen;
	private short unkSpacer2;
	
	private DynamixPalette palette;
	
	public DynamixBitmap() {}

	private Bytes imageData;
	
	public short getRows() {
		return rows;
	}

	public void setRows(short rows) {
		this.rows = rows;
	}

	public short getCols() {
		return cols;
	}

	public void setCols(short cols) {
		this.cols = cols;
	}

	public short getBitDepth() {
		return bitDepth;
	}

	public void setBitDepth(short blockSize) {
		this.bitDepth = blockSize;
	}

	public Bytes getImageData() {
		return imageData;
	}

	public void setImageData(Bytes imageData) {
		this.imageData = imageData;
	}

	public DynamixPalette getPalette() {
		return palette;
	}

	public void setPalette(DynamixPalette palette) {
		this.palette = palette;
	}

	public byte getUnkSpacer1() {
		return unkSpacer1;
	}

	public short getUnkSpacer2() {
		return unkSpacer2;
	}

	public void setUnkSpacer1(byte unkSpacer1) {
		this.unkSpacer1 = unkSpacer1;
	}

	public void setUnkSpacer2(short unkSpacer2) {
		this.unkSpacer2 = unkSpacer2;
	}

	public int getImageDataLen() {
		return imageDataLen;
	}

	public void setImageDataLen(int imageDataLen) {
		this.imageDataLen = imageDataLen;
	}
}
