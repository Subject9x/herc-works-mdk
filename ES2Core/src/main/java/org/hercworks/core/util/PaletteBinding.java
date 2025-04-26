package org.hercworks.core.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * WARN: As far as I can tell, the binaries (VSHELL, DBSIM) must have some kind of logic OR LUT
 * 	for determining how a DPL palette is bound to any-given DBA/DBM. So far there's no observable 
 * 	external data for how the two file types get matched up.
 * 
 * 	What follows here is a rough way of making our own bindings with some fuzzy name checking / unit testing.
 */
public final class PaletteBinding {

	private static PaletteBinding INSTANCE;
	
	private List<PaletteBindingEntry> bindings = new ArrayList<PaletteBindingEntry>();
	
	private boolean indexAlpha = true;
	private boolean noAlphaPixel = false;
	
	private PaletteBinding() {
		//VSHELL
		bindings.add(new PaletteBindingEntry("ALPH.DPL", Arrays.asList("ALPH2."), noAlphaPixel));
		bindings.add(new PaletteBindingEntry("ALPHA.DPL", Arrays.asList("ALPHA."), noAlphaPixel));
		
		bindings.add(new PaletteBindingEntry("ARMING.DPL", Arrays.asList("MT_3QTR", "_BOD.", "_INT.", "_OUT.", "_WEP.","ARM_WEAP.", "ARM_HERC.", "ARM_WPNS.", "RPR_"), indexAlpha));
		
		bindings.add(new PaletteBindingEntry("BAY.DPL", Arrays.asList("BAY2A_80", "BAY2A_81", "BAY2A_82", "BAY2A_83", "BAY2A_84"), noAlphaPixel));

		bindings.add(new PaletteBindingEntry("BR_ER.DPL", Arrays.asList(""), noAlphaPixel));
		bindings.add(new PaletteBindingEntry("BR_MN.DPL", Arrays.asList(""), noAlphaPixel));
		bindings.add(new PaletteBindingEntry("BR_W1.DPL", Arrays.asList(""), noAlphaPixel));
		bindings.add(new PaletteBindingEntry("BR_W2.DPL", Arrays.asList(""), noAlphaPixel));
		bindings.add(new PaletteBindingEntry("BR_W3.DPL", Arrays.asList(""), noAlphaPixel));
		bindings.add(new PaletteBindingEntry("BR_W4.DPL", Arrays.asList(""), noAlphaPixel));
		bindings.add(new PaletteBindingEntry("BR_W5.DPL", Arrays.asList(""), noAlphaPixel));
		
		bindings.add(new PaletteBindingEntry("BRAV.DPL", Arrays.asList("BRAV1."), noAlphaPixel));
		bindings.add(new PaletteBindingEntry("BRAVO.DPL", Arrays.asList("BRAVO."), noAlphaPixel));
		
		bindings.add(new PaletteBindingEntry("CAM_ER.DPL", Arrays.asList(""), noAlphaPixel));
		bindings.add(new PaletteBindingEntry("CAM_MOON.DPL", Arrays.asList(""), noAlphaPixel));
		
		bindings.add(new PaletteBindingEntry("DB_ER.DPL", Arrays.asList(""), noAlphaPixel));
		bindings.add(new PaletteBindingEntry("DB_MOON.DPL", Arrays.asList(""), noAlphaPixel));
		bindings.add(new PaletteBindingEntry("DB_W1.DPL", Arrays.asList(""), noAlphaPixel));
		bindings.add(new PaletteBindingEntry("DB_W2.DPL", Arrays.asList(""), noAlphaPixel));
		bindings.add(new PaletteBindingEntry("DB_W3.DPL", Arrays.asList(""), noAlphaPixel));
		bindings.add(new PaletteBindingEntry("DB_W4.DPL", Arrays.asList(""), noAlphaPixel));
		bindings.add(new PaletteBindingEntry("DB_W5.DPL", Arrays.asList(""), noAlphaPixel));
		
		bindings.add(new PaletteBindingEntry("DELT.DPL", Arrays.asList("DELT1."), noAlphaPixel));
		bindings.add(new PaletteBindingEntry("DELTA.DPL", Arrays.asList("DELTA."), noAlphaPixel));
		
		bindings.add(new PaletteBindingEntry("ESII.DPL", Arrays.asList(""), noAlphaPixel));
		
		bindings.add(new PaletteBindingEntry("INTR_PT1.DPL", Arrays.asList(""), noAlphaPixel));
		
		bindings.add(new PaletteBindingEntry("LUNA.DPL", Arrays.asList("LUNA", "LUNA1"), noAlphaPixel));
		
		bindings.add(new PaletteBindingEntry("MAP.DPL", Arrays.asList(""), noAlphaPixel));
		bindings.add(new PaletteBindingEntry("MAP_PAL1.DPL", Arrays.asList(""), noAlphaPixel));
		bindings.add(new PaletteBindingEntry("MAP_PAL2.DPL", Arrays.asList(""), noAlphaPixel));
		
		bindings.add(new PaletteBindingEntry("OMIC.DPL", Arrays.asList("OMIC."), noAlphaPixel));
		bindings.add(new PaletteBindingEntry("OMICRON.DPL", Arrays.asList("OMICRON."), noAlphaPixel));
		
		bindings.add(new PaletteBindingEntry("PALETTE.DPL", Arrays.asList("MAINPIC1"), indexAlpha));
		
		
		//SIMVOL
		
	}
	
	public PaletteBindingEntry getPalette(String fileName) {
		PaletteBindingEntry found = null;
		
		for(PaletteBindingEntry binding : bindings) {
			List<String> fragments = binding.getFiles();
			if(fragments != null && !fragments.isEmpty()) {
				for(String stub : fragments) {
					if(fileName.toLowerCase().contains(stub.toLowerCase())) {
						found = binding;
						break;
					}
				}
			}
		}
		
		return found;
	}	
	
	public static PaletteBinding instance() {
		if(INSTANCE == null) {
			INSTANCE = new PaletteBinding();
		}
		return INSTANCE;
	}	
}
