package com.mech.works.data.file.dyn;

import java.nio.charset.StandardCharsets;

import com.mech.works.data.ref.files.DataFile;

import at.favre.lib.bytes.Bytes;

/**
 * FILE
 * 		VOL file, .DBM
 * 		Dynamix Bitmap
 * 		its a bitmap file, needs a matching .DPL file to really be viewed.
 */
public class DynamixBitmap extends DataFile{

	public static Bytes header = Bytes.from("0E002800", StandardCharsets.UTF_8);
}
