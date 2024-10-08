package org.hercworks.core.io.transform.shell;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.hercworks.core.data.file.dat.shell.ArmHerc;
import org.hercworks.core.data.struct.vshell.hercs.UiHardpointGraphic;
import org.hercworks.core.data.struct.vshell.hercs.UiImageDBA;
import org.hercworks.core.io.transform.ThreeSpaceByteTransformer;
import org.hercworks.voln.DataFile;
import org.hercworks.voln.FileType;

public class ArmHercTransformer extends ThreeSpaceByteTransformer{

	public ArmHercTransformer() {}
	
	@Override
	public DataFile bytesToObject(byte[] inputArray) throws ClassCastException {
		
		if(inputArray == null || inputArray.length == 0) {
			//TODO  - error for empty byte array
			return null;
		}
		setBytes(inputArray);

		ArmHerc armData = new ArmHerc();
		armData.setRawBytes(inputArray);
		armData.setExt(FileType.DAT);
		armData.setDir(FileType.GAM);
		
		UiHardpointGraphic topHercImg = new UiHardpointGraphic();
		armData.setTopImgArrId(indexShortLE());
		topHercImg.setOriginX(indexIntLE());
		topHercImg.setOriginY(indexIntLE());
		topHercImg.setOutlineX(indexIntLE());	//note: for some reason these are probably because all UIharpdoint images use the same struct.
		topHercImg.setOutlineY(indexIntLE());	//note: for some reason these are probably because all UIharpdoint images use the same struct.
		topHercImg.setFrameId(indexShortLE());
		topHercImg.setFlags(UiImageDBA.RFlag.get(indexShortLE()));
		armData.setHercTopImg(topHercImg);
		
		UiHardpointGraphic bottomHercImg = new UiHardpointGraphic();
		armData.setBottomImgArrId(indexShortLE());
		bottomHercImg.setOriginX(indexIntLE());
		bottomHercImg.setOriginY(indexIntLE());
		bottomHercImg.setOutlineX(indexIntLE());	//note: for some reason these are probably because all UIharpdoint images use the same struct.
		bottomHercImg.setOutlineY(indexIntLE());	//note: for some reason these are probably because all UIharpdoint images use the same struct.
		bottomHercImg.setFrameId(indexShortLE());
		bottomHercImg.setFlags(UiImageDBA.RFlag.get(indexShortLE()));
		armData.setHercBotImg(bottomHercImg);
		
		armData.setTotalWeapons(indexShortLE());
		
		//Begun Weapon-Id-Hardpoint map
		Map<Short, UiHardpointGraphic[]> weaponHardpoints = new HashMap<Short, UiHardpointGraphic[]>();
		for(int i=0; i < (int)armData.getTotalWeapons(); i++) {
			short weaponId = indexShortLE();
			short pointTotal = indexShortLE();
			
			UiHardpointGraphic[] graphics = new UiHardpointGraphic[pointTotal];
			
			for(int h=0; h < (int)pointTotal; h++) {
				UiHardpointGraphic hardpoint = new UiHardpointGraphic();
				hardpoint.setId(indexShortLE());
				hardpoint.setOriginX(indexIntLE());
				hardpoint.setOriginY(indexIntLE());
				hardpoint.setOutlineX(indexIntLE());
				hardpoint.setOutlineY(indexIntLE());
				hardpoint.setFrameId(indexShortLE());
				hardpoint.setFlags(UiImageDBA.RFlag.get(indexShortLE()));
				graphics[h] = hardpoint;
			}
			weaponHardpoints.put(weaponId, graphics);
		}
		armData.setWeaponHardpoints(weaponHardpoints);
		
		return armData;
	}

	@Override
	public byte[] objectToBytes(DataFile source) throws ClassCastException, IOException {
		
		ArmHerc data = (ArmHerc)source;
		
		ByteArrayOutputStream objectBytes = new ByteArrayOutputStream();
		
		objectBytes.write(writeShortLE(data.getTopImgArrId()));
		uiImageToByte(data.getHercTopImg(), objectBytes);
		
		objectBytes.write(writeShortLE(data.getBottomImgArrId()));
		uiImageToByte(data.getHercBotImg(), objectBytes);
		
		objectBytes.write(writeShortLE(data.getTotalWeapons()));
		
		//Begun Weapon-Id-Hardpoint map
		for(short id : data.getWeaponHardpoints().keySet()) {
			UiHardpointGraphic[] items = data.getWeaponHardpoints().get(id);
			
			objectBytes.write(writeShortLE(id));
			objectBytes.write(writeShortLE((short)items.length));
			
			for(UiHardpointGraphic graphic : items) {
				objectBytes.write(writeShortLE(graphic.getId()));
				uiHardpointToBytes(graphic, objectBytes);
			}
		}
		
		return objectBytes.toByteArray();
	}

	private void uiImageToByte(UiImageDBA img, ByteArrayOutputStream targ) throws IOException {
		
		targ.write(writeIntLE(img.getOriginX()));
		targ.write(writeIntLE(img.getOriginY()));
		targ.write(writeIntLE(img.getOriginX()));
		targ.write(writeIntLE(img.getOriginY()));
		targ.write(writeShortLE(img.getFrameId()));
		targ.write(writeShortLE(img.getFlags().val()));
	}
	
	private void uiHardpointToBytes(UiHardpointGraphic img, ByteArrayOutputStream targ) throws IOException {
		
		targ.write(writeIntLE(img.getOriginX()));
		targ.write(writeIntLE(img.getOriginY()));
		targ.write(writeIntLE(img.getOutlineX()));
		targ.write(writeIntLE(img.getOutlineY()));
		targ.write(writeShortLE(img.getFrameId()));
		targ.write(writeShortLE(img.getFlags().val()));
	}
}
