package org.hercworks.core.io.transform.dbsim;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.hercworks.core.data.file.dbsim.Vue;
import org.hercworks.core.data.file.dbsim.Vue.Entry;
import org.hercworks.core.io.transform.ThreeSpaceByteTransformer;
import org.hercworks.voln.DataFile;
import org.hercworks.voln.FileType;

public class VueTransformer extends ThreeSpaceByteTransformer {

	@Override
	public DataFile bytesToObject(byte[] inputArray) throws ClassCastException {
		if(inputArray == null || inputArray.length <= 0) {
			return null;
		}
		setBytes(inputArray);
		
		Vue data = new Vue();
		data.setExt(FileType.VUE);
		data.setDir(FileType.VUE);
		data.setRawBytes(inputArray);
		
		data.setTotalViewports(indexIntLE());
		data.setEntries(new Entry[data.getTotalViewports()]);
		
		for(int i=0; i < data.getTotalViewports(); i++) {
			Entry entry = data.newEntry();
			
			entry.setOriginX(indexIntLE());
			entry.setOriginY(indexIntLE());
			entry.setWidthMax(indexIntLE());
			entry.setHeightMax(indexIntLE());
			
			entry.setUnkOfsX(indexIntLE());
			entry.setUnkOfsY(indexIntLE());
			entry.setUnkOfsW(indexIntLE());
			entry.setUnkOfsH(indexIntLE());
			
			data.getEntries()[i] = entry;
		}
		
		return data;
	}

	@Override
	public byte[] objectToBytes(DataFile source) throws ClassCastException, IOException {

		Vue data = (Vue)source;
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		out.write(writeIntLE(data.getTotalViewports()));
		
		for(int i=0; i < data.getTotalViewports(); i++) {
			Entry entry = data.getEntries()[i];
			out.write(writeIntLE(entry.getOriginX()));
			out.write(writeIntLE(entry.getOriginY()));
			out.write(writeIntLE(entry.getWidthMax()));
			out.write(writeIntLE(entry.getHeightMax()));
			out.write(writeIntLE(entry.getUnkOfsX()));
			out.write(writeIntLE(entry.getUnkOfsY()));
			out.write(writeIntLE(entry.getUnkOfsW()));
			out.write(writeIntLE(entry.getUnkOfsH()));
		}
		
		return out.toByteArray();
	}

}
