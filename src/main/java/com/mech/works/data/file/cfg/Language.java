package com.mech.works.data.file.cfg;

import com.mech.works.data.ref.files.DataFile;

/**
 * FILE
 * 		[ROOT]/DATA/LANGUAGE.CFG
 */
/**
	E
 */
public class Language extends DataFile{

	//no other fields needed, the only options are 'E', 'F', or 'G'
	
	public Language() {
		super("LANGUAGE.CFG", "DATA/");
	}

}
