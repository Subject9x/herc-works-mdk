package org.hercworks.transfer.svc.impl;

import java.util.HashMap;
import java.util.Map;

import org.hercworks.core.data.file.dat.shell.RprHerc;
import org.hercworks.core.data.struct.WeaponLUT;
import org.hercworks.core.data.struct.vshell.hercs.UiHardpointGraphic;
import org.hercworks.core.data.struct.vshell.hercs.UiImageDBA;
import org.hercworks.core.data.struct.vshell.hercs.UiImageDBA.RFlag;
import org.hercworks.transfer.dto.file.TransferObject;
import org.hercworks.transfer.dto.file.shell.RepairHercDTO;
import org.hercworks.transfer.dto.struct.shell.UiImageDTO;
import org.hercworks.transfer.svc.GeneralDTOService;
import org.hercworks.voln.DataFile;
import org.hercworks.voln.FileType;

public class RepairHercDTOServiceImpl implements GeneralDTOService {

	@Override
	public TransferObject convertToDTO(DataFile source) {
		
		RepairHercDTO dto = new RepairHercDTO();
		RprHerc data = (RprHerc)source;
		
		Map<Short, UiImageDTO> body = new HashMap<Short, UiImageDTO>();
		for(Short id : data.getBodyImages().keySet()) {
			UiImageDBA frame = data.getBodyImages().get(id);
			UiImageDTO img = new UiImageDTO();
			
			img.setId(frame.getFrameId());
			img.setOriginX(frame.getOriginX());
			img.setOriginY(frame.getOriginY());
			img.setFrameId(frame.getFrameId());
			img.setFlag(frame.getFlags());
			
			body.put(id, img);
		}
		dto.setBodyImages(body);
		
		UiImageDTO internalsImg = new UiImageDTO();
		internalsImg.setOriginX(data.getInternalImage().getOriginX());
		internalsImg.setOriginX(data.getInternalImage().getOriginY());
		internalsImg.setFrameId(data.getInternalImage().getFrameId());
		internalsImg.setFlag(RFlag.get(data.getInternalImage().getFlags().val()));
		
		dto.setInternalImage(internalsImg);
		
		Map<WeaponLUT, UiImageDTO[]> weapons = new HashMap<WeaponLUT, UiImageDTO[]>();
		for(Short id : data.getWeaponHardpoints().keySet()) {
			UiHardpointGraphic[] hardpoints = data.getWeaponHardpoints().get(id);
			UiImageDTO[] sockets = new UiImageDTO[hardpoints.length];
			
			for(int h=0; h < sockets.length; h++) {
				UiHardpointGraphic entry =  hardpoints[h];
				UiImageDTO newSocket = new UiImageDTO();
				newSocket.setId(entry.getId());
				newSocket.setOriginX(entry.getOriginX());
				newSocket.setOriginY(entry.getOriginY());
				newSocket.setFrameId(entry.getFrameId());
				newSocket.setFlag(RFlag.get(entry.getFlags().val()));
				
				sockets[h] = newSocket;
			}
			weapons.put(WeaponLUT.getById((short)id), sockets);
		}
		dto.setWeaponsMap(weapons);
		
		return dto;
	}

	@Override
	public DataFile fromDTO(TransferObject source) {
	
		RepairHercDTO dto = (RepairHercDTO)source;
		RprHerc repairHerc = new RprHerc();
		repairHerc.setExt(FileType.DAT);
		
		short bodImg = (short)dto.getBodyImages().size();
		repairHerc.setBodyImgTotal(bodImg);
		
		Map<Short, UiImageDBA> bodFrames = new HashMap<Short, UiImageDBA>();
		for(Short id : dto.getBodyImages().keySet()) {
			UiImageDTO img = dto.getBodyImages().get(id);
			UiImageDBA frame = new UiImageDBA();
			frame.setOriginX(img.getOriginX());
			frame.setOriginY(img.getOriginY());
			frame.setFrameId(img.getFrameId());
			frame.setFlags(RFlag.get(img.getFlag().val()));
			bodFrames.put(id, frame);
		}
		repairHerc.setBodyImages(bodFrames);
		
		UiHardpointGraphic internals = new UiHardpointGraphic();
		internals.setId(dto.getInternalImage().getId());
		internals.setOriginX(dto.getInternalImage().getOriginX());
		internals.setOriginY(dto.getInternalImage().getOriginY());
		internals.setFrameId(dto.getInternalImage().getFrameId());
		internals.setFlags(RFlag.get(dto.getInternalImage().getFlag().val()));
		repairHerc.setInternalImage(internals);
		
		short totalWeapons = (short)dto.getWeaponsMap().size();
		repairHerc.setTotalHardpoints(totalWeapons);
		Map<Short, UiHardpointGraphic[]> weapons = new HashMap<Short, UiHardpointGraphic[]>();
		for(WeaponLUT weaponId : dto.getWeaponsMap().keySet()) {
			UiImageDTO[] sockets = dto.getWeaponsMap().get(weaponId);
			UiHardpointGraphic[] hardpoints = new UiHardpointGraphic[sockets.length];
			
			for(int h=0; h < (int)sockets.length; h++) {
				UiImageDTO entry = sockets[h];
				UiHardpointGraphic socket = new UiHardpointGraphic();
				socket.setId(entry.getId());
				socket.setOriginX(entry.getOriginX());
				socket.setOriginY(entry.getOriginY());
				socket.setFrameId(entry.getFrameId());
				socket.setFlags(RFlag.get(entry.getFlag().val()));
				hardpoints[h] = socket;
			}
			weapons.put((short)weaponId.getId(), hardpoints);
		}
		repairHerc.setWeaponHardpoints(weapons);
		
		return repairHerc;
	}
}
