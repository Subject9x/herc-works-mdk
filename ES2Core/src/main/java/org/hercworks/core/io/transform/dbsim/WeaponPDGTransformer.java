package org.hercworks.core.io.transform.dbsim;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.hercworks.core.data.file.dbsim.WeaponPaperDiagram;
import org.hercworks.core.io.transform.ThreeSpaceByteTransformer;
import org.hercworks.voln.DataFile;
import org.hercworks.voln.FileType;

public class WeaponPDGTransformer extends ThreeSpaceByteTransformer {

	@Override
	public DataFile bytesToObject(byte[] inputArray) throws ClassCastException {
		index = 0;
		
		if(inputArray == null || inputArray.length <= 0) {
			//TODO - null input
			return null;
		}
		
		WeaponPaperDiagram data = new WeaponPaperDiagram();
		data.setFileName("WEAPONS");
		data.setExt(FileType.PDG);
		data.setDir(FileType.PDG);
		data.setRawBytes(inputArray);
		
		WeaponPaperDiagram.Entry[] entries = new WeaponPaperDiagram.Entry[indexIntLE()];
		
		for(int i = 0; i < entries.length; i++) {
			
			WeaponPaperDiagram.Entry entry = data.newEntry();
			entry.setX(indexIntLE());
			entry.setY(indexIntLE());
			
			entries[i] = entry;
		}
		data.setEntries(entries);
		
		
		return data;
	}

	@Override
	public byte[] objectToBytes(DataFile source) throws ClassCastException, IOException {
		
		WeaponPaperDiagram data = (WeaponPaperDiagram)source;
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		out.write(writeIntLE(data.getEntries().length));
		
		for(int i = 0; i < data.getEntries().length; i++) {
			out.write(writeIntLE(data.getEntries()[i].getX()));
			out.write(writeIntLE(data.getEntries()[i].getY()));
		}
		
		return out.toByteArray();
	}

}
