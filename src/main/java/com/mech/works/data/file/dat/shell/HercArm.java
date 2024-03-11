package com.mech.works.data.file.dat.shell;

import com.mech.works.data.ref.files.DataFile;

import at.favre.lib.bytes.Bytes;

/**
 * 	FILE
 * 		/SHELL/GAM/ARM_[HERC].dat
 * 
 * 	0 - 43 - currently unknown header bytes, same for every herc.
 *	44- UINT16 - ? -  different per herc.
 *	
 *  46-47 - spacer bytes
 * 	
 *  48- UINT16 - hardpoint count;
 * 	50- UINT16 - ? -  static, all have 0x02
 *  52- UINT16 - ? -  different per herc
 *  
 *  54 - 55 spacer bytes
 *  
 *  56- UINT16 - ? - different per herc
 *  
 *  58-59 - spacer bytes
 *  
 *  60- UINT16 - ? - matches UINT16 at 52
 *  
 *  62-63 spacer bytes
 *  
 *  64- UINT16 - ? - matches UINT16 at 56
 *  
 *  66-67 - spacer bytes
 *  
 *  68- UINT16 - ?
 *  
 *  70-71 - spacer bytes
 *  
 *  72- UINT16 - ? - static, all have 0x03
 *  
 *  74- UINT16 - ? - different per herc
 */
public class HercArm extends DataFile{
	
	public Bytes header44bytes;
	
	public short unk1_44;
	
	public short hardpointCount; 
	
	public static short unk2_50 = (byte)0x02;
	
	public short unk3_52;
	
	public short unk4_56;
	
	public short unk5_60;	//matches unk3_52
	
	public short unk6_64;	//matches unk4_56;
	
	public static short unk7_72 = (byte)0x03;
	
	public short unk8_74;
	
	
	
	
	public HercArm() {}

	
	
}
