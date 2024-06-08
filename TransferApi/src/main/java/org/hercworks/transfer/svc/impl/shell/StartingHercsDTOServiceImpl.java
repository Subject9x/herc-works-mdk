package org.hercworks.transfer.svc.impl.shell;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hercworks.core.data.file.dat.shell.Hercs;
import org.hercworks.core.data.struct.WeaponLUT;
import org.hercworks.core.data.struct.herc.HercLUT;
import org.hercworks.core.data.struct.vshell.hercs.ShellHercData;
import org.hercworks.core.data.struct.vshell.hercs.UiWeaponEntry;
import org.hercworks.transfer.dto.file.TransferObject;
import org.hercworks.transfer.dto.file.shell.StartHercsDTO;
import org.hercworks.transfer.dto.struct.shell.HercHardpointDTO;
import org.hercworks.transfer.dto.struct.shell.ShellHercDTO;
import org.hercworks.transfer.dto.struct.shell.StartHercsEntryDTO;
import org.hercworks.transfer.svc.GeneralDTOService;
import org.hercworks.voln.DataFile;
import org.hercworks.voln.FileType;

public class StartingHercsDTOServiceImpl implements GeneralDTOService{

	@Override
	public TransferObject convertToDTO(DataFile source) {
		
		Hercs srcData = (Hercs)source;
		
		StartHercsDTO dto = new StartHercsDTO();
		
		List<StartHercsEntryDTO> hercs = new ArrayList<StartHercsEntryDTO>();
		for(int i=0; i < srcData.getData().length; i++) {
			Hercs.Entry entry = srcData.getData()[i];
			
			StartHercsEntryDTO hercStartEntry = new StartHercsEntryDTO();
			ShellHercDTO hercDTO = new ShellHercDTO();
			
			hercStartEntry.setBayId(entry.getBayId());
			hercDTO.setHercId(HercLUT.getById(entry.getHerc().getHercId()));
			hercDTO.setBuildCompleteLevel(entry.getHerc().getBuildCompleteLevel());
			hercDTO.setHealthRatio(entry.getHerc().getHealthRatio());
			
			HercHardpointDTO[] hardpoints = new HercHardpointDTO[entry.getHerc().getHardpoints().size()];
			
			for(int h=0; h < entry.getHerc().getHardpoints().size(); h++) {
				UiWeaponEntry item = entry.getHerc().getHardpoints().get((short)h);
				HercHardpointDTO slot = new HercHardpointDTO();
				
				slot.setHardpoint((short)h);
				slot.setItem(WeaponLUT.getById(item.getItemId()));
				slot.setHealthPercent(item.getHealthPercent());
				slot.setMissileType(item.getMissileType());
				hardpoints[h] = slot;
			}
			hercDTO.setHardpoints(hardpoints);
			hercStartEntry.setHerc(hercDTO);
			hercs.add(hercStartEntry);
		}
		dto.setHercs(hercs);
		return dto;
	}

	@Override
	public DataFile fromDTO(TransferObject source) {
		
		StartHercsDTO srcData = (StartHercsDTO)source;
		
		Hercs object = new Hercs((short)srcData.getHercs().size());
		object.setExt(FileType.DAT);
		
		for(int i=0; i < srcData.getHercs().size(); i++) {
			StartHercsEntryDTO dtoHerc = srcData.getHercs().get(i);
			
			Hercs.Entry hercsEntry = object.addEntry();
			hercsEntry.setHerc(new ShellHercData());
			
			hercsEntry.setBayId(dtoHerc.getBayId());
			hercsEntry.getHerc().setHercId(dtoHerc.getHerc().getHercId().getId());
			hercsEntry.getHerc().setHealthRatio(dtoHerc.getHerc().getHealthRatio());
			hercsEntry.getHerc().setBuildCompleteLevel(dtoHerc.getHerc().getBuildCompleteLevel());
			hercsEntry.getHerc().setHardpoints(new HashMap<Short, UiWeaponEntry>());
			
			for(int h=0; h < dtoHerc.getHerc().getHardpoints().length; h++) {
				HercHardpointDTO dtoSlot = dtoHerc.getHerc().getHardpoints()[h];
				
				UiWeaponEntry item = new UiWeaponEntry();
				item.setItemId((short)dtoSlot.getItem().getId());
				item.setHealthPercent(dtoSlot.getHealthPercent());
				item.setMissileType(dtoSlot.getMissileType());
				
				hercsEntry.getHerc().getHardpoints().put((short)h, item);
			}
			object.getData()[(short)i] = hercsEntry;
		}
		
		return object;
	}

}
