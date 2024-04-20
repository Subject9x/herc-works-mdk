package org.hercworks.core.data.struct.vshell.sav;

import org.hercworks.core.data.file.sav.PlayerSave;
import org.hercworks.core.data.struct.WeaponLUT;

/**
 *	Specifically bound to {@linkplain PlayerSave},
 * 		there's a chunk of save data dealing just with workshop state.
 *  
 *  total slots is always 5, probably set by VSHELL.
 *  
 */
public class Workshop {

	private short remainingSlots;
	private WeaponLUT[] slots;
	
	public Workshop() {}
	
	public Workshop(short remainingSlots) {
		this.remainingSlots = remainingSlots;
		this.slots = new WeaponLUT[5];
	}

	
	public short getRemainingSlots() {
		return remainingSlots;
	}

	public WeaponLUT[] getSlots() {
		return slots;
	}

	public void setRemainingSlots(short remainingSlots) {
		this.remainingSlots = remainingSlots;
	}

	public void setSlots(WeaponLUT[] slots) {
		this.slots = slots;
	}
}
