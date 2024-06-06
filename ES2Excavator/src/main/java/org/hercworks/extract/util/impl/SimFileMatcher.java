package org.hercworks.extract.util.impl;

import java.util.Arrays;
import java.util.List;

import org.hercworks.extract.util.FileMatcher;
import org.hercworks.extract.util.SimFileNames;
import org.hercworks.voln.FileType;

public class SimFileMatcher implements FileMatcher {

	private static String jsonExt = ".json";	
	
	private List<FileType> ext = Arrays.asList(FileType.DAT, FileType.DMG, FileType.PDG, FileType.GL);
	
	@Override
	public boolean matchFile(String file) {
	
		String fileName = file.toLowerCase();
		
		if(fileName.contains(jsonExt)) {
			return false;
		}
		for(FileType t : ext) {
			if(fileName.contains(t.name().toLowerCase())) {
				return true;
			}
		}
		
		if(SimFileNames.getByPattern(fileName) != null) {
			return true;
		}
		
		return false;
	}

	public static String getJsonExt() {
		return jsonExt;
	}

	public List<FileType> getExt() {
		return ext;
	}
}
