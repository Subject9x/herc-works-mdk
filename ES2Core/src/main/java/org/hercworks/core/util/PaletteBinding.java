package org.hercworks.core.util;

import java.util.Arrays;
import java.util.HashMap;
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
	
	private HashMap<String, List<String>> bindings = new HashMap<String, List<String>>();
	
	private PaletteBinding() {
		bindings.put("ARMING.DPL", Arrays.asList("ARM_", "ARMING", "_WEP", "_BOD", "ARMING"));
	}
	
	public String getPalette(String fileName) {
		String found = null;
		
		for(String dpl : bindings.keySet()) {
			//maybe file straight matches palette
			if(dpl.toLowerCase().equals(fileName.toLowerCase())) {
				found = dpl;
				break;
			}
			//ok it doesn't, lets get fuzzy.
			List<String> fragments = bindings.get(dpl);
			if(fragments != null && !fragments.isEmpty()) {
				for(String stub : fragments) {
					if(fileName.toLowerCase().contains(stub.toLowerCase())) {
						found = dpl;
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
