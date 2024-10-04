package org.hercworks.core.io.transform.shell;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.hercworks.core.data.file.dat.shell.RprHerc;
import org.hercworks.core.data.struct.vshell.hercs.UiHardpointGraphic;
import org.hercworks.core.data.struct.vshell.hercs.UiImageDBA;
import org.hercworks.core.data.struct.vshell.hercs.UiImageDBA.RFlag;
import org.hercworks.core.io.transform.ThreeSpaceByteTransformer;
import org.hercworks.voln.DataFile;
import org.hercworks.voln.FileType;

public class RprHercTransform extends ThreeSpaceByteTransformer{

	@Override
	public DataFile bytesToObject(byte[] inputArray) throws ClassCastException {
		if(inputArray == null || inputArray.length == 0) {
			//TODO  - error for empty byte array
			return null;
		}
		setBytes(inputArray);
		RprHerc repairHerc = new RprHerc();
		repairHerc.setRawBytes(inputArray);
		repairHerc.setExt(FileType.DAT);
		repairHerc.setDir(FileType.GAM);
		
		repairHerc.setBodyImgTotal(indexShortLE());
		
		Map<Short, UiImageDBA> bodFrames = new HashMap<Short, UiImageDBA>();
		for(int i=0; i < (int)repairHerc.getBodyImgTotal(); i++) {
			UiImageDBA frame = new UiImageDBA();
			short id = indexShortLE();
			frame.setOriginX(indexIntLE());
			frame.setOriginY(indexIntLE());
			frame.setFrameId(indexShortLE());
			frame.setFlags(RFlag.get(indexShortLE()));
			bodFrames.put(id, frame);
		}
		repairHerc.setBodyImages(bodFrames);
		
		UiHardpointGraphic internals = new UiHardpointGraphic();
		internals.setId(indexShortLE());
		internals.setOriginX(indexIntLE());
		internals.setOriginY(indexIntLE());
		internals.setFrameId(indexShortLE());
		internals.setFlags(RFlag.get(indexShortLE()));
		repairHerc.setInternalImage(internals);
		
		short totalWeapons = indexShortLE();
		repairHerc.setTotalHardpoints(totalWeapons);
		Map<Short, UiHardpointGraphic[]> weapons = new HashMap<Short, UiHardpointGraphic[]>();
		for(int w=0; w < (int)totalWeapons; w++) {
			short itemId = indexShortLE();
			short size = indexShortLE();
			UiHardpointGraphic[] points = new UiHardpointGraphic[size];
			for(int h=0; h < (int)size; h++) {
				UiHardpointGraphic socket = new UiHardpointGraphic();
				socket.setId(indexShortLE());
				socket.setOriginX(indexIntLE());
				socket.setOriginY(indexIntLE());
				socket.setFrameId(indexShortLE());
				socket.setFlags(RFlag.get(indexShortLE()));
				points[h] = socket;
			}
			weapons.put(itemId, points);
		}
		repairHerc.setWeaponHardpoints(weapons);
		
		return repairHerc;
	}

	@Override
	public byte[] objectToBytes(DataFile source) throws ClassCastException, IOException {
		
		RprHerc data = (RprHerc)source;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		out.write(writeShortLE(data.getBodyImgTotal()));
		for(Short id : data.getBodyImages().keySet()) {
			UiImageDBA frame = data.getBodyImages().get(id);
			out.write(writeShortLE(id));
			byte[] bytes = uiImageToByte(frame);
			out.write(bytes);
		}
		
		out.write(writeShortLE(data.getInternalImage().getId()));
		out.write(writeIntLE(data.getInternalImage().getOriginX()));
		out.write(writeIntLE(data.getInternalImage().getOriginY()));
		out.write(writeShortLE(data.getInternalImage().getFrameId()));
		out.write(writeShortLE(data.getInternalImage().getFlags().val()));
		
		out.write(writeShortLE(data.getTotalHardpoints()));
		for(Short id : data.getWeaponHardpoints().keySet()) {
			UiHardpointGraphic[] sockets = data.getWeaponHardpoints().get(id);
			out.write(writeShortLE(id));
			out.write(writeShortLE((short)sockets.length));
			for(int h=0; h < (int)sockets.length; h++) {
				UiHardpointGraphic img = sockets[h];
				out.write(writeShortLE(img.getId()));
				out.write(writeIntLE(img.getOriginX()));
				out.write(writeIntLE(img.getOriginY()));
				out.write(writeShortLE(img.getFrameId()));
				out.write(writeShortLE(img.getFlags().val()));
			}
		}
		return out.toByteArray();
	}
	
	//Somehow there's a struct difference between these images 
	// and the ones in the ARM_[herc].DAT files
	private byte[] uiImageToByte(UiImageDBA img) throws IOException {
		
		ByteArrayOutputStream bass = new ByteArrayOutputStream();
		bass.write(writeIntLE(img.getOriginX()));
		bass.write(writeIntLE(img.getOriginY()));
		bass.write(writeShortLE(img.getFrameId()));
		bass.write(writeShortLE(img.getFlags().val()));
		
		return bass.toByteArray();
	}
}
