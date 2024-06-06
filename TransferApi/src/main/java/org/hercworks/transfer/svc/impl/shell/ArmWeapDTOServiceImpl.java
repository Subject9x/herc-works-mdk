package org.hercworks.transfer.svc.impl.shell;

import org.hercworks.core.data.file.dat.shell.ArmWeap;
import org.hercworks.core.data.struct.WeaponLUT;
import org.hercworks.core.data.struct.vshell.hercs.UiHardpointGraphic;
import org.hercworks.core.data.struct.vshell.hercs.UiImageDBA.RFlag;
import org.hercworks.transfer.dto.file.TransferObject;
import org.hercworks.transfer.dto.file.shell.ArmWeapDTO;
import org.hercworks.transfer.dto.struct.shell.ArmWeapIconDTO;
import org.hercworks.transfer.svc.GeneralDTOService;
import org.hercworks.voln.DataFile;

public class ArmWeapDTOServiceImpl implements GeneralDTOService{

	@Override
	public TransferObject convertToDTO(DataFile source) {
		
		ArmWeap srcData = (ArmWeap)source;
		
		ArmWeapDTO dto = new ArmWeapDTO(srcData.getTotalWeapons());
		
		for(int i=0; i < dto.getTotalWeapons(); i++) {
			UiHardpointGraphic graphic = srcData.getEntries()[i];
			ArmWeapIconDTO icon = new ArmWeapIconDTO();
			
			icon.setId(WeaponLUT.getById(graphic.getId()));
			icon.setOriginX(graphic.getOriginX());
			icon.setOriginY(graphic.getOriginY());
			icon.setFrameId(graphic.getFrameId());
			icon.setFlag(RFlag.NORMAL);
			
			dto.getIcons()[i] = icon;
		}
		
		dto.setSecondList(srcData.getTotalSecondList());
		dto.setSecondary(new ArmWeapIconDTO[srcData.getTotalSecondList()]);
		for(int i=0; i < srcData.getTotalSecondList(); i++) {
			UiHardpointGraphic graphic = srcData.getSecondary()[i];
			ArmWeapIconDTO icon = new ArmWeapIconDTO();
			
			icon.setId(WeaponLUT.getById(graphic.getId()));
			icon.setOriginX(graphic.getOriginX());
			icon.setOriginY(graphic.getOriginY());
			icon.setFrameId(graphic.getFrameId());
			icon.setFlag(RFlag.NORMAL);
			
			dto.getSecondary()[i] = icon;
		}
		
		return dto;
	}

	@Override
	public DataFile fromDTO(TransferObject source) {
		
		ArmWeapDTO srcData = (ArmWeapDTO)source;
		
		ArmWeap armWeap = new ArmWeap(srcData.getTotalWeapons());
		
		for(int i=0; i < srcData.getTotalWeapons(); i++) {
			UiHardpointGraphic graphic = new UiHardpointGraphic();
			ArmWeapIconDTO icon = srcData.getIcons()[i];
			
			graphic.setId((short)icon.getId().getId());
			graphic.setOriginX(icon.getOriginX());
			graphic.setOriginY(icon.getOriginY());
			graphic.setFrameId(icon.getFrameId());
			graphic.setFlags(RFlag.NORMAL);
			
			armWeap.getEntries()[i] = graphic;
		}
		
		armWeap.setTotalSecondList(srcData.getSecondList());
		armWeap.setSecondary(new UiHardpointGraphic[srcData.getSecondList()]);
		for(int i=0; i < srcData.getSecondList(); i++) {
			UiHardpointGraphic graphic = new UiHardpointGraphic();
			ArmWeapIconDTO icon = srcData.getSecondary()[i];
			
			graphic.setId((short)icon.getId().getId());
			graphic.setOriginX(icon.getOriginX());
			graphic.setOriginY(icon.getOriginY());
			graphic.setFrameId(icon.getFrameId());
			graphic.setFlags(RFlag.NORMAL);
			
			armWeap.getSecondary()[i] = graphic;
		}
		
		return armWeap;
	}
}
