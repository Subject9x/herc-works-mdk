package org.hercworks.core.io.transform.shell;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

import org.hercworks.core.data.file.dat.shell.InitHerc;
import org.hercworks.core.data.struct.vshell.hercs.ShellHercData;
import org.hercworks.core.data.struct.vshell.hercs.UiWeaponEntry;
import org.hercworks.core.io.transform.ThreeSpaceByteTransformer;
import org.hercworks.voln.DataFile;
import org.hercworks.voln.FileType;

public class InitHercTransformer extends ThreeSpaceByteTransformer {

	public InitHercTransformer() {}
	
	@Override
	public DataFile bytesToObject(byte[] inputArray) throws ClassCastException {
		
		if(inputArray == null || inputArray.length <= 0) {
			return null;
		}
		setBytes(inputArray);
		
		InitHerc initHerc = new InitHerc();
		initHerc.setExt(FileType.DAT);
		
		ShellHercData data = new  ShellHercData();
		
		data.setHercId(indexShortLE());
		data.setHealthRatio(indexShortLE());
		data.setBuildCompleteLevel(indexShortLE());
		
		short hardpointCount = indexShortLE();
		data.setHardpoints(new HashMap<Short, UiWeaponEntry>());
		
		for(short h=0; h < hardpointCount; h+=(short)1) {
			UiWeaponEntry entry = new UiWeaponEntry();
			short hardpointId = indexShortLE();
			entry.setItemId(indexShortLE());
			entry.setHealthPercent(indexShortLE());
			entry.setMissileEnum(indexShortLE());
			data.getHardpoints().put(hardpointId, entry);
		}
		
		initHerc.setData(data);
		return initHerc;
	}

	@Override
	public byte[] objectToBytes(DataFile source) throws ClassCastException, IOException {
		
		InitHerc data = (InitHerc)source;
		ShellHercData herc = data.getData();
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		
		output.write(writeShortLE(herc.getHercId()));
		output.write(writeShortLE(herc.getHealthRatio()));
		output.write(writeShortLE(herc.getBuildCompleteLevel()));
		output.write(writeShortLE((short)herc.getHardpoints().size()));
		
		for(Short id : herc.getHardpoints().keySet()) {
			UiWeaponEntry entry = herc.getHardpoints().get(id);
			output.write(writeShortLE(id));
			output.write(writeShortLE(entry.getItemId()));
			output.write(writeShortLE(entry.getHealthPercent()));
			output.write(writeShortLE(entry.getMissileEnum()));
		}
		
		return output.toByteArray();
	}

}
