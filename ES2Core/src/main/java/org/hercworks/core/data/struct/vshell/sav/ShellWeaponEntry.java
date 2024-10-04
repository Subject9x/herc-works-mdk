package org.hercworks.core.data.struct.vshell.sav;

import org.hercworks.core.data.struct.MissileType;
import org.hercworks.core.data.struct.WeaponLUT;
import org.hercworks.core.data.struct.vshell.hercs.UiWeaponEntry;

/**
 * 	Found in .sav files, for some reason this is double the information in {@linkplain UiWeaponEntry},
 * 	I've abstracted it out to its own class, but who knows if either is needed, or maybe resolve the 2 to a single class.
 */
public class ShellWeaponEntry {

	private WeaponLUT id;
	private short nameId;
	private short healthArmor;
	private short healthInteral;
	private MissileType missileType;
	
	public ShellWeaponEntry() {}

	public WeaponLUT getId() {
		return id;
	}

	public short getNameId() {
		return nameId;
	}

	public MissileType getMissileType() {
		return missileType;
	}

	public void setId(WeaponLUT id) {
		this.id = id;
	}

	public void setNameId(short nameId) {
		this.nameId = nameId;
	}

	public void setMissileType(MissileType missileType) {
		this.missileType = missileType;
	}

	public short getHealthArmor() {
		return healthArmor;
	}

	public short getHealthInteral() {
		return healthInteral;
	}

	public void setHealthArmor(short healthArmor) {
		this.healthArmor = healthArmor;
	}

	public void setHealthInternal(short healthInteral) {
		this.healthInteral = healthInteral;
	}
}