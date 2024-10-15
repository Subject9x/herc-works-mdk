package org.hercworks.core.data.file;

import org.hercworks.core.data.file.dat.shell.ArmHerc;
import org.hercworks.core.data.file.dat.shell.ArmWeap;
import org.hercworks.core.data.file.dat.shell.CareerMissions;
import org.hercworks.core.data.file.dat.shell.DamageRepairCost;
import org.hercworks.core.data.file.dat.shell.HardpointOverlayConfig;
import org.hercworks.core.data.file.dat.shell.HercInf;
import org.hercworks.core.data.file.dat.shell.Hercs;
import org.hercworks.core.data.file.dat.shell.InitHerc;
import org.hercworks.core.data.file.dat.shell.RprHerc;
import org.hercworks.core.data.file.dat.shell.TrainingHercs;
import org.hercworks.core.data.file.dat.shell.WeaponsDat;
import org.hercworks.core.data.file.dat.sim.BeamData;
import org.hercworks.core.data.file.dat.sim.HercSimDat;
import org.hercworks.core.data.file.dat.sim.MissileDatFile;
import org.hercworks.core.data.file.dat.sim.ProjectileData;
import org.hercworks.core.data.file.dat.sim.Weapons;
import org.hercworks.core.data.file.dbsim.FlightModel;
import org.hercworks.core.data.file.dbsim.GunLayout;
import org.hercworks.core.data.file.dbsim.HercSimDamage;
import org.hercworks.core.data.file.dbsim.PaperDollGraphic;
import org.hercworks.voln.DataFile;

/**
 * Why:
 * 	DBSIM and VSHELL are hardcoded to load specific files, with specific names and extensions,
 * 		each exe knows which files to pull from which folders...
 * 
 * 	any outside program doesn't have this limitation, or meta-info. 
 * Example:
 * 	/GAM/ARM_OUTL.DAT
 * 
 * 	/DAT/OUTLAW.DAT
 * 
 * 	both are 'dat' file extenions, but /GAM/ is for VSHELL and /DAT/ is DBSIM.
 * 	Worse, only /ARM_OUTL.DAT has any sort of unique key-phrase in the file name.
 * 
 * 	To make sure any other users can name their files whatever they'd like, but also bind to a known
 * 	ES2 file type, here's "FileClassDefs".
 */
public enum FileClassDefs {
	//SHELL
	ArmHerc("ArmHerc", ArmHerc.class),
	ArmWeap("ArmWeap", ArmWeap.class),
	CareerMissions("CareerMissions", CareerMissions.class),
	DamageRepairCost("DamageRepairCost", DamageRepairCost.class),
	HardpointOverlay("HardpointOverlay", HardpointOverlayConfig.class),
	HercInf("HercInfo", HercInf.class),
	Hercs("Hercs", Hercs.class),
	InitHerc("InitHerc", InitHerc.class),
	RprHerc("RepairHerc", RprHerc.class),
	TrainingHercs("TrainingHercs", TrainingHercs.class),
	WeaponsDat("ShellWeaponsDat", WeaponsDat.class),
	//SIM
	BeamData("BeamData", BeamData.class),
	HercSimData("HercSimData", HercSimDat.class),
	MissileData("MissileData", MissileDatFile.class),
	ProjectileData("ProjectileData", ProjectileData.class),
	WeaponsSimData("WeaponsSimData", Weapons.class),
	FlightModel("FlightModel", FlightModel.class),
	GunLayout("GunLayout", GunLayout.class),
	HercSimDamageInfo("HercSimDamageInfo", HercSimDamage.class),
	PaperDollGraphic("PaperDollGraphic", PaperDollGraphic.class)
	;
	
	
	
	private String val;
	private Class<? extends DataFile> type;
	
	private FileClassDefs(String val, Class<? extends DataFile> type) {
		this.val = val;
		this.type = type;
	}
	
	public String val() {
		return this.val;
	}
	
	public Class<? extends DataFile> getClassType(){
		return this.type;
	}
	
}
