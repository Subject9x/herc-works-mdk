package com.mech.works.io.transform.common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.mech.works.data.file.dyn.DynamixBitmap;
import com.mech.works.data.ref.files.DataFile;
import com.mech.works.io.transform.ThreeSpaceByteTransformer;
import com.mech.works.vol.data.Voln;

import at.favre.lib.bytes.Bytes;

/**
 * Transforms byete[] data to and from {@linkplain DynamixBitmap} game files.
 * 
 */
public class DynamixBitmapTransformer extends ThreeSpaceByteTransformer{

	public DynamixBitmapTransformer() {}
	
	@Override
	public DataFile bytesToObject(byte[] inputArray) throws ClassCastException {
		
		if(inputArray == null || inputArray.length <= 0) {
			//TODO - log null
			return null;
		}
		setBytes(inputArray);
		DynamixBitmap dbm = new DynamixBitmap();
		dbm.setRawBytes(Bytes.from(inputArray).array());
		dbm.setExt(Voln.FileType.DBM);
		
		dbm.setHeader(indexSegment(4));
		dbm.setFileSize(Bytes.from(indexSegmentLE(4)));
		dbm.setRows(indexShortLE());
		dbm.setCols(indexShortLE());
		dbm.setBitDepth(indexShortLE());
		
		index+=1;
		
		int byteLen = indexIntLE();
		
		index+=2;
		
		dbm.setImageData(Bytes.from(indexSegment(byteLen)));
		
		return dbm;
	}

	@Override
	public byte[] objectToBytes(DataFile source) throws ClassCastException, IOException {
		
		if(source == null) {
			//TODO - null file check
			return null;
		}
		
		DynamixBitmap dbm = (DynamixBitmap)source;
		
		ByteArrayOutputStream objectBytes = new ByteArrayOutputStream();
		
		objectBytes.write(DynamixBitmap.header.array());
		objectBytes.write(dbm.getFileSize().array());
		objectBytes.write(writeShortLE(dbm.getRows()));
		objectBytes.write(writeShortLE(dbm.getCols()));
		objectBytes.write(writeShortLE(dbm.getBitDepth()));
		
		objectBytes.write((byte)0x00);
		
		objectBytes.write(writeIntLE(dbm.getImageData().array().length));
		
		objectBytes.write((byte)0x00);
		objectBytes.write((byte)0x00);
		
		objectBytes.write(dbm.getImageData().array());
		
		return objectBytes.toByteArray();
	}

}
