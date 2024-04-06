package org.hercworks.transfer.svc.impl;

import java.util.HashMap;
import java.util.Map;

import org.hercworks.core.data.file.dat.shell.ArmHerc;
import org.hercworks.core.data.struct.WeaponLUT;
import org.hercworks.core.data.struct.vshell.hercs.UiHardpointGraphic;
import org.hercworks.transfer.dto.file.shell.ArmHercDTO;
import org.hercworks.transfer.dto.shell.struct.UiHardpointDTO;
import org.hercworks.transfer.dto.shell.struct.UiImageDTO;
import org.hercworks.transfer.svc.ArmHercDTOService;

public class ArmHercDTOServiceImpl implements ArmHercDTOService{

	@Override
	public ArmHercDTO convertToDTO(ArmHerc source) {
		
		ArmHercDTO dto = new ArmHercDTO();
		
		dto.setTopImgArrId(source.getTopImgArrId());
		dto.setBottomImgArrId(source.getBottomImgArrId());
		
		UiImageDTO topImage = new UiImageDTO();
		topImage.setFrameId(source.getHercTopImg().getFrameId());
		topImage.setOriginX(source.getHercTopImg().getOriginX());
		topImage.setOriginY(source.getHercTopImg().getOriginY());
		topImage.setFlag(source.getHercTopImg().getFlags());
		dto.setHercTopImg(topImage);
		
		UiImageDTO botImage = new UiImageDTO();
		botImage.setFrameId(source.getHercBotImg().getFrameId());
		botImage.setOriginX(source.getHercBotImg().getOriginX());
		botImage.setOriginY(source.getHercBotImg().getOriginY());
		botImage.setFlag(source.getHercBotImg().getFlags());
		dto.setHercBotImg(botImage);
		
		dto.setTotalWeapons(source.getTotalWeapons());
		
		Map<WeaponLUT, UiHardpointDTO[]> dtoHardpoints = new HashMap<WeaponLUT, UiHardpointDTO[]>();
		for(Short id : source.getWeaponHardpoints().keySet()) {
			UiHardpointGraphic[] graphics = source.getWeaponHardpoints().get(id);
			
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
	public ArmHerc fromDTO(ArmHercDTO source) {
		
		ArmHerc armData = new ArmHerc();
		UiHardpointGraphic topHercImg = new UiHardpointGraphic();
		armData.setTopImgArrId(source.getTopImgArrId());
		topHercImg.setOriginX(source.getHercTopImg().getOriginX());
		topHercImg.setOriginY(source.getHercTopImg().getOriginY());
		topHercImg.setOutlineX(source.getHercTopImg().getOriginX());	//note: for some reason these are probably because all UIharpdoint images use the same struct.
		topHercImg.setOutlineY(source.getHercTopImg().getOriginY());	//note: for some reason these are probably because all UIharpdoint images use the same struct.
		topHercImg.setFrameId(source.getHercTopImg().getFrameId());
		topHercImg.setFlags(source.getHercTopImg().getFlag());
		armData.setHercTopImg(topHercImg);
		
		UiHardpointGraphic bottomHercImg = new UiHardpointGraphic();
		armData.setBottomImgArrId(source.getBottomImgArrId());
		bottomHercImg.setOriginX(source.getHercBotImg().getOriginX());
		bottomHercImg.setOriginY(source.getHercBotImg().getOriginY());
		bottomHercImg.setOutlineX(source.getHercBotImg().getOriginX());	//note: for some reason these are probably because all UIharpdoint images use the same struct.
		bottomHercImg.setOutlineY(source.getHercBotImg().getOriginY());	//note: for some reason these are probably because all UIharpdoint images use the same struct.
		bottomHercImg.setFrameId(source.getHercBotImg().getFrameId());
		bottomHercImg.setFlags(source.getHercBotImg().getFlag());
		armData.setHercBotImg(bottomHercImg);
		
		armData.setTotalWeapons(source.getTotalWeapons());
		
		//Begun Weapon-Id-Hardpoint map
		Map<Short, UiHardpointGraphic[]> weaponHardpoints = new HashMap<Short, UiHardpointGraphic[]>();
		for(int i=0; i < (int)source.getTotalWeapons(); i++) {
			WeaponLUT weaponEnum = (WeaponLUT)source.getWeaponsMap().keySet().toArray()[i];
			
			UiHardpointDTO[] dtoPoints = source.getWeaponsMap().get(weaponEnum);
			
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
