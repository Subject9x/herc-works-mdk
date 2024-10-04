package org.hercworks.core.data.file.cfg;

import org.hercworks.voln.DataFile;

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
