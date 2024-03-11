package com.mech.works.data.file.dyn;

import java.nio.charset.StandardCharsets;
import java.util.LinkedHashSet;
import java.util.Set;

import com.mech.works.data.ref.files.DataFile;

import at.favre.lib.bytes.Bytes;

/**
 * FILE
 * 		VOL file, .DBA
 * 		Dynamix Bitmap Array (somewhat guessed)
 * 		its a bitmap with defined frames.
 */
public class DynamixBitmapArray extends DataFile{
	
	public static Bytes header = Bytes.from("01002800", StandardCharsets.UTF_8);
	
	private LinkedHashSet<DynamixBitmap> images;
	
	private short arrayRow;
	private short arrayCols;

	private DynamixPalette palette;
	
	public DynamixBitmapArray() {}


	public Set<DynamixBitmap> getImages() {
		return images;
	}

	public void setImages(LinkedHashSet<DynamixBitmap> images) {
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
