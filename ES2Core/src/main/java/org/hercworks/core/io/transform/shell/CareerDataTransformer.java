package org.hercworks.core.io.transform.shell;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;

import org.hercworks.core.data.file.dat.shell.CareerMissions;
import org.hercworks.core.data.struct.MissionSector;
import org.hercworks.core.io.transform.ThreeSpaceByteTransformer;
import org.hercworks.voln.DataFile;
import org.hercworks.voln.FileType;

public class CareerDataTransformer extends ThreeSpaceByteTransformer {

	@Override
	public DataFile bytesToObject(byte[] inputArray) throws ClassCastException {
		
		if(inputArray == null || inputArray.length <= 0) {
			return null;
		}
		
		setBytes(inputArray);
		
		CareerMissions data = new CareerMissions();
		data.setFileName("CAREER");
		data.setExt(FileType.DAT);
		data.setDir(FileType.GAM);
		
		int totalSectors = (int)indexShortLE();
		
		LinkedHashMap<MissionSector, int[]> sectors = new LinkedHashMap<MissionSector, int[]>();
		
		for(int s=0; s < totalSectors; s++) {
			MissionSector sec = MissionSector.getById((int)indexShortLE());
			int[] missions = new int[indexShortLE()];
			
			for(int i=0; i < missions.length; i++) {
				missions[i] = Integer.valueOf((int)indexShortLE());
			}
			
			sectors.put(sec, missions);
		}
		data.setSectors(sectors);
		
		
		return data;
	}

	@Override
	public byte[] objectToBytes(DataFile source) throws ClassCastException, IOException {
		CareerMissions data = (CareerMissions)source;
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		out.write(writeShortLE((short)data.getSectors().values().size()));
		
		for(MissionSector sector : data.getSectors().keySet()) {
			
			out.write(writeShortLE(sector.id()));
			
			int[] missions = data.getSectors().get(sector);
			
			for(int i=0; i < missions.length; i++) {
				missions[i] = Integer.valueOf((int)indexShortLE());
				out.write(writeShortLE((short)missions[i]));
			}
		}
		
		return out.toByteArray();
	}
}
