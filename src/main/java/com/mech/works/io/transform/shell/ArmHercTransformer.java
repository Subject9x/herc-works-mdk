package com.mech.works.io.transform.shell;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.mech.works.data.file.dat.shell.ArmHerc;
import com.mech.works.data.ref.files.DataFile;
import com.mech.works.data.struct.vshell.hercs.UiHardpointGraphic;
import com.mech.works.data.struct.vshell.hercs.UiImageDBA;
import com.mech.works.io.transform.ThreeSpaceByteTransformer;

public class ArmHercTransformer extends ThreeSpaceByteTransformer{

	public ArmHercTransformer() {}
	
	@Override
	public DataFile bytesToObject(byte[] inputArray) throws ClassCastException {
		
		ArmHerc armData = new ArmHerc();
		resetIndex();
		setBytes(inputArray);
		
		
		UiImageDBA topHercImg = new UiImageDBA();
		armData.setTopImgArrId(indexShortLE());
		topHercImg.setOriginX(indexIntLE());
		topHercImg.setOriginY(indexIntLE());
		skip(4); //int unk2
		skip(4); //int unk3
		topHercImg.setFrameId(indexShortLE());
		topHercImg.setFlags(UiImageDBA.RFlag.get(indexShortLE()));
		armData.setHercTopImg(topHercImg);
		
		
		UiImageDBA bottomHercImg = new UiImageDBA();
		armData.setBottomImgArrId(indexShortLE());
		bottomHercImg.setOriginX(indexIntLE());
		bottomHercImg.setOriginY(indexIntLE());
		skip(4); //int unk5
		skip(4); //int unk6
		bottomHercImg.setFrameId(indexShortLE());
		bottomHercImg.setFlags(UiImageDBA.RFlag.get(indexShortLE()));
		armData.setHercBotImg(bottomHercImg);
		
		armData.setTotalWeapons(indexShortLE());
		
		skip(2);	//spacer
		
		armData.setTotalHardpoints(indexShortLE());
		
		//Setup 'empty' hardpoint graphic list
		Map<Short, UiHardpointGraphic> emptyHardpoints = new HashMap<Short, UiHardpointGraphic>(); 
		for(int i=0; i < armData.getTotalHardpoints(); i++) {
			UiHardpointGraphic emptySlot = new UiHardpointGraphic();
			emptySlot.setId(indexShortLE());
			emptySlot.setOriginX(indexIntLE());
			emptySlot.setOriginY(indexIntLE());
			emptySlot.setOutlineX(indexIntLE());
			emptySlot.setOutlineX(indexIntLE());
			emptySlot.setFrameId(indexShortLE());
			emptySlot.setFlags(UiImageDBA.RFlag.get(indexShortLE()));
		}
		armData.setEmptyHardpoints(emptyHardpoints);
		
		//Begun Weapon-Id-Hardpoint map
		Map<Short, UiHardpointGraphic[]> weaponHardpoints = new HashMap<Short, UiHardpointGraphic[]>();
		for(int i=0; i < armData.getTotalWeapons(); i++) {
			short weaponId = indexShortLE();
			short pointTotal = indexShortLE();
			UiHardpointGraphic[] graphics = new UiHardpointGraphic[pointTotal];
			
			for(int h=0; h < pointTotal; h++) {
				UiHardpointGraphic hardpoint = new UiHardpointGraphic();
				hardpoint.setId(indexShortLE());
				hardpoint.setOriginX(indexIntLE());
				hardpoint.setOriginY(indexIntLE());
				hardpoint.setOutlineX(indexIntLE());
				hardpoint.setOutlineX(indexIntLE());
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
		objectBytes.write(uiImageToByte(data.getHercTopImg()));
		
		objectBytes.write(writeShortLE(data.getBottomImgArrId()));
		objectBytes.write(uiImageToByte(data.getHercBotImg()));
		
		objectBytes.write(writeShortLE(data.getTotalWeapons()));
		objectBytes.write((byte)0x00);
		objectBytes.write((byte)0x00);
		
		objectBytes.write(writeShortLE(data.getTotalHardpoints()));
		
		//TODO - log null / empty data
		//Setup 'empty' hardpoint graphic list
		for(short id : data.getEmptyHardpoints().keySet()) {
			objectBytes.write(writeShortLE(id));
			objectBytes.write(uiHardpointToBytes(data.getEmptyHardpoints().get(id)));
		}
		
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
