package org.hercworks.core.io.transform.common;

import java.io.IOException;

import org.hercworks.core.data.file.msn.MissionStringFile;
import org.hercworks.core.data.file.msn.MissionStringFile.StringEntry;
import org.hercworks.core.io.transform.ThreeSpaceByteTransformer;
import org.hercworks.voln.DataFile;

import at.favre.lib.bytes.Bytes;

public class MissionStringFileTransformer extends ThreeSpaceByteTransformer {

	@Override
	public DataFile bytesToObject(byte[] inputArray) throws ClassCastException {
		
		
		setBytes(inputArray);
		
		MissionStringFile str = new MissionStringFile();
		
		StringEntry[] entries = new StringEntry[indexShortLE()];
		
		for(int i=0; i < entries.length; i++) {
			
			short guid = indexShortLE();
			short rval = indexShortLE();
			short rflag = indexShortLE();
			short len = indexShortLE();
			
			StringEntry ent = str.createEntry(guid, rval, rflag, len, String.valueOf(Bytes.from(indexSegment(len)).toCharArray()));
			
			System.out.println(ent.toString());
			entries[i] = ent;
		}
		str.setStrings(entries);
		
		
		return str;
	}

	@Override
	public byte[] objectToBytes(DataFile source) throws ClassCastException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

}
