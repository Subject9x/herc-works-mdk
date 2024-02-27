package com.mech.works.data.file.dat.sim;

import com.mech.works.data.ref.files.DataFile;

/**
 * 	FILE - dat\\[herc].dat
 * 	UINT16 - turning speed
 *  UINT16 - reverse speed, second byte 255 for reverse.
 *  UINT16 - forward speed
 *  UINT16 - unknown value
 *  UINT16 - turning deceleration
 *  UINT16 - Bone ID to mount camera.
 *  UINT16 - Some kind of input flags, Razor has different value, but all hercs have 0x0001
 *  UINT8  - unknown value
 *  UINT16 - unknown value - animation related maybe
 *  UINT16 - unknown value - animation / speed binding related
 *  UINT16 - unknown value - colossus has 400 
 *  UINT16 - unknown value - possibly armor/internals
 *  UINT16 - 
 *  
 *  
 *  STRING - 12 bytes - Debris files
 */
public class HercInfoDat extends DataFile {

	
	
}
