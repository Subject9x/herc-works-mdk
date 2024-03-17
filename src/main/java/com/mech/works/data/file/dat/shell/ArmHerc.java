package com.mech.works.data.file.dat.shell;

import java.util.HashMap;
import java.util.Set;

import com.mech.works.data.ref.files.DataFile;
import com.mech.works.data.struct.vshell.hercs.UiHardpoint;
import com.mech.works.data.struct.vshell.hercs.UiHardpointWeapon;
import com.mech.works.data.struct.vshell.hercs.UiImageDBA;

/**
 * 	FILE
 * 		/SHELL/GAM/ARM_[HERC].dat
 * 
 *  {@link UiImageDBA} Herc Top Image
 *  0- UINT16 - possibly z-layer!?
 *  2- UINT32 - UI X-coord offset of [HERC]_BOD.DBA/FRAME0 (upper body) graphic from Herc Panel org
 *  6- UINT32 - UI Y-coord offset of [HERC]_BOD.DBA/FRAME0 (upper body) graphic from Herc Panel org
 *  10- UINT32 - ? - unknown value
 *  14- UINT32 - ? - unknown value
 *  18- UINT16 - Frame Number of [HERC]_BOD.DBA (upper body) 
 *  20- UNIT16 - Render flags
 *   
 *  {@link UiImageDBA} Herc Bottom Image
 *  22- UINT16 - possibly z-layer!?
 *  24- UINT32 - UI X-coord offset of [HERC]_BOD.DBA/FRAME1 (lower body) graphic from Herc Panel org
 *  28- UINT32 - UI Y-coord offset of [HERC]_BOD.DBA/FRAME1 (lower body) graphic from Herc Panel org
 *  32- UINT32 - 
 *  36- UINT32 - same as [28] but no visible effect.
 *  40- UINT16 - Herc's Hangar DBA frames [HERC]_BOD.DBA/FRAME1
 *  42- UINT16 - render flags
 *  
 *  44- UINT16 - weapon count total - all hercs have 26, which does match total weapons in game, but not sure if this value is weapons.
 *  
 *  46-47 - ? - space bytes?
 *  
 *  48- UINT16 - hardpoint count - Hardpoints are NOT 0-index, they start at 0x01
 *  
 *  SEQ0 - hardpoint "none" graphic display data.
 *  	S0_0 - UINT16 - unknown value, possibly hardpoint id, non-zero-index
 *  	S0_2- UINT16 - hardpoint 1 - unknown value, possibly hardpoint id.
 *  	S0_6- UINT32 - hardpoint 1 - unknown screen coords - match x60 but no observable effect.
 *  	S0_10- UINT32 - hardpoint 1 - unknown screen coords - match x64 but no observable effect.
 *  	S0_14- UINT32 - hardpoint 1 - 'select' cross hair X offset from panel org.
 *  	S0_18- UINT32 - hardpoint 1 - 'select' cross hair Y offset from panel org.
 *  	S0_22- UINT32 - hardpoint 1 - 'outline' frame id from [HERC]_OUT.DBA
 *  
 *  SEQ1 - hardpoint graphics segments {@linkplain UiHardpoint}
 *  	S1_0- UINT16 - hardpoint number.
 *  	S1_2- UINT32 - X-ofs - [HERC]_OUT.DBA
 *  	S1_6- UINT32- Y-ofs - [HERC]_OUT.DBA
 *  	S1_10- UINT32- X-ofs - [HERC]_OUT.DBA
 *  	S1_14- UINT32- Y-ofs - [HERC]_OUT.DBA
 *  	S1_18- UINT16 - frame number - [HERC]_WEP.DBA/FRAME_X & [HERC]_OUT.DBA/FRAME_X
 *      S1_20- UINT16 - Orientation Flag - 1= flip X&Y, 2=flip horizontal 3=flip vertical, 4=normal
 *  
 *  SEQ2 - hardpoint validation and graphics
 *  	S2_0- UINT16 - Weapon Id (see main weapon list)
 *  	S2_2- UINT16 - total hardpoints applicable
 *  		SEQ3 - {@linkplain UiHardpointWeapon}
 *  			S3_0- UINT16 - hardpoint id
 *  			S3_2- UINT32 - graphic X coord;
 *  			S3_6- UINT32 - graphic y coord;
 *  			S3_10- UINT32 - outline X coord;
 *  			S3_14- UINT32 - outline y coord;
 *  			S3_18- UINT16 - frame number - [HERC]_WEP.DBA/FRAME_X & [HERC]_OUT.DBA/FRAME_X
 *      		S3_20- UINT16 - Orientation Flag - 1= flip X&Y, 2=flip horizontal 3=flip vertical, 4=normal
 *      	^--repeat for S2_2 value
 *  	^- repeat for x44 value 
 */
public class ArmHerc extends DataFile{
	
	private UiImageDBA hercTopImg;
	private UiImageDBA hercBotImg;
	
	private short totalWeapons;
	
	private short totalHardpoints;
	
	private HashMap<Short, UiHardpoint> emptyHardpoints;
	private HashMap<Short, Set<UiHardpointWeapon>> weaponHardpoints;
	
	
	
	public ArmHerc() {}



	public UiImageDBA getHercTopImg() {
		return hercTopImg;
	}



	public UiImageDBA getHercBotImg() {
		return hercBotImg;
	}



	public short getTotalWeapons() {
		return totalWeapons;
	}



	public short getTotalHardpoints() {
		return totalHardpoints;
	}



	public HashMap<Short, UiHardpoint> getEmptyHardpoints() {
		return emptyHardpoints;
	}



	public HashMap<Short, Set<UiHardpointWeapon>> getWeaponHardpoints() {
		return weaponHardpoints;
	}



	public void setHercTopImg(UiImageDBA hercTopImg) {
		this.hercTopImg = hercTopImg;
	}



	public void setHercBotImg(UiImageDBA hercBotImg) {
		this.hercBotImg = hercBotImg;
	}



	public void setTotalWeapons(short totalWeapons) {
		this.totalWeapons = totalWeapons;
	}



	public void setTotalHardpoints(short totalHardpoints) {
		this.totalHardpoints = totalHardpoints;
	}



	public void setEmptyHardpoints(HashMap<Short, UiHardpoint> emptyHardpoints) {
		this.emptyHardpoints = emptyHardpoints;
	}



	public void setWeaponHardpoints(HashMap<Short, Set<UiHardpointWeapon>> weaponHardpoints) {
		this.weaponHardpoints = weaponHardpoints;
	}
}