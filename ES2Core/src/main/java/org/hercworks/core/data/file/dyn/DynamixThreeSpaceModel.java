package org.hercworks.core.data.file.dyn;

import java.util.LinkedList;

import org.hercworks.core.data.file.dts.DTSObject;
import org.hercworks.voln.DataFile;

/**
 * FILE
 * 		DTS - Dynamix ThreeSpace
 * 	primary 3D model format for ES2 engine.
 */
public class DynamixThreeSpaceModel extends DataFile {
	
	private LinkedList<DTSObject> parts;
	
	public DynamixThreeSpaceModel() {}
	
	public DynamixThreeSpaceModel(String fileName, String dirPath) {
		super(fileName, dirPath);
	}

	public LinkedList<DTSObject> getDTSParts() {
		return parts;
	}

	public void setDTSParts(LinkedList<DTSObject> parts) {
		this.parts = parts;
	}
}
