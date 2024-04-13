package org.hercworks.extract.cmd;

import org.hercworks.extract.CommandLineMain;
import org.junit.Test;


public class TestExports {

	public static String[] wepDatArgs = new String[] {"-x","/UNPACK/SHELL0/GAM/WEAPONS.DAT"};
	public static String[] wepJsonArgs = new String[] {"-x","/UNPACK/WEAPONS.json"};
	
	public static String[] hercsDatArgs = new String[] {"-x","/UNPACK/SHELL0/GAM/HERCS.DAT"};
	public static String[] hercsJsonArgs = new String[] {"-x","/UNPACK/HERCS.json"};
	
	public static String[] hercInfDatArgs = new String[] {"-x","/UNPACK/SHELL0/GAM/HERC_INF.DAT"};
	public static String[] hercInfJsonArgs = new String[] {"-x","/UNPACK/HERC_INF.json"};
	
	
	public static String[] hercInitDatArgs = new String[] {"-x","/UNPACK/SHELL0/GAM/INI_OUTL.DAT"};
	public static String[] hercInitJsonArgs = new String[] {"-x","/UNPACK/INI_OUTL.json"};
	
	public static String[] trainHercDatArgs = new String[] {"-x","/UNPACK/SHELL0/GAM/TRN_HERC.DAT"};
	public static String[] trainHercJsonArgs = new String[] {"-x","/UNPACK/TRN_HERC.json"};
	
	public static String[] argsExportAll = new String[] {"-x","/UNPACK/SHELL0/GAM/WEAPONS.DAT","/UNPACK/SHELL0/GAM/HERCS.DAT","/UNPACK/SHELL0/GAM/TRN_HERC.DAT","/UNPACK/SHELL0/GAM/TRN_HERC.DAT","/UNPACK/SHELL0/GAM/INI_OUTL.DAT","/UNPACK/SHELL0/GAM/HERC_INF.DAT"};
	public static String[] argsImportAll = new String[] {"-x","/UNPACK/WEAPONS.json","/UNPACK/HERCS.json","/UNPACK/TRN_HERC.json","/UNPACK/TRN_HERC.json","/UNPACK/INI_OUTL.json","/UNPACK/HERC_INF.json"};
	@Test
	public void exportAll() {
		CommandLineMain.main(argsExportAll);	
	}
	
	@Test
	public void importAll() {
		CommandLineMain.main(argsImportAll);	
	}
	
	@Test
	public void testWeaponsDat() {
		
//		CommandLineMain.main(wepDatArgs);
		
		CommandLineMain.main(wepJsonArgs);
		
	}
	
	@Test
	public void testHercs() {
		
		CommandLineMain.main(hercsDatArgs);
		
		CommandLineMain.main(hercsJsonArgs);
		
		
	}
	
	@Test
	public void testHercInf() {
		
		CommandLineMain.main(hercInfDatArgs);
		
		CommandLineMain.main(hercInfJsonArgs);
		
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
