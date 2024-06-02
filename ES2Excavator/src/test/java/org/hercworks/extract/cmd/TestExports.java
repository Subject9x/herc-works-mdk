package org.hercworks.extract.cmd;

import org.hercworks.extract.CommandLineMain;
import org.junit.Test;


public class TestExports {

	public static String[] wepDatArgs = new String[] {"-x","/UNPACK/SHELL0/GAM/WEAPONS.DAT"};
	public static String[] wepJsonArgs = new String[] {"-x","/UNPACK/GAM/WEAPONS.GAM.json"};
	
	public static String[] hercsDatArgs = new String[] {"-x","/UNPACK/SHELL0/GAM/HERCS.DAT"};
	public static String[] hercsJsonArgs = new String[] {"-x","/UNPACK/GAM/HERCS.GAM.json"};
	
	public static String[] simHercDatArgs = new String[] {"-x","/UNPACK/SIMVOL0/dat/APOCA.DAT"};
	public static String[] simHercJsonDatArgs = new String[] {"-x","/UNPACK/DAT/APOCA.DAT.json"};
	
	public static String[] hercInfDatArgs = new String[] {"-x","/UNPACK/SHELL0/GAM/HERC_INF.DAT"};
	public static String[] hercInfJsonArgs = new String[] {"-x","/UNPACK/HERC_INF.json"};
	
	public static String[] hercInitDatArgs = new String[] {"-x","/UNPACK/SHELL0/GAM/INI_OUTL.DAT"};
	public static String[] hercInitJsonArgs = new String[] {"-x","/UNPACK/INI_OUTL.json"};
	
	public static String[] trainHercDatArgs = new String[] {"-x","/UNPACK/SHELL0/GAM/TRN_HERC.DAT"};
	public static String[] trainHercJsonArgs = new String[] {"-x","/UNPACK/TRN_HERC.json"};
	
	public static String[] argsExportAll = new String[] {"-x","/UNPACK/SHELL0/GAM/WEAPONS.DAT","/UNPACK/SHELL0/GAM/HERCS.DAT","/UNPACK/SHELL0/GAM/TRN_HERC.DAT","/UNPACK/SHELL0/GAM/TRN_HERC.DAT","/UNPACK/SHELL0/GAM/INI_OUTL.DAT","/UNPACK/SHELL0/GAM/HERC_INF.DAT"};
	public static String[] argsImportAll = new String[] {"-x","/UNPACK/WEAPONS.json","/UNPACK/HERCS.json","/UNPACK/TRN_HERC.json","/UNPACK/TRN_HERC.json","/UNPACK/INI_OUTL.json","/UNPACK/HERC_INF.json"};
	
	
	
	@Test
	public void testPDGFile() {
		CommandLineMain.main(new String[] {"-x", 
		"/UNPACK/pdg/OUTLAW.PDG.json",
		"/UNPACK/pdg/TOMAHAWK.PDG.json",
		"/UNPACK/pdg/RAMSES.PDG.json",
		"/UNPACK/pdg/ACHILLES.PDG.json",
		"/UNPACK/pdg/DIABLO.PDG.json",
		"/UNPACK/pdg/SPIDER.PDG.json",
		"/UNPACK/pdg/RAZOR.PDG.json",
		
		}); 
	}
	
	@Test
	public void exportDBA() {
		CommandLineMain.main(new String[] {"-x", "-p", 
				"/UNPACK/simvol0/dba/apoca.dba",
				"/UNPACK/simvol0/dpl/World0.DPL"}); 
//				"/UNPACK/simvol0/dba/TOMAHAWK.DBA",
//				"/UNPACK/simvol0/dpl/World0.DPL"});
	}
	
	@Test
	public void exportHercSimDmg() {
//		CommandLineMain.main(new String[] {"-x", 
//		"/UNPACK/simvol0/dmg/TOMAHAWK.DMG"});
//		CommandLineMain.main(new String[] {"-x", 
//		"/UNPACK/dmg/TOMAHAWK.DMG.json"});
	}
	
	
	@Test
	public void exportHercSimDat() {
//		CommandLineMain.main(simHercJsonDatArgs);
//		CommandLineMain.main(new String[] {"-x", 
//				"/UNPACK/SIMVOL0/gl/ACHILLES.GL",
//				"/UNPACK/SIMVOL0/gl/PITBULL.GL",
//				"/UNPACK/SIMVOL0/gl/RAMSES.GL",
//				"/UNPACK/SIMVOL0/gl/TOMAHAWK.GL"});
//		CommandLineMain.main(new String[] {"-x", 
//				"/UNPACK/dmg/TOMAHAWK.GL.json"});
//		CommandLineMain.main(simHercDatArgs);
	}
	
	@Test
	public void exportSave() {
//		CommandLineMain.main(new String[] {"-x", "/GAME_0.sav"});	
	}
	
	@Test
	public void exportRepair() {
//		CommandLineMain.main(new String[] {"-x", "/UNPACK/RPR_OUTL.json"});	
	}
	
	@Test
	public void exportAll() {
//		CommandLineMain.main(argsExportAll);	
	}
	
	@Test
	public void importAll() {
//		CommandLineMain.main(argsImportAll);	
	}
	
	@Test
	public void testWeaponsDat() {
		
//		CommandLineMain.main(wepDatArgs);
		
		CommandLineMain.main(wepJsonArgs);
		
	}
	
	@Test
	public void testHercs() {
		
//		CommandLineMain.main(hercsDatArgs);
		
		CommandLineMain.main(hercsJsonArgs);
		
		
	}
	
	@Test
	public void testHercInf() {
		
		CommandLineMain.main(hercInfDatArgs);
		
//		CommandLineMain.main(hercInfJsonArgs);
		
	}
	
	@Test
	public void testInitHer() {
		
		CommandLineMain.main(hercInitDatArgs);
		
//		CommandLineMain.main(hercInitJsonArgs);
		
	}
	
	@Test
	public void testTrainHercs() {
		
		CommandLineMain.main(trainHercDatArgs);
//		
//		CommandLineMain.main(trainHercJsonArgs);
		
	}
	
}
