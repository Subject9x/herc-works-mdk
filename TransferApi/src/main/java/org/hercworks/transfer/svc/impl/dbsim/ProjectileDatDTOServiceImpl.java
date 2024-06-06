package org.hercworks.transfer.svc.impl.dbsim;

import org.hercworks.core.data.file.dat.sim.ProjectileData;
import org.hercworks.core.data.file.dat.sim.ProjectileData.Projectile;
import org.hercworks.transfer.dto.file.TransferObject;
import org.hercworks.transfer.dto.file.sim.ProjectileDataDTO;
import org.hercworks.transfer.dto.struct.dbsim.ProjectileEntryDTO;
import org.hercworks.transfer.dto.struct.dbsim.ProjectileEntryDTO.ImpactEffectIds;
import org.hercworks.transfer.svc.GeneralDTOService;
import org.hercworks.voln.DataFile;
import org.hercworks.voln.FileType;

public class ProjectileDatDTOServiceImpl implements GeneralDTOService {
	
	@Override
	public TransferObject convertToDTO(DataFile source) {
		
		ProjectileData src = (ProjectileData)source;
		ProjectileDataDTO dto = new ProjectileDataDTO();
		
		dto.setProjectiles(new ProjectileEntryDTO[src.getData().length]);
		for(int p=0; p < src.getData().length; p++) {
			Projectile proj = src.getData()[p];
			ProjectileEntryDTO entry = new ProjectileEntryDTO();
			
			entry.setProjId(p);
			entry.setUnk1_val(proj.getUnk1_val());
			entry.setMissileId(proj.getMissileId());
			entry.setDamageShield(proj.getDamageShield());
			entry.setDamageArmor(proj.getDamageArmor());
			
			entry.setUnk2_val(proj.getUnk2_val());
			entry.setSpeed(proj.getSpeed());
			
			ImpactEffectIds effects = entry.newEffectsObj();
			
			effects.getShields()[0] = proj.getImpactFXShield()[0];
			effects.getShields()[1] = proj.getImpactFXShield()[1];
			effects.getShields()[2] = proj.getImpactFXShield()[2];
			effects.getShields()[3] = proj.getImpactFXShield()[3];
			
			effects.getGround()[0] = proj.getImpactFXGround()[0];
			effects.getGround()[1] = proj.getImpactFXGround()[1];
			effects.getGround()[2] = proj.getImpactFXGround()[2];
			effects.getGround()[3] = proj.getImpactFXGround()[3];

			effects.getArmor()[0] = proj.getImpactFXArmor()[0];
			effects.getArmor()[1] = proj.getImpactFXArmor()[1];
			effects.getArmor()[2] = proj.getImpactFXArmor()[2];
			effects.getArmor()[3] = proj.getImpactFXArmor()[3];
			
			entry.setImpactEffects(effects);
			
			dto.getProjectiles()[p] = entry;
		}
		
		return dto;
	}

	@Override
	public DataFile fromDTO(TransferObject source) {
		
		ProjectileDataDTO src = (ProjectileDataDTO)source;
		ProjectileData data = new ProjectileData();
		data.setExt(FileType.DAT);
		data.setDir(FileType.DAT);
		
		data.setTotal((short)src.getProjectiles().length);
		data.setData(new Projectile[src.getProjectiles().length]);
		for(int p=0; p < data.getData().length; p++) {
			
			ProjectileEntryDTO proj =src.getProjectiles()[p];
			Projectile entry = data.newProjectile();
			
			entry.setUnk1_val((short)proj.getUnk1_val());
			entry.setMissileId((short)proj.getMissileId());
			entry.setDamageShield((short)proj.getDamageShield());
			entry.setDamageArmor((short)proj.getDamageArmor());
			entry.setUnk2_val((short)proj.getUnk2_val());
			entry.setSpeed((short)proj.getSpeed());

			ImpactEffectIds effects = proj.getImpactEffects();
			
			entry.getImpactFXShield()[0] = (short)effects.getShields()[0];
			entry.getImpactFXShield()[1] = (short)effects.getShields()[1];
			entry.getImpactFXShield()[2] = (short)effects.getShields()[2];
			entry.getImpactFXShield()[3] = (short)effects.getShields()[3];
			
			entry.getImpactFXGround()[0] = (short)effects.getGround()[0];
			entry.getImpactFXGround()[1] = (short)effects.getGround()[1];
			entry.getImpactFXGround()[2] = (short)effects.getGround()[2];
			entry.getImpactFXGround()[3] = (short)effects.getGround()[3];

			entry.getImpactFXArmor()[0] = (short)effects.getArmor()[0];
			entry.getImpactFXArmor()[1] = (short)effects.getArmor()[1];
			entry.getImpactFXArmor()[2] = (short)effects.getArmor()[2];
			entry.getImpactFXArmor()[3] = (short)effects.getArmor()[3];
			
			data.getData()[p] = entry;
		}
		
		return data;
	}
}