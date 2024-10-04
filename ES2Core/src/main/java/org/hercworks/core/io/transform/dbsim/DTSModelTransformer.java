package org.hercworks.core.io.transform.dbsim;

import java.io.IOException;

import org.hercworks.core.data.file.dts.TSBasePart;
import org.hercworks.core.data.file.dts.TSPartList;
import org.hercworks.core.data.file.dyn.DynamixThreeSpaceModel;
import org.hercworks.core.io.transform.ThreeSpaceByteTransformer;
import org.hercworks.voln.DataFile;
import org.hercworks.voln.FileType;

public class DTSModelTransformer extends ThreeSpaceByteTransformer {

	@Override
	public DataFile bytesToObject(byte[] inputArray) throws ClassCastException {
		
		if(inputArray == null || inputArray.length <= 0) {
			//TODO - log null
			return null;
		}
		setBytes(inputArray);
		
		DynamixThreeSpaceModel dts = new DynamixThreeSpaceModel();
		dts.setRawBytes(inputArray);
		dts.setExt(FileType.DTS);
		dts.setDir(FileType.DTS);
		
		byte[] header = indexSegmentLE(4);
		System.out.println("start= " + header);
		
		int i32SegmentSize = indexIntLE();
		System.out.println("unk_int32= " + i32SegmentSize);
		
		TSPartList parent = new TSPartList();
		
		
		return null;
	}

	@Override
	public byte[] objectToBytes(DataFile source) throws ClassCastException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

}
