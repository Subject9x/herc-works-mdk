package org.hercworks.core.io.read;

import java.nio.ByteOrder;
import java.util.HashMap;

import org.hercworks.core.data.file.dat.shell.InitHerc;
import org.hercworks.core.data.struct.MissileType;
import org.hercworks.core.data.struct.vshell.hercs.ShellHercData;
import org.hercworks.core.data.struct.vshell.hercs.UiWeaponEntry;
import org.hercworks.voln.DataFile;
import org.hercworks.voln.VolEntry;

import at.favre.lib.bytes.Bytes;

/**
 * Reads various .DAT file types in.
 * .DAT files are wildcards, they could be almost any kind of data, and are dependent on the file name or folder
 * 	for contextual determination.
 */
public final class DatFileReader {

	public static InitHerc parseIniHercDatStats(byte[] volByte, DataFile file) throws Exception {
		
		if(volByte == null || volByte.length == 0) {
			throw new Exception("ERROR: vol data bytes array was empty.");
		}
		
		if(!(file instanceof DataFile)) {
			throw new Exception("ERROR: file("+file.getFileName()+") was not a <DataFile> object.");
		}
		
		InitHerc iniStats = new InitHerc(file.getFileName(), file.getFilePath());
		
		if(file.getRawBytes() == null || file.getRawBytes().length == 0) {
			throw new Exception("ERROR: file("+file.getFileName()+") raw bytes was null or empty.");
		}
		
		iniStats.setRawBytes(file.getRawBytes());
		
		byte[] data = iniStats.getRawBytes();
		int cursor = 0;
		
		iniStats.setData(new ShellHercData());
		
		iniStats.getData().setHercId(Bytes.from(data, cursor, 2).byteOrder(ByteOrder.LITTLE_ENDIAN).toShort());
		cursor +=2;

		iniStats.getData().setHealthRatio(Bytes.from(data, cursor, 2).byteOrder(ByteOrder.LITTLE_ENDIAN).toShort());
		cursor +=2;
		
		iniStats.getData().setBuildCompleteLevel(Bytes.from(data, cursor, 2).byteOrder(ByteOrder.LITTLE_ENDIAN).toShort());
		cursor +=2;
		
		//WARN: this does not sync up to herc's total hardpoint....so we will have to figure out why
		short activeHardpoints = Bytes.from(data, cursor, 2).byteOrder(ByteOrder.LITTLE_ENDIAN).toShort();
		cursor +=2;
		
		iniStats.getData().setHardpoints(new HashMap<Short, UiWeaponEntry>());
		
		for(int i=cursor; i < iniStats.getRawBytes().length; i+=2) {
			
			short id = Bytes.from(data, i, 2).byteOrder(ByteOrder.LITTLE_ENDIAN).toShort();
			UiWeaponEntry hardpoint = new UiWeaponEntry();
			i += 2;
			
			hardpoint.setItemId(Bytes.from(data, i, 2).byteOrder(ByteOrder.LITTLE_ENDIAN).toShort());
			i += 2;
			
			hardpoint.setHealthPercent(Bytes.from(data, i, 2).byteOrder(ByteOrder.LITTLE_ENDIAN).toShort());
			i += 2;
			
			short mslType = Bytes.from(data, i, 2).byteOrder(ByteOrder.LITTLE_ENDIAN).toShort();
			hardpoint.setMissileType(MissileType.getById((int)mslType));
		}
		
		return iniStats;
	}
	
	public VolEntry replaceDatBytes(byte[] newData, DataFile targetFile) throws Exception{
		
		if(!(targetFile instanceof VolEntry)) {
			throw new Exception("ERROR: file("+targetFile.getFileName()+") was not a <VolEntry> object.");
		}
		
		Bytes spliceData = Bytes.from(targetFile.header());
		
		spliceData = spliceData.append(targetFile.getRawBytes());
		
		targetFile.setRawBytes(spliceData.array());
		
		return (VolEntry)targetFile;
	}
}
