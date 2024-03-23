package org.hercworks.core.data.file.dat.sim;

import org.hercworks.voln.DataFile;

/**
 * 	FILE
 * 		/DBSIM/DAT/PROJ.DAT
 * 
 * 	0- UINT16 - total weapons
 * 	Entries are in Weapon ID order!
 * 	SEQ0 - 36 bytes per segment
 * 		S0_0- UINT16 - ?
 * 		S1_2- UINT16 - MODEL/ID? -> BULLETS/ROCKETS link?
 * 		S2_4- UINT16 - DMG/SHIELD, possibly fixed-point
 * 		S3_6- UINT16 - DMG/ARMOR
 * 		S4_8- UINT16 - ?
 * 		S5_10- UINT16 - projectile speed in fixed point. 5000 -> 500.0
 * 		S6_12
 * 		S7_14
 * 		S8_16
 * 		S9_18
 * 		S10_20- UINT16 - impact graphic #1
 * 		S11_22- UINT16 - impact graphic #2
 * 		S12_24
 * 		S13_26
 * 		S14_28
 * 		S15_30
 * 		S16_32
 * 		S17_34
 * 		S18_36
 */
public class Proj extends DataFile {

}
