package com.mech.works.io.read;

import java.nio.ByteOrder;

import com.mech.works.data.file.dat.shell.Ini_Herc_Dat;
import com.mech.works.data.ref.files.DataFile;
import com.mech.works.vol.data.VolEntry;

import at.favre.lib.bytes.Bytes;

/**
 * Reads various .DAT file types in.
 * .DAT files are wildcards, they could be almost any kind of data, and are dependent on the file name or folder
 * 	for contextual determination.
 */
public final class DatFileReader {

	public static Ini_Herc_Dat parseIniHercDatStats(byte[] volByte, DataFile file) throws Exception {
		
		if(volByte == null || volByte.length == 0) {
			throw new Exception("ERROR: vol data bytes array was empty.");
		}
		
		if(!(file instanceof DataFile)) {
			throw new Exception("ERROR: file("+file.getFileName()+") was not a <DataFile> object.");
		}
		
		Ini_Herc_Dat iniStats = new Ini_Herc_Dat(file.getFileName(), file.getFilePath());
		
		if(file.getRawBytes() == null || file.getRawBytes().length == 0) {
			throw new Exception("ERROR: file("+file.getFileName()+") raw bytes was null or empty.");
		}
		
		iniStats.setRawBytes(file.getRawBytes());
		
		byte[] data = iniStats.getRawBytes();
		int cursor = 0;
		
		iniStats.setHercId(Bytes.from(data, cursor, 2).byteOrder(ByteOrder.LITTLE_ENDIAN).toShort());
		cursor +=2;

		iniStats.setUnknown2_100(Bytes.from(data, cursor, 2).byteOrder(ByteOrder.LITTLE_ENDIAN));
		cursor +=4;	//acounts for 2-byte spacer(assumed)
		
		//WARN: this does not sync up to herc's total hardpoint....so we will have to figure out why
		iniStats.setHardpointCount(Bytes.from(data, cursor, 2).byteOrder(ByteOrder.LITTLE_ENDIAN).toShort());
		cursor +=2;
		
		iniStats.initHardpoints();
		
		for(int i=cursor; i < iniStats.getRawBytes().length; i+=2) {
			
			Ini_Herc_Dat.Hardpoint hardpoint = iniStats.addHardpoint(Bytes.from(data, i, 2).byteOrder(ByteOrder.LITTLE_ENDIAN).toShort());
			i += 2;
			
			hardpoint.setUint16_1(Bytes.from(data, i, 2).byteOrder(ByteOrder.LITTLE_ENDIAN).toShort());
			i += 2;
			
			hardpoint.setUint16_2(Bytes.from(data, i, 2).byteOrder(ByteOrder.LITTLE_ENDIAN).toShort());
			i += 2;
			
			hardpoint.setUint16_3(Bytes.from(data, i, 2).byteOrder(ByteOrder.LITTLE_ENDIAN).toShort());
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
