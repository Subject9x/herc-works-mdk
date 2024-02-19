package com.mech.works.dyn;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.mech.works.data.file.dyn.DynamixBitmap;
import com.mech.works.data.file.dyn.DynamixBitmapArray;
import com.mech.works.data.file.dyn.DynamixPalette;
import com.mech.works.io.read.DynFileReader;
import com.mech.works.io.write.DynFileWriter;
import com.mech.works.util.PaletteViewPanel;
import com.mech.works.util.PaletteViewer;

public class TestDBAandDPL {

	@Test	
	public void loadAndExportDBM() {
		
		String targURL = "E:\\ES2_OS\\dev\\earthsiege2\\VOL\\DBG\\SHELL\\DBG_SHELL01\\DBM\\MAINPIC1.DBM";
		
		List<String> pals = new ArrayList<String>();
		pals.add( "ESII.DPL");
		pals.add( "ARMING.DPL");
		pals.add( "BAY.DPL");
		pals.add( "PALETTE.DPL");
		pals.add( "ALPHA.DPL");
		pals.add( "MAP.DPL");
		pals.add( "MAP_PAL1.DPL");
		pals.add( "MAP_PAL2.DPL");
		
		List<DynamixPalette> dplList = new ArrayList<DynamixPalette>();
		
		String targDPL = "ESII.DPL";
		
		DynamixBitmap loadedDBM = null;
		try {
			loadedDBM = DynFileReader.loadDBM(targURL);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		assertNotNull(loadedDBM);
		
		String volDir = DynFileReader.getVolDirOfFile(targURL, loadedDBM.getFileName());
		
		DynamixPalette mainDPL = null;
		try {
			
			for(String p : pals) {
				dplList.add( DynFileReader.loadDPL(volDir + "\\DPL\\"+p));
			}
			assertFalse(pals.isEmpty());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		assertFalse(dplList.isEmpty());
		
		String fileName = loadedDBM.originNameNoExt();
		for(DynamixPalette pal : dplList) {
			if(pals.iterator().hasNext()) {
				loadedDBM.setFileName(fileName+"_"+pal.originNameNoExt());
				DynFileWriter.writeDBMToFile(loadedDBM, pal, volDir+"DBM\\exp\\");
			}
			
		}
		
		
//		DynFileWriter.writeDBMToFileNoPalette(loadedDBM, volDir+"DBM\\exp\\");
		
	}
	
	@Test	
	public void loadAndExportDBA() {
		
		String targUrl = "E:\\ES2_OS\\dev\\earthsiege2\\VOL\\DBG\\SHELL\\DBG_SHELL01\\DBA\\TOMA_INT.DBA";

		List<String> pals = new ArrayList<String>();
		pals.add( "PALETTE.DPL");
		pals.add( "ESII.DPL");
		pals.add( "ARMING.DPL");
		pals.add( "BAY.DPL");
		
//		
		List<DynamixPalette> dplList = new ArrayList<DynamixPalette>();
		
		
		File targFile = new File(targUrl);
		
		DynamixBitmapArray loadedDBA = null;
		try {
			loadedDBA = DynFileReader.loadDBA(targUrl);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		assertNotNull(loadedDBA);
		
		File dbaExportdir = new File(loadedDBA.getFilePath()+"\\"+loadedDBA.originNameNoExt());
		if(!dbaExportdir.exists()) {
			dbaExportdir.mkdir();
		}
		
		String volDir = DynFileReader.getVolDirOfFile(targUrl, loadedDBA.getFileName());
		
		DynamixPalette mainDPL = null;
		try {
			for(String p : pals) {
				dplList.add( DynFileReader.loadDPL(volDir + "\\DPL\\"+p));
			}
			
			assertFalse(pals.isEmpty());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		assertFalse(dplList.isEmpty());
		
		//normal ops
		for(DynamixBitmap dbm : loadedDBA.getImages()) {
			
			
			dbm.setFileName(loadedDBA.originNameNoExt() + dbm.getFileName());
			dbm.assignDir(dbaExportdir.getPath()+"\\");
			String name = dbm.getFileName();
			for(DynamixPalette pal : dplList) {
					DynFileReader.matchUniqueColorToPalette(DynFileReader.getDBMUniqueColors(dbm), pal);
					dbm.setFileName(name+pal.originNameNoExt());
					DynFileWriter.writeDBMToFile(dbm, pal, dbm.getFilePath());
			}
//			DynFileWriter.writeDBMToFile(dbm, mainDPL, dbaDir);
		}
		
	}
	
	
	@Test	
	public void loadAndExportDBAtoDBM() {
		
		String targUrl = "E:\\ES2_OS\\dev\\earthsiege2\\VOL\\DBG\\SHELL\\DBG_SHELL01\\DBA\\TOM_BOD.DBA";


		File targFile = new File(targUrl);
		
		DynamixBitmapArray loadedDBA = null;
		try {
			loadedDBA = DynFileReader.loadDBA(targUrl);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		assertNotNull(loadedDBA);
		
		File dbaExportdir = new File(loadedDBA.getFilePath()+"\\"+loadedDBA.originNameNoExt());
		if(!dbaExportdir.exists()) {
			dbaExportdir.mkdir();
		}
		
		String volDir = DynFileReader.getVolDirOfFile(targUrl, loadedDBA.getFileName());
		

		for(DynamixBitmap dbm : loadedDBA.getImages()) {
			
			
			dbm.setFileName(loadedDBA.originNameNoExt() + dbm.getFileName());
			dbm.assignDir(dbaExportdir.getPath()+"\\");
			String name = dbm.getFileName();
			
			DynFileWriter.writeDBMToFile(dbm, dbm.getFilePath());
			
		}
		
	}
	
	
	public void loadAndExport(String dplPath, String targDB) {
		
		DynamixPalette palette = null;
		try {
			//E:\ES2_OS\dev\earthsiege2\VOL\DBG\SIMVOL0\DBG_SIMVOL0\dpl
			palette = DynFileReader.loadDPL(dplPath);
			System.out.println(palette.toString());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(palette);
		
		assertFalse(palette.getColors().isEmpty());
		
		File targFile = new File(targDB);
		
		DynamixBitmapArray loadedDBA = null;
		try {
			loadedDBA = DynFileReader.loadDBA(targDB);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		assertNotNull(loadedDBA);
		
		PaletteViewer pview = new PaletteViewer(palette);
		pview.setBackground(Color.black);
		
		
		PaletteViewPanel canvas = new PaletteViewPanel(pview, loadedDBA.getImages().iterator().next()) ;
	    pview.add(canvas);
	    
	    
	    pview.pack();
	    pview.setLocationRelativeTo(null);
	    pview.revalidate();
	    pview.repaint();
	    pview.setVisible(true);
	    pview.setTitle(palette.getFileName());
		
	}

	public static void main(String[] args)
	{
		String targDpl = "E:\\ES2_OS\\dev\\earthsiege2\\VOL\\DBG\\SHELL\\DBG_SHELL01\\DPL\\BAY.DPL";
		String targUrl = "E:\\ES2_OS\\dev\\earthsiege2\\VOL\\DBG\\SHELL\\DBG_SHELL01\\DBA\\TOM_OUT.DBA";
		
		
		TestDBAandDPL test = new TestDBAandDPL();
		test.loadAndExport(targDpl, targUrl);
		
		
		
//		test = new TestDBAandDPL();
//		test.loadAndExport("E:\\ES2_OS\\dev\\earthsiege2\\VOL\\DBG\\SHELL\\DBG_SHELL01\\dpl\\ESII.DPL");
//		test = new TestDBAandDPL();
//		test.loadAndExport("E:\\ES2_OS\\dev\\earthsiege2\\VOL\\DBG\\SHELL\\DBG_SHELL01\\dpl\\PALETTE.DPL");
	}	
}
