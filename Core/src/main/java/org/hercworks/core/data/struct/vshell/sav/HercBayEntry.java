package org.hercworks.core.data.struct.vshell.sav;

import java.util.HashMap;
import java.util.Map;

import org.hercworks.core.data.file.sav.PlayerSave;
import org.hercworks.core.data.struct.herc.HercExternals;
import org.hercworks.core.data.struct.herc.HercInternals;
import org.hercworks.core.data.struct.herc.HercLUT;
import org.hercworks.core.data.struct.vshell.hercs.ShellHercPart;

/**
 * Specifically bound to {@linkplain PlayerSave}, segment of  save data is an array
 * of herc bay entries
 */
public class HercBayEntry {
	
	private HercLUT id;
	private short nameId;
	private Map<HercExternals, ShellHercPart> healthExternals;
	private Map<HercInternals, ShellHercPart> healthInternals;
	private ShellHercPart[] healthHardpoints = new ShellHercPart[10];
	private short buildPercent;
	private short buildStepNum;
	private short hardpointMax;
	private short activeSockets;
	private Map<Short, ShellWeaponEntry> weapons = new HashMap<Short, ShellWeaponEntry>();
	
	public HercBayEntry() {}

	public HercLUT getId() {
		return id;
	}

	public short getNameId() {
		return nameId;
	}

	public Map<HercExternals, ShellHercPart> getHealthExternals() {
		return healthExternals;
	}

	public Map<HercInternals, ShellHercPart> getHealthInternals() {
		return healthInternals;
	}

	public ShellHercPart[] getHealthHardpoints() {
		return healthHardpoints;
	}

	public short getBuildPercent() {
		return buildPercent;
	}

	public short getBuildStepNum() {
		return buildStepNum;
	}

	public short getHardpointMax() {
		return hardpointMax;
	}

	public short getActiveSockets() {
		return activeSockets;
	}

	public Map<Short, ShellWeaponEntry> getWeapons() {
		return weapons;
	}

	public void setId(HercLUT id) {
		this.id = id;
	}

	public void setNameId(short nameId) {
		this.nameId = nameId;
	}

	public void setHealthExternals(Map<HercExternals, ShellHercPart> healthExternals) {
		this.healthExternals = healthExternals;
	}

	public void setHealthInternals(Map<HercInternals, ShellHercPart> healthInternals) {
		this.healthInternals = healthInternals;
	}

	public void setHealthHardpoints(ShellHercPart[] healthHardpoints) {
		this.healthHardpoints = healthHardpoints;
	}

	public void setBuildPercent(short buildPercent) {
		this.buildPercent = buildPercent;
	}

	public void setBuildStepNum(short buildStepNum) {
		this.buildStepNum = buildStepNum;
	}

	public void setHardpointMax(short hardpointMax) {
		this.hardpointMax = hardpointMax;
	}

	public void setActiveSockets(short activeSockets) {
		this.activeSockets = activeSockets;
	}

	public void setWeapons(Map<Short, ShellWeaponEntry> weapons) {
		this.weapons = weapons;
	}
}
