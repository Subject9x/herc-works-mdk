package org.hercworks.core.data.file.dat.shell;

import org.hercworks.core.data.struct.vshell.hercs.UiHardpointGraphic;
import org.hercworks.voln.DataFile;


/**
 * 	FILE
 * 		/SHELL/GAM/ARM_WEAP.DAT
 * 	Configures image render position offset in /UI/ARMING/Weapon_panel.
 * 		origin here is the top-left corner of the arming weapon panel image.
 * 
 * 	0- UINT16 - total weapons
 *  SEQ_1- 12 byte segments
 *  	SEQ1_0 - UINT16 - weapon id
 *  	SEQ1_2 - UINT32 - x offset to weapon panel top-left.
 *  	SEQ1_6 - UINT32 - y offset to weapon panel top-left.
 *  	SEQ1_10 - UINT16 - end byte value, probably DBA frame Id
 *  
 *  0 + (12 * total Weapons) - UINT16 - secondary list? default 4
 *  SEQ
 */
public class ArmWeap extends DataFile{

	private short totalWeapons;
	private short totalSecondList;
	
	private UiHardpointGraphic[] entries;
	
	private UiHardpointGraphic[] secondary;
	
	public ArmWeap() {}
	
	public ArmWeap(short totalWeapons) {
		this.totalWeapons = totalWeapons;
		this.entries = new UiHardpointGraphic[totalWeapons];
	}
	
	public short getTotalWeapons() {
		return totalWeapons;
	}

	public UiHardpointGraphic[] getEntries() {
		return entries;
	}

	public void setTotalWeapons(short totalWeapons) {
		this.totalWeapons = totalWeapons;
	}

	public void setEntries(UiHardpointGraphic[] entries) {
		this.entries = entries;
	}

	public short getTotalSecondList() {
		return totalSecondList;
	}

	public UiHardpointGraphic[] getSecondary() {
		return secondary;
	}

	public void setTotalSecondList(short totalSecondList) {
		this.totalSecondList = totalSecondList;
	}

	public void setSecondary(UiHardpointGraphic[] secondary) {
		this.secondary = secondary;
	}
}
