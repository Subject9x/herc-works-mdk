package org.hercworks.core.io.transform.shell;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.hercworks.core.data.file.dat.shell.ArmWeap;
import org.hercworks.core.data.struct.vshell.hercs.UiHardpointGraphic;
import org.hercworks.core.data.struct.vshell.hercs.UiImageDBA.RFlag;
import org.hercworks.core.io.transform.ThreeSpaceByteTransformer;
import org.hercworks.voln.DataFile;
import org.hercworks.voln.FileType;

public class ArmWeapTransformer extends ThreeSpaceByteTransformer {

	public ArmWeapTransformer() {}
	
	@Override
	public DataFile bytesToObject(byte[] inputArray) throws ClassCastException {
		
		if(inputArray == null || inputArray.length <= 0) {
			return null;
		}
		
		setBytes(inputArray);
		short count = indexShortLE();
		ArmWeap armWeap = new ArmWeap(count);
		armWeap.setTotalWeapons(count);
		armWeap.setRawBytes(inputArray);
		armWeap.setExt(FileType.DAT);
		armWeap.setFileName("ARM_WEAP");
		
		for(int i=0; i < armWeap.getTotalWeapons(); i++) {
			UiHardpointGraphic icon = new UiHardpointGraphic();
			icon.setId(indexShortLE());
			icon.setOriginX(indexIntLE());
			icon.setOriginY(indexIntLE());
			icon.setFrameId(indexShortLE());
			icon.setFlags(RFlag.NORMAL);
			System.out.println(icon.toString());
			armWeap.getEntries()[i] = icon;
		}
		
		armWeap.setTotalSecondList(indexShortLE());
		UiHardpointGraphic[] secondList = new UiHardpointGraphic[armWeap.getTotalSecondList()];
				
		for(int i=0; i < armWeap.getTotalSecondList(); i++) {
			UiHardpointGraphic icon = new UiHardpointGraphic();
			icon.setId(indexShortLE());
			icon.setOriginX(indexIntLE());
			icon.setOriginY(indexIntLE());
			icon.setFrameId(indexShortLE());
			icon.setFlags(RFlag.NORMAL);
			System.out.println(icon.toString());
			secondList[i] = icon;
		}
		armWeap.setSecondary(secondList);
		
		
		return armWeap;
	}

	@Override
	public byte[] objectToBytes(DataFile source) throws ClassCastException, IOException {
		
		ArmWeap data = (ArmWeap)source;
		
		ByteArrayOutputStream objectBytes = new ByteArrayOutputStream();
		
		objectBytes.write(writeShortLE(data.getTotalWeapons()));
		
		for(int i=0; i < data.getTotalWeapons(); i++) {
			UiHardpointGraphic icon = data.getEntries()[i];
			objectBytes.write(writeShortLE(icon.getId()));
			objectBytes.write(writeIntLE(icon.getOriginX()));
			objectBytes.write(writeIntLE(icon.getOriginY()));
			objectBytes.write(writeShortLE(icon.getFrameId()));
		}
		
		objectBytes.write(writeShortLE(data.getTotalSecondList()));
		
		for(int i=0; i < data.getTotalSecondList(); i++) {
			UiHardpointGraphic icon = data.getSecondary()[i];
			objectBytes.write(writeShortLE(icon.getId()));
			objectBytes.write(writeIntLE(icon.getOriginX()));
			objectBytes.write(writeIntLE(icon.getOriginY()));
			objectBytes.write(writeShortLE(icon.getFrameId()));
		}
		
		return objectBytes.toByteArray();
	}

}
