package org.hercworks.transfer.svc.impl.dbsim;

import org.hercworks.core.data.file.dbsim.HercSimDamage;
import org.hercworks.core.data.file.dbsim.HercSimDamage.HercPiece;
import org.hercworks.core.data.file.dbsim.HercSimDamage.InternalsHealth;
import org.hercworks.core.data.file.dbsim.HercSimDamage.InternalsTarget;
import org.hercworks.core.data.struct.herc.HercInternals;
import org.hercworks.transfer.dto.file.TransferObject;
import org.hercworks.transfer.dto.file.sim.HercDmgDTO;
import org.hercworks.transfer.dto.struct.dbsim.HercDmgPieceDTO;
import org.hercworks.transfer.svc.GeneralDTOService;
import org.hercworks.voln.DataFile;
import org.hercworks.voln.FileType;

public class HercSimDmgDTOServiceImpl  implements GeneralDTOService{

	@Override
	public TransferObject convertToDTO(DataFile source) {
		
		HercDmgDTO dto = new HercDmgDTO();
		HercSimDamage src = (HercSimDamage)source;
		
		for(int i = 0; i < src.getInternals().length; i++) {
			if( src.getInternals()[i] != null) {
				InternalsHealth internal =  src.getInternals()[i];
				dto.getInternals().put(HercInternals.getById(internal.getId()).name(), internal.getArmor());	
			}
		}
		
		dto.setHercParts(new HercDmgPieceDTO[src.getComponentData().length]);
		
		
		for(int i = 0; i < src.getComponentData().length; i++) {
			HercPiece piece = src.getComponentData()[i];
			HercDmgPieceDTO part = new HercDmgPieceDTO();
			part.setArmor(piece.getArmor());
			part.setDebrisFlags(piece.getDebrisFlags());
			part.setBoneId(piece.getBoneId());
			part.setUnk1_val(piece.getUnk_val());
			
			for(int p = 0; p < piece.getMappedInternals().length; p++) {
				float critChance = (float)piece.getMappedInternals()[p].getCritChance();
				part.getInternals().put(piece.getMappedInternals()[p].getInternalsId().name(), critChance/100f);
			}
			dto.getHercParts()[i] = part;
		}
		
		return dto;
	}

	@Override
	public DataFile fromDTO(TransferObject source) {

		HercDmgDTO dto = (HercDmgDTO)source;
		HercSimDamage data = new HercSimDamage();
		data.setExt(FileType.DMG);
		data.setDir(FileType.DMG);
		
		data.setInternalsTotal((short)dto.getInternals().size());
		
		data.setInternals(new InternalsHealth[dto.getInternals().size()]);
		for(String internal : dto.getInternals().keySet()) {
			
			InternalsHealth intHp = data.newInternalsHealth();
			
			intHp.setArmor(dto.getInternals().get(internal));
			intHp.setId(HercInternals.valueOf(internal).getId());
			intHp.setName(HercInternals.valueOf(internal));
			data.getInternals()[HercInternals.valueOf(internal).getId()] = intHp;
		}
		
		data.setComponentData(new HercPiece[dto.getHercParts().length]);
		for(int i = 0; i < dto.getHercParts().length; i++) {	
			HercDmgPieceDTO piece = dto.getHercParts()[i];
			HercPiece part = data.newHercPiece();
			
			part.setArmor((short)piece.getArmor());
			part.setDebrisFlags((short)piece.getDebrisFlags());
			part.setBoneId((byte)piece.getBoneId());
			part.setUnk_val((byte)piece.getUnk1_val());
			
			part.setMappedInternals(new InternalsTarget[piece.getInternals().size()]);
			
			int cnt = 0;
			for(String key : piece.getInternals().keySet()) {
				float critChance =  piece.getInternals().get(key) * 100f;
				
				InternalsTarget intTarg = data.newInternalsTarget();
				
				intTarg.setCritChance((short)critChance);
				intTarg.setId(HercInternals.valueOf(key));
				part.getMappedInternals()[cnt] = intTarg; 
				
				cnt += 1;
			}
			data.getComponentData()[i] = part;
		}
		return data;
	}

}
