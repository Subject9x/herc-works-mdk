package com.mech.works.data.file;

import java.nio.charset.StandardCharsets;

import com.mech.works.data.ref.files.DataFile;

import at.favre.lib.bytes.Bytes;

/**
 * FILE
 * 		VOL file, .DBA
 * 		Dynamix Bitmap Animation (somewhat guessed)
 * 		its a bitmap with defined frames.
 */
public class DynamixBitmapAnim extends DataFile{
	
	public static Bytes header = Bytes.from("01002800", StandardCharsets.UTF_8);
	
	
	
	
	
}
