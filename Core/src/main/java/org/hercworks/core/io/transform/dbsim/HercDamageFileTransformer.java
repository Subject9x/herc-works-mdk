package org.hercworks.core.io.transform.dbsim;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.hercworks.core.data.file.dbsim.HercSimDamage;
import org.hercworks.core.data.file.dbsim.HercSimDamage.HercPiece;
import org.hercworks.core.data.file.dbsim.HercSimDamage.InternalsHealth;
import org.hercworks.core.data.file.dbsim.HercSimDamage.InternalsTarget;
import org.hercworks.core.data.struct.herc.HercInternals;
import org.hercworks.core.data.struct.herc.HercLUT;
import org.hercworks.core.io.transform.ThreeSpaceByteTransformer;
import org.hercworks.voln.DataFile;
import org.hercworks.voln.FileType;

public class HercDamageFileTransformer extends ThreeSpaceByteTransformer{

	@Override
	public DataFile bytesToObject(byte[] inputArray) throws ClassCastException {
		index = 0;
		
		if(inputArray == null || inputArray.length <= 0) {
			//TODO - null input
			return null;
		}
		
		HercSimDamage data = new HercSimDamage();
		data.setRawBytes(inputArray);
		data.setExt(FileType.DMG);
		data.setDir(FileType.DMG);
		
		setBytes(inputArray);
		
		data.setInternalsTotal(indexShortLE());
		
		data.setInternals(new InternalsHealth[data.getInternalsTotal()]);
		for(int i = 0; i < data.getInternalsTotal(); i++) {
			short val = indexShortLE();
			
			if(i < 10) {
				InternalsHealth system = data.newInternalsHealth();
				system.setId((short)i);
				system.setArmor(val);
				if(val != 0) {
					system.setName(HercInternals.getById((short)i));	
				}
				data.getInternals()[i] = system;
			}
		}
		
		for(int i=0; i< 22 - data.getInternals().length; i++) {
			skip(2);
		}
		
		short totalComponents = indexShortLE();
		
		//non-skimmer, non-spider hercs have 29
		data.setComponentData(new HercPiece[totalComponents]);
		for(int i = 0; i < 29; i++) {
			data.getComponentData()[i] = parseHercPiece(data);
		}
		
//		//COCKPIT\FRONT
//		data.getComponentData().add(parseHercPiece(data));
//		
//		//COCKPIT\REAR
//		data.getComponentData().add(parseHercPiece(data));
//		
//		//SHOULDER\LEFT
//		data.getComponentData().add(parseHercPiece(data));
//		
//		//SHOULDER\RIGHT
//		data.getComponentData().add(parseHercPiece(data));
//		
//		//WEPN_BRACK\LEFT
//		data.getComponentData().add(parseHercPiece(data));
//		
//		//WEPN_BRACK\RIGHT
//		data.getComponentData().add(parseHercPiece(data));
//		
//		//TORSO
//		data.getComponentData().add(parseHercPiece(data));
//		
//		//LEG\LEFT\UPPER
//		data.getComponentData().add(parseHercPiece(data));
//		
//		//LEG\RIGHT\UPPER
//		data.getComponentData().add(parseHercPiece(data));
//		
//		//LEG\LEFT\LOWER
//		data.getComponentData().add(parseHercPiece(data));
//		
//		//LEG\RIGHT\LOWER
//		data.getComponentData().add(parseHercPiece(data));
//		
//		//FOOT\LEFT
//		data.getComponentData().add(parseHercPiece(data));
//		
//		//FOOT\RIGHT
//		data.getComponentData().add(parseHercPiece(data));
		
		
		return data;
	}

	@Override
	public byte[] objectToBytes(DataFile source) throws ClassCastException, IOException {
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		HercSimDamage data = (HercSimDamage)source;

		int diff = 0;
		if(data.getFileName().toLowerCase().contains(HercLUT.SKIMMER.getAbbrevDat().toLowerCase())) {
			out.write(writeShortLE((short)1));
		}
		else{
			out.write(writeShortLE((short)22));
			diff = 22 - data.getInternals().length;
		}
		
		for(int i = 0; i < data.getInternalsTotal(); i++) {
			out.write(writeShortLE(data.getInternals()[i].getArmor()));
		}
		
		for(int i = 0; i < diff; i++) {
			out.write(writeShortLE((short)0));
		}
		
		out.write(writeShortLE((short)data.getComponentData().length));
		
		for(int p = 0; p < data.getComponentData().length; p++) {
			HercPiece piece = data.getComponentData()[p]; 
			out.write(writeShortLE(piece.getArmor()));
			out.write(writeShortLE(piece.getDebrisFlags()));
			out.write(piece.getBoneId());
			out.write(piece.getUnk_val());
			out.write(writeShortLE((short)piece.getMappedInternals().length));
			
			for(int i = 0; i < piece.getMappedInternals().length; i++) {
				short chance = (short) (piece.getMappedInternals()[i].getCritChance() * 100);
				out.write(writeShortLE(chance));
				out.write(writeShortLE(piece.getMappedInternals()[i].getInternalsId().getId()));
			}
		}
		
		return out.toByteArray();
	}
	
	
	private HercPiece parseHercPiece(HercSimDamage data) {
		HercPiece piece = data.newHercPiece();
		piece.setArmor(indexShortLE());
		piece.setDebrisFlags(indexShortLE());
		piece.setBoneId(indexByte());
		piece.setUnk_val(indexByte());
		
		piece.setMappedInternals(new InternalsTarget[indexShortLE()]);
		for(int i=0; i < piece.getMappedInternals().length; i++) {
			InternalsTarget internalComp = data.newInternalsTarget();
			internalComp.setCritChance(indexShortLE());
			internalComp.setId(HercInternals.getById(indexShortLE()));
			piece.getMappedInternals()[i] = internalComp;
		}
		return piece;
	}
}
