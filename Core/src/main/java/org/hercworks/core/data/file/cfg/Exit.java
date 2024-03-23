package org.hercworks.core.data.file.cfg;

import org.hercworks.voln.DataFile;

/**
 * FILE
 * 		[ROOT]/DATA/EXIT.CFG
 * 
 * (lord knows what this file is for, investigate decomp)
 */
/**
  
 */
public class Exit  extends DataFile{

	//no other fields needed, file appears to be 2 empty-space bytes
	
	public Exit() {
		super("EXIT.CFG", "DATA/");
	}

}
