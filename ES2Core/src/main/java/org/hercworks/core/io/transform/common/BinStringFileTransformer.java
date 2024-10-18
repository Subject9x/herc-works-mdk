package org.hercworks.core.io.transform.common;

import java.io.IOException;

import org.hercworks.core.data.file.StringBinaryFile;
import org.hercworks.core.io.transform.ThreeSpaceByteTransformer;
import org.hercworks.voln.DataFile;

public class BinStringFileTransformer extends ThreeSpaceByteTransformer {

	@Override
	public DataFile bytesToObject(byte[] inputArray) throws ClassCastException {
		
		if(inputArray == null) {
			return null;
		}
		setBytes(inputArray);
		
		StringBinaryFile bin = new StringBinaryFile();
		bin.setRawBytes(inputArray);
		
		bin.setTotalStrings(indexIntLE());
		
		skip(4);	//UINT32 of total bytes.
		
		for(int i = 0; i < bin.getTotalStrings(); i++) {
			
		}
		
		
		
		return bin;
	}

	@Override
	public byte[] objectToBytes(DataFile source) throws ClassCastException, IOException {
		
		
		
		
		return null;
	}

}
