package org.hercworks.core.data.struct.vshell.hercs;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.hercworks.core.data.file.dat.shell.Hercs;
import org.hercworks.core.data.file.dat.shell.InitHerc;

/**
 * 	Common struct, observed in 2 places so far. </br>
 *  <ul>
 *  	<li>{@linkplain Hercs}</li>
 *  	<li>{@linkplain InitHerc}</li>
 *  </ul>	
 *  Generally used in sequence
 *  
 *  S0_0- UINT16 - Herc Id
 *  S0_2- UINT16 - health ratio?
 *  S0_4- UINT16 - build completeness, 00 = complete, > 0 = remaining missions to build.
 *  S0_6- UINT16 - hardpoint count
 *  	SEQ1 - hardpoint count
 *  		S1_0- UINT - hardpoint ID
 *  		S1_2- UINT - item ID
 *  		S1_4- UINT - health percentage
 *  		S1_6- UINT - missile enum, 05 = no missile type.
 */
public class ShellHercData {
	private short hercId;
	private short healthRatio;
	private short buildCompleteLevel;
	private HashMap<Short, UiWeaponEntry> hardpoints;
	
	public ShellHercData() {}
	
	public short getHercId() {
		return hercId;
	}

	public short getHealthRatio() {
		return healthRatio;
	}

	public short getBuildCompleteLevel() {
		return buildCompleteLevel;
	}

	public HashMap<Short, UiWeaponEntry> getHardpoints() {
		return hardpoints;
	}

	public void setHercId(short hercId) {
		this.hercId = hercId;
	}

	public void setHealthRatio(short healthRatio) {
		this.healthRatio = healthRatio;
	}

	public void setBuildCompleteLevel(short buildCompleteLevel) {
		this.buildCompleteLevel = buildCompleteLevel;
	}
	
	public void setHardpoints(HashMap<Short, UiWeaponEntry> data) {
		this.hardpoints = data;
	}
}
