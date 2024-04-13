package org.hercworks.core.io.transform.shell;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.hercworks.core.data.file.dat.shell.HercInf;
import org.hercworks.core.data.struct.vshell.hercs.HercInfEntry;
import org.hercworks.core.io.transform.ThreeSpaceByteTransformer;
import org.hercworks.voln.DataFile;
import org.hercworks.voln.FileType;

public class HercInfoTransformer extends ThreeSpaceByteTransformer{

	public HercInfoTransformer() {}
	
	@Override
	public DataFile bytesToObject(byte[] inputArray) throws ClassCastException {
	
		if(inputArray == null || inputArray.length <= 0) {
			//TODO - null array error
			return null;
		}
		
		setBytes(inputArray);
		short totalHercs = indexShortLE();
		
		HercInf hercInfo = new HercInf(totalHercs);
		hercInfo.setRawBytes(inputArray);
		hercInfo.setExt(FileType.DAT);
		
		for(short i=0; i < totalHercs; i+=(short)1) {
			HercInfEntry item = new HercInfEntry();
			item.setHercId(indexShortLE());
			item.setWeight(indexShortLE());
			item.setSpeed(indexShortLE());
			item.setHardpointTotal(indexShortLE());
			item.setSalvageReq(indexShortLE());
			item.setUnknownFlag(indexShortLE());
			item.setBuildMissionCount(indexShortLE());
			item.setFlagCampaignStart(indexShortLE());
			hercInfo.getData()[i] = item;
		}
		
		return hercInfo;
	}

	@Override
	public byte[] objectToBytes(DataFile source) throws ClassCastException, IOException {
		
		HercInf data = (HercInf)source;
		
		ByteArrayOutputStream objectBytes = new ByteArrayOutputStream();
		
		objectBytes.write(writeShort(data.getTotalHercs()));
		
		for(HercInfEntry entry : data.getData()) {
			objectBytes.write(writeShort(entry.getHercId()));
			objectBytes.write(writeShortLE(entry.getWeight()));
			objectBytes.write(writeShortLE(entry.getSpeed()));
			objectBytes.write(writeShortLE(entry.getHardpointTotal()));
			objectBytes.write(writeShortLE(entry.getSalvageReq()));
			objectBytes.write(writeShortLE(entry.getUnknownFlag()));
			objectBytes.write(writeShortLE(entry.getBuildMissionCount()));
			objectBytes.write(writeShortLE(entry.getFlagCampaignStart()));
		}
		return objectBytes.toByteArray();
	}
}
