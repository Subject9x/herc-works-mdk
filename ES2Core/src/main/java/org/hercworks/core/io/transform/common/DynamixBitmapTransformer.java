package org.hercworks.core.io.transform.common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.hercworks.core.data.file.dyn.DynamixBitmap;
import org.hercworks.core.io.transform.ThreeSpaceByteTransformer;
import org.hercworks.voln.DataFile;
import org.hercworks.voln.FileType;

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
		dbm.setExt(FileType.DBM);
		dbm.setDir(FileType.DBM);
		
		dbm.setHeader(indexSegment(4));
		dbm.setFileSize(Bytes.from(indexSegmentLE(4)));
		dbm.setRows(indexShortLE());
		dbm.setCols(indexShortLE());
		dbm.setBitDepth(indexShortLE());
		dbm.setUnkSpacer1(indexByte());
		dbm.setImageDataLen(indexIntLE());
		dbm.setUnkSpacer2(indexShortLE());
		dbm.setImageData(Bytes.from(indexSegment(dbm.getImageDataLen())));
		
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
		
		int size = dbm.getImageData().array().length + 13;
		
		objectBytes.write(writeIntLE(size));
		objectBytes.write(writeShortLE(dbm.getRows()));
		objectBytes.write(writeShortLE(dbm.getCols()));
		objectBytes.write(writeShortLE(dbm.getBitDepth()));
		objectBytes.write(dbm.getUnkSpacer1());
		objectBytes.write(writeIntLE(dbm.getImageDataLen()));
		objectBytes.write(writeShortLE(dbm.getUnkSpacer2()));
		objectBytes.write(dbm.getImageData().array());
		
		return objectBytes.toByteArray();
	}

}
