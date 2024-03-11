package com.mech.works.io.transform.common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteOrder;
import java.util.LinkedHashSet;

import com.mech.works.data.file.dyn.DynamixBitmap;
import com.mech.works.data.file.dyn.DynamixBitmapArray;
import com.mech.works.data.ref.files.DataFile;
import com.mech.works.io.transform.ThreeSpaceByteTransformer;

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
		dba.setImages(new LinkedHashSet<DynamixBitmap>());
		
		dba.setHeader(indexSegment(4));
		dba.setFileSize(Bytes.from(indexSegmentLE(4)));
		dba.setArrayRow(indexShortLE());
		dba.setArrayCols(indexShortLE());
		
		int imageCount = 0;
		
		DynamixBitmapTransformer dynbitmapTransform = new DynamixBitmapTransformer();
		
		for(int i=index; i < bytes.length-index; i++) {
			int imgByteLen = Bytes.from(bytes, i+15, 4).byteOrder(ByteOrder.LITTLE_ENDIAN).toInt();
			int fileChunk = 21 + imgByteLen;
			
			Bytes dbaItem = Bytes.from(bytes, i, fileChunk);
			
			dynbitmapTransform.resetIndex();
			DynamixBitmap dbm = (DynamixBitmap)dynbitmapTransform.bytesToObject(dbaItem.array());
			
			i += dbm.getRawBytes().length;	//accounts for the 7 bytes of meta data AFTER file size in bytes.
			
			System.out.println("IMAGE:"+imageCount+"-------------");
			System.out.println("Width:" + dbm.getCols());
			System.out.println("Height:" + dbm.getRows());
			System.out.println("imgByteLen:" + imgByteLen);
			dbm.setFileName(imageCount+"_");
			
			dba.getImages().add(dbm);
			imageCount++;
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
