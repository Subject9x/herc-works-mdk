package com.mech.works.dyn;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.awt.Color;

import org.junit.Test;

import com.mech.works.data.file.dyn.DynamixPalette;
import com.mech.works.io.read.DynFileReader;
import com.mech.works.util.PaletteViewPanel;
import com.mech.works.util.PaletteViewer;

public class TestDBAandDPL {

	@Test	
	public void loadAndExportDBM() {
		
		try {
			DynFileReader.loadDBM("E:\\ES2_OS\\dev\\earthsiege2\\VOL\\DBG\\SHELL\\DBG_SHELL01\\dbm\\BAY3.DBM");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test	
	public void loadAndExportDBA() {
		
		try {
			DynFileReader.loadDBM("E:\\ES2_OS\\dev\\earthsiege2\\VOL\\DBG\\SHELL\\DBG_SHELL01\\dba\\APOC_BOD.DBA");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void loadAndExport() {
		
		DynamixPalette palette = null;
		try {
			//E:\ES2_OS\dev\earthsiege2\VOL\DBG\SIMVOL0\DBG_SIMVOL0\dpl
			palette = DynFileReader.loadDPL("E:\\ES2_OS\\dev\\earthsiege2\\VOL\\DBG\\SHELL\\DBG_SHELL01\\dpl\\PALETTE.DPL");
			System.out.println(palette.toString());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(palette);
		
		assertFalse(palette.getColors().isEmpty());
		
		PaletteViewer pview = new PaletteViewer(palette);
		pview.setBackground(Color.black);
		
		
		PaletteViewPanel canvas = new PaletteViewPanel(pview) ;
	    pview.add(canvas);
	    
	    
	    pview.add(new PaletteViewPanel(pview));
	    pview.pack();
	    pview.setLocationRelativeTo(null);
	    pview.revalidate();
	    pview.repaint();
	    pview.setVisible(true);
		
	}

	public static void main(String[] args)
	{
		TestDBAandDPL test = new TestDBAandDPL();
		test.loadAndExport();
	}	
}
