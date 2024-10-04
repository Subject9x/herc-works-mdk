package org.hercworks.core.io.transform.dbsim;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.hercworks.core.data.file.dat.sim.BeamData;
import org.hercworks.core.data.file.dat.sim.BeamData.Entry;
import org.hercworks.core.io.transform.ThreeSpaceByteTransformer;
import org.hercworks.voln.DataFile;
import org.hercworks.voln.FileType;

public class BeamDatFileTransformer extends ThreeSpaceByteTransformer {

	@Override
	public DataFile bytesToObject(byte[] inputArray) throws ClassCastException {
		if(inputArray == null || inputArray.length <= 0) {
			return null;
		}
		
		setBytes(inputArray);
		
		BeamData data = new BeamData((short)indexShortLE());
		data.setExt(FileType.DAT);
		data.setDir(FileType.DAT);
		data.setRawBytes(inputArray);
		
		for(int b=0; b < data.getData().length; b++) {
			Entry beam = data.newEntry(indexShortLE(), indexShortLE());
			
			data.getData()[b] = beam;
		}
		
		return data;
	}

	@Override
	public byte[] objectToBytes(DataFile source) throws ClassCastException, IOException {
		
		if(source == null) {
			return null;
		}
		
		BeamData data = (BeamData)source;
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		out.write(writeShortLE((short)data.getData().length));
		
		for(int b=0; b < data.getData().length; b++) {
			Entry beam = data.getData()[b];
			
			out.write(writeShortLE(beam.getWidth()));
			out.write(writeShortLE(beam.getColorId()));
		}
		
		return out.toByteArray();
	}
}
