package org.hercworks.transfer.svc.impl.shell;

import org.hercworks.core.data.file.dat.shell.HercInf;
import org.hercworks.core.data.struct.herc.HercLUT;
import org.hercworks.core.data.struct.vshell.hercs.HercInfEntry;
import org.hercworks.transfer.dto.file.TransferObject;
import org.hercworks.transfer.dto.file.shell.HercInfDTO;
import org.hercworks.transfer.dto.struct.shell.HercInfoDTOEntry;
import org.hercworks.transfer.svc.GeneralDTOService;
import org.hercworks.voln.DataFile;
import org.hercworks.voln.FileType;


public class HercInfoDTOServiceImpl implements GeneralDTOService{

	@Override
	public TransferObject convertToDTO(DataFile source) {
		
		HercInf srcData = (HercInf)source;
		
		HercInfDTO dto =  new HercInfDTO(srcData.getTotalHercs());
		
		for(short i=0; i < srcData.getData().length; i++) {
			HercInfEntry item = srcData.getData()[i];
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
	public DataFile fromDTO(TransferObject source) {
		
		HercInfDTO srcData = (HercInfDTO)source;
		
		HercInf hercInf = new HercInf(srcData.getTotalHercs());
		hercInf.setExt(FileType.DAT);
		
		for(short i=0; i < srcData.getTotalHercs(); i+=(short)1) {
			HercInfoDTOEntry item = srcData.getData()[i];
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
