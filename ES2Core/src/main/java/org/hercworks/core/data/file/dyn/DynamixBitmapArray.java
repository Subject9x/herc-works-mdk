package org.hercworks.core.data.file.dyn;

import org.hercworks.voln.DataFile;

import at.favre.lib.bytes.Bytes;

/**
 * FILE
 * 		VOL file, .DBA
 * 		Dynamix Bitmap Array (somewhat guessed)
 * 		its a bitmap with defined frames.
 */
public class DynamixBitmapArray extends DataFile{
	
	public static Bytes header = Bytes.from(0x01002800);
	
	private short arrayRow;
	private short arrayCols;
	
	private DynamixBitmap[] images;
	
	private DynamixPalette palette;
	
	public DynamixBitmapArray() {}


	public DynamixBitmap[] getImages() {
		return images;
	}

	public void setImages(DynamixBitmap[] images) {
		this.images = images;
	}

	public short getArrayRow() {
		return arrayRow;
	}

	public void setArrayRow(short arrayRow) {
		this.arrayRow = arrayRow;
	}

	public short getArrayCols() {
		return arrayCols;
	}

	public void setArrayCols(short arrayCols) {
		this.arrayCols = arrayCols;
	}

	public DynamixPalette getPalette() {
		return palette;
	}

	public void setPalette(DynamixPalette palette) {
		this.palette = palette;
	}
}
