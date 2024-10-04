package org.hercworks.transfer.svc.impl.shell;

import java.util.HashMap;

import org.hercworks.core.data.file.dat.shell.InitHerc;
import org.hercworks.core.data.struct.WeaponLUT;
import org.hercworks.core.data.struct.herc.HercLUT;
import org.hercworks.core.data.struct.vshell.hercs.ShellHercData;
import org.hercworks.core.data.struct.vshell.hercs.UiWeaponEntry;
import org.hercworks.transfer.dto.file.TransferObject;
import org.hercworks.transfer.dto.file.shell.InitHercDTO;
import org.hercworks.transfer.dto.struct.shell.HercHardpointDTO;
import org.hercworks.transfer.svc.GeneralDTOService;
import org.hercworks.voln.DataFile;
import org.hercworks.voln.FileType;

public class InitHercDTOServiceImpl implements GeneralDTOService{

	@Override
	public TransferObject convertToDTO(DataFile source) {
		
		InitHerc srcData = (InitHerc)source;
		
		InitHercDTO dto = new InitHercDTO();
		dto.setHercId(HercLUT.getById(srcData.getData().getHercId()));
		dto.setHealthRatio(srcData.getData().getHealthRatio());
		dto.setBuildCompleteLevel(srcData.getData().getBuildCompleteLevel());
		
		HercHardpointDTO[] hardpoints = new HercHardpointDTO[srcData.getData().getHardpoints().size()];
		int idx = 0;
		for(Short id : srcData.getData().getHardpoints().keySet()) {
			HercHardpointDTO hardpoint = new HercHardpointDTO();
			UiWeaponEntry entry = srcData.getData().getHardpoints().get(id);
			
			hardpoint.setHardpoint(id);
			hardpoint.setItem(WeaponLUT.getById(entry.getItemId()));
			hardpoint.setArmor(entry.getHealthPercent());
			hardpoint.setStructure(entry.getHealthPercent());
			hardpoint.setMissileType(entry.getMissileType());
			
			hardpoints[idx] = hardpoint;
			idx++;
		}
		dto.setHardpoints(hardpoints);
		
		return dto;
	}

	@Override
	public DataFile fromDTO(TransferObject source) {
		
		InitHercDTO srcData = (InitHercDTO)source;
		
		InitHerc object = new InitHerc();
		object.setExt(FileType.DAT);
		object.setDir(FileType.GAM);
		
		object.setData(new ShellHercData());
		ShellHercData herc = object.getData();
		
		herc.setHercId(srcData.getHercId().getId());
		herc.setHealthRatio((short)srcData.getHealthRatio());
		herc.setBuildCompleteLevel((short)srcData.getBuildCompleteLevel());
		herc.setHardpoints(new HashMap<Short, UiWeaponEntry>());
		
		for(HercHardpointDTO slotDTO : srcData.getHardpoints()) {
			UiWeaponEntry entry = new UiWeaponEntry();
			entry.setItemId((short)slotDTO.getItem().getId());
			entry.setHealthPercent((short)slotDTO.getArmor());
			entry.setMissileType(slotDTO.getMissileType());
			herc.getHardpoints().put(slotDTO.getHardpoint(), entry);
		}
		return object;
	}
}
