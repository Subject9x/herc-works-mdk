package org.hercworks.extract.util.impl;

import java.util.Arrays;
import java.util.List;

import org.hercworks.extract.util.FileMatcher;
import org.hercworks.voln.FileType;

public class DynFileMatcher implements FileMatcher {

	private static List<FileType> types = 
			Arrays.asList(FileType.DB0,
					FileType.DB1,
					FileType.DB2,
					FileType.DBA,
					FileType.DBM,
					FileType.DPL,
					FileType.HB0,
					FileType.HB1,
					FileType.HB2,
					FileType.HBA,
					FileType.VOL);
	
	@Override
	public boolean matchFile(String file) {
		file = file.toLowerCase();
		
		for(FileType t : types) {
			if(file.contains("."+t.name().toLowerCase())) {
				return true;
			}
		}
		
		return false;
	}

}
