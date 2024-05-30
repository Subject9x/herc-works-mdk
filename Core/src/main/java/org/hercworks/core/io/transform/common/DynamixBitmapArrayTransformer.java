package org.hercworks.core.io.transform.common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteOrder;
import java.util.LinkedHashSet;

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
		
		dba.setImages(new LinkedHashSet<DynamixBitmap>());
		
		dba.setHeader(indexSegment(4));
		dba.setFileSize(Bytes.from(indexSegmentLE(4)));
		dba.setArrayRow(indexShortLE());
		dba.setArrayCols(indexShortLE());
		
		int imageCount = 0;
		
		DynamixBitmapTransformer dynbitmapTransform = new DynamixBitmapTransformer();
		
		int actualBytes = bytes.length - index;
		while(index < actualBytes) {
			int fileLength = Bytes.from(bytes, index+4, 4).byteOrder(ByteOrder.LITTLE_ENDIAN).toInt();
			
			byte[] dbaItem = indexSegment(fileLength + 8);	//4 for header, 4 for total file len.
			
			
			dynbitmapTransform.resetIndex();
			DynamixBitmap dbm = (DynamixBitmap)dynbitmapTransform.bytesToObject(dbaItem);
			
			System.out.println("IMAGE:"+imageCount+"-------------");
			System.out.println("");
			System.out.println("Width:" + dbm.getCols());
			System.out.println("Height:" + dbm.getRows());
			System.out.println("imgByteLen:" + fileLength);
			dbm.setFileName("_"+imageCount);
			
			dba.getImages().add(dbm);
			imageCount++;
			
			if(index+1 < actualBytes) {
				byte space = indexByte();
				if(space != 0x00) {
					index = index - 1;
				}	
			}
		}
		
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
		objectBytes.write(dba.getFileSize().array());
		objectBytes.write(writeShort(dba.getArrayRow()));
		objectBytes.write(writeShort(dba.getArrayCols()));
		
		DynamixBitmapTransformer dbmConvert = new DynamixBitmapTransformer();
		for(DynamixBitmap dbm : dba.getImages()) {
			
			byte[] bytes = dbmConvert.objectToBytes(dbm);
			objectBytes.write(bytes);
			dbmConvert.resetIndex();
		}
		
		
		return objectBytes.toByteArray();
	}

}
