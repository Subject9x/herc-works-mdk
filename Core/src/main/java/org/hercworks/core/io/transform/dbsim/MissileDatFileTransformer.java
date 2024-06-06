package org.hercworks.core.io.transform.dbsim;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.hercworks.core.data.file.dat.sim.MissileDatFile;
import org.hercworks.core.data.struct.dbsim.ProjMissileDatEntry;
import org.hercworks.core.io.transform.ThreeSpaceByteTransformer;
import org.hercworks.voln.DataFile;
import org.hercworks.voln.FileType;

public class MissileDatFileTransformer extends ThreeSpaceByteTransformer {

	@Override
	public DataFile bytesToObject(byte[] inputArray) throws ClassCastException {
		if(inputArray == null || inputArray.length <= 0) {
			return null;
		}
		setBytes(inputArray);
		
		short total = indexShortLE();
		
		MissileDatFile data = new MissileDatFile(total);
		data.setExt(FileType.DAT);
		data.setDir(FileType.DAT);
		data.setRawBytes(inputArray);
		
		for(int b=0; b < total; b++) {
			ProjMissileDatEntry bullet = new ProjMissileDatEntry();
			
			bullet.setModelId(indexShortLE());
			bullet.setLifetime(indexShortLE());
			bullet.setClipRadius(indexShortLE());
			bullet.setUnk2_flag(indexShortLE());
			bullet.setSfxFireIdBullets(indexShortLE());
			bullet.setUnk3_uint16(indexShortLE());
			bullet.setSfxFireIdMissiles(indexShortLE());
			
			data.getEntries()[b] = bullet;
		}
		
		return data;
	}

	@Override
	public byte[] objectToBytes(DataFile source) throws ClassCastException, IOException {
		if(source == null) {
			//TODO log null
			return null;
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		MissileDatFile data = (MissileDatFile)source;
		
		out.write(writeShortLE((short)data.getEntries().length));
		
		for(int b=0; b < data.getEntries().length; b++) {
			ProjMissileDatEntry bullet = data.getEntries()[b];
			
			out.write(writeShortLE(bullet.getModelId()));
			out.write(writeShortLE(bullet.getLifetime()));
			out.write(writeShortLE(bullet.getClipRadius()));
			out.write(writeShortLE(bullet.getUnk2_flag()));
			out.write(writeShortLE(bullet.getSfxFireIdBullets()));
			out.write(writeShortLE(bullet.getUnk3_uint16()));
			out.write(writeShortLE(bullet.getSfxFireIdMissiles()));
		}
		
		return out.toByteArray();
	}
}
