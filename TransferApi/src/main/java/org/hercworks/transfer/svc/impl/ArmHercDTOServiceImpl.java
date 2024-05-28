package org.hercworks.transfer.svc.impl;

import java.util.HashMap;
import java.util.Map;

import org.hercworks.core.data.file.dat.shell.ArmHerc;
import org.hercworks.core.data.struct.WeaponLUT;
import org.hercworks.core.data.struct.vshell.hercs.UiHardpointGraphic;
import org.hercworks.transfer.dto.file.TransferObject;
import org.hercworks.transfer.dto.file.shell.ArmHercDTO;
import org.hercworks.transfer.dto.struct.shell.UiHardpointDTO;
import org.hercworks.transfer.dto.struct.shell.UiImageDTO;
import org.hercworks.transfer.svc.GeneralDTOService;
import org.hercworks.voln.DataFile;

public class ArmHercDTOServiceImpl implements GeneralDTOService{

	@Override
	public TransferObject convertToDTO(DataFile source) {
		
		ArmHerc srcData = (ArmHerc)source;
		
		ArmHercDTO dto = new ArmHercDTO();
		
		dto.setTopImgArrId(srcData.getTopImgArrId());
		dto.setBottomImgArrId(srcData.getBottomImgArrId());
		
		UiImageDTO topImage = new UiImageDTO();
		topImage.setFrameId(srcData.getHercTopImg().getFrameId());
		topImage.setOriginX(srcData.getHercTopImg().getOriginX());
		topImage.setOriginY(srcData.getHercTopImg().getOriginY());
		topImage.setFlag(srcData.getHercTopImg().getFlags());
		dto.setHercTopImg(topImage);
		
		UiImageDTO botImage = new UiImageDTO();
		botImage.setFrameId(srcData.getHercBotImg().getFrameId());
		botImage.setOriginX(srcData.getHercBotImg().getOriginX());
		botImage.setOriginY(srcData.getHercBotImg().getOriginY());
		botImage.setFlag(srcData.getHercBotImg().getFlags());
		dto.setHercBotImg(botImage);
		
		dto.setTotalWeapons(srcData.getTotalWeapons());
		
		Map<WeaponLUT, UiHardpointDTO[]> dtoHardpoints = new HashMap<WeaponLUT, UiHardpointDTO[]>();
		for(Short id : srcData.getWeaponHardpoints().keySet()) {
			UiHardpointGraphic[] graphics = srcData.getWeaponHardpoints().get(id);
			
			UiHardpointDTO[] sprites = new UiHardpointDTO[graphics.length];
			
			for(int h=0; h < (int)graphics.length; h++) {
				UiHardpointGraphic img = graphics[h];
				UiHardpointDTO hardpointDto = new UiHardpointDTO();
				
				hardpointDto.setId(img.getId());
				hardpointDto.setOriginX(img.getOriginX());
				hardpointDto.setOriginY(img.getOriginY());
				hardpointDto.setOutlineX(img.getOutlineX());
				hardpointDto.setOutlineY(img.getOutlineY());
				hardpointDto.setFrameId(img.getFrameId());
				hardpointDto.setFlag(img.getFlags());
				sprites[h] = hardpointDto;
			}
			dtoHardpoints.put(WeaponLUT.getById(id), sprites);
		}
		dto.setWeaponsMap(dtoHardpoints);
		
		return dto;
	}

	@Override
	public DataFile fromDTO(TransferObject source) {
		
		ArmHercDTO srcData = (ArmHercDTO)source;
		
		ArmHerc armData = new ArmHerc();
		UiHardpointGraphic topHercImg = new UiHardpointGraphic();
		armData.setTopImgArrId(srcData.getTopImgArrId());
		topHercImg.setOriginX(srcData.getHercTopImg().getOriginX());
		topHercImg.setOriginY(srcData.getHercTopImg().getOriginY());
		topHercImg.setOutlineX(srcData.getHercTopImg().getOriginX());	//note: for some reason these are probably because all UIharpdoint images use the same struct.
		topHercImg.setOutlineY(srcData.getHercTopImg().getOriginY());	//note: for some reason these are probably because all UIharpdoint images use the same struct.
		topHercImg.setFrameId(srcData.getHercTopImg().getFrameId());
		topHercImg.setFlags(srcData.getHercTopImg().getFlag());
		armData.setHercTopImg(topHercImg);
		
		UiHardpointGraphic bottomHercImg = new UiHardpointGraphic();
		armData.setBottomImgArrId(srcData.getBottomImgArrId());
		bottomHercImg.setOriginX(srcData.getHercBotImg().getOriginX());
		bottomHercImg.setOriginY(srcData.getHercBotImg().getOriginY());
		bottomHercImg.setOutlineX(srcData.getHercBotImg().getOriginX());	//note: for some reason these are probably because all UIharpdoint images use the same struct.
		bottomHercImg.setOutlineY(srcData.getHercBotImg().getOriginY());	//note: for some reason these are probably because all UIharpdoint images use the same struct.
		bottomHercImg.setFrameId(srcData.getHercBotImg().getFrameId());
		bottomHercImg.setFlags(srcData.getHercBotImg().getFlag());
		armData.setHercBotImg(bottomHercImg);
		
		armData.setTotalWeapons(srcData.getTotalWeapons());
		
		//Begun Weapon-Id-Hardpoint map
		Map<Short, UiHardpointGraphic[]> weaponHardpoints = new HashMap<Short, UiHardpointGraphic[]>();
		for(int i=0; i < (int)srcData.getTotalWeapons(); i++) {
			WeaponLUT weaponEnum = (WeaponLUT)srcData.getWeaponsMap().keySet().toArray()[i];
			
			UiHardpointDTO[] dtoPoints = srcData.getWeaponsMap().get(weaponEnum);
			
			UiHardpointGraphic[] graphics = new UiHardpointGraphic[dtoPoints.length];
			
			for(int h=0; h < dtoPoints.length; h++) {
				UiHardpointDTO dtoHardpoint = dtoPoints[(short)h];
				UiHardpointGraphic hardpoint = new UiHardpointGraphic();
				hardpoint.setId(dtoHardpoint.getId());
				hardpoint.setOriginX(dtoHardpoint.getOriginX());
				hardpoint.setOriginY(dtoHardpoint.getOriginY());
				hardpoint.setOutlineX(dtoHardpoint.getOutlineX());
				hardpoint.setOutlineY(dtoHardpoint.getOutlineY());
				hardpoint.setFrameId(dtoHardpoint.getFrameId());
				hardpoint.setFlags(dtoHardpoint.getFlag());
				graphics[h] = hardpoint;
			}
			weaponHardpoints.put((short)weaponEnum.getId(), graphics);
		}
		armData.setWeaponHardpoints(weaponHardpoints);
		
		return armData;
	}
}
