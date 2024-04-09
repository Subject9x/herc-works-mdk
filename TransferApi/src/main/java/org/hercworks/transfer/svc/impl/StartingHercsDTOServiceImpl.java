package org.hercworks.transfer.svc.impl;

import java.util.ArrayList;
import java.util.List;

import org.hercworks.core.data.file.dat.shell.Hercs;
import org.hercworks.core.data.struct.HercLUT;
import org.hercworks.core.data.struct.WeaponLUT;
import org.hercworks.core.data.struct.vshell.hercs.UiWeaponEntry;
import org.hercworks.transfer.dto.file.shell.StartHercsDTO;
import org.hercworks.transfer.dto.shell.struct.HercHardpointDTO;
import org.hercworks.transfer.dto.shell.struct.StartHercsEntryDTO;
import org.hercworks.transfer.svc.StartingHercsDTOService;

public class StartingHercsDTOServiceImpl implements StartingHercsDTOService{

	@Override
	public StartHercsDTO convertToDTO(Hercs source) {
		
		StartHercsDTO dto = new StartHercsDTO();
		
		List<StartHercsEntryDTO> hercs = new ArrayList<StartHercsEntryDTO>();
		for(int i=0; i < source.getTotal(); i++) {
			Hercs.Entry entry = source.getData()[(short)i];
			
			StartHercsEntryDTO herc = new StartHercsEntryDTO(entry.getHardpointCount());
			herc.setBayId(entry.getBayId());
			herc.setBuildCompleteLevel(entry.getBuildCompleteLevel());
			herc.setHealthRatio(entry.getHealthRatio());
			herc.setHercId(HercLUT.getById(entry.getHercId()));
			
			for(int h=0; h < entry.getHardpointCount(); h++) {
				UiWeaponEntry item = entry.getData().get((short)h);
				
				HercHardpointDTO slot = new HercHardpointDTO();
				slot.setHardpoint((short)h);
				slot.setItem(WeaponLUT.getById(item.getItemId()));
				slot.setHealthPercent(item.getHealthPercent());
				slot.setMissileNum(item.getMissileEnum());
				herc.getHardpoints()[h] = slot;
			}
			hercs.add(herc);
		}
		dto.setHercs(hercs);
		return dto;
	}

	@Override
	public Hercs fromDTO(StartHercsDTO source) {
		
		Hercs object = new Hercs((short)source.getHercs().size());
		
		for(int i=0; i < source.getHercs().size(); i++) {
			StartHercsEntryDTO dtoHerc = source.getHercs().get(i);
			
			Hercs.Entry herc = object.addEntry();
			herc.setBayId(dtoHerc.getBayId());
			herc.setBuildCompleteLevel(dtoHerc.getBuildCompleteLevel());
			herc.setHealthRatio(dtoHerc.getHealthRatio());
			herc.setHercId(dtoHerc.getHercId().getId());
			herc.setHardpointCount((short)dtoHerc.getHardpoints().length);
			
			for(int h=0; h < dtoHerc.getHardpoints().length; h++) {
				HercHardpointDTO dtoSlot = dtoHerc.getHardpoints()[h];
				
				UiWeaponEntry item = new UiWeaponEntry();
				item.setItemId((short)dtoSlot.getItem().getId());
				item.setHealthPercent(dtoSlot.getHealthPercent());
				item.setMissileEnum(dtoSlot.getMissileNum());
				
				herc.getData().put((short)h, item);
			}
			
			object.getData()[(short)i] = herc;
		}
		
		return object;
	}

}
