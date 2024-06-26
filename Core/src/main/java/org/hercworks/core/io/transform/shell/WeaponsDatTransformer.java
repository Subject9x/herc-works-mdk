package org.hercworks.core.io.transform.shell;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.hercworks.core.data.file.dat.shell.WeaponsDat;
import org.hercworks.core.data.struct.MissileType;
import org.hercworks.core.data.struct.vshell.hercs.UiWeaponEntry;
import org.hercworks.core.io.transform.ThreeSpaceByteTransformer;
import org.hercworks.voln.DataFile;
import org.hercworks.voln.FileType;

public class WeaponsDatTransformer extends ThreeSpaceByteTransformer{

	public WeaponsDatTransformer() {}
	
	@Override
	public DataFile bytesToObject(byte[] inputArray) throws ClassCastException {
		
		if(inputArray == null || inputArray.length <= 0) {
			//TODO - empty array warning
			return null;
		}
		
		setBytes(inputArray);
		
		short totalWeapons = indexShortLE();
		
		WeaponsDat data = new WeaponsDat(totalWeapons);
		data.setFileName("WEAPONS");
		data.setRawBytes(inputArray);
		data.setExt(FileType.DAT);
		data.setDir(FileType.GAM);
		
		for(int i=0; i < totalWeapons; i++) {
			WeaponsDat.Entry entry = data.addEntry(i);
			entry.setId(indexShortLE());
			entry.setNameLen(indexShortLE());
			entry.setName(indexSegment(entry.getNameLen()));
			entry.setSalvageCost(indexShortLE());
			entry.setStartUnlock(indexByte());
			entry.setAutobuildPriority(indexShortLE());
		}
		
		data.setStartWeaponTotal(indexShortLE());
		data.setStartingWeapons(new UiWeaponEntry[data.getStartWeaponTotal()]);
		
		for(int i=0; i < data.getStartWeaponTotal(); i++) {
			UiWeaponEntry item = new UiWeaponEntry();
			item.setItemId(indexShortLE());
			item.setHealthPercent(indexShortLE());
			item.setMissileType(MissileType.getById((int)indexShortLE()));
			data.getStartingWeapons()[i] = item;
		}
		
		return data;
	}

	@Override
	public byte[] objectToBytes(DataFile source) throws ClassCastException, IOException {
		
		ByteArrayOutputStream objectBytes = new ByteArrayOutputStream();
		
		WeaponsDat data = (WeaponsDat)source;
		
		objectBytes.write(writeShortLE(data.getTotalCount()));
		for(int i=0; i < data.getTotalCount(); i++) {
			WeaponsDat.Entry entry = data.getData()[i];
			
			objectBytes.write(writeShortLE(entry.getId()));
			objectBytes.write(writeShortLE(entry.getNameLen()));
			objectBytes.write(entry.getName());
			objectBytes.write(writeShortLE(entry.getSalvageCost()));
			objectBytes.write(entry.getStartUnlock());
			objectBytes.write(writeShortLE(entry.getAutobuildPriority()));
		}
		
		objectBytes.write(writeShortLE(data.getStartWeaponTotal()));
		for(int i=0; i < data.getStartWeaponTotal(); i++) {
			UiWeaponEntry item = data.getStartingWeapons()[i];
			objectBytes.write(writeShortLE(item.getItemId()));
			objectBytes.write(writeShortLE(item.getHealthPercent()));
			objectBytes.write(writeShortLE((short)item.getMissileType().getId()));
		}
		
		return objectBytes.toByteArray();
	}
}
