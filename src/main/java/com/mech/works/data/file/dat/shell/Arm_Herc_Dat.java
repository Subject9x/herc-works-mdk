package com.mech.works.data.file.dat.shell;

import java.nio.charset.StandardCharsets;

import com.mech.works.data.ref.files.DataFile;

import at.favre.lib.bytes.Bytes;

public class Arm_Herc_Dat extends DataFile{

	public static Bytes header = Bytes.from("3A20D05A", StandardCharsets.UTF_8);
	
	
	
}
