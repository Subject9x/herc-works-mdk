package org.hercworks.core.io.transform.shell;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.hercworks.core.data.file.dat.shell.TrainingHercs;
import org.hercworks.core.data.struct.vshell.hercs.ShellHercData;
import org.hercworks.core.data.struct.vshell.hercs.UiWeaponEntry;
import org.hercworks.core.io.transform.ThreeSpaceByteTransformer;
import org.hercworks.voln.DataFile;

public class TrainingHercsTransform extends ThreeSpaceByteTransformer{

	public TrainingHercsTransform() {}
	
	@Override
	public DataFile bytesToObject(byte[] inputArray) throws ClassCastException {
		if(inputArray == null || inputArray.length <= 0) {
			//TODO - warn empty array
			return null;
		}
		setBytes(inputArray);
		
		TrainingHercs training = new TrainingHercs();
		
		training.setData(new ArrayList<ShellHercData>());
		
		while(index < bytes.length) {
			ShellHercData herc = new ShellHercData();
			herc.setHercId(indexShortLE());
			herc.setHealthRatio(indexShortLE());
			herc.setBuildCompleteLevel(indexShortLE());
			herc.setHardpoints(new LinkedHashMap<Short, UiWeaponEntry>());
			int activeHardpoints = (int)indexShortLE();
			
			for(int h=0; h < activeHardpoints; h++) {
				UiWeaponEntry entry = new UiWeaponEntry();
				short id = indexShortLE();
				entry.setItemId(indexShortLE());
				entry.setHealthPercent(indexShortLE());
				entry.setMissileEnum(indexShortLE());
				herc.getHardpoints().put(id, entry);
			}
			training.getData().add(herc);
		}
		return training;
	}

	@Override
	public byte[] objectToBytes(DataFile source) throws ClassCastException, IOException {
		
		TrainingHercs training = (TrainingHercs)source;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		for(ShellHercData herc : training.getData()) {
			out.write(writeShortLE(herc.getHercId()));
			out.write(writeShortLE(herc.getHealthRatio()));
			out.write(writeShortLE(herc.getBuildCompleteLevel()));
			out.write(writeShortLE((short)herc.getHardpoints().size()));
			
			for(Short id : herc.getHardpoints().keySet()) {
				UiWeaponEntry entry = herc.getHardpoints().get(id);
				out.write(writeShortLE(id));
				out.write(writeShortLE(entry.getItemId()));
				out.write(writeShortLE(entry.getHealthPercent()));
				out.write(writeShortLE(entry.getMissileEnum()));
			}
		}
		
		return out.toByteArray();
	}
}
