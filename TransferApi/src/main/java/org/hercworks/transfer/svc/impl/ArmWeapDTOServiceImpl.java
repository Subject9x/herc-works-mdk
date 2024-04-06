package org.hercworks.transfer.svc.impl;

import org.hercworks.core.data.file.dat.shell.ArmWeap;
import org.hercworks.core.data.struct.WeaponLUT;
import org.hercworks.core.data.struct.vshell.hercs.UiHardpointGraphic;
import org.hercworks.core.data.struct.vshell.hercs.UiImageDBA.RFlag;
import org.hercworks.transfer.dto.file.shell.ArmWeapDTO;
import org.hercworks.transfer.dto.shell.struct.ArmWeapIconDTO;
import org.hercworks.transfer.svc.ArmWeapDTOService;

public class ArmWeapDTOServiceImpl implements ArmWeapDTOService{

	@Override
	public ArmWeapDTO convertToDTO(ArmWeap source) {
		
		ArmWeapDTO dto = new ArmWeapDTO(source.getTotalWeapons());
		
		for(int i=0; i < dto.getTotalWeapons(); i++) {
			UiHardpointGraphic graphic = source.getEntries()[i];
			ArmWeapIconDTO icon = new ArmWeapIconDTO();
			
			icon.setId(WeaponLUT.getById(graphic.getId()));
			icon.setOriginX(graphic.getOriginX());
			icon.setOriginX(graphic.getOriginY());
			icon.setFrameId(graphic.getFrameId());
			
			dto.getIcons()[i] = icon;
		}
		
		dto.setSecondary(new ArmWeapIconDTO[source.getTotalSecondList()]);
		for(int i=0; i < source.getTotalSecondList(); i++) {
			UiHardpointGraphic graphic = source.getSecondary()[i];
			ArmWeapIconDTO icon = new ArmWeapIconDTO();
			
			icon.setId(WeaponLUT.getById(graphic.getId()));
			icon.setOriginX(graphic.getOriginX());
			icon.setOriginX(graphic.getOriginY());
			icon.setFrameId(graphic.getFrameId());
			
			dto.getSecondary()[i] = icon;
		}
		
		return dto;
	}

	@Override
	public ArmWeap fromDTO(ArmWeapDTO source) {
		
		ArmWeap armWeap = new ArmWeap(source.getTotalWeapons());
		
		for(int i=0; i < source.getTotalWeapons(); i++) {
			UiHardpointGraphic graphic = new UiHardpointGraphic();
			ArmWeapIconDTO icon = source.getIcons()[i];
			
			graphic.setId((short)icon.getId().getId());
			graphic.setOriginX(icon.getOriginX());
			graphic.setOriginX(icon.getOriginY());
			graphic.setFrameId(icon.getFrameId());
			graphic.setFlags(RFlag.NORMAL);
			
			armWeap.getEntries()[i] = graphic;
		}
		
		armWeap.setSecondary(new UiHardpointGraphic[source.getSecondList()]);
		for(int i=0; i < source.getSecondList(); i++) {
			UiHardpointGraphic graphic = new UiHardpointGraphic();
			ArmWeapIconDTO icon = source.getSecondary()[i];
			
			graphic.setId((short)icon.getId().getId());
			graphic.setOriginX(icon.getOriginX());
			graphic.setOriginX(icon.getOriginY());
			graphic.setFrameId(icon.getFrameId());
			graphic.setFlags(RFlag.NORMAL);
			
			armWeap.getSecondary()[i] = graphic;
		}
		
		return armWeap;
	}
}
