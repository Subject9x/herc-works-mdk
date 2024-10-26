package org.hercworks.core.io.transform.dbsim;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.hercworks.core.data.file.dat.sim.DebrisHerc;
import org.hercworks.core.io.transform.ThreeSpaceByteTransformer;
import org.hercworks.voln.DataFile;
import org.hercworks.voln.FileType;

public class DebrisHercTransformer extends ThreeSpaceByteTransformer {

	@Override
	public DataFile bytesToObject(byte[] inputArray) throws ClassCastException {
		
		if(inputArray == null) {
			return null;
		}
		
		setBytes(inputArray);
		
		DebrisHerc debris = new DebrisHerc();
		debris.setDir(FileType.DAT);
		debris.setExt(FileType.DAT);
		
		DebrisHerc.Entry[] entries = new DebrisHerc.Entry[indexShortLE()];
		
		for(int i = 0; i < entries.length; i++) {
			DebrisHerc.Entry entry = debris.newEntry();

			entry.setUnk1_val(indexShortLE());
			entry.setSpawnDebrisFlag(indexShortLE());
			entry.setMeshGroupId(indexShortLE());
			entry.setUnk4_0A(indexShortLE());
			entry.setUnk5_03(indexShortLE());
			entry.getThrowDir()[0] = indexShortLE();
			entry.getThrowDir()[1] = indexShortLE();
			entry.getThrowDir()[2] = indexShortLE();
			entry.setMass(indexShortLE());
			
			entries[i] = entry;
		}
		
		debris.setData(entries);
		
		return debris;
	}

	@Override
	public byte[] objectToBytes(DataFile source) throws ClassCastException, IOException {
	
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		DebrisHerc data = (DebrisHerc)source;
		
		out.write(writeShortLE((short)data.getData().length));
		
		for(int i = 0; i < data.getData().length; i++) {
			DebrisHerc.Entry entry = data.getData()[i];
			
			out.write(writeShortLE(entry.getUnk1_val()));
			out.write(writeShortLE(entry.setSpawnDebrisFlag()));
			out.write(writeShortLE(entry.getMeshGroupId()));
			out.write(writeShortLE(entry.getUnk4_0A()));
			out.write(writeShortLE(entry.getUnk5_03()));
			out.write(writeShortLE(entry.getThrowDir()[0]));
			out.write(writeShortLE(entry.getThrowDir()[1]));
			out.write(writeShortLE(entry.getThrowDir()[2]));
			out.write(writeShortLE(entry.getMass()));
		}
		
		return out.toByteArray();
	}

}
