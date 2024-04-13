package org.hercworks.core.io.transform.shell;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;

import org.hercworks.core.data.file.dat.shell.Hercs;
import org.hercworks.core.data.struct.vshell.hercs.ShellHercData;
import org.hercworks.core.data.struct.vshell.hercs.UiWeaponEntry;
import org.hercworks.core.io.transform.ThreeSpaceByteTransformer;
import org.hercworks.voln.DataFile;
import org.hercworks.voln.FileType;

public class HercsStartTransformer extends ThreeSpaceByteTransformer{

	public HercsStartTransformer() {}
	
	@Override
	public DataFile bytesToObject(byte[] inputArray) throws ClassCastException {
		
		if(inputArray == null || inputArray.length <= 0) {
			//TODO - warn empty array
			return null;
		}
		setBytes(inputArray);
		
		Hercs startHercs = new Hercs();
		startHercs.setExt(FileType.DAT);
		startHercs.setData(new Hercs.Entry[indexShortLE()]);
		
		for(int i=0; i < startHercs.getData().length; i++) {
			Hercs.Entry entry = startHercs.addEntry();
			entry.setHerc(new ShellHercData());
			
			entry.setBayId(indexShortLE());
			entry.getHerc().setHercId(indexShortLE());
			entry.getHerc().setHealthRatio(indexShortLE());
			entry.getHerc().setBuildCompleteLevel(indexShortLE());
			
			short hardpointCount = indexShortLE();
			entry.getHerc().setHardpoints(new LinkedHashMap<Short, UiWeaponEntry>());
			
			for(int h=0; h < (int)hardpointCount; h++) {
				UiWeaponEntry item = new UiWeaponEntry();
				short hardpointId = indexShort();
				item.setItemId(indexShortLE());
				item.setHealthPercent(indexShortLE());
				item.setMissileEnum(indexShortLE());
				entry.getHerc().getHardpoints().put(hardpointId, item);
			}
			startHercs.getData()[(short)i] = entry;
		}
		
		return startHercs;
	}

	@Override
	public byte[] objectToBytes(DataFile source) throws ClassCastException, IOException {
		
		ByteArrayOutputStream object = new ByteArrayOutputStream();
		
		Hercs hercs = (Hercs)source;
		
		object.write(writeShortLE((short)hercs.getData().length));
		for(int i=0; i < hercs.getData().length; i++) {
			Hercs.Entry entry = hercs.getData()[i];
			
			object.write(writeShortLE(entry.getBayId()));
			object.write(writeShortLE(entry.getHerc().getHercId()));
			object.write(writeShortLE(entry.getHerc().getHealthRatio()));
			object.write(writeShortLE(entry.getHerc().getBuildCompleteLevel()));
			object.write(writeShortLE((short)entry.getHerc().getHardpoints().size()));
			
			for(int h=0; h < (int)entry.getHerc().getHardpoints().size(); h++) {
				UiWeaponEntry item = entry.getHerc().getHardpoints().get((short)h);
				object.write(writeShortLE((short)h));
				object.write(writeShortLE(item.getItemId()));
				object.write(writeShortLE(item.getHealthPercent()));
				object.write(writeShortLE(item.getMissileEnum()));
			}
		}
		
		return object.toByteArray();
	}
}
