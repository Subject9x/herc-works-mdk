package org.hercworks.core.data.file.dyn;

import java.nio.ByteOrder;

import org.hercworks.voln.DataFile;

import at.favre.lib.bytes.Bytes;

/**
 * FILE
 * 		DTS - Dynamix ThreeSpace
 * 	primary 3D model format for ES2 engine.
 */
public class DynamixThreeSpaceModel extends DataFile {

	//observed in ACHILLES.DTS, other herc dts files
	public static Bytes chunkMarker = Bytes.from("03001E00").byteOrder(ByteOrder.BIG_ENDIAN);
	
	//specifically the header for 
	public static Bytes staticMarker = Bytes.from("08001400").byteOrder(ByteOrder.BIG_ENDIAN);
	
	
	
	public DynamixThreeSpaceModel() {}
	
	public DynamixThreeSpaceModel(String fileName, String dirPath) {
		super(fileName, dirPath);
	}
	
}
