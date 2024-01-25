package com.mech.works.data.file.cfg;

import com.mech.works.data.ref.files.DataFile;

/**
 * FILE
 * 	[ROOT]/DATA/PREFS.CFG
 */
/**
 * VIEWED as 16bit Hex
  0x00 00 // MUSIC ON / OFF - 00 off, 01 on
  0x01 00 // SFX ON / OFF - 00 off, 01 on
  0x02 02  // unknown value
  0x03 02  // unknown value
  0x04 00  // WINDOW RESOLUTION 00 HiRes 640 , 01 LowRes 320
  0x05 00  // unknown value
  0x06 00  // FULLSCREEN 00 off, 01 on
  0x07 0x20,0C //HERC REPAIR enum - [00 repair all, 01 manual my herc, 02 manual all hercs]
  0x08 0x20,0D //WEAPON BUILD - [00 auto build, 01 manual build]
  0x09 04	//  unknown value
  0x0A 02	//  unknown value
  0x0B 02	//  unknown value
  0x0C 00	//  unknown value
  0x0D 00	//  unknown value
  0x0E 00	//  unknown value
  0x0F 00	//  unknown value
  
  01x00 00	//  unknown value
  01x01 00	//  unknown value
  01x02 00	//  unknown value
  01x03 00	//  unknown value
  01x04 00	//  unknown value
  01x05 00	//  unknown value
  01x06 00	//  unknown value
  01x07 00	//  unknown value
  01x08 00 	//  unknown value
  01x09 00	//  unknown value
  01x0A 00	//  unknown value
  01x0B 00	//  unknown value
  01x0C 00	//  unknown value
  01x0D 00	//  unknown value
  01x0E 00	//  unknown value
  01x0F 00	//  unknown value
  
  02x00 00	//  unknown value
  02x01 00	//  unknown value
  02x02 02	//  unknown value
  02x03 02	//  unknown value
  02x04 00	//  unknown value
  02x05 00	//  unknown value
  02x06 00	//  unknown value
  02x07 01	//  unknown value
  02x08 05 	//  unknown value
  02x09 01	//  unknown value
  02x0A 01	//  unknown value
  02x0B 02	//  unknown value
  02x0C 00	//  unknown value
  02x0D 00	//  unknown value
  02x0E 01	//  unknown value
  02x0F 01	//  unknown value
  
  03x00 00	//  unknown value
  03x01 00	//  unknown value
  03x02 00	//  unknown value
  03x03 00	//  unknown value
  03x04 00	//  unknown value
  03x05 00	//  unknown value
 */


public class Prefs extends DataFile{

	
	public Prefs() {
		super("PREFS.CFG", "DATA/");
	}
}
