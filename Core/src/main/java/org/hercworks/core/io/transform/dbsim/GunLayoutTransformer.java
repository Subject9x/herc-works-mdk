package org.hercworks.core.io.transform.dbsim;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.hercworks.core.data.file.dbsim.GunLayout;
import org.hercworks.core.data.file.dbsim.GunLayout.HardpointEntry;
import org.hercworks.core.io.transform.ThreeSpaceByteTransformer;
import org.hercworks.voln.DataFile;
import org.hercworks.voln.FileType;

public class GunLayoutTransformer extends ThreeSpaceByteTransformer{

	@Override
	public DataFile bytesToObject(byte[] inputArray) throws ClassCastException {
		
		if(inputArray == null || inputArray.length <= 0) {
			return null;
		}
		setBytes(inputArray);
		
		GunLayout data = new GunLayout(indexShortLE());
		data.setRawBytes(inputArray);
		data.setExt(FileType.GL);
		data.setDir(FileType.GL);
		
		for(int i=0; i < data.getTotalGuns(); i++) {
			HardpointEntry entry = data.newEntry();
			
			entry.setBoneId(indexShortLE());
			entry.setUnk1_val(indexShortLE());
			entry.setUnk2_val(indexShortLE());
			entry.setAngleDirOption(indexByte());
			entry.setFireChainNumber(indexByte());
			entry.setUnk3_0or_Neg5000(indexShortLE());
			entry.setUnk4_0or_5000(indexShortLE());
			entry.setUnk5_Neg8000(indexShortLE());
			entry.setUnk6_16000(indexShortLE());
			entry.getOffset()[0] = indexShortLE();
			entry.getOffset()[1] = indexShortLE();
			entry.getOffset()[2] = indexShortLE();
			entry.setUnk7_val(indexByte());
			entry.setHardpointId(indexByte());
			entry.setUnk8_val(indexShortLE());
			
			data.getHardpoints()[i] = entry;
		}
		
		return data;
	}

	@Override
	public byte[] objectToBytes(DataFile source) throws ClassCastException, IOException {
		
		GunLayout src = (GunLayout)source;
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		out.write(writeShortLE(src.getTotalGuns()));
		
		if(src.getHardpoints() != null) {
			for(int i=0; i < src.getTotalGuns(); i++) {
				HardpointEntry entry = src.getHardpoints()[i];
				
				out.write(writeShortLE(entry.getBoneId()));
				out.write(writeShortLE(entry.getUnk1_val()));
				out.write(writeShortLE(entry.getUnk2_val()));
				out.write(entry.getAngleDirOption());
				out.write(entry.getFireChainNumber());
				out.write(writeShortLE(entry.getUnk3_0or_Neg5000()));
				out.write(writeShortLE(entry.getUnk4_0or_5000()));
				out.write(writeShortLE(entry.getUnk5_Neg8000()));
				out.write(writeShortLE(entry.getUnk6_16000()));
				out.write(writeShortLE(entry.getOffset()[0]));
				out.write(writeShortLE(entry.getOffset()[1]));
				out.write(writeShortLE(entry.getOffset()[2]));
				out.write(entry.getUnk7_val());
				out.write(entry.getHardpointId());
				out.write(entry.getUnk8_val());
			}
		}
		
		return out.toByteArray();
	}

}
