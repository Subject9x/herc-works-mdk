package org.hercworks.core.io.transform.common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.hercworks.core.data.file.StringBinaryFile;
import org.hercworks.core.io.transform.ThreeSpaceByteTransformer;
import org.hercworks.voln.DataFile;
import org.hercworks.voln.FileType;

public class BinStringFileTransformer extends ThreeSpaceByteTransformer {

	@Override
	public DataFile bytesToObject(byte[] inputArray) throws ClassCastException {
		
		if(inputArray == null) {
			return null;
		}
		index = 0;
		setBytes(inputArray);
		
		StringBinaryFile binFile = new StringBinaryFile();
		binFile.setRawBytes(inputArray);
		binFile.setExt(FileType.BIN);
		
		//note - the reading here ditches the structure of the file,
		// writing out to the format will do the metadata generation.
		int totalStrings = indexIntLE();
		
		skip(4);	//skips total strings size, this is not needed.
		
		int indexStart = index;
		int stringStart = index + (totalStrings * 2);
		
		String[] values = new String[totalStrings];
		for(int i=0; i < totalStrings; i++) {
			index = indexStart + (i * 2);
			int offset = indexShortLE();
			
			if(i < totalStrings - 1) {
				index = indexStart + ((i+1) * 2);
				int nextIndex = indexShortLE(); 
				
				index = stringStart + offset;
				values[i] = indexString(nextIndex - offset).trim();
			}
			else {
				index = stringStart + offset;
				values[i] = indexString(inputArray.length - index).trim();
			}
		}
		
		binFile.setValues(values);
		
		return binFile;
	}

	@Override
	public byte[] objectToBytes(DataFile source) throws ClassCastException, IOException {
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		if(source == null) {
			return null;
		}

		StringBinaryFile sbf = (StringBinaryFile)source;
		
		
		short[] index = new short[sbf.getValues().length];
		
		int size = 0;
		for(int s=0; s < sbf.getValues().length; s++) {
			index[s] = (short)size;
			size += sbf.getValues()[s].length();
			size += sbf.getValues()[s].endsWith(" ") ? 0 : 1; //null terminal byte
		}
		
		out.write(writeIntLE(sbf.getValues().length));
		out.write(writeIntLE(size));
		for(int i=0; i < sbf.getValues().length; i++) {
			out.write(writeShortLE(index[i]));
		}
		for(int t=0; t < sbf.getValues().length; t++) {
			out.write(sbf.getValues()[t].getBytes());
			out.write(0x00);
		}
		
		return out.toByteArray();
	}

}
