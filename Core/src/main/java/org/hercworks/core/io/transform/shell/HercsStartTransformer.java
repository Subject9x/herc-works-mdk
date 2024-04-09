package org.hercworks.core.io.transform.shell;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;

import org.hercworks.core.data.file.dat.shell.Hercs;
import org.hercworks.core.data.struct.vshell.hercs.UiWeaponEntry;
import org.hercworks.core.io.transform.ThreeSpaceByteTransformer;
import org.hercworks.voln.DataFile;

public class HercsStartTransformer extends ThreeSpaceByteTransformer{

	@Override
	public DataFile bytesToObject(byte[] inputArray) throws ClassCastException {
		
		if(inputArray == null || inputArray.length <= 0) {
			//TODO - warn empty array
			return null;
		}
		setBytes(inputArray);
		
		Hercs startHercs = new Hercs();
		
		startHercs.setTotal(indexShortLE());
		startHercs.setData(new Hercs.Entry[startHercs.getTotal()]);
		
		for(int i=0; i < (int)startHercs.getTotal(); i++) {
			Hercs.Entry entry = startHercs.addEntry();
			entry.setBayId(indexShortLE());
			entry.setHercId(indexShortLE());
			entry.setHealthRatio(indexShortLE());
			entry.setBuildCompleteLevel(indexShortLE());
			entry.setHardpointCount(indexShortLE());
			
			entry.setData(new LinkedHashMap<Short, UiWeaponEntry>());
			for(int h=0; h < (int)entry.getHardpointCount(); h++) {
				UiWeaponEntry item = new UiWeaponEntry();
				short hardpointId = indexShortLE();
				item.setItemId(indexShortLE());
				item.setHealthPercent(indexShortLE());
				item.setMissileEnum(indexShortLE());
				entry.getData().put(hardpointId, item);
			}
			startHercs.getData()[(short)i] = entry;
		}
		
		return startHercs;
	}

	@Override
	public byte[] objectToBytes(DataFile source) throws ClassCastException, IOException {
		
		ByteArrayOutputStream object = new ByteArrayOutputStream();
		
		Hercs hercs = (Hercs)source;
		
		object.write(writeShort(hercs.getTotal()));
		for(int i=0; i < (int)hercs.getTotal(); i++) {
			Hercs.Entry entry = hercs.addEntry();
			
			object.write(writeShort(entry.getBayId()));
			object.write(writeShort(entry.getHercId()));
			object.write(writeShort(entry.getHealthRatio()));
			object.write(writeShort(entry.getBuildCompleteLevel()));
			object.write(writeShort(entry.getHardpointCount()));
			
			for(int h=0; h < (int)entry.getHardpointCount(); h++) {
				UiWeaponEntry item = new UiWeaponEntry();
				object.write(writeShort((short)h));
				object.write(writeShort(item.getItemId()));
				object.write(writeShort(item.getHealthPercent()));
				object.write(writeShort(item.getMissileEnum()));
			}
		}
		
		return object.toByteArray();
	}

}
