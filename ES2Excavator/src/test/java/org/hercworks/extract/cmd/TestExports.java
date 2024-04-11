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
}
