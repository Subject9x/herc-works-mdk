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
	
	private static String pathSimVol = "/unpack/simvol0/";
	private static String pathUnpack = "/unpack/";
	
	
	@Test
	public void simExportAll() {
		CommandLineMain.main(new String[] {
				"-x",
				pathSimVol + "pdg/OUTLAW.PDG",
				pathSimVol + "pdg/TOMAHAWK.PDG",
				pathSimVol + "pdg/RAMSES.PDG",
				pathSimVol + "pdg/ACHILLES.PDG",
				pathSimVol + "pdg/DIABLO.PDG",
				pathSimVol + "pdg/SPIDER.PDG",
				pathSimVol + "pdg/RAZOR.PDG",
				
				pathSimVol + "gl/OUTLAW.GL",
				pathSimVol + "gl/TOMAHAWK.GL",
				pathSimVol + "gl/RAMSES.GL",
				pathSimVol + "gl/ACHILLES.GL",
				pathSimVol + "gl/DIABLO.GL",
				pathSimVol + "gl/SPIDER.GL",
				pathSimVol + "gl/RAZOR.GL",
				
				pathSimVol + "FM/RAZOR.FM",
				
				pathSimVol + "dat/OUTLAW.DAT",
				pathSimVol + "dat/TOMAHAWK.DAT",
				pathSimVol + "dat/RAMSES.DAT",
				pathSimVol + "dat/ACHILLES.DAT",
				pathSimVol + "dat/DIABLO.DAT",
				pathSimVol + "dat/SPIDER.DAT",
				pathSimVol + "dat/RAZOR.DAT",
				
				pathSimVol + "dat/PROJ.DAT",
				pathSimVol + "dat/BULLETS.DAT",
				pathSimVol + "dat/ROCKETS.DAT",
				pathSimVol + "dat/BEAM.DAT",
				
		});
	}
	
	@Test
	public void simImportAll() {
		CommandLineMain.main(new String[] {
				"-x",
				pathUnpack + "pdg/OUTLAW.PDG.json",
				pathUnpack + "pdg/TOMAHAWK.PDG.json",
				pathUnpack + "pdg/RAMSES.PDG.json",
				pathUnpack + "pdg/ACHILLES.PDG.json",
				pathUnpack + "pdg/DIABLO.PDG.json",
				pathUnpack + "pdg/SPIDER.PDG.json",
				pathUnpack + "pdg/RAZOR.PDG.json",
				
				pathUnpack + "gl/OUTLAW.GL.json",
				pathUnpack + "gl/TOMAHAWK.GL.json",
				pathUnpack + "gl/RAMSES.GL.json",
				pathUnpack + "gl/ACHILLES.GL.json",
				pathUnpack + "gl/DIABLO.GL.json",
				pathUnpack + "gl/SPIDER.GL.json",
				pathUnpack + "gl/RAZOR.GL.json",
				
				pathUnpack + "FM/RAZOR.FM.json",
				
				pathUnpack + "dat/OUTLAW.DAT.json",
				pathUnpack + "dat/TOMAHAWK.DAT.json",
				pathUnpack + "dat/RAMSES.DAT.json",
				pathUnpack + "dat/ACHILLES.DAT.json",
				pathUnpack + "dat/DIABLO.DAT.json",
				pathUnpack + "dat/SPIDER.DAT.json",
				pathUnpack + "dat/RAZOR.DAT.json",
				
				pathUnpack + "dat/PROJ.DAT.json",
				pathUnpack + "dat/BULLETS.DAT.json",
				pathUnpack + "dat/ROCKETS.DAT.json",
				pathUnpack + "dat/BEAM.DAT.json",
				
		});
	}
	
	
	@Test
	public void shellExportAll() {
		
	}

	@Test
	public void shellImportAll() {
		
	}
}

