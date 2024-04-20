package org.hercworks.core.data.struct.vshell.sav;

import org.hercworks.core.data.struct.WeaponLUT;
import org.hercworks.core.data.struct.vshell.hercs.UiWeaponEntry;

/**
 * 	Found in .sav files, for some reason this is double the information in {@linkplain UiWeaponEntry},
 * 	I've abstracted it out to its own class, but who knows if either is needed, or maybe resolve the 2 to a single class.
 */
public class ShellWeaponEntry {

	private WeaponLUT id;
	private short nameId;
	private short[] health = new short[2];
	private short missileNum;
	
	public ShellWeaponEntry() {}

	public WeaponLUT getId() {
		return id;
	}

	public short getNameId() {
		return nameId;
	}

	public short[] getHealth() {
		return health;
	}

	public short getMissileNum() {
		return missileNum;
	}

	public void setId(WeaponLUT id) {
		this.id = id;
	}

	public void setNameId(short nameId) {
		this.nameId = nameId;
	}

	public void setHealth(short[] health) {
		this.health = health;
	}

	public void setMissileNum(short missileNum) {
		this.missileNum = missileNum;
	}
}