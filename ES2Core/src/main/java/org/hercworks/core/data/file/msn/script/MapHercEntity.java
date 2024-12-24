package org.hercworks.core.data.file.msn.script;

import org.hercworks.core.data.struct.WeaponLUT;
import org.hercworks.core.data.struct.herc.HercLUT;

/**
 * 	Observed  in .MSN / scrip.var files.
 * 
 * 	each AI herc is 134 byte chunks of data.
 */

public class MapHercEntity {
	
	private HercLUT hercId;
	
	private WeaponLUT[] hardpoints = new WeaponLUT[10]; // info provides 10 slots but game trims down.

	private short[] unkFlags = new short[22];

	private short unkFlag1_5;
	private short unkFlag2_5;
	private short unkFlag3_5;
	private short unkFlag4_5;
	private short unkFlag5_5;

	private short unkFlag6_1;
	private short unkFlag7_1;
	private short unkFlag8_5;
	private short unkFlag9_5;
	private short unkFlag10_5;
	private short unkFlag11_2;
	private short unkFlag12_neg1;
	private short unkFlag13_2; //sometimes -1
	private short startingHealthMod; //default 0x64 = 100
	private short unkFlag15_1;
}
