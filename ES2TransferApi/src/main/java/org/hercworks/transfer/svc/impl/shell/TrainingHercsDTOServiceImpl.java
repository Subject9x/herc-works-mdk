package org.hercworks.transfer.svc.impl.shell;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hercworks.core.data.file.dat.shell.TrainingHercs;
import org.hercworks.core.data.struct.WeaponLUT;
import org.hercworks.core.data.struct.herc.HercLUT;
import org.hercworks.core.data.struct.vshell.hercs.ShellHercData;
import org.hercworks.core.data.struct.vshell.hercs.UiWeaponEntry;
import org.hercworks.transfer.dto.file.TransferObject;
import org.hercworks.transfer.dto.file.shell.TrainingHercsDTO;
import org.hercworks.transfer.dto.struct.shell.HercHardpointDTO;
import org.hercworks.transfer.dto.struct.shell.ShellHercDTO;
import org.hercworks.transfer.svc.GeneralDTOService;
import org.hercworks.voln.DataFile;
import org.hercworks.voln.FileType;

public class TrainingHercsDTOServiceImpl implements GeneralDTOService{

	@Override
	public TransferObject convertToDTO(DataFile source) {
		TrainingHercs srcData = (TrainingHercs)source;
		
		TrainingHercsDTO dto = new TrainingHercsDTO();
		
		dto.setData(new ShellHercDTO[srcData.getData().size()]);
		
		for(int i=0; i < srcData.getData().size(); i++) {
			ShellHercDTO dtoHerc = new ShellHercDTO();
			ShellHercData entry = srcData.getData().get(i);
			
			dtoHerc.setHercId(HercLUT.getById(entry.getHercId()));
			dtoHerc.setHealthRatio(entry.getHealthRatio());
			dtoHerc.setBuildCompleteLevel(entry.getBuildCompleteLevel());
			
			HercHardpointDTO[] hardpoints = new HercHardpointDTO[entry.getHardpoints().size()];
			int h = 0;
			for(Short id : entry.getHardpoints().keySet()) {
				UiWeaponEntry item = entry.getHardpoints().get(id);
				HercHardpointDTO dtoSlot = new HercHardpointDTO();
				
				dtoSlot.setHardpoint(id);
				dtoSlot.setItem(WeaponLUT.getById(item.getItemId()));
				dtoSlot.setArmor(item.getHealthPercent());
				dtoSlot.setMissileType(item.getMissileType());
				
				hardpoints[h] = dtoSlot;
				h++;
			}
			dtoHerc.setHardpoints(hardpoints);
			
			dto.getData()[i] = dtoHerc;
		}
		
		return dto;
	}

	@Override
	public DataFile fromDTO(TransferObject source) {
		
		TrainingHercsDTO srcData = (TrainingHercsDTO)source;
		
		TrainingHercs trainHercs = new TrainingHercs();
		trainHercs.setExt(FileType.DAT);
		trainHercs.setDir(FileType.GAM);
		List<ShellHercData> data = new ArrayList<ShellHercData>();

		for(ShellHercDTO dtoHerc : srcData.getData()) {
			ShellHercData herc = new ShellHercData();
			
			herc.setHercId(dtoHerc.getHercId().getId());
			herc.setHealthRatio(dtoHerc.getHealthRatio());
			herc.setBuildCompleteLevel(dtoHerc.getBuildCompleteLevel());
			
			HashMap<Short, UiWeaponEntry> hardpoints = new HashMap<Short, UiWeaponEntry>();
			for(int h=0; h < dtoHerc.getHardpoints().length; h++) {
				HercHardpointDTO slot = dtoHerc.getHardpoints()[h];
				UiWeaponEntry item = new  UiWeaponEntry();
				
				item.setItemId((short)slot.getItem().getId());
				item.setHealthPercent((short)slot.getArmor());
				item.setMissileType(slot.getMissileType());
				
				hardpoints.put((short)h, item);
			}
			herc.setHardpoints(hardpoints);
			data.add(herc);
		}
		
		trainHercs.setData(data);
		
		return trainHercs;
	}
}