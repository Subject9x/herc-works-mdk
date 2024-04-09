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
		armData.setExt(FileType.DAT);
		
		
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
		
		System.out.println("total entries=" + armData.getTotalWeapons());
		
		//Begun Weapon-Id-Hardpoint map
		Map<Short, UiHardpointGraphic[]> weaponHardpoints = new HashMap<Short, UiHardpointGraphic[]>();
		for(int i=0; i < (int)armData.getTotalWeapons(); i++) {
			short weaponId = indexShortLE();
			short pointTotal = indexShortLE();
			System.out.println("-----------weapon id=" + weaponId + "---------------");
			System.out.println("+ hardpoints=" + pointTotal);
			
			UiHardpointGraphic[] graphics = new UiHardpointGraphic[pointTotal];
			
			for(int h=0; h < (int)pointTotal; h++) {
				System.out.println("+---h=" + h);
				UiHardpointGraphic hardpoint = new UiHardpointGraphic();
				hardpoint.setId(indexShortLE());
				hardpoint.setOriginX(indexIntLE());
				hardpoint.setOriginY(indexIntLE());
				hardpoint.setOutlineX(indexIntLE());
				hardpoint.setOutlineY(indexIntLE());
				hardpoint.setFrameId(indexShortLE());
				hardpoint.setFlags(UiImageDBA.RFlag.get(indexShortLE()));
				graphics[h] = hardpoint;
				System.out.println(hardpoint.toString());
				
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
		objectBytes.write(uiImageToByte(data.getHercTopImg()));
		
		objectBytes.write(writeShortLE(data.getBottomImgArrId()));
		objectBytes.write(uiImageToByte(data.getHercBotImg()));
		
		objectBytes.write(writeShortLE(data.getTotalWeapons()));
		objectBytes.write((byte)0x00);
		objectBytes.write((byte)0x00);
		
		objectBytes.write(writeShortLE(data.getTotalHardpoints()));
		
		//Begun Weapon-Id-Hardpoint map
		for(short id : data.getWeaponHardpoints().keySet()) {
			UiHardpointGraphic[] items = data.getWeaponHardpoints().get(id);
			
			objectBytes.write(writeShortLE(id));
			objectBytes.write(writeShortLE((short)items.length));
			
			for(UiHardpointGraphic graphic : items) {
				objectBytes.write(writeShortLE(graphic.getId()));
				objectBytes.write(uiHardpointToBytes(graphic));
			}
		}
		
		return objectBytes.toByteArray();
	}

	private byte[] uiImageToByte(UiImageDBA img) throws IOException {
		
		ByteArrayOutputStream bass = new ByteArrayOutputStream();
		bass.write(writeIntLE(img.getOriginX()));
		bass.write(writeIntLE(img.getOriginY()));
		bass.write(writeIntLE(img.getOriginX()));
		bass.write(writeIntLE(img.getOriginY()));
		bass.write(writeShortLE(img.getFrameId()));
		bass.write(writeShortLE(img.getFlags().val()));
		
		return bass.toByteArray();
	}
	
	private byte[] uiHardpointToBytes(UiHardpointGraphic img) throws IOException {
		
		ByteArrayOutputStream bass = new ByteArrayOutputStream();
		bass.write(writeIntLE(img.getOriginX()));
		bass.write(writeIntLE(img.getOriginY()));
		bass.write(writeIntLE(img.getOutlineX()));
		bass.write(writeIntLE(img.getOutlineY()));
		bass.write(writeShortLE(img.getFrameId()));
		bass.write(writeShortLE(img.getFlags().val()));
		
		return bass.toByteArray();
	}
}
