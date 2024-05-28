package org.hercworks.core.io.transform.dbsim;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.hercworks.core.data.file.dbsim.HercSimDamage;
import org.hercworks.core.data.file.dbsim.HercSimDamage.ExternalComponentEntry;
import org.hercworks.core.data.file.dbsim.HercSimDamage.HercPiece;
import org.hercworks.core.data.file.dbsim.HercSimDamage.InternalsTarget;
import org.hercworks.core.data.struct.herc.HercInternals;
import org.hercworks.core.io.transform.ThreeSpaceByteTransformer;
import org.hercworks.voln.DataFile;
import org.hercworks.voln.FileType;

public class HercDamageFileTransformer extends ThreeSpaceByteTransformer{

	@Override
	public DataFile bytesToObject(byte[] inputArray) throws ClassCastException {
		index = 0;
		
		HercSimDamage data = new HercSimDamage();
		data.setExt(FileType.DMG);
		data.setRawBytes(inputArray);
		
		setBytes(inputArray);
		
		data.setInternalsTotal(indexShortLE());
		data.getInternals().put(HercInternals.SERVOS_LEG_LEFT, indexShortLE());
		data.getInternals().put(HercInternals.SERVOS_LEG_RIGHT, indexShortLE());
		data.getInternals().put(HercInternals.SENSOR_ARRAY, indexShortLE());
		data.getInternals().put(HercInternals.TARG_COMP, indexShortLE());
		data.getInternals().put(HercInternals.SHIELD_GEN, indexShortLE());
		data.getInternals().put(HercInternals.ENGINE, indexShortLE());
		data.getInternals().put(HercInternals.HYDRAULICS, indexShortLE());
		data.getInternals().put(HercInternals.STABILIZERS, indexShortLE());
		data.getInternals().put(HercInternals.LIFE_SUPPRT, indexShortLE());
		data.getInternals().put(HercInternals.PILOT, indexShortLE());
		
		for(int i=0; i< 22 - data.getInternals().size(); i++) {
			skip(2);
		}
		
		//COCKPIT\FRONT
		data.getComponentData().add(parseHercPiece(data));
		
		//COCKPIT\REAR
		data.getComponentData().add(parseHercPiece(data));
		
		//SHOULDER\LEFT
		data.getComponentData().add(parseHercPiece(data));
		
		//SHOULDER\RIGHT
		data.getComponentData().add(parseHercPiece(data));
		
		//WEPN_BRACK\LEFT
		data.getComponentData().add(parseHercPiece(data));
		
		//WEPN_BRACK\RIGHT
		data.getComponentData().add(parseHercPiece(data));
		
		//TORSO
		data.getComponentData().add(parseHercPiece(data));
		
		//LEG\LEFT\UPPER
		data.getComponentData().add(parseHercPiece(data));
		
		//LEG\RIGHT\UPPER
		data.getComponentData().add(parseHercPiece(data));
		
		//LEG\LEFT\LOWER
		data.getComponentData().add(parseHercPiece(data));
		
		//LEG\RIGHT\LOWER
		data.getComponentData().add(parseHercPiece(data));
		
		//FOOT\LEFT
		data.getComponentData().add(parseHercPiece(data));
		
		//FOOT\RIGHT
		data.getComponentData().add(parseHercPiece(data));
		
		for(int i=0; i<16; i++) {
			data.getHardpoints()[i] = parseExternalModule(data);
		}
		
		return data;
	}

	@Override
	public byte[] objectToBytes(DataFile source) throws ClassCastException, IOException {
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		HercSimDamage data = (HercSimDamage)source;
		
		out.write(writeShortLE(data.getInternalsTotal()));
		
		int diff = 22 - data.getInternals().size();
		for(HercInternals e : HercInternals.values()) {
			if(data.getInternals().containsKey(e)) {
				out.write(writeShortLE(data.getInternals().get(e)));
			}
		}
		
		for(int i=0; i < diff; i++) {
			out.write((short)0);
		}
		
		if(data.getComponentData() != null && !data.getComponentData().isEmpty()) {
			data.getComponentData().forEach((c)->{writeHercPiece(c, out);});
		}
		
		for(int i=0; i < 16; i++) {
			writeExternalComponent(data.getHardpoints()[i], out );
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
			internalComp.setUnk_val14(indexShortLE());
			internalComp.setId(HercInternals.getById(indexShortLE()));
			piece.getMappedInternals()[i] = internalComp;
		}
		return piece;
	}
	
	private ExternalComponentEntry parseExternalModule(HercSimDamage data) {
		ExternalComponentEntry mod = data.newExternalComponentEntry();
		mod.setUnk_first(indexShortLE());
		mod.setUnk_flag1(indexShortLE());
		mod.setDebrisOrgBoneId(indexShortLE());
		mod.setPossibleSpacer(indexShortLE());
		return mod;
	}
	
	
	private void writeHercPiece(HercPiece piece, ByteArrayOutputStream out) {
		try {
			out.write(writeShortLE(piece.getArmor()));
			out.write(writeShortLE(piece.getDebrisFlags()));
			out.write(piece.getBoneId());
			out.write(piece.getUnk_val());
			out.write(writeShortLE((short)piece.getMappedInternals().length));
			
			for(int i=0; i<piece.getMappedInternals().length; i++) {
				out.write(writeShortLE(piece.getMappedInternals()[i].getUnk_val14()));
				out.write(writeShortLE(piece.getMappedInternals()[i].getInternalsId().getId()));
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void writeExternalComponent(ExternalComponentEntry entry, ByteArrayOutputStream out ) {
		try {
			out.write(writeShortLE(entry.getUnk_first()));
			out.write(writeShortLE(entry.getUnk_flag1()));
			out.write(writeShortLE(entry.getDebrisOrgBoneId()));
			out.write(writeShortLE(entry.getPossibleSpacer()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
