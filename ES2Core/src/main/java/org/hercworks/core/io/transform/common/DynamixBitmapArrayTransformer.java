package org.hercworks.core.io.transform.common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.hercworks.core.data.file.dyn.DynamixBitmap;
import org.hercworks.core.data.file.dyn.DynamixBitmapArray;
import org.hercworks.core.io.transform.ThreeSpaceByteTransformer;
import org.hercworks.voln.DataFile;
import org.hercworks.voln.FileType;

import at.favre.lib.bytes.Bytes;

public class DynamixBitmapArrayTransformer extends ThreeSpaceByteTransformer{

	public DynamixBitmapArrayTransformer() {}
	
	@Override
	public DataFile bytesToObject(byte[] inputArray) throws ClassCastException {
		
		if(inputArray == null) {
			//TODO log null
			return null;
		}
		
		setBytes(inputArray);
		
		DynamixBitmapArray dba = new DynamixBitmapArray();
		dba.setRawBytes(Bytes.from(inputArray).array());
		dba.setExt(FileType.DBA);
		dba.setDir(FileType.DBA);
		
		dba.setHeader(indexSegment(4));
		dba.setFileSize(Bytes.from(indexSegmentLE(4)));
		dba.setArrayRow(indexShortLE());
		dba.setArrayCols(indexShortLE());	//TODO could this actually be an INT32 for total images?
		
		DynamixBitmap[] images = new DynamixBitmap[dba.getArrayRow()];
		
		int imageCount = 0;
		
		DynamixBitmapTransformer dynbitmapTransform = new DynamixBitmapTransformer();
		
		int actualBytes = bytes.length - index;
		while(index < actualBytes) {
			skip(4);
			
			int fileLength = indexIntLE();	//slight read ahead to get file size first 
			
			index -= 8;	//transformer assumes a clean read from byte[0], so reset head read.
			byte[] dbaItem = indexSegment(fileLength + 8);	//+8 for the 4 byte DBM header and INT32 file size.
			
			dynbitmapTransform.resetIndex();
			DynamixBitmap dbm = (DynamixBitmap)dynbitmapTransform.bytesToObject(dbaItem);
			
			dbm.setFileName("_" + imageCount);
			
			images[imageCount] = dbm;
			imageCount++;
			
			if(index+1 < actualBytes) {
				byte space = indexByte();
				if(space != 0x00) {
					index = index - 1;
				}	
			}
		}
		dba.setImages(images);
		
		return dba;
	}

	@Override
	public byte[] objectToBytes(DataFile source) throws ClassCastException, IOException {
		
		if(source == null) {
			//TODO log null
			return null;
		}
		
		DynamixBitmapArray dba = (DynamixBitmapArray)source;
		
		ByteArrayOutputStream objectBytes = new ByteArrayOutputStream();
		
		objectBytes.write(DynamixBitmapArray.header.array());
		objectBytes.write(dba.getFileSize().reverse().array());
		objectBytes.write(writeShortLE(dba.getArrayRow()));
		objectBytes.write(writeShortLE(dba.getArrayCols()));

		DynamixBitmapTransformer dbmConvert = new DynamixBitmapTransformer();
		for(DynamixBitmap dbm : dba.getImages()) {
			
			dbmConvert.resetIndex();
			byte[] data = dbmConvert.objectToBytes(dbm);
			if(data != null) {
				objectBytes.write(data);
				objectBytes.write(0x00);
				objectBytes.flush();
			}
		}
		
		return objectBytes.toByteArray();
	}
}
