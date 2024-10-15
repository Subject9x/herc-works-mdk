package org.hercworks.transfer.svc.impl.dbsim;

import org.hercworks.core.data.file.dat.sim.MissileDatFile;
import org.hercworks.core.data.struct.dbsim.ProjMissileDatEntry;
import org.hercworks.transfer.dto.file.TransferObject;
import org.hercworks.transfer.dto.file.sim.MissileDatDTO;
import org.hercworks.transfer.dto.struct.dbsim.ProjMissileDatDTO;
import org.hercworks.transfer.svc.GeneralDTOService;
import org.hercworks.voln.DataFile;
import org.hercworks.voln.FileType;

public class MissileDatDTOServiceImpl implements GeneralDTOService {

	@Override
	public TransferObject convertToDTO(DataFile source) {
	
		MissileDatFile src = (MissileDatFile)source;
		MissileDatDTO dto = new MissileDatDTO();
		
		dto.setMissiles(new ProjMissileDatDTO[src.getTotal()]);
		for(int b=0; b < src.getEntries().length; b++) {
			ProjMissileDatEntry bullet = src.getEntries()[b];
			ProjMissileDatDTO entry = new ProjMissileDatDTO();
			
			entry.setId(b);
			entry.setModelId(bullet.getModelId());
			entry.setLifetime(bullet.getLifetime());
			entry.setClipRadius(bullet.getClipRadius());
			entry.setUnk2_flag(bullet.getUnk2_flag());
			entry.setSfxIdFireBullet(bullet.getSfxFireIdBullets());
			entry.setUnk3_uint16(bullet.getUnk3_uint16());
			entry.setSfxIdFireMissile(bullet.getSfxFireIdMissiles());
			
			dto.getMissiles()[b] = entry;
		}
		
		return dto;
	}

	@Override
	public DataFile fromDTO(TransferObject source) {
				
		MissileDatDTO dto = (MissileDatDTO)source;
		MissileDatFile data = new MissileDatFile((short)dto.getMissiles().length);
		data.setFileName(dto.getFileName());
		data.setExt(FileType.DAT);
		data.setDir(FileType.DAT);
		
		for(int b=0; b < dto.getMissiles().length; b++) {
			ProjMissileDatDTO bullet = dto.getMissiles()[b];
			ProjMissileDatEntry entry = new ProjMissileDatEntry();
			
			entry.setModelId((short)bullet.getModelId());
			entry.setLifetime((short)bullet.getLifetime());
			entry.setClipRadius((short)bullet.getClipRadius());
			entry.setUnk2_flag((short)bullet.getUnk2_flag());
			entry.setSfxFireIdBullets((short)bullet.getSfxIdFireBullet());
			entry.setUnk3_uint16((short)bullet.getUnk3_uint16());
			entry.setSfxFireIdMissiles((short)bullet.getSfxIdFireMissile());
			
			data.getEntries()[b] = entry;
		}
		
		return data;
	}
}
