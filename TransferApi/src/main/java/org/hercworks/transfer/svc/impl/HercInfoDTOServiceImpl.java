package org.hercworks.transfer.svc.impl;

import org.hercworks.core.data.file.dat.shell.HercInf;
import org.hercworks.core.data.struct.HercLUT;
import org.hercworks.core.data.struct.vshell.hercs.HercInfEntry;
import org.hercworks.transfer.dto.file.shell.HercInfDTO;
import org.hercworks.transfer.dto.shell.struct.HercInfoDTOEntry;
import org.hercworks.transfer.svc.HercInfDTOService;
import org.hercworks.voln.FileType;


public class HercInfoDTOServiceImpl implements HercInfDTOService{

	@Override
	public HercInfDTO convertToDTO(HercInf source) {
		
		HercInfDTO dto =  new HercInfDTO(source.getTotalHercs());
		
		for(short i=0; i < source.getData().length; i++) {
			HercInfEntry item = source.getData()[i];
			HercInfoDTOEntry entry = dto.newEntry();
			entry.setHercId(item.getHercId());
			entry.setHercName(HercLUT.getById(item.getHercId()));
			entry.setWeight(item.getWeight());
			entry.setSpeed(item.getSpeed());
			entry.setHardpointTotal(item.getHardpointTotal());
			entry.setSalvageReq(item.getSalvageReq());
			entry.setUnknownFlag(item.getUnknownFlag());
			entry.setBuildMissionCount(item.getBuildMissionCount());
			entry.setFlagCampaignStart(item.getFlagCampaignStart());
			dto.getData()[i] = entry;
		}
		return dto;
	}

	@Override
	public HercInf fromDTO(HercInfDTO source) {
		
		HercInf hercInf = new HercInf(source.getTotalHercs());
		hercInf.setExt(FileType.DAT);
		
		for(short i=0; i < source.getTotalHercs(); i+=(short)1) {
			HercInfoDTOEntry item = source.getData()[i];
			HercInfEntry entry = new HercInfEntry();
			entry.setHercId(item.getHercId());
			entry.setWeight(item.getWeight());
			entry.setSpeed(item.getSpeed());
			entry.setHardpointTotal(item.getHardpointTotal());
			entry.setSalvageReq(item.getSalvageReq());
			entry.setUnknownFlag(item.getUnknownFlag());
			entry.setBuildMissionCount(item.getBuildMissionCount());
			entry.setFlagCampaignStart(item.getFlagCampaignStart());
			hercInf.getData()[i] = entry;
		}
		
		return hercInf;
	}

}
