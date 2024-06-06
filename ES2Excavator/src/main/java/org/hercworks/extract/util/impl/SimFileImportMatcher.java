package org.hercworks.extract.util.impl;

import org.hercworks.extract.util.SimFileNames;
import org.hercworks.voln.FileType;

public class SimFileImportMatcher extends SimFileMatcher {

	
	@Override
	public boolean matchFile(String file) {
	
		String fileName = file.toLowerCase();
		
		if(!fileName.contains(getJsonExt())) {
			return false;
		}
		for(FileType t : getExt()) {
			if(fileName.contains(t.name().toLowerCase())) {
				return true;
			}
		}
		
		if(SimFileNames.getByPattern(fileName) != null) {
			return true;
		}
		
		return false;
	}

}
