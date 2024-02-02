package com.mech.works.data.file.dyn;

import java.nio.charset.StandardCharsets;

import com.mech.works.data.ref.files.DataFile;

import at.favre.lib.bytes.Bytes;

/**
 * FILE
 * 		VOL file, .DPL
 * 		contains palette colors per-game object, some real optimization right there.
 */
public class DynamixPalette extends DataFile {

	public static Bytes header = Bytes.from("0F002800", StandardCharsets.UTF_8);
	
	
}
